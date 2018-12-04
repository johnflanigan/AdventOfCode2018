package net.johnflanigan.adventofcode2018.day2;

import net.johnflanigan.adventofcode2018.Day;

import java.util.LinkedList;
import java.util.List;

public class Day2 extends Day {

    public void solve() {

        String file ="src/main/resources/day2_input.txt";
        List<String> ids = readFile(file);

        List<char[]> candidates = new LinkedList<>();
        int twoCount = 0;
        int threeCount = 0;

        /* process each id */
        for (String id : ids) {
            char[] charArray = id.toCharArray();
            char[] letterCounts = new char[26];
            for (int i = 0; i < charArray.length; i++) {
                int charAsIndex = (int) charArray[i] - 97; // assumes input ids are lowercase
                letterCounts[charAsIndex]++;
            }

            boolean twoFlag = false;
            boolean threeFlag = false;
            for (int i = 0; i < letterCounts.length; i++) {
                // check for exactly two of any letter
                if (letterCounts[i] == 2) {
                    twoFlag = true;
                }

                // check for exactly three of any letter
                if (letterCounts[i] == 3) {
                    threeFlag = true;
                }
            }

            if (twoFlag) {
                twoCount++;
            }
            if (threeFlag) {
                threeCount++;
            }

            if (twoFlag || threeFlag) {
                candidates.add(charArray);
            }

        }

        // FIXME brute force
        for (char[] current : candidates) {
            for (char[] other : candidates) {
                int differences = 0;
                // for each letter in the ids
                for (int i = 0; i < current.length; i++) {
                    if (current[i] != other[i]) {
                        differences++;
                    }
                }

                if (differences == 1) {
                    // print only matching characters
                    for (int i = 0; i < current.length; i++) {
                        if (current[i] == other[i])
                        System.out.print(current[i]);
                    }
                    System.out.println();
                }
            }
        }

        System.out.println(String.valueOf(twoCount * threeCount));

    }
}
