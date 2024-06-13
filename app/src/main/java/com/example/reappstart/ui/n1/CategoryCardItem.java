package com.example.reappstart.ui.n1;

public class CategoryCardItem {

    private int categoryImageResource;
    private String categoryText;

    public CategoryCardItem(int categoryImageResource, String categoryText) {
        this.categoryImageResource = categoryImageResource;
        this.categoryText = categoryText;
    }

    public int getCategoryImageResource() {
        return categoryImageResource;
    }

    public String getCategoryText() {
        return categoryText;
    }
}
