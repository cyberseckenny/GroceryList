package me.grocery.grocerylist;

public class ItemModel {
    private String itemName;

    public ItemModel(String itemName) {
        this.itemName = itemName;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }
}
