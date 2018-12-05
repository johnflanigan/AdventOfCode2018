package net.johnflanigan.adventofcode2018;

import net.johnflanigan.adventofcode2018.day1.Day1;
import net.johnflanigan.adventofcode2018.day2.Day2;
import net.johnflanigan.adventofcode2018.day3.Day3;
import net.johnflanigan.adventofcode2018.day4.Day4;
import net.johnflanigan.adventofcode2018.day5.Day5;

public class Main {

    private static final int DAY = 4;

    public static void main(String[] args) {

        Day day = null;

        switch(DAY) {
            case 1:
                day = new Day1();
            case 2:
                day = new Day2();
            case 3:
                day = new Day3();
            case 4:
                day = new Day4();
            case 5:
                day = new Day5();
        }

        if (day != null) {
            day.solve();
        }

    }
}
