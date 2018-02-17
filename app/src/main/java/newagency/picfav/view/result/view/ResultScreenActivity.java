package newagency.picfav.view.result.view;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import newagency.picfav.R;
import newagency.picfav.dagger.DaggerViewComponent;
import newagency.picfav.dagger.ViewModule;
import newagency.picfav.util.CapturePhotoUtils;
import newagency.picfav.util.PermissionManager;
import newagency.picfav.view.BaseActivity;
import newagency.picfav.view.custom.GameLoadingView;
import newagency.picfav.view.result.ResultScreenContract;

public class ResultScreenActivity extends BaseActivity implements ResultScreenContract.View {

    private final static String RESULT_KEY = "result_key";
    private static final String PACKAGE = "package:";

    private static final String PERMISSION_WRITE_STORAGE = Manifest.permission.WRITE_EXTERNAL_STORAGE;

    private static final int RC_SHARE_TO_INSTA = 1;
    private static final int RC_SHARE_TO_FACEBOOK = 2;

    @BindView(R.id.result_tv)
    TextView mResultTv;

    @BindView(R.id.toolbar_title_tv)
    TextView mToolbarTitle;

    @BindView(R.id.loading_game)
    GameLoadingView mGameLoadingView;

    @BindView(R.id.result_content)
    View contentResult;

    private int mResult;

    public static void start(Context context, int score) {
        Intent starter = new Intent(context, ResultScreenActivity.class);
        starter.putExtra(RESULT_KEY, score);
        context.startActivity(starter);
    }

    @Override
    protected void onViewReady() {
        getArgsData();
        mToolbarTitle.setText(R.string.result_activity_title);
        mGameLoadingView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                mGameLoadingView.setResult(mResult);
                mGameLoadingView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            }
        });
    }

    @Override
    protected void onViewDestroy() {

    }

    @Override
    protected int onRequestLayout() {
        return R.layout.activity_result_screen;
    }

    @Override
    protected void onInitializeInjection() {
        DaggerViewComponent.builder()
                .applicationComponent(getApplicationComponent())
                .viewModule(new ViewModule(this))
                .build()
                .inject(this);
    }

    @OnClick(R.id.iv_back)
    void onBack() {
        onBackPressed();
    }

    @OnClick(R.id.iv_insta)
    void instaShare() {
        if (!hasStoragePermissionForShareToInsta()) return;

        shareToInstagram(getBitmapFromView(contentResult));
    }

    @OnClick(R.id.iv_facebook)
    void facebookShare() {
        if (!hasStoragePermissionForShareToFacebook()) return;

        shareFacebook(getBitmapFromView(contentResult));
    }

    private void getArgsData() {
        if (getIntent() != null)
            mResult = getIntent().getIntExtra(RESULT_KEY, 0);
    }

    public void shareToInstagram(@NonNull Bitmap bitmap) {
        Intent intent = this.getPackageManager().getLaunchIntentForPackage("com.instagram.android");
        if (intent != null) {
            Intent shareIntent = new Intent();
            shareIntent.setAction(Intent.ACTION_SEND);
            shareIntent.setPackage("com.instagram.android");

            shareIntent.putExtra(Intent.EXTRA_STREAM, Uri.parse(CapturePhotoUtils.insertImage(getContentResolver(), bitmap, "share_result_to_insta", "success")));
            shareIntent.setType("image/*");

            startActivity(shareIntent);
        } else {
            intent = new Intent(Intent.ACTION_VIEW);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.setData(Uri.parse("market://details?id=" + "com.instagram.android"));
            startActivity(intent);
        }
    }

    private void shareFacebook(@NonNull Bitmap shareScreen) {
        List<Bitmap> bitmaps = new ArrayList<>();
        bitmaps.add(shareScreen);
        String res = "My result in PicFav.";

        Intent tweetIntent = new Intent(bitmaps.size() > 1 ? Intent.ACTION_SEND_MULTIPLE : Intent.ACTION_SEND);
        tweetIntent.putExtra(Intent.EXTRA_SUBJECT, "Pic Fav app");

        if (bitmaps.size() > 1) {
            ArrayList<Uri> imageUris = new ArrayList<Uri>();
            for (Bitmap bitmap : bitmaps) {
                String imagePath = CapturePhotoUtils.insertImage(
                        getContentResolver(), bitmap,
                        "share_facebook", "success");
                if (imagePath != null) {
                    try {
                        imageUris.add(Uri.parse(imagePath));
                    } catch (RuntimeException e) {
                        Log.e(ResultScreenActivity.class.getSimpleName(), "Can't get image for sharing");
                    }
                }
            }
            tweetIntent.putParcelableArrayListExtra(Intent.EXTRA_STREAM, imageUris);
        } else {
            tweetIntent.putExtra(Intent.EXTRA_STREAM, Uri.parse(CapturePhotoUtils.insertImage(getContentResolver(), bitmaps.get(0), "share_facebook", "success")));
        }
        tweetIntent.putExtra(Intent.EXTRA_TEXT, res);
        Log.d("EXTRA_TEXT", res);

        tweetIntent.setType("image/*");

        PackageManager packManager = getPackageManager();
        List<ResolveInfo> resolvedInfoList = packManager.queryIntentActivities(tweetIntent, PackageManager.MATCH_DEFAULT_ONLY);

        boolean resolved = false;
        for (ResolveInfo resolveInfo : resolvedInfoList) {
            if (resolveInfo.activityInfo.packageName.startsWith("com.facebook.katana")) {
                tweetIntent.setClassName(
                        resolveInfo.activityInfo.packageName,
                        resolveInfo.activityInfo.name);
                resolved = true;
                break;
            }
        }
        if (resolved) {
            tweetIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(tweetIntent);
        } else {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.setData(Uri.parse("market://details?id=" + "com.facebook.katana"));
            startActivity(intent);
        }
    }


    public Bitmap getBitmapFromView(View view) {
        Bitmap returnedBitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(returnedBitmap);
        Drawable bgDrawable = view.getBackground();
        if (bgDrawable != null)
            bgDrawable.draw(canvas);
        else
            canvas.drawColor(Color.WHITE);
        view.draw(canvas);
        return returnedBitmap;
    }

    private boolean hasStoragePermissionForShareToInsta() {
        return PermissionManager.hasPermission(this, PERMISSION_WRITE_STORAGE, RC_SHARE_TO_INSTA);
    }

    private boolean hasStoragePermissionForShareToFacebook() {
        return PermissionManager.hasPermission(this, PERMISSION_WRITE_STORAGE, RC_SHARE_TO_FACEBOOK);
    }

    private boolean isPermissionNeverAsk(String permission) {
        return PermissionManager.isNeverAsk(this, permission);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults.length > 0 && permissions.length > 0) {
            int result = grantResults[0];
            String permission = permissions[0];

            if (requestCode == RC_SHARE_TO_INSTA) {
                if (result == PackageManager.PERMISSION_GRANTED) {
                    shareToInstagram(getBitmapFromView(contentResult));
                } else {
                    if (isPermissionNeverAsk(permission)) {
                        showPermissionNeverAskDialog(getPermissionName(requestCode));
                    }
                }
                return;
            }

            if (requestCode == RC_SHARE_TO_FACEBOOK) {
                if (result == PackageManager.PERMISSION_GRANTED) {
                    shareFacebook(getBitmapFromView(contentResult));
                } else {
                    if (isPermissionNeverAsk(permission)) {
                        showPermissionNeverAskDialog(getPermissionName(requestCode));
                    }
                }
                return;
            }
        }
    }

    private void showPermissionNeverAskDialog(String permission) {
        new AlertDialog.Builder(this)
                .setTitle(R.string.no_permissions)
                .setMessage(getString(R.string.enable_permission, permission))
                .setPositiveButton(R.string.open_settings, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        startAppSettingsActivity();
                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

    private void startAppSettingsActivity() {
        final Intent appSettingsIntent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        appSettingsIntent.addCategory(Intent.CATEGORY_DEFAULT);
        appSettingsIntent.setData(Uri.parse(PACKAGE + getPackageName()));
        appSettingsIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        appSettingsIntent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        appSettingsIntent.addFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
        startActivity(appSettingsIntent);
    }

    @NonNull
    private String getPermissionName(int requestCode) {
        switch (requestCode) {
            case RC_SHARE_TO_INSTA:
                return getString(R.string.storage);
        }
        return "";
    }

    @Override
    public void showProgress() {
        mProgressDialog.show();
    }

    @Override
    public void hideProgress() {
        mProgressDialog.hide();
    }
}
