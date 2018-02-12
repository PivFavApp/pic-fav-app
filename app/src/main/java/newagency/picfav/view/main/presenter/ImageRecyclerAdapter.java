package newagency.picfav.view.main.presenter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import newagency.picfav.R;
import newagency.picfav.netwotk.response.ImageModel;
import newagency.picfav.util.GameUtil;

/**
 * Created by oroshka on 1/30/18.
 */

public class ImageRecyclerAdapter extends RecyclerView.Adapter<ImageRecyclerAdapter.ImageHolder> {

    public interface ImageRecyclerAdapterCallback {

        void onChangeCountSelected(int count);

        void selectedMaxCount(int maxCount);

        void changeRemainState(int needCount);

        void hideRemainCount();

        void showRemainCount();

    }

    private static int MAX_SELECTED_COUNT = 5;

    private static int MIN_SELECTED_COUNT = 3;

    private static int mCountNeedPreliminary = -1;

    private List<ImageModel> mImageItemList;

    private Context mContext;

    private ImageRecyclerAdapterCallback mCallback;

    private int mSelectedCount = 0;


    public ImageRecyclerAdapter(Context context, ImageRecyclerAdapterCallback callback) {
        mContext = context;
        this.mCallback = callback;
    }

    @Override
    public ImageHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_picture, parent, false);
        return new ImageHolder(view);
    }

    @Override
    public void onBindViewHolder(ImageHolder holder, int position) {
        holder.bind(mImageItemList.get(position));
    }

    @Override
    public int getItemCount() {
        return mImageItemList != null ? mImageItemList.size() : 0;
    }

    public int getSelectedCount() {
        return mSelectedCount;
    }

    public List<ImageModel> getData() {
        return mImageItemList;
    }

    public void addAll(List<ImageModel> imageItemList, int countNeedPreliminary) {
        mCountNeedPreliminary = countNeedPreliminary;
        if (this.mImageItemList == null) {
            this.mImageItemList = new ArrayList<>();
        }
        this.mImageItemList.clear();
        this.mImageItemList.addAll(imageItemList);
        notifyChange();
    }

    public void add(ImageModel imageItemModel) {
        if (mImageItemList == null) {
            mImageItemList = new ArrayList<>();
        }
        mImageItemList.add(imageItemModel);
        notifyItemInserted(mImageItemList.size() - 1);
    }

    public void notifyChange() {
        mSelectedCount = GameUtil.calculateSelected(mImageItemList);
        mCallback.onChangeCountSelected(mSelectedCount);
        if (mCountNeedPreliminary != -1) {
            int diff = mCountNeedPreliminary - mSelectedCount;
            if (diff == 0)
                mCallback.hideRemainCount();
            else
                mCallback.showRemainCount();
            mCallback.changeRemainState(diff);
        }
        notifyDataSetChanged();
    }

    public class ImageHolder extends RecyclerView.ViewHolder {
        ImageView mImageView;

        CardView mWrapperCv;

        public ImageHolder(final View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.game_iv);
            mWrapperCv = itemView.findViewById(R.id.wrapper_image_cv);

            mWrapperCv.setOnLongClickListener(v -> {
                int positionClicked = getAdapterPosition();

                if (positionClicked != -1) {
                    ImageModel imageItemModel = mImageItemList.get(positionClicked);
                    changeSelectionItem(imageItemModel);
                }
                return false;
            });

            mWrapperCv.setOnClickListener(v -> {
                int positionClicked = getAdapterPosition();
                if (positionClicked != -1) {
                    ImageModel imageModel = mImageItemList.get(positionClicked);
                    if (imageModel.isSelected) {
                        imageModel.isSelected = false;
                        notifyChange();
                    }
                }
            });
        }

        public void bind(ImageModel imageItem) {
            int colorBackground = imageItem.isSelected
                    ? ContextCompat.getColor(mContext, R.color.colorAccent)
                    : ContextCompat.getColor(mContext, R.color.colorWhite);
            mWrapperCv.setCardBackgroundColor(colorBackground);

            Glide.with(mContext)
                    .load(imageItem.url)
                    .into(mImageView);
        }
    }

    private void changeSelectionItem(ImageModel imageItemModel) {
        if (!imageItemModel.isSelected && mSelectedCount >= getMaxSelectedCount()) {
            mCallback.selectedMaxCount(getMaxSelectedCount());

        } else {
            imageItemModel.isSelected = !imageItemModel.isSelected;
            notifyChange();
        }
    }

    private static int getMaxSelectedCount() {
        if(mCountNeedPreliminary == -1) {
            return MAX_SELECTED_COUNT;
        } else {
            return mCountNeedPreliminary;
        }
    }

    public static int getMinSelectedCount() {
        if(mCountNeedPreliminary == -1) {
            return MIN_SELECTED_COUNT;
        } else {
            return mCountNeedPreliminary;
        }
    }
}
