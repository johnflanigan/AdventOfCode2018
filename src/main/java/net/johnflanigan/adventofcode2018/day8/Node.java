package net.johnflanigan.adventofcode2018.day8;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Node {

    List<Node> children;
    List<Integer> metadata;

    public Node() {
        this.children = new ArrayList<>();
        this.metadata = new LinkedList<>();
    }

    public void addChild(Node child) {
        this.children.add(child);
    }

    public void addMetadata(int metadata) {
        this.metadata.add(metadata);
    }

    public List<Integer> getMetadata() {
        return this.metadata;
    }

    public int getValue() {
        int value = 0;
        if (this.children.size() == 0) {
            for (Integer metadataId : metadata) {
                value += metadataId;
            }
        } else {
            for (Integer metadataId : metadata) {
                if (metadataId <= this.children.size()) {
                    value += this.children.get(metadataId - 1).getValue();
                }
            }
        }
        return value;
    }
}
