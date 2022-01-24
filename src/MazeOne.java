import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class MazeOne extends Maze<Integer> {


    public MazeOne() throws HeadlessException {

        this.mazeState = new MazeOneState(locationGrid[startRow][startCol]);
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



}