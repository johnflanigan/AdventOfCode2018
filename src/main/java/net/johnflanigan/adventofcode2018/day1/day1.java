package net.johnflanigan.adventofcode2018.day1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class day1 {

    public static String day1() {

        String file ="src/main/resources/day1_input.txt";
        List<Integer> changes = new LinkedList<>();

        /* save the input to a list */
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));

            String currentLine = reader.readLine();
            while (currentLine != null) {
                int change = Integer.parseInt(currentLine);
                changes.add(change);
                currentLine = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        /* repeat until a duplicate frequency appears */
        Set<Integer> frequencies = new HashSet<>();
        int frequency = 0;
        while (true) {
            for (Integer change : changes) {
                frequency += change;
                if (frequencies.contains(frequency)) {
                    return String.valueOf(frequency);
                } else {
                    frequencies.add(frequency);
                }
            }
        }

    }

}
