package com.wootric.androidsdk.views.tablet;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wootric.androidsdk.R;
import com.wootric.androidsdk.utils.ScreenUtils;

/**
 * Created by maciejwitowski on 10/8/15.
 */
public class ScoreView extends TextView implements View.OnClickListener {

	private int mTextColorSelected;
	private int mTextColorNotSelected;

	private OnScoreClickListener onScoreClickListener;

	public ScoreView(Context context) {
		super(context);
		init();
	}

	public ScoreView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public ScoreView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		init();
	}

	private void init() {
		setGravity(Gravity.CENTER);

		final Context context = getContext();
		final Resources res = context.getResources();

		mTextColorSelected = res.getColor(R.color.wootric_score_selected);
		mTextColorNotSelected = res.getColor(R.color.wootric_score_unselected);

		final Resources resources = getResources();
		Drawable drawable;

		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
			drawable = resources.getDrawable(R.drawable.score, null);
		} else {
			drawable = resources.getDrawable(R.drawable.score);
		}

		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
			setBackground(drawable);
		}

		setHeight((int) ScreenUtils.dpToPx(42));
		setWidth((int) ScreenUtils.dpToPx(42));

		LinearLayout.LayoutParams llp = new LinearLayout.LayoutParams(
			LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
		int margin = (int) ScreenUtils.dpToPx(6);
		llp.setMargins(margin, 0, 0, 0);
		setLayoutParams(llp);

		setSelected(false);
		setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		setSelected(true);

		if (onScoreClickListener != null) {
			int score = Integer.valueOf(((ScoreView) v).getText().toString());
			onScoreClickListener.onScoreClick(score);
		}
	}

	@Override
	public void setSelected(boolean selected) {
		super.setSelected(selected);

		setTextColor(isSelected() ? mTextColorSelected : mTextColorNotSelected);
		setTypeface(null, isSelected() ? Typeface.BOLD : Typeface.NORMAL);
	}

	public void setOnScoreClickListener(OnScoreClickListener onScoreClickListener) {
		this.onScoreClickListener = onScoreClickListener;
	}

	public interface OnScoreClickListener {
		void onScoreClick(int score);
	}
}
