package net.johnflanigan.adventofcode2018.day3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day3 {

    class Claim {
        int id;
        int col;
        int row;
        int width;
        int height;

        private Claim() {

        }

        private Claim(int id, int col, int row, int width, int height) {
            this.id = id;
            this.col = col;
            this.row = row;
            this.width = width;
            this.height = height;
        }

        @Override
        public String toString() {
            return "Claim{" +
                    "id=" + id +
                    ", col=" + col +
                    ", row=" + row +
                    ", width=" + width +
                    ", height=" + height +
                    '}';
        }
    }

    public void day3() {

        int[][] fabric = new int[1000][1000];

        String file = "src/main/resources/day3_input.txt";
        List<String> inputs = new LinkedList<>();

        /* save the input to a list */
        // TODO these lines could probably be done in one step
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));

            String currentLine = reader.readLine();
            while (currentLine != null) {
                inputs.add(currentLine);
                currentLine = reader.readLine();
            }
        } catch (
                IOException e) {
            e.printStackTrace();
        }

        List<Claim> claims = new LinkedList<>();
        for (String input : inputs) {
            claims.add(convertInputToClaim(input));
        }

        // count how many squares used on each pass
        for (Claim claim : claims) {
            updateFabric(fabric, claim);
        }

        // find claim that does not overlap
        for (Claim claim : claims) {
            if (!overlap(fabric, claim)) {
                System.out.println(claim.id);
            }
        }

    }

    private boolean overlap(int[][] fabric, Claim claim) {
        int currentRow = claim.row;

        // for every row of the claim
        for (int i = 0; i < claim.height; i++) {
            // get the left-most column of claim
            int currentCol = claim.col;

            // for every column of the claim, check if there is overlap
            for (int j = 0; j < claim.width; j++) {
                if (fabric[currentCol][currentRow] > 1) {
                    return true;
                }
                currentCol++;
            }
            currentRow++;
        }

        return false;
    }

    private void updateFabric(int[][] fabric, Claim claim) {
        int currentRow = claim.row;

        // for every row of the claim
        for (int i = 0; i < claim.height; i++) {
            // get the left-most column of claim
            int currentCol = claim.col;

            // for every column of the claim, increment the count of overlaps
            for (int j = 0; j < claim.width; j++) {
                fabric[currentCol][currentRow]++;
                currentCol++;
            }
            currentRow++;
        }
    }

    // input format is: #(id) @ (col),(row): (width)x(height)
    public Claim convertInputToClaim(String input) {
        String regex = "#(\\d+) @ (\\d+),(\\d+): (\\d+)x(\\d+)";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        Claim claim = new Claim();
        if (matcher.find()) {
            claim.id = Integer.parseInt(matcher.group(1));
            claim.col = Integer.parseInt(matcher.group(2));
            claim.row = Integer.parseInt(matcher.group(3));
            claim.width = Integer.parseInt(matcher.group(4));
            claim.height = Integer.parseInt(matcher.group(5));
        }
        return claim;
    }

}
