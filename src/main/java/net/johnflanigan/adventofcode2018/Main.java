package net.johnflanigan.adventofcode2018;

import net.johnflanigan.adventofcode2018.day1.Day1;
import net.johnflanigan.adventofcode2018.day2.Day2;
import net.johnflanigan.adventofcode2018.day3.Day3;

public class Main {

    private static final int DAY = 3;

    public static void main(String[] args) {

        switch(DAY) {
            case 1:
                Day1 day1 = new Day1();
                day1.day1();
            case 2:
                Day2 day2 = new Day2();
                day2.day2();
            case 3:
                Day3 day3 = new Day3();
                day3.day3();
        }

    }
}
