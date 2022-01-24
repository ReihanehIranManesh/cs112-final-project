import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Location<T> {
    private int row;
    private int col;
    private T content;

    protected Location(int row, int col, T content) {
        this.row = row;
        this.col = col;
        this.content = content;

    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public T getContent() {
        return content;
    }

    public boolean isGoal() {
        return false;
    }

    public boolean isStart() {
        return false;
    }

    @Override
    public String toString() {
        return "Location{" +
                "row=" + row +
                ", col=" + col +
                ", content=" + content +
                '}';
    }


    public static <T> Location<T>[][] createLocationGrid(int mazeRow, int mazeCol, List<T> contents, int startRow, int startCol, int goalRow, int goalCol) {
        Location<T>[][] locationsGrid = new Location[mazeRow][mazeCol];
        for (int i = 0; i < mazeRow; i++) {
            for (int j = 0; j < mazeCol; j++) {
                if (i == startRow && j == startCol) {
                    locationsGrid[i][j] = new StartLocation(i, j, contents.remove(0));
                    continue;
                }
                if (i == goalRow && j == goalCol) {
                    locationsGrid[i][j] = new GoalLocation(i, j, contents.remove(0));
                    continue;
                }
                locationsGrid[i][j] = new Location(i, j, contents.remove(0));
            }
        }

        return locationsGrid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Location location = (Location) o;
        return row == location.row && col == location.col && content == location.content;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, col, content);
    }
}
