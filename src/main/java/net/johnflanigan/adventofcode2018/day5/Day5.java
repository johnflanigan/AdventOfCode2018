package net.johnflanigan.adventofcode2018.day5;

import net.johnflanigan.adventofcode2018.Day;

import java.util.*;

public class Day5 extends Day {

    @Override
    public void solve() {

        // Data is assumed to be only one line
        String polymer = readFile("src/main/resources/day5_input.txt").get(0);

        System.out.println("Part 1 solution: " + getReactedPolymerLength(polymer));

        // Reset the list, tracking unique unit types
        Set<Character> unitTypes = new HashSet<>();
        for (int i = 0; i < polymer.length(); i++) {
            unitTypes.add(Character.toLowerCase(polymer.charAt(i)));
        }

        int shortestLength = polymer.length();
        for (Character unitType : unitTypes) {
            String regex = "(" + Character.toLowerCase(unitType) + "|" + Character.toUpperCase(unitType) + ")";
            String updatedPolymer = polymer.replaceAll(regex, "");
            int polymerLength = getReactedPolymerLength(updatedPolymer);
            if (shortestLength > polymerLength) {
                shortestLength = polymerLength;
            }
        }

        System.out.println("Part 2 solution: " + shortestLength);
    }

    private int getReactedPolymerLength(String polymer) {
        List<Character> units = new LinkedList<>();
        for (int i = 0; i < polymer.length(); i++) {
            units.add(polymer.charAt(i));
        }

        int index = 1;
        while (index < units.size()) {
            if (index > 0 && units.get(index) == reverseCase(units.get(index - 1))) {
                units.remove(index - 1);
                units.remove(index - 1);
                index--;
            } else if (index < units.size() - 1 && units.get(index) == reverseCase(units.get(index + 1))) {
                units.remove(index);
                units.remove(index);
            } else {
                index++;
            }
        }

        return units.size();
    }

    private Character reverseCase(Character character) {
        if (Character.isUpperCase(character)) {
            return Character.toLowerCase(character);
        } else {
            return Character.toUpperCase(character);
        }
    }
}
