package com.project.ECommerceApplication.model;



public class CategoryTreeNode {
    private Category category;
    private CategoryTreeNode left;
    private CategoryTreeNode right;

    public CategoryTreeNode(Category category) {
        this.category = category;
        this.left = null;
        this.right = null;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public CategoryTreeNode getLeft() {
        return left;
    }

    public void setLeft(CategoryTreeNode left) {
        this.left = left;
    }

    public CategoryTreeNode getRight() {
        return right;
    }

    public void setRight(CategoryTreeNode right) {
        this.right = right;
    }
}
