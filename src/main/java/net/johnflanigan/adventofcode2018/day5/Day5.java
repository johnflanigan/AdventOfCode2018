package net.johnflanigan.adventofcode2018.day5;

import net.johnflanigan.adventofcode2018.Day;

import java.util.*;
import java.util.stream.Stream;

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

        Deque<Character> stack = new LinkedList<>();
        for (Character unit : units) {
            if (reverseCase(unit).equals(stack.peek())) {
                stack.pop();
            } else {
                stack.push(unit);
            }
        }

        return stack.size();
    }

    private Character reverseCase(Character character) {
        if (Character.isUpperCase(character)) {
            return Character.toLowerCase(character);
        } else {
            return Character.toUpperCase(character);
        }
    }
}
