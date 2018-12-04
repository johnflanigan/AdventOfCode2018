package net.johnflanigan.adventofcode2018.day4;

import java.time.LocalDateTime;

public class ShiftStart extends LogEntry {

    private int id;

    public ShiftStart(LocalDateTime dateTime, int id) {
        super(dateTime);
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
