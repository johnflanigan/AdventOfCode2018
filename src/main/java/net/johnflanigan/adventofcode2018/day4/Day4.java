package net.johnflanigan.adventofcode2018.day4;

import net.johnflanigan.adventofcode2018.Day;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day4 extends Day {

    public void solve() {

        String file = "src/main/resources/day4_input.txt";
        List<String> inputs = readFile(file);

        List<LogEntry> entries = new ArrayList<>();
        for (String input : inputs) {
            // convert input from String to LogEntry
            LogEntry entry = convertInputToLogEntry(input);
            entries.add(entry);
        }

        Comparator<LogEntry> logEntryComparator = new Comparator<LogEntry>() {
            @Override
            public int compare(LogEntry entry1, LogEntry entry2) {
                return entry1.getDateTime().compareTo(entry2.getDateTime());
            }
        };

        entries.sort(logEntryComparator);

        int currentId = 0;
        int minuteFellAsleep = 0;
        Map<Integer, int[]> sleepCountById = new HashMap<>();

        for (LogEntry entry : entries) {
            if (entry instanceof ShiftStart) {
                currentId = ((ShiftStart) entry).getId();
                if (!sleepCountById.containsKey(currentId)) {
                    sleepCountById.put(currentId, new int[60]);
                }
            } else if (entry instanceof FallAsleep) {
                minuteFellAsleep = entry.getDateTime().getMinute();
            } else {
                int minuteWokeUp = entry.getDateTime().getMinute();
                int[] updatedCount = updateSleepCount(sleepCountById.get(currentId), minuteFellAsleep, minuteWokeUp);
                sleepCountById.put(currentId, updatedCount);
            }
        }

        // Find guard that slept the most
        int maxId = 0;
        int maxSleepCount = 0;
        for (Map.Entry<Integer, int[]> entry : sleepCountById.entrySet()) {
            int id = entry.getKey();
            int[] sleepCount = entry.getValue();
            int minutesSpentSleeping = 0;

            for (int minute = 0; minute < 60; minute++) {
                minutesSpentSleeping += sleepCount[minute];
            }

            if (minutesSpentSleeping > maxSleepCount) {
                maxId = id;
                maxSleepCount = minutesSpentSleeping;
            }
        }

        // Find minute that guard spent most sleeping
        int maxMinute = 0;
        int[] sleepCount = sleepCountById.get(maxId);
        for (int minute = 0; minute < 60; minute++) {
            if (sleepCount[minute] > sleepCount[maxMinute]) {
                maxMinute = minute;
            }
        }

        System.out.println("Part 1 solution: " + maxId * maxMinute);

        // Find the max minute across all guards
        int overallMaxId = maxId;
        int overallMaxMinute = maxMinute;

        for (Map.Entry<Integer, int[]> entry : sleepCountById.entrySet()) {
            int id = entry.getKey();
            int[] currentSleepCount = entry.getValue();

            for (int minute = 0; minute < 60; minute++) {
                if (currentSleepCount[minute] > sleepCountById.get(overallMaxId)[overallMaxMinute]) {
                    overallMaxId = id;
                    overallMaxMinute = minute;
                }
            }
        }

        System.out.println("Part 2 solution: " + overallMaxId * overallMaxMinute);
    }

    private int[] updateSleepCount(int[] sleepCount, int minuteFellAsleep, int minuteWokeUp) {
        for (int minute = minuteFellAsleep; minute < minuteWokeUp; minute++) {
            sleepCount[minute]++;
        }
        return sleepCount;
    }

    private LogEntry convertInputToLogEntry(String input) {

        String regex = "";

        if (input.contains("begins")) {
            regex = "\\[(\\d{4})-(\\d{2})-(\\d{2}) (\\d{2}):(\\d{2})] Guard #(\\d+) begins shift";
        } else if (input.contains("falls")) {
            regex = "\\[(\\d{4})-(\\d{2})-(\\d{2}) (\\d{2}):(\\d{2})] falls asleep";
        } else if (input.contains("wakes")) {
            regex = "\\[(\\d{4})-(\\d{2})-(\\d{2}) (\\d{2}):(\\d{2})] wakes up";
        }

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);

        if (matcher.find()) {

            int year = Integer.parseInt(matcher.group(1));
            int month = Integer.parseInt(matcher.group(2));
            int day = Integer.parseInt(matcher.group(3));
            int hour = Integer.parseInt(matcher.group(4));
            int minute = Integer.parseInt(matcher.group(5));
            LocalDateTime dateTime = LocalDateTime.of(year, Month.of(month), day, hour, minute);

            if (input.contains("begins")) {
                return new ShiftStart(dateTime, Integer.parseInt(matcher.group(6)));
            } else if (input.contains("falls")) {
                return new FallAsleep(dateTime);
            } else if (input.contains("wakes")) {
                return new WakeUp(dateTime);
            }

        }

        return null;
    }

}
