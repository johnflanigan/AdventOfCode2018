package net.johnflanigan.adventofcode2018;

import net.johnflanigan.adventofcode2018.day1.Day1;
import net.johnflanigan.adventofcode2018.day2.Day2;
import net.johnflanigan.adventofcode2018.day3.Day3;
import net.johnflanigan.adventofcode2018.day4.Day4;
import net.johnflanigan.adventofcode2018.day5.Day5;
import net.johnflanigan.adventofcode2018.day6.Day6;
import net.johnflanigan.adventofcode2018.day7.Day7;
import net.johnflanigan.adventofcode2018.day8.Day8;
import net.johnflanigan.adventofcode2018.day9.Day9;

public class Main {

    private static final int DAY = 9;

    public static void main(String[] args) {

        Day day = null;

        switch(DAY) {
            case 1:
                day = new Day1();
                break;
            case 2:
                day = new Day2();
                break;
            case 3:
                day = new Day3();
                break;
            case 4:
                day = new Day4();
                break;
            case 5:
                day = new Day5();
                break;
            case 6:
                day = new Day6();
                break;
            case 7:
                day = new Day7();
                break;
            case 8:
                day = new Day8();
                break;
            case 9:
                day = new Day9();
                break;
        }

        if (day != null) {
            day.solve();
        }

    }
}
