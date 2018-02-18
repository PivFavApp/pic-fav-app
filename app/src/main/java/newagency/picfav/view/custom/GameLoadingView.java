package newagency.picfav.view.custom;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
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

    private final static int ANIMATION_DURATION = 1500;

    @BindView(R.id.progress_counter)
    ProgressBar mProgressBar;

    @BindView(R.id.count_responded)
    TextView mCountTv;

    private int mCountResponded = 0;

    private int mMaxMember = 100;

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
        mProgressBar.setMax(mMaxMember);
        updateCountResponded(mCountResponded);
    }

    private void updateCountResponded(int progress) {
        ValueAnimator widthAnimator = ValueAnimator.ofInt(0, progress);
        widthAnimator.setDuration(ANIMATION_DURATION);
        widthAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int animatedValue = (int) animation.getAnimatedValue();
                String s = getContext().getString(R.string.game_loading_text, animatedValue);
                SpannableString ss1 = new SpannableString(s);
                ss1.setSpan(new RelativeSizeSpan(1.5f), 0, s.length() - 1, 0);
                mCountTv.setText(ss1);
            }
        });
        widthAnimator.start();
    }

    public void setResult(int count) {
        mCountResponded = count;
        updateCountResponded(mCountResponded);
        updateProgress();
        invalidate();
    }

    private void updateProgress() {
        ObjectAnimator progressAnimator = ObjectAnimator.ofInt(mProgressBar, "progress", 0, mCountResponded);
        progressAnimator.setDuration(ANIMATION_DURATION);
        progressAnimator.start();

    }
}
