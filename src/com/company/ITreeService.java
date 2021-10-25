package com.company;

import com.company.entity.BinaryTreeNode;

public interface ITreeService {
    boolean insert(int value);

    BinaryTreeNode search(Integer searchValue);

    void printTreeDeep();

    void PrintBinaryTreeWide();

    void delete(Integer deleteValue);
}
