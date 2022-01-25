import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class MazeOne extends Maze<Integer, Void> {


    public MazeOne() throws HeadlessException {
        super();
    }

    @Override
    public List<Integer> populateContents(Scanner sc) {

        List<Integer> contents = new LinkedList<>();
        while (sc.hasNextInt()) {

            contents.add(sc.nextInt());
        }
        return contents;
    }

    @Override
    public String getContentText (Integer content) {
        return "" + content;
    }

    @Override
    public Color getContentColor(Integer content) {
        return Color.black;
    }

    @Override
    public boolean isAlternateColors() {
        return true;
    }

    @Override
    public void initMaze(String filename) {
        super.initMaze(filename);
        this.mazeState = new MazeOneState(locationGrid[startRow][startCol]);

    }
}