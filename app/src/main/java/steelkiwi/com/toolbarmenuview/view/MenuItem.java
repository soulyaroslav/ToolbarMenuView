package steelkiwi.com.toolbarmenuview.view;

import android.support.annotation.IdRes;

/**
 * Created by yaroslav on 5/30/17.
 */

public class MenuItem {
    private int itemId;
    private @IdRes int background;
    private @IdRes int icon;
    private @IdRes int title;

    public MenuItem(int itemId, int background, int icon, int title) {
        this.itemId = itemId;
        this.background = background;
        this.icon = icon;
        this.title = title;
    }

    public int getItemId() {
        return itemId;
    }

    public int getBackground() {
        return background;
    }

    public int getIcon() {
        return icon;
    }

    public int getTitle() {
        return title;
    }
}
