package com.boxing.cart.unit;

public enum ItemType {
    ELECTRONICS(new String[]{"ipad", "iphone", "显示器", "笔记本电脑", "键盘"}), FOOD(new String[]{"面包", "饼干", "蛋糕", "牛肉", "鱼", "蔬菜"}), DAILY_NECESSITIES(new String[]{"餐巾纸", "收纳箱", "咖啡杯", "雨伞"}), ALCOHOL(new String[]{"啤酒", "白酒", "伏特加"});
    private String[] items;

    private ItemType(String[] items) {
        this.items = items;
    }
    public String[] getItems() {
        return items;
    }
}
