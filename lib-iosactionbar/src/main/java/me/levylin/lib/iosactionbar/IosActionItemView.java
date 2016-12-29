package me.levylin.lib.iosactionbar;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Action子项视图
 */
public class IosActionItemView extends RelativeLayout implements View.OnClickListener {

    //标题
    private TextView mTitleView;
    //动作
    private Action mAction;
    private static final int MAX_ICON_SIZE = 32; // dp
    private int mMaxIconSize;

    public IosActionItemView(Context context) {
        super(context);
        init(context);
    }

    public IosActionItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public IosActionItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        setOnClickListener(this);
        final Resources res = context.getResources();
        final float density = res.getDisplayMetrics().density;
        mMaxIconSize = (int) (MAX_ICON_SIZE * density + 0.5f);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mTitleView = (TextView) findViewById(R.id.layout_actionbar_item_tv);
    }


    /**
     * 设置动作
     *
     * @param action
     */
    public void setAction(Action action) {
        mAction = action;
        if (action.getTextRes() != 0) {
            mTitleView.setText(action.getTextRes());
        } else if (action.getTextStr() != null) {
            mTitleView.setText(action.getTextStr());
        }
        if (action.getDrawable() != 0) {
            setIcon(ContextCompat.getDrawable(getContext(), action.getDrawable()));
        }
        if (action.getTextColor() != -1) {
            mTitleView.setTextColor(action.getTextColor());
        }
        setTag(action);
        action.setActionItemView(this);
    }


    public void setIcon(Drawable icon) {
        if (icon != null) {
            int width = icon.getIntrinsicWidth();
            int height = icon.getIntrinsicHeight();
            if (width > mMaxIconSize) {
                final float scale = (float) mMaxIconSize / width;
                width = mMaxIconSize;
                height *= scale;
            }
            if (height > mMaxIconSize) {
                final float scale = (float) mMaxIconSize / height;
                height = mMaxIconSize;
                width *= scale;
            }
            icon.setBounds(0, 0, width, height);
        }
        mTitleView.setCompoundDrawables(icon, null, null, null);
    }

    public TextView getItemView() {
        return mTitleView;
    }

    @Override
    public void onClick(View v) {
        if (mAction == null)
            return;
        mAction.performAction(v);
    }
}
