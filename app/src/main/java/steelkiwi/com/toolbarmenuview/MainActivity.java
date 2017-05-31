package steelkiwi.com.toolbarmenuview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import java.util.List;

import steelkiwi.com.toolbarmenuview.util.OnMenuItemClickListener;
import steelkiwi.com.toolbarmenuview.view.MenuItem;
import steelkiwi.com.toolbarmenuview.view.ToolbarMenuView;
import steelkiwi.com.toolbarmenuview.view.builder.ItemBuilder;
import steelkiwi.com.toolbarmenuview.view.builder.MenuBuilder;

public class MainActivity extends AppCompatActivity implements OnMenuItemClickListener {

    private static final int TAB_1 = 0;
    private static final int TAB_2 = 1;
    private static final int TAB_3 = 2;
    private static final int TAB_4 = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ToolbarMenuView menuView = (ToolbarMenuView) findViewById(R.id.toolBarMenu);
        menuView.setMenuItems(prepareItems());
        menuView.setOnMenuItemClickListener(this);
    }

    private List<MenuItem> prepareItems() {
        return new MenuBuilder()
                .addItem(new ItemBuilder()
                        .setItemId(TAB_1)
                        .setBackground(R.color.service_layout_background)
                        .setIcon(R.mipmap.room_service)
                        .setTitle(R.string.services)
                        .build())
                .addItem(new ItemBuilder()
                        .setItemId(TAB_2)
                        .setBackground(R.color.auto_layout_background)
                        .setIcon(R.mipmap.directions_car)
                        .setTitle(R.string.auto)
                        .build())
                .addItem(new ItemBuilder()
                        .setItemId(TAB_3)
                        .setBackground(R.color.job_layout_background)
                        .setIcon(R.mipmap.account_circle)
                        .setTitle(R.string.job)
                        .build())
                .addItem(new ItemBuilder()
                        .setItemId(TAB_4)
                        .setBackground(R.color.reality_layout_background)
                        .setIcon(R.mipmap.home)
                        .setTitle(R.string.reality)
                        .build())
                .build();
    }

    @Override
    public void onItemClick(int itemId) {
        switch (itemId) {
            case TAB_1:
                Toast.makeText(this, "Tab 1 clicked", Toast.LENGTH_SHORT).show();
                break;
            case TAB_2:
                Toast.makeText(this, "Tab 2 clicked", Toast.LENGTH_SHORT).show();
                break;
            case TAB_3:
                Toast.makeText(this, "Tab 3 clicked", Toast.LENGTH_SHORT).show();
                break;
            case TAB_4:
                Toast.makeText(this, "Tab 4 clicked", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
