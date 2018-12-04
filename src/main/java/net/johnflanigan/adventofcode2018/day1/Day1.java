package net.johnflanigan.adventofcode2018.day1;

import net.johnflanigan.adventofcode2018.Day;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class Day1 extends Day {

    public void solve() {

        String file ="src/main/resources/day1_input.txt";
        List<String> inputs = readFile(file);

        List<Integer> changes = new LinkedList<>();
        for (String input : inputs) {
            changes.add(Integer.parseInt(input));
        }

        /* repeat until a duplicate frequency appears */
        Set<Integer> frequencies = new HashSet<>();
        int frequency = 0;
        while (true) {
            for (Integer change : changes) {
                frequency += change;
                if (frequencies.contains(frequency)) {
                    System.out.println(String.valueOf(frequency));
                    return;
                } else {
                    frequencies.add(frequency);
                }
            }
        }

    }

}
