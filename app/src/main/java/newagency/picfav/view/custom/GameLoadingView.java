package newagency.picfav.view.custom;

import android.content.Context;
import android.text.SpannableString;
import android.text.style.RelativeSizeSpan;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import newagency.picfav.R;

/**
 * Created by oroshka on 10/20/17.
 */

public class GameLoadingView extends RelativeLayout {

    @BindView(R.id.progress_counter)
    ProgressBar mProgressBar;

    @BindView(R.id.count_responded)
    TextView mCountTv;

    private int mCountResponded = 0;

    private int mMaxMember;

    public GameLoadingView(Context context) {
        super(context);
        init();
    }

    public GameLoadingView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public GameLoadingView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        View view = inflate(getContext(), R.layout.custom_game_loagin_layout, this);
        ButterKnife.bind(view);
        mCountResponded = 0;
        updateCountResponded();
    }

    private void updateCountResponded() {
        String s = getContext().getString(R.string.game_loading_text, mCountResponded);
        SpannableString ss1 = new SpannableString(s);
        ss1.setSpan(new RelativeSizeSpan(2f), 0, 2, 0);
        mCountTv.setText(ss1);
    }

    public void setCountResponded(int count) {
        mCountResponded = count;
        updateCountResponded();
        updateProgress();
        invalidate();
    }

    private void updateProgress() {
        mProgressBar.setProgress(mCountResponded);
    }

    public void setMaxMembers(int maxMembers) {
        mMaxMember = maxMembers;
        mProgressBar.setMax(mMaxMember);
    }
}
