package me.levylin.lib.iosactionbar;

import android.view.View;

/**
 * 他趣Action
 *
 * @author: LinX
 * @Date: 2015/7/29 9:58.
 */
public abstract class Action {

    private boolean isVisible = true;

    private IosActionItemView actionItemView;

    public String getTextStr() {
        return null;
    }

    public int getTextRes() {
        return 0;
    }

    public int getDrawable() {
        return 0;
    }

    public int getTextColor() {
        return -1;
    }

    public abstract void performAction(View view);

    public IosActionItemView getActionItemView() {
        return actionItemView;
    }

    public void setActionItemView(IosActionItemView actionItemView) {
        this.actionItemView = actionItemView;
    }

    public boolean isVisible() {
        return isVisible;
    }

    public void setIsVisible(boolean isVisible) {
        this.isVisible = isVisible;
        if (actionItemView != null) {
            if (isVisible) {
                actionItemView.setVisibility(View.VISIBLE);
            } else {
                actionItemView.setVisibility(View.GONE);
            }
        }
    }
}
