package com.company.entity;

public class BinaryTreeNodeWithParent {
     private BinaryTreeNode TreeNode;
     private BinaryTreeNode Parent;
     private boolean isLeftParent = false;
     private boolean isRightParent = false;


    public BinaryTreeNodeWithParent(BinaryTreeNode treeNode, BinaryTreeNode parent) {
        TreeNode = treeNode;
        Parent = parent;
    }

    public BinaryTreeNodeWithParent(BinaryTreeNode treeNode) {
        TreeNode = treeNode;
    }

    public BinaryTreeNode getTreeNode() {
        return TreeNode;
    }

    public void setTreeNode(BinaryTreeNode treeNode) {
        TreeNode = treeNode;
    }

    public BinaryTreeNode getParent() {
        return Parent;
    }

    public void setParent(BinaryTreeNode parent) {
        Parent = parent;
    }

    public boolean isLeftParent() {
        return isLeftParent;
    }

    public void setLeftParent(boolean leftParent) {
        isLeftParent = leftParent;
    }

    public boolean isRightParent() {
        return isRightParent;
    }

    public void setRightParent(boolean rightParent) {
        isRightParent = rightParent;
    }
}
