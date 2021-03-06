package com.company.entity;

import com.company.TreeOperations;
import com.company.exceptions.TreeAppException;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class BinaryTreeNode implements TreeOperations {

    private Integer value;
    private BinaryTreeNode left;
    private BinaryTreeNode right;

    public BinaryTreeNode(Integer value, BinaryTreeNode left, BinaryTreeNode right) {
        this.value = value;
        this.left = left;
        this.right = right;
    }

    public BinaryTreeNode(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public BinaryTreeNode getLeft() {
        return left;
    }

    public void setLeft(BinaryTreeNode left) {
        this.left = left;
    }

    public BinaryTreeNode getRight() {
        return right;
    }

    public void setRight(BinaryTreeNode right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                ", leftChild=" + left +
                ", rightChild=" + right +
                '}';
    }

    BinaryTreeNode root;

    @Override
    public boolean insert(int value) {
        BinaryTreeNode newNode = new BinaryTreeNode(value);
        try {
            if (insertRoot(value, newNode)) {
                return true;
            }

            BinaryTreeNode existValue = search(value);
            BinaryTreeNode parent;
            BinaryTreeNode current;
            current = root;

            if (existValue != null) {
                throw new TreeAppException("Element exist " + value);
            }

            while (true) {

                if (value > current.getValue()) {
                    parent = current.getRight();
                    current = insertRightChild(newNode, parent, current);
                } else {
                    parent = current.getLeft();
                    current = insertLeftChild(value, newNode, parent, current);
                }

                if (current == null) {
                    return true;
                }
            }
        } catch (TreeAppException | NullPointerException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public BinaryTreeNode search(Integer searchValue) {
        System.out.println("Start binary tree search");

        if (root == null) {
            return null;
        }
        BinaryTreeNode current = root;

        while (searchValue.equals(current.getValue()) ) {
            if (searchValue > current.getValue()) {
                current = current.getRight();
            } else {
                current = current.getLeft();
            }

            if (current == null) {
                return null;
            }
        }
        return current;
    }

    @Override
    public void printTreeDeep() {
        Stack<BinaryTreeNode> bst = new Stack<>();
        String outString = "";
        bst.push(root);

        while (!bst.isEmpty()) {
            BinaryTreeNode node = bst.pop();

            outString += " " + node.getValue().toString();

            if (node.getRight() != null) {
                bst.push(node.getRight());
            }

            if (node.getLeft() != null) {
                bst.push(node.getLeft());
            }
        }

        System.out.println("Print deep: " + outString);
    }

    @Override
    public void printBinaryTreeWide() {
        ArrayList<Integer> lists = new ArrayList<>();

        Queue<BinaryTreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            BinaryTreeNode tree = queue.poll();
            if (tree.getLeft() != null)
                queue.offer(tree.getLeft());
            if (tree.getRight() != null)
                queue.offer(tree.getRight());
            lists.add(tree.getValue());
        }

        System.out.println("Print wide: " + lists);
    }

    @Override
    public void delete(Integer deleteValue) {
        BinaryTreeNode parentLeft = null;
        BinaryTreeNode parentRight = null;

        if (root == null) {
            return;
        }

        BinaryTreeNodeWithParent existTreeNode = searchTreeNodeWithParent(deleteValue);

        if(deleteValue == null){
            return;
        }
        BinaryTreeNode current = existTreeNode.getTreeNode();
        if (existTreeNode.isRightParent()) {
            parentRight = existTreeNode.getParent();
        } else {
            parentLeft = existTreeNode.getParent();
        }

        // 1. ?????? ????????????????
        if (deleteNodeNoChildren(parentLeft, parentRight, current)) {
            System.out.println("Delete successful " + deleteValue);
            return;
        }

        // 2. ?????????? 1 ??????????????
        if (deleteTreeWithOneChildren(parentLeft, parentRight, current)) {
            System.out.println("Delete successful for value " + deleteValue);
            return;
        }

        //2 ??????????????
        BinaryTreeNode leftChildren = current.getLeft();
        BinaryTreeNode rightChildren = current.getRight();

        //2.1 ???????? ?? ???????????? ?????????????? ?????? ?????????????? ??????????????
        if (deleteNodeIfDontExistRightChildByLeftChild(parentLeft, parentRight, leftChildren, rightChildren)) {
            return;
        }

        //2.2 ???????? ?? ???????????? ?????????????? ???????? ???????????? ??????????????
        //find max ???? ???????????? leftChildren
        var maxLeftChWithParent = getMaxByBinaryTreeNode(leftChildren);
        BinaryTreeNode parent = maxLeftChWithParent.getParent();
        BinaryTreeNode treeNode = maxLeftChWithParent.getTreeNode();

        //???????? ?? ?????????????????????? ?????????????? ?????????????? ?????? ?????????? ????????????????
        if (treeNode.getLeft() == null) {
            parent.setRight(null);
            treeNode.setRight(rightChildren);
            treeNode.setLeft(leftChildren);
            if (parentRight != null) {
                parentRight.setRight(treeNode);
            } else if (parentLeft != null) {
                parentLeft.setLeft(treeNode);
            }

        } else {
            BinaryTreeNode leftMaxLeftCh = treeNode.getLeft();
            parent.setRight(leftMaxLeftCh);
            if (parentRight != null) {
                parentRight.setRight(treeNode);
                treeNode.setRight(rightChildren);
                treeNode.setLeft(leftChildren);

            } else if (parentLeft != null) {
                parentLeft.setLeft(treeNode);
            } else {
                treeNode.setRight(rightChildren);
                treeNode.setLeft(leftChildren);
                root = treeNode;
            }
        }
    }

    private boolean deleteNodeIfDontExistRightChildByLeftChild(BinaryTreeNode parentLeft, BinaryTreeNode parentRight, BinaryTreeNode leftChildren, BinaryTreeNode rightChildren) {
        if (leftChildren.getRight() == null) {
            if (parentLeft != null) {
                parentLeft.setLeft(leftChildren);
                leftChildren.setRight(rightChildren);
            } else if (parentRight != null) {
                parentRight.setRight(leftChildren);
                leftChildren.setRight(rightChildren);
            } else {
                root = leftChildren;
                leftChildren.setRight(rightChildren);
            }
            return true;
        }
        return false;
    }

    private BinaryTreeNodeWithParent searchTreeNodeWithParent(Integer searchValue){
        //?????????? ???????????????? ?? ????????????
        BinaryTreeNodeWithParent treeNodeWithParent = new BinaryTreeNodeWithParent(root);
        BinaryTreeNode parentLeft = null;
        BinaryTreeNode parentRight = null;
        while (searchValue.equals(treeNodeWithParent.getTreeNode().getValue())) {
            parentLeft = null;
            parentRight = null;
            if (searchValue > treeNodeWithParent.getTreeNode().getValue()) {
                parentRight = treeNodeWithParent.getTreeNode();
                treeNodeWithParent.setTreeNode(treeNodeWithParent.getTreeNode().getRight());
            } else {
                parentLeft = treeNodeWithParent.getTreeNode();
                treeNodeWithParent.setTreeNode(treeNodeWithParent.getTreeNode().getLeft());
            }

            if (treeNodeWithParent.getTreeNode() == null) {
                return null;
            }
        }

        if (parentRight != null) {
            treeNodeWithParent.setParent(parentRight);
            treeNodeWithParent.setRightParent(true);
        } else if (parentLeft != null) {
            treeNodeWithParent.setParent(parentLeft);
            treeNodeWithParent.setLeftParent(true);
        }

        return treeNodeWithParent;
    }

    private BinaryTreeNode insertLeftChild(int value, BinaryTreeNode newNode, BinaryTreeNode parent, BinaryTreeNode current) {
        if (parent == null) {
            current.setLeft(newNode);
            System.out.println("insert true for " + value);
            return null;
        } else {
            current = parent;
        }
        return current;
    }

    private BinaryTreeNode insertRightChild(BinaryTreeNode newNode, BinaryTreeNode parent, BinaryTreeNode current) {
        if (parent == null) {
            current.setRight(newNode);
            System.out.println("insert true for " + newNode.getValue());
            return null;
        } else {
            current = parent;
        }
        return current;
    }

    private boolean insertRoot(int value, BinaryTreeNode newNode) {
        if (root == null) {
            root = newNode;
            System.out.println("insert true for " + value);
            return true;
        }
        return false;
    }

    private BinaryTreeNodeWithParent getMaxByBinaryTreeNode(BinaryTreeNode treeNode) {
        BinaryTreeNode current = treeNode;
        BinaryTreeNode next = null;
        BinaryTreeNode parent = treeNode;

        while (current != null) {
            parent = next;
            next = current;

            current = current.getRight();
        }

        return new BinaryTreeNodeWithParent(next, parent);
    }

    private boolean deleteTreeWithOneChildren(BinaryTreeNode parentLeft, BinaryTreeNode parentRight, BinaryTreeNode current) {
        if (current.getLeft() == null && current.getRight() != null
                || current.getLeft() != null && current.getRight() == null) {

            if (parentLeft != null) {
                if (current.getLeft() != null) {
                    parentLeft.setLeft(current.getLeft());
                } else {
                    parentLeft.setLeft(current.getRight());
                }
            } else if (parentRight != null) {
                if (current.getLeft() != null) {
                    parentRight.setRight(current.getLeft());
                } else {
                    parentRight.setRight(current.getRight());
                }
            } else {
                if (current.getLeft() != null) {
                    root = current.getLeft();
                } else {
                    root = current.getRight();
                }
            }
            return true;
        }
        return false;
    }

    private boolean deleteNodeNoChildren(BinaryTreeNode parentLeft, BinaryTreeNode parentRight, BinaryTreeNode current) {
        if (current.getRight() == null && current.getLeft() == null) {
            if (parentLeft != null) {
                parentLeft.setLeft(null);
            } else {
                parentRight.setRight(null);
            }
            return true;
        }
        return false;
    }

}
