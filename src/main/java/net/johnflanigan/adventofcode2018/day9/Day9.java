package net.johnflanigan.adventofcode2018.day9;

import net.johnflanigan.adventofcode2018.Day;

import java.util.*;

public class Day9 extends Day {

    private static final int NUMBER_OF_PLAYERS = 476;
    private static final long LAST_MARBLE = 71431 * 100;
//    private static final int NUMBER_OF_PLAYERS = 9;
//    private static final int LAST_MARBLE = 25;

    LinkedList<Long> circle;

    @Override
    public void solve() {

//        String file = "src/main/resources/day9_input.txt";
//        List<String> inputs = readFile(file);

        long[] playerScores = new long[NUMBER_OF_PLAYERS];

        circle = new LinkedList<>();
        circle.add(0L);

        long currentMarble = 1;
        int currentPlayer = 0;

        while (currentMarble <= LAST_MARBLE) {
            // Is current marble a multiple of 23
            if (currentMarble % 23 == 0) {
                // Move index seven counter-clockwise
                rotate(-7);
                long removedMarble = circle.removeLast();
                playerScores[currentPlayer] = playerScores[currentPlayer] + currentMarble + removedMarble;
            } else {
                // Move current index two clockwise
                rotate(2);
                circle.addLast(currentMarble);
            }
            currentMarble++;
            // Update current player
            currentPlayer++;
            if (currentPlayer == NUMBER_OF_PLAYERS) {
                currentPlayer = 0;
            }
        }

        long highScore = 0;
        for (long score : playerScores) {
            System.out.println(score);
            if (score > highScore) {
                highScore = score;
            }
        }

        System.out.println("Part 1 solution: " + highScore);
    }

    /**
     *
     * @param turns positive turn to go clockwise, negative turn to go counterclockwise
     */
    private void rotate(int turns) {
        if (turns > 0) {
            for (int i = 0; i < turns; i++) {
                long val = circle.removeLast();
                circle.addFirst(val);
            }
        } else if (turns < 0) {
            for (int i = 0; i < Math.abs(turns); i++) {
                long val = circle.removeFirst();
                circle.addLast(val);
            }
        }

    }
}
