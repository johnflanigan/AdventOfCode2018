package net.johnflanigan.adventofcode2018.day9;

import net.johnflanigan.adventofcode2018.Day;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day9 extends Day {

    private class CircularQueue<T> extends LinkedList<T> {
        /**
         * @param turns positive turn to go clockwise, negative turn to go counterclockwise
         */
        private void rotate(int turns) {
            if (turns > 0) {
                for (int i = 0; i < turns; i++) {
                    T val = this.removeLast();
                    this.addFirst(val);
                }
            } else if (turns < 0) {
                for (int i = 0; i < Math.abs(turns); i++) {
                    T val = this.removeFirst();
                    this.addLast(val);
                }
            }
        }
    }

    @Override
    public void solve() {
        String file = "src/main/resources/day9_input.txt";
        String input = readFile(file).get(0);

        String regex = "(\\d+) players; last marble is worth (\\d+) points";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);

        if (matcher.find()) {
            int numberOfPlayers = Integer.parseInt(matcher.group(1));
            int numberOfMarbles = Integer.parseInt(matcher.group(2));

            System.out.println("Part 1 solution: " + game(numberOfPlayers, numberOfMarbles));
            System.out.println("Part 2 solution: " + game(numberOfPlayers, numberOfMarbles * 100));
        }
    }

    private long game(int numberOfPlayers, int numberOfMarbles) {

        long[] playerScores = new long[numberOfPlayers];

        CircularQueue<Integer> circularQueue = new CircularQueue<>();
        circularQueue.add(0);

        int currentPlayer = 0;

        for (int currentMarble = 1; currentMarble <= numberOfMarbles; currentMarble++) {
            // Is current marble a multiple of 23
            if (currentMarble % 23 == 0) {
                // Move index seven counter-clockwise
                circularQueue.rotate(-7);
                long removedMarble = circularQueue.removeLast();
                playerScores[currentPlayer] = playerScores[currentPlayer] + currentMarble + removedMarble;
            } else {
                // Move current index two clockwise
                circularQueue.rotate(2);
                circularQueue.addLast(currentMarble);
            }
            // Update current player
            currentPlayer++;
            if (currentPlayer == numberOfPlayers) {
                currentPlayer = 0;
            }
        }

        return Arrays.stream(playerScores).max().getAsLong();
    }
}
