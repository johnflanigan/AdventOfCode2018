package net.johnflanigan.adventofcode2018.day4;

import java.time.LocalDateTime;

public class LogEntry {

    private LocalDateTime dateTime;

    public LogEntry(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }
}
