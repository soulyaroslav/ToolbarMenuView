package steelkiwi.com.toolbarmenuview.view;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.support.annotation.LayoutRes;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import steelkiwi.com.toolbarmenuview.R;
import steelkiwi.com.toolbarmenuview.util.OnMenuItemClickListener;
import steelkiwi.com.toolbarmenuview.util.ViewState;

/**
 * Created by yaroslav on 5/29/17.
 */

public class ToolbarMenuView extends RelativeLayout {

    private List<MenuItem> menuItems = new ArrayList<>();
    private List<View> views = new ArrayList<>();
    private ViewState state = ViewState.IDLE;
    private OnMenuItemClickListener listener;
    private int screenHeight;
    private int barHeight;
    private int viewHeight;
    private boolean isClickable = false;

    public ToolbarMenuView(Context context) {
        super(context);
        init();
    }

    public ToolbarMenuView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ToolbarMenuView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                getViewTreeObserver().removeOnGlobalLayoutListener(this);
                setScreenHeight(getHeight());
                setBarHeight(getResources().getDimensionPixelSize(R.dimen.bar_size));
                setViewHeight((getScreenHeight() /*+ (menuItems.size() * 50)*/) / menuItems.size());
            }
        });
    }

    private void prepareMenuItems() {
        for(int i = 0; i < menuItems.size(); i++) {
            MenuItem item = getMenuItem(i);
            View view = prepareView(item);
            setViewClickListener(i, view);
            views.add(view);
            addView(view);
        }
    }

    private void setViewClickListener(final int position, final View view) {
        view.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(getState() == ViewState.EXPAND) {
                    setState(ViewState.IDLE);
                    returnBackViews();
                    bringViewToFront(v);
                } else {
                    setState(ViewState.EXPAND);
                    moveViews();
                }
                onMenuItemClick(position);
            }
        });
    }

    private void onMenuItemClick(int position) {
        if(listener != null && isViewClickable()) {
            listener.onItemClick(position);
            setViewClickable(false);
        }
    }

    private void moveViews() {
        int startPosition = 0;
        for(int i = 0; i < views.size(); i++) {
            final View view = getView(i);
            view.bringToFront();
            translateViewByY(view, startPosition);
            resizeViewHeight(view, getBarHeight(), getViewHeight());
            startPosition += (getViewHeight() /*- 50*/);
            setVisibleAlpha(view.findViewById(R.id.ivIcon));
        }
    }

    private void returnBackViews() {
        for(int i = 0; i < views.size(); i++) {
            final View view = getView(i);
            view.bringToFront();
            translateViewByYBack(view, 0);
            resizeViewHeight(view, getViewHeight(), getBarHeight());
            setInvisibleAlpha(view.findViewById(R.id.ivIcon));
        }
    }

    private void translateViewByY(final View view, final int position) {
        ObjectAnimator moveAnimator = ObjectAnimator.ofFloat(view, "translationY", position);
        moveAnimator.setDuration(600);
        moveAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                setViewClickable(true);
            }
        });
        moveAnimator.start();
    }

    private void translateViewByYBack(final View view, final int position) {
        ObjectAnimator moveAnimator = ObjectAnimator.ofFloat(view, "translationY", position);
        moveAnimator.setDuration(600);
        moveAnimator.start();
    }

    private void resizeViewHeight(final View view, int startHeight, int endHeight) {
        ValueAnimator resizeAnimator = ValueAnimator.ofInt(startHeight, endHeight);
        resizeAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                int value = (int) valueAnimator.getAnimatedValue();
                setViewLayoutParams(view, value);
            }
        });
        resizeAnimator.setDuration(600);
        resizeAnimator.start();
    }

    private void setVisibleAlpha(final View view) {
        ObjectAnimator alphaAnimator = ObjectAnimator.ofFloat(view, "alpha", 0f, 1f);
        alphaAnimator.setDuration(1000);
        alphaAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                view.setVisibility(VISIBLE);
            }
        });
        alphaAnimator.start();
    }

    private void setInvisibleAlpha(final View view) {
        ObjectAnimator alphaAnimator = ObjectAnimator.ofFloat(view, "alpha", 1f, 0f);
        alphaAnimator.setDuration(400);
        alphaAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                view.setVisibility(GONE);
            }
        });
        alphaAnimator.start();
    }

    private void bringViewToFront(View view) {
        view.bringToFront();
    }

    private void setViewLayoutParams(View view, int height) {
        view.getLayoutParams().height = height;
        requestLayout();
    }

    private View prepareView(MenuItem item) {
        View view = inflate(R.layout.default_item_layout);
        ImageView icon = (ImageView) view.findViewById(R.id.ivIcon);
        TextView title = (TextView) view.findViewById(R.id.tvTitle);
        icon.setImageResource(item.getIcon());
        title.setText(item.getTitle());
        view.setBackgroundResource(item.getBackground());
        return view;
    }

    private View inflate(@LayoutRes int layout) {
        return LayoutInflater.from(getContext()).inflate(layout, this, false);
    }

    private ViewState getState() {
        return state;
    }

    private void setState(ViewState state) {
        this.state = state;
    }

    private int getScreenHeight() {
        return screenHeight;
    }

    private void setScreenHeight(int screenHeight) {
        this.screenHeight = screenHeight;
    }

    private int getBarHeight() {
        return barHeight;
    }

    private void setBarHeight(int barHeight) {
        this.barHeight = barHeight;
    }

    private int getViewHeight() {
        return viewHeight;
    }

    private void setViewHeight(int viewHeight) {
        this.viewHeight = viewHeight;
    }

    private MenuItem getMenuItem(int position) {
        return menuItems.get(position);
    }

    private View getView(int position) {
        return views.get(position);
    }

    private boolean isViewClickable() {
        return isClickable;
    }

    private void setViewClickable(boolean clickable) {
        isClickable = clickable;
    }

    public void setMenuItems(List<MenuItem> menuItems) {
        this.menuItems = menuItems;
        prepareMenuItems();
    }

    public void setOnMenuItemClickListener(OnMenuItemClickListener listener) {
        this.listener = listener;
    }
}
