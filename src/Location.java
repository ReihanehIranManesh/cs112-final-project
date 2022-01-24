import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Location {
    private int row;
    private int col;
    private int content;
    private List<Transition> transitions;

    protected Location(int row, int col, int content) {
        this.row = row;
        this.col = col;
        this.content = content;
        this.transitions = new ArrayList<>();

    }

    public void setTransitions(List<Transition> transitions) {
        this.transitions = transitions;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public int getContent() {
        return content;
    }

    public List<Transition> getTransitions() {
        return transitions;
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
                ", transitions=" + transitions +
                '}';
    }


    public String toStringNoTransition() {
        return "Location{" +
                "row=" + row +
                ", col=" + col +
                ", content=" + content +
                '}';
    }

    private static void populateTransitions(int mazeRow, int mazeCol, Location loc, Location[][] locationsGrid) {
        List<Transition> transitions = new ArrayList<>();
        int rowRight = loc.getRow();
        int colRight = loc.getCol() + loc.getContent();
        if (colRight < mazeCol) {
            Transition rightTransition = new Transition(locationsGrid[rowRight][colRight]);
            transitions.add(rightTransition);
        }

        int rowLeft = loc.getRow();
        int colLeft = loc.getCol() - loc.getContent();
        if (colLeft >= 0) {
            Transition leftTransition = new Transition(locationsGrid[rowLeft][colLeft]);
            transitions.add(leftTransition);

        }


        int rowUp = loc.getRow() - loc.getContent();
        int colUp = loc.getCol();
        if (rowUp >= 0) {
            Transition upTransition = new Transition(locationsGrid[rowUp][colUp]);
            transitions.add(upTransition);
        }

        int rowDown = loc.getRow() + loc.getContent();
        int colDown = loc.getCol();
        if (rowDown < mazeRow) {
            Transition downTransition = new Transition(locationsGrid[rowDown][colDown]);
            transitions.add(downTransition);

        }
        loc.setTransitions(transitions);
    }

    public static Location[][] createLocationGrid(int mazeRow, int mazeCol, List<Integer> contents, int startRow, int startCol, int goalRow, int goalCol) {
        Location[][] locationsGrid = new Location[mazeRow][mazeCol];
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

        for (int i = 0; i < mazeRow; i++) {
            for (int j = 0; j < mazeCol; j++) {
                Location loc = locationsGrid[i][j];
                populateTransitions(mazeRow, mazeCol, loc, locationsGrid);
            }
        }
        return locationsGrid;
    }
}
