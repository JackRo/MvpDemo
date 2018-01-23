package cn.jackro.mvpdemo.util;

import android.view.View;

/**
 * @author JackRo
 */
public class ViewUtil {

    private static final int VISIBLE = View.VISIBLE;

    private static final int GONE = View.GONE;

    private static final int INVISIBLE = View.INVISIBLE;

    public static void setViewVisible(final View view) {
        final int visibility = view.getVisibility();
        if (visibility == GONE || visibility == INVISIBLE) {
            view.setVisibility(VISIBLE);
        }
    }

    public static void setViewGone(final View view) {
        if (view.getVisibility() == VISIBLE) {
            view.setVisibility(GONE);
        }
    }

    @SuppressWarnings("unused")
    public static void setViewInvisible(final View view) {
        if (view.getVisibility() == VISIBLE) {
            view.setVisibility(INVISIBLE);
        }
    }
}
