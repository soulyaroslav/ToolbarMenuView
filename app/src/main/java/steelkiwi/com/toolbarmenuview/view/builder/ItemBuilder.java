package steelkiwi.com.toolbarmenuview.view.builder;

import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;

import steelkiwi.com.toolbarmenuview.view.MenuItem;

/**
 * Created by yaroslav on 5/31/17.
 */

public final class ItemBuilder {
    private int itemId;
    private @ColorRes int background;
    private @DrawableRes int icon;
    private @StringRes int title;

    public ItemBuilder() {
    }

    public ItemBuilder setItemId(int itemId) {
        this.itemId = itemId;
        return this;
    }

    public ItemBuilder setBackground(@ColorRes int background) {
        this.background = background;
        return this;
    }

    public ItemBuilder setIcon(@DrawableRes int icon) {
        this.icon = icon;
        return this;
    }

    public ItemBuilder setTitle(@StringRes int title) {
        this.title = title;
        return this;
    }

    public MenuItem build() {
        return new MenuItem(itemId, background, icon, title);
    }
}
