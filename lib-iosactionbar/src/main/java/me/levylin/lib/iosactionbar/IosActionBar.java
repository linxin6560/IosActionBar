package me.levylin.lib.iosactionbar;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * 标题栏
 * Created by LinXin on 2016/6/7 14:01.
 */
@SuppressWarnings({"JavaDoc", "unused"})
public class IosActionBar extends LinearLayout {

    /**
     * 左边布局
     */
    private LinearLayout mLeftLl;
    /**
     * 中间布局
     */
    private LinearLayout mCenterLl;
    /**
     * 右边布局
     */
    private LinearLayout mRightLl;
    /**
     * 标题文本
     */
    private TextView mTitleTv;

    public IosActionBar(Context context) {
        super(context);
        init(context);
    }

    public IosActionBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        setOrientation(VERTICAL);
        inflate(context, R.layout.layout_actionbar, this);
        mLeftLl = (LinearLayout) findViewById(R.id.layout_actionbar_left_ll);
        mCenterLl = (LinearLayout) findViewById(R.id.layout_actionbar_center_ll);
        mRightLl = (LinearLayout) findViewById(R.id.layout_actionbar_right_ll);
        mTitleTv = (TextView) findViewById(R.id.layout_actionbar_title_tv);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        int childCount = getChildCount();
        if (childCount == 1)
            return;
        for (int i = 1; i < childCount; i++) {
            View view = getChildAt(i);
            LayoutParams lp = (LayoutParams) view.getLayoutParams();
            removeView(view);
            mCenterLl.addView(view);
        }
    }

    /**
     * 设置标题
     *
     * @param resId
     */
    public void setTitle(int resId) {
        mTitleTv.setText(resId);
    }

    /**
     * 设置标题
     *
     * @param title
     */
    public void setTitle(CharSequence title) {
        mTitleTv.setText(title);
    }

    /**
     * 设置自定义视图
     *
     * @param customView
     */
    public void setCustomView(View customView, LayoutParams lp) {
        mCenterLl.addView(customView, lp);
        mTitleTv.setVisibility(View.GONE);
    }

    /**
     * 设置自定义视图
     *
     * @param customView
     */
    public void setCustomView(View customView) {
        mCenterLl.addView(customView);
        mTitleTv.setVisibility(View.GONE);
    }

    /**
     * 设置搜索界面,设置之后，将没有后退按钮
     *
     * @param searchView 搜索界面
     */
    public void setSearchView(View searchView) {
        removeAllViews();
        addView(searchView, LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
    }

    /**
     * 批量添加按钮
     *
     * @param actions
     */
    public void addRightActions(Action... actions) {
        for (Action action : actions) {
            addRightAction(action);
        }
    }

    /**
     * 添加右边按钮
     *
     * @param action
     */
    public void addRightAction(Action action) {
        IosActionItemView itemView = makeItemView(action, mRightLl);
        mRightLl.addView(itemView);
        if (action.isVisible()) {
            itemView.setVisibility(View.VISIBLE);
        } else {
            itemView.setVisibility(View.GONE);
        }
    }

    /**
     * 添加左边按钮
     *
     * @param action
     */
    public void addLeftAction(Action action) {
        IosActionItemView actionItemView = makeItemView(action, mLeftLl);
        mLeftLl.addView(actionItemView);
        if (action.isVisible()) {
            actionItemView.setVisibility(View.VISIBLE);
        } else {
            actionItemView.setVisibility(View.GONE);
        }
    }

    /**
     * 生成ItemView
     *
     * @param action
     * @return itemView
     */
    private IosActionItemView makeItemView(Action action, ViewGroup parent) {
        IosActionItemView itemView = (IosActionItemView) LayoutInflater.from(getContext()).inflate(R.layout.layout_actionbar_item, parent, false);
        itemView.setAction(action);
        return itemView;
    }
}
