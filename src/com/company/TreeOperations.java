package com.company;

import com.company.entity.BinaryTreeNode;

public interface TreeOperations {
    boolean insert(int value);

    BinaryTreeNode search(Integer searchValue);

    void printTreeDeep();

    void printBinaryTreeWide();

    void delete(Integer deleteValue);
}
