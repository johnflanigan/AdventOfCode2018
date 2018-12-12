package net.johnflanigan.adventofcode2018.day9;

import net.johnflanigan.adventofcode2018.Day;

import java.util.*;

public class Day9Backup extends Day {

    //    private static final int NUMBER_OF_PLAYERS = 476;
//    private static final int LAST_MARBLE = 71431 * 100;
    private static final int NUMBER_OF_PLAYERS = 9;
    private static final int LAST_MARBLE = 25;

    @Override
    public void solve() {

//        String file = "src/main/resources/day9_input.txt";
//        List<String> inputs = readFile(file);

        int[] playerScores = new int[NUMBER_OF_PLAYERS];

        List<Integer> circle = new LinkedList<>();
        circle.add(0);

//        int currentIndex = 0;
        int currentMarble = 1;
        int currentPlayer = 0;
        ListIterator<Integer> iterator = circle.listIterator();

        while (currentMarble <= LAST_MARBLE) {
            // Is current marble a multiple of 23
            if (currentMarble % 23 == 0) {
                // Move iterator seven positions counter-clockwise
                for (int i = 0; i < 7; i++) {
                    if (iterator.previousIndex() == -1) {
                        iterator = circle.listIterator(circle.size() - 1);
                    } else {
                        iterator.previous();
                    }
                }
                int removedMarble = iterator.next();
                iterator.remove();
//                currentIndex = currentIndex - 7;
//                if (currentIndex < 0) {
//                    currentIndex = currentIndex + circle.size();
//                }
//                int removedMarble = circle.remove(currentIndex);
                playerScores[currentPlayer] = playerScores[currentPlayer] + currentMarble + removedMarble;
            } else {
                // Move iterator two positions clockwise
                for (int i = 0; i < 2; i++) {
                    if (iterator.hasNext()) {
                        iterator.next();
                    } else {
                        iterator = circle.listIterator();
                    }
                }
                iterator.add(currentMarble);
                iterator.previous();

//                currentIndex = currentIndex + 2;
//                if (currentIndex >= circle.size()) {
//                    currentIndex = currentIndex % circle.size();
//                }
//
//                circle.add(currentIndex, currentMarble);
            }

            currentMarble++;
            // Update current player
            currentPlayer++;
            if (currentPlayer == NUMBER_OF_PLAYERS) {
                currentPlayer = 0;
            }
        }

        int highScore = 0;
        for (int score : playerScores) {
            if (score > highScore) {
                highScore = score;
            }
        }

        System.out.println("Part 1 solution: " + highScore);
    }
}
