package me.grocery.grocerylist;

import java.util.ArrayList;

public class CategoryModel {
    private String categoryName;
    private ArrayList<TextModel> items;

    public CategoryModel(String categoryName, ArrayList<TextModel> items) {
        this.categoryName = categoryName;
        this.items = items;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public ArrayList<TextModel> getItems() {
        return items;
    }
}
