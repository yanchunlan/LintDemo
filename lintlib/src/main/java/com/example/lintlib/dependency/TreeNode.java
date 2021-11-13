package com.example.lintlib.dependency;

import java.util.List;

public class TreeNode {
    private String currentName;//当前module的名字
    private List<TreeNode> chidrenNodes;//当前module下面的子module

    public TreeNode(String currentName) {
        this.currentName = currentName;
    }

    public TreeNode(String currentName, List<TreeNode> chidrenNodes) {
        this.currentName = currentName;
        this.chidrenNodes = chidrenNodes;
    }

    public String getCurrentName() {
        return currentName;
    }

    public List<TreeNode> getChidrenNodes() {
        return chidrenNodes;
    }
}