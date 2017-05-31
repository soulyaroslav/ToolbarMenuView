package steelkiwi.com.toolbarmenuview.view.builder;

import java.util.ArrayList;
import java.util.List;

import steelkiwi.com.toolbarmenuview.view.MenuItem;

/**
 * Created by yaroslav on 5/31/17.
 */

public final class MenuBuilder {
    private List<MenuItem> items;

    public MenuBuilder() {
        items = new ArrayList<>();
    }

    public MenuBuilder addItem(MenuItem item) {
        items.add(item);
        return this;
    }

    public List<MenuItem> build() {
        return items;
    }
}
