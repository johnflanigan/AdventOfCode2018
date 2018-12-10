package net.johnflanigan.adventofcode2018.day8;

import net.johnflanigan.adventofcode2018.Day;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Day8 extends Day {

    private List<Node> nodes;
    private List<Integer> integers;

    public Day8() {
        this.nodes = new LinkedList<>();
    }

    @Override
    public void solve() {
        String file = "src/main/resources/day8_input.txt";
        String inputs = readFile(file).get(0);
        String[] splitInputs = inputs.split(" ");

        this.integers = new ArrayList<>();
        for (String input : splitInputs) {
            this.integers.add(Integer.parseInt(input));
        }

        Node root = beginProcessing();

        int metadataSum = 0;
        for (Node node : nodes) {
            List<Integer> metadata = node.getMetadata();
            for (Integer value : metadata) {
                metadataSum += value;
            }
        }

        System.out.println(metadataSum);
        System.out.println(root.getValue());
    }

    private Node beginProcessing() {
        int index = 0;

        int quantityOfChildren = this.integers.get(index++);
        int quantityOfMetadata = this.integers.get(index++);

        Node root = new Node();

        for (int i = 0; i < quantityOfChildren; i++) {
            index = processNode(root, index);
        }

        for (int i = 0; i < quantityOfMetadata; i++) {
            root.addMetadata(this.integers.get(index++));
        }

        this.nodes.add(root);
        return root;
    }

    /**
     * @param parent The parent node of this node
     * @param startIndex The index
     * @return the index immediately after this
     */
    private int processNode(Node parent, int startIndex) {
        int index = startIndex;

        int quantityOfChildren = this.integers.get(index++);
        int quantityOfMetadata = this.integers.get(index++);

        Node node = new Node();

        for (int i = 0; i < quantityOfChildren; i++) {
            index = processNode(node, index);
        }

        for (int i = 0; i < quantityOfMetadata; i++) {
            node.addMetadata(this.integers.get(index++));
        }

        parent.addChild(node);
        this.nodes.add(node);
        return index;
    }
}
