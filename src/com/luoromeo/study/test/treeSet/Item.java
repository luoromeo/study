package com.luoromeo.study.test.treeSet;

import java.util.Objects;

/**
 * @description
 * @author zhanghua.luo
 * @date 2018年07月31日 16:24
 * @modified By
 */
public class Item implements Comparable<Item>{

    private String description;

    private int partNum;

    public Item(String description, int partNum) {
        this.description = description;
        this.partNum = partNum;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "[description=" + description + ", partNumber=" + partNum + "]";
    }

    @Override
    public boolean equals(Object otherObject) {
        if (this == otherObject) {
            return true;
        }
        if (otherObject == null) {
            return false;
        }
        if (getClass() != otherObject.getClass()) {
            return false;
        }
        Item other = (Item) otherObject;
        return Objects.equals(description, other.description) && partNum == other.partNum;
    }

    @Override
    public int hashCode() {
        return Objects.hash(description, partNum);
    }

    @Override
    public int compareTo(Item other) {
        int diff = Integer.compare(partNum, other.partNum);
        return diff != 0 ? diff : description.compareTo(other.description);
    }
}
