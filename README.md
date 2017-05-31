# ToolbarMenuView

Simple Container view to show menu in top od the container

## View

![](https://github.com/soulyaroslav/ToolbarMenuView/blob/master/assets/toolbar_menu.gif)

## Usage

Add ToolbarMenuView to your xml layout

```xml
<steelkiwi.com.toolbarmenuview.view.ToolbarMenuView
    android:id="@+id/toolBarMenu"
    android:layout_width="match_parent"
    android:layout_height="match_parent"/>
```

After that need prepare menu item. All you need is use builder instance to customize menu item

```java
new ItemBuilder()
    .setItemId(TAB_1)
    .setBackground(R.color.service_layout_background)
    .setIcon(R.mipmap.room_service)
    .setTitle(R.string.services)
    .build()
```

* setItemId() // it's item id, used for detect what item was clicked
* setBackground() // it's color recourse for item background
* setIcon() // drawable recourse for item icon
* setTitle() // string recourse for item title

You can create menu that has more than one element, than you need create list of item
For example:

```java
new MenuBuilder()
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
    .build();
```

When you prepared your menu, all you need is add this collection to ToolbarMenuView

```java
setMenuItems(List<MenuItem> menuItems);
```

Don't forget implement OnMenuItemClickListener and set this listener to view

```java
setOnMenuItemClickListener(OnMenuItemClickListener listener);
```

## License

```
Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```