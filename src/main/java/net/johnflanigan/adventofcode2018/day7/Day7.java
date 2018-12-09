package net.johnflanigan.adventofcode2018.day7;

import net.johnflanigan.adventofcode2018.Day;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day7 extends Day {

    Map<String, Node> nodeMap = new HashMap<>();
    SortedSet<Node> toProcess;
    List<Node> sorted;

    @Override
    public void solve() {

        String file = "src/main/resources/day7_input.txt";
        List<String> inputs = readFile(file);

        for (String input : inputs) {
            convertInput(input);
        }

        toProcess = new TreeSet<>();
        sorted = new LinkedList<>();

        // Find starting nodes
        for (Map.Entry<String, Node> entry : nodeMap.entrySet()) {
            if (entry.getValue().getPrev().size() == 0) {
                toProcess.add(entry.getValue());
            }
        }


        while (!toProcess.isEmpty()) {
            // Get next node that is ready to process
            Node current = toProcess.first();
            toProcess.remove(current);

            // Add it to the end of the sorted list
            sorted.add(current);

            // Check to see if any of its child nodes are ready to process
            for (Node child : current.getNext()) {
                if (isNodeReadyToProcess(child)) {
                    toProcess.add(child);
                }
            }
        }

        String order = "";
        for (Node node : sorted) {
            order = order.concat(node.getId());
        }
        System.out.println(order);
    }

    private boolean isNodeReadyToProcess(Node node) {

        // If node has not yet been processed
        if (!sorted.contains(node)) {
            // Check if all parents have been processed
            boolean areParentsProcessed = true;
            for (Node parent : node.getPrev()) {
                if (!sorted.contains(parent)) {
                    areParentsProcessed = false;
                }
            }
            return areParentsProcessed;
        } else {
            return false;
        }
    }


    private void convertInput(String input) {

        String regex = "Step ([A-Z]) must be finished before step ([A-Z]) can begin.";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);

        if (matcher.find()) {
            String startId = matcher.group(1);
            Node startNode;
            if (nodeMap.containsKey(startId)) {
                startNode = nodeMap.get(startId);
            } else {
                startNode = new Node(startId);
                nodeMap.put(startNode.getId(), startNode);
            }

            String endId = matcher.group(2);
            Node endNode;
            if (nodeMap.containsKey(endId)) {
                endNode = nodeMap.get(endId);
            } else {
                endNode = new Node(endId);
                nodeMap.put(endNode.getId(), endNode);
            }

            startNode.addNextNode(endNode);
            endNode.addPrevNode(startNode);
        }
    }


}
