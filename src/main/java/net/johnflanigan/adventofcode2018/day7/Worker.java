package net.johnflanigan.adventofcode2018.day7;

public class Worker {

    private Node nodeProcessing;
    private int processingStartTime;

    public boolean isProcessingFinished(int currentTime) {
        if (nodeProcessing == null) {
            return false;
        } else {
            return currentTime == processingStartTime + nodeProcessing.getTimeToComplete();
        }
    }

    public Node getNodeProcessing() {
        return this.nodeProcessing;
    }

    public void setNodeProcessing(Node nodeProcessing) {
        this.nodeProcessing = nodeProcessing;
    }

    public int getProcessingStartTime() {
        return this.processingStartTime;
    }

    public void setProcessingStartTime(int processingStartTime) {
        this.processingStartTime = processingStartTime;
    }
}
