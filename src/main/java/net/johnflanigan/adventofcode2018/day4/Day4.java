package net.johnflanigan.adventofcode2018.day4;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day4 {

    public void solve() {

        String file = "src/main/resources/day4_input.txt";
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
        // [yyyy-MM-dd hh:mm] Guard #(id) begins shift
        String startShiftRegex = "\\[(\\d{4})-(\\d{2})-(\\d{2}) (\\d{2}):(\\d{2})] Guard #(\\d+) begins shift";
        Pattern startShiftPattern = Pattern.compile(startShiftRegex);
        Matcher startShiftMatcher = startShiftPattern.matcher(input);
        if (startShiftMatcher.find()) {
            int year = Integer.parseInt(startShiftMatcher.group(1));
            int month = Integer.parseInt(startShiftMatcher.group(2));
            int day = Integer.parseInt(startShiftMatcher.group(3));
            int hour = Integer.parseInt(startShiftMatcher.group(4));
            int minute = Integer.parseInt(startShiftMatcher.group(5));
            LocalDateTime dateTime = LocalDateTime.of(year, Month.of(month), day, hour, minute);
            return new ShiftStart(dateTime, Integer.parseInt(startShiftMatcher.group(6)));
        }

        // [yyyy-MM-dd hh:mm] falls asleep
        String fallAsleepRegex = "\\[(\\d{4})-(\\d{2})-(\\d{2}) (\\d{2}):(\\d{2})] falls asleep";
        Pattern fallAsleepPattern = Pattern.compile(fallAsleepRegex);
        Matcher fallAsleepMatcher = fallAsleepPattern.matcher(input);
        if (fallAsleepMatcher.find()) {
            int year = Integer.parseInt(fallAsleepMatcher.group(1));
            int month = Integer.parseInt(fallAsleepMatcher.group(2));
            int day = Integer.parseInt(fallAsleepMatcher.group(3));
            int hour = Integer.parseInt(fallAsleepMatcher.group(4));
            int minute = Integer.parseInt(fallAsleepMatcher.group(5));
            LocalDateTime dateTime = LocalDateTime.of(year, Month.of(month), day, hour, minute);
            return new FallAsleep(dateTime);
        }

        // [yyyy-MM-dd hh:mm] wakes up
        String wakeUpRegex = "\\[(\\d{4})-(\\d{2})-(\\d{2}) (\\d{2}):(\\d{2})] wakes up";
        Pattern wakeUpPattern = Pattern.compile(wakeUpRegex);
        Matcher wakeUpMatcher = wakeUpPattern.matcher(input);
        if (wakeUpMatcher.find()) {
            int year = Integer.parseInt(wakeUpMatcher.group(1));
            int month = Integer.parseInt(wakeUpMatcher.group(2));
            int day = Integer.parseInt(wakeUpMatcher.group(3));
            int hour = Integer.parseInt(wakeUpMatcher.group(4));
            int minute = Integer.parseInt(wakeUpMatcher.group(5));
            LocalDateTime dateTime = LocalDateTime.of(year, Month.of(month), day, hour, minute);
            return new WakeUp(dateTime);
        }

        return null;
    }

}
