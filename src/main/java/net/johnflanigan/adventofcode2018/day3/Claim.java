package net.johnflanigan.adventofcode2018.day3;

public class Claim {

    int id;
    int col;
    int row;
    int width;
    int height;

    public Claim() {

    }

    public Claim(int id, int col, int row, int width, int height) {
        this.id = id;
        this.col = col;
        this.row = row;
        this.width = width;
        this.height = height;
    }

    @Override
    public String toString() {
        return "Claim{" +
                "id=" + id +
                ", col=" + col +
                ", row=" + row +
                ", width=" + width +
                ", height=" + height +
                '}';
    }
}