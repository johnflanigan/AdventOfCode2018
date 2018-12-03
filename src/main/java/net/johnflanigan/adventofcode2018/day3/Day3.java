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
        int x;
        int y;
        int width;
        int height;

        private Claim() {

        }

        private Claim(int id, int x, int y, int width, int height) {
            this.id = id;
            this.x = x;
            this.y = y;
            this.width = width;
            this.height = height;
        }

        @Override
        public String toString() {
            return "Claim{" +
                    "id=" + id +
                    ", x=" + x +
                    ", y=" + y +
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

        int count = 0;
        for (int i = 0; i < 1000; i++) {
            for (int j = 0; j < 1000; j++) {
                if (fabric[i][j] > 1) {
                    count++;
                }
            }
        }

        System.out.println(count);

    }

    // TODO fix index naming, function is confusing
    private boolean overlap(int[][] fabric, Claim claim) {

        int currentY = claim.y;

        for (int i = 0; i < claim.height; i++) {
            int currentX = claim.x;

            for (int j = 0; j < claim.width; j++) {
                if (fabric[currentX][currentY] > 1) {
                    return true;
                }
                currentX++;
            }
            currentY++;
        }

        return false;
    }

    // TODO fix index naming, function is confusing
    private void updateFabric(int[][] fabric, Claim claim) {
        int currentY = claim.y;

        for (int i = 0; i < claim.height; i++) {
            int currentX = claim.x;

            for (int j = 0; j < claim.width; j++) {
                fabric[currentX][currentY]++;
                currentX++;
            }
            currentY++;
        }
    }

    public Claim convertInputToClaim(String input) {
        String regex = "#(\\d+) @ (\\d+),(\\d+): (\\d+)x(\\d+)";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        Claim claim = new Claim();
        if (matcher.find()) {
            claim.id = Integer.parseInt(matcher.group(1));
            claim.x = Integer.parseInt(matcher.group(2));
            claim.y = Integer.parseInt(matcher.group(3));
            claim.width = Integer.parseInt(matcher.group(4));
            claim.height = Integer.parseInt(matcher.group(5));
        }
        return claim;
    }

}
