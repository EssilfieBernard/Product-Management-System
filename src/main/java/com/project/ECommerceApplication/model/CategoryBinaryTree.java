package com.project.ECommerceApplication.model;

import org.springframework.stereotype.Component;

@Component
public class CategoryBinaryTree {
    private CategoryTreeNode root;

    // Insert a new category into the tree
    public void insertCategory(Category category) {
        root = insertRecursive(root, category);
    }

    private CategoryTreeNode insertRecursive(CategoryTreeNode node, Category category) {
        if (node == null) {
            return new CategoryTreeNode(category);
        }
        if (category.getName().compareTo(node.getCategory().getName()) < 0) {
            node.setLeft(insertRecursive(node.getLeft(), category));
        } else if (category.getName().compareTo(node.getCategory().getName()) > 0) {
            node.setRight(insertRecursive(node.getRight(), category));
        }
        return node;
    }

    // Search for a category in the tree
    public Category searchCategory(String name) {
        return searchRecursive(root, name);
    }

    private Category searchRecursive(CategoryTreeNode node, String name) {
        if (node == null || node.getCategory().getName().equals(name)) {
            return node != null ? node.getCategory() : null;
        }
        if (name.compareTo(node.getCategory().getName()) < 0) {
            return searchRecursive(node.getLeft(), name);
        }
        return searchRecursive(node.getRight(), name);
    }
}
