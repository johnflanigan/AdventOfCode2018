package net.johnflanigan.adventofcode2018.day5;

import net.johnflanigan.adventofcode2018.Day;

import java.util.*;
import java.util.function.Supplier;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Day5 extends Day {

    @Override
    public void solve() {
        
        // Data is assumed to be only one line
        String polymer = readFile("src/main/resources/day5_input.txt").get(0);

        // Convert string to stream
        List<Character> units = new LinkedList<>();
        for (int i = 0; i < polymer.length(); i++) {
            units.add(polymer.charAt(i));
        }
        Supplier<Stream<Character>> streamSupplier = units::stream;

        System.out.println("Part 1 solution: " + getReactedPolymerLengthStream(streamSupplier.get()));

        // Reset the list, tracking unique unit types
        Set<Character> unitTypes = new HashSet<>();
        for (int i = 0; i < polymer.length(); i++) {
            unitTypes.add(Character.toLowerCase(polymer.charAt(i)));
        }

        int shortestLength = polymer.length();
        for (Character unitType : unitTypes) {
            Stream<Character> polymerStream = streamSupplier.get();
            Stream<Character> updatedPolymer = polymerStream.filter(character -> Character.toLowerCase(character) != unitType);

            int polymerLength = getReactedPolymerLengthStream(updatedPolymer);
            if (shortestLength > polymerLength) {
                shortestLength = polymerLength;
            }
        }

        System.out.println("Part 2 solution: " + shortestLength);
    }

    private int getReactedPolymerLengthStream(Stream<Character> polymer) {
        Deque<Character> stack = new LinkedList<>();

        polymer.forEach((character -> {
            if (reverseCase(character).equals(stack.peek())) {
                stack.pop();
            } else {
                stack.push(character);
            }
        }));

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
