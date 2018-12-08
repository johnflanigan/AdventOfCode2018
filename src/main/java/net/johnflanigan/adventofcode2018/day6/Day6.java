package net.johnflanigan.adventofcode2018.day6;

import net.johnflanigan.adventofcode2018.Day;

import java.util.List;

public class Day6 extends Day {

    @Override
    public void solve() {

        String file ="src/main/resources/day6_input.txt";
        List<String> inputs = readFile(file);

        int[][] pairs = new int[inputs.size()][2];

        // Transform the input strings into coordinate pairs stored in a 2d array
        for (int i = 0; i < inputs.size(); i++) {
            String input = inputs.get(i);
            String[] splitInput = input.split(",");
            pairs[i][0] = Integer.parseInt(splitInput[0].trim());
            pairs[i][1] = Integer.parseInt(splitInput[1].trim());
        }

        // find the maximum row and col coordinates
        int maxRow = 0;
        int maxCol = 0;
        for (int i = 0; i < pairs.length; i++) {
            if (pairs[i][0] > maxRow) {
                maxRow = pairs[i][0];
            }
            if (pairs[i][1] > maxCol) {
                maxCol = pairs[i][1];
            }
        }

        int[][] grid = new int[maxRow + 2][maxCol + 2];

        // initialize the grid to -1s
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[0].length; col++) {
                grid[row][col] = -1;
            }
        }

        // for each point on the grid, record the index of the coordinate it is closest too
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[0].length; col++) {
                grid[row][col] = getIndexOfClosestPair(row, col, pairs);
            }
        }

        int[] count = new int[pairs.length];

        // Mark infinite pairs by checking all edges
        for (int row = 0; row < grid.length; row++) {
            if (grid[row][0] != -1) {
                count[grid[row][0]]  = -1;
            }
            if (grid[row][maxCol + 1] != -1) {
                count[grid[row][maxCol + 1]]  = -1;
            }
        }

        for (int col = 0; col < grid[0].length; col++) {
            if (grid[0][col] != -1) {
                count[grid[0][col]] = -1;
            }
            if (grid[maxRow + 1][col] != -1) {
                count[grid[maxRow + 1][col]] = -1;
            }
        }

        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid.length; col++) {
                if (grid[row][col] != -1 && count[grid[row][col]] != -1) {
                    count[grid[row][col]]++;
                }
            }
        }

        int maxCount = 0;
        for (int i = 0; i < count.length; i++) {
            if (count[i] > maxCount) {
                maxCount = count[i];
            }
        }

        System.out.println("Part 1 solution: " + maxCount);

        // For part 2, get the total distance for each point on grid
        int[][] totalDistance = new int[maxRow + 2][maxCol + 2];
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[0].length; col++) {
                totalDistance[row][col] = getTotalDistance(row, col, pairs);
            }
        }

        // Count number of locations with total distance less than 10000
        int totalDistanceCount = 0;
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[0].length; col++) {
                if (totalDistance[row][col] < 10000) {
                    totalDistanceCount++;
                }
            }
        }

        System.out.println("Part 2 solution: " + totalDistanceCount);
    }

    private int getTotalDistance(int row, int col, int[][] pairs) {
        int totalDistance = 0;

        for (int i = 0; i < pairs.length; i++) {
            int distance = Math.abs(row - pairs[i][0]) + Math.abs(col - pairs[i][1]);
            totalDistance += distance;
        }

        return totalDistance;
    }

    private int getIndexOfClosestPair(int row, int col, int[][] pairs) {

        int indexOfClosest = -1;
        int minDistance = Integer.MAX_VALUE;

        for (int i = 0; i < pairs.length; i++) {

            int distance = Math.abs(row - pairs[i][0]) + Math.abs(col - pairs[i][1]);
            if (distance < minDistance) {
                indexOfClosest = i;
                minDistance = distance;
            } else if (distance == minDistance) {
                indexOfClosest = -1;
            }

        }

        return indexOfClosest;
    }
}
