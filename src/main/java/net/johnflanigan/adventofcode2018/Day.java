package net.johnflanigan.adventofcode2018;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public abstract class Day {

    public abstract void solve();

    public List<String> readFile(String fileName) {
        List<String> lines = new LinkedList<>();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));

            String currentLine = reader.readLine();
            while (currentLine != null) {
                lines.add(currentLine);
                currentLine = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return lines;
    }
}
