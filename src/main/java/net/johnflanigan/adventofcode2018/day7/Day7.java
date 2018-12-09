package net.johnflanigan.adventofcode2018.day7;

import net.johnflanigan.adventofcode2018.Day;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day7 extends Day {

    private static final int NUMBER_OF_WORKERS = 5;

    private Map<String, Node> nodeMap = new HashMap<>();
    private SortedSet<Node> readyToProcess;
    private List<Node> sorted;
    private List<Worker> workers;
    private int clock = 0;

    @Override
    public void solve() {

        String file = "src/main/resources/day7_input.txt";
        List<String> inputs = readFile(file);

        for (String input : inputs) {
            convertInput(input);
        }

        readyToProcess = new TreeSet<>();
        sorted = new LinkedList<>();
        workers = new LinkedList<>();

        // Find starting nodes
        for (Map.Entry<String, Node> entry : nodeMap.entrySet()) {
            if (entry.getValue().getParents().size() == 0) {
                readyToProcess.add(entry.getValue());
            }
        }

        // Initialize workers
        for (int i = 0; i < NUMBER_OF_WORKERS; i++) {
            workers.add(new Worker());
        }

        while (!readyToProcess.isEmpty() || nodeMap.size() != sorted.size()) {
            // Check if any workers have finished processing
            for (Worker worker : workers) {
                if (worker.isProcessingFinished(clock)) {
                    // Get completed node and update worker
                    Node completed = worker.getNodeProcessing();
                    worker.setNodeProcessing(null);

                    sorted.add(completed);
                    // Check to see if any of its child nodes are ready to process
                    for (Node child : completed.getChildren()) {
                        if (isNodeReadyToProcess(child)) {
                            readyToProcess.add(child);
                        }
                    }
                }
            }

            // Check to see if any workers are ready to start processing
            for (Worker worker : workers) {
                if (worker.getNodeProcessing() == null && !readyToProcess.isEmpty()) {
                    Node ready = readyToProcess.first();
                    readyToProcess.remove(ready);
                    worker.setNodeProcessing(ready);
                    worker.setProcessingStartTime(clock);
                }
            }

            clock++;
        }

        String order = "";
        for (Node node : sorted) {
            order = order.concat(node.getId());
        }
        System.out.println("Part 1 solution: " + order);

        System.out.println("Part 2 solution: " + (clock - 1));
    }

    private boolean isNodeReadyToProcess(Node node) {

        // If node has not yet been processed
        if (!sorted.contains(node)) {
            // Check if all parents have been processed
            boolean areParentsProcessed = true;
            for (Node parent : node.getParents()) {
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
