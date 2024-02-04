package me.grocery.grocerylist;

import java.util.ArrayList;

public class CategoryModel {
    private String categoryName;
    private ArrayList<ItemModel> items;

    public CategoryModel(String categoryName, ArrayList<ItemModel> items) {
        this.categoryName = categoryName;
        this.items = items;
    }

    public String getCategoryName() { return categoryName; }
    public ArrayList<ItemModel> getItems() { return items; }
}

