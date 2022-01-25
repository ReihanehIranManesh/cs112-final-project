import java.awt.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class MazeTwo extends Maze<MazeTwoContent, Integer> {


    public MazeTwo() throws HeadlessException {
        super();
    }

    @Override
    public List<MazeTwoContent> populateContents(Scanner sc) {

        List<MazeTwoContent> contents = new LinkedList<>();
        while (sc.hasNext()) {

            String[] row = sc.next().split(" ");

            for (int i = 0; i < row.length; i++) {

                String[] cols = row[i].split("_");
                int velocity = Integer.parseInt(cols[cols.length - 1]);
                List<String> directions = new LinkedList<>();

                for (int j = 0; j < cols.length - 1; j++) {
                    directions.add(cols[j]);
                }

                MazeTwoContent mazeTwoContent = new MazeTwoContent(velocity, directions);
                System.out.println(mazeTwoContent);
                contents.add(mazeTwoContent);

            }
        }
        return contents;
    }

    @Override
    public String getContentText (MazeTwoContent content) {
        return "" + content;
    }

    @Override
    public Color getContentColor(MazeTwoContent content) {
        return Color.black;
    }

    @Override
    public boolean isAlternateColors() {
        return true;
    }

    @Override
    public void initMaze(String filename) {
        super.initMaze(filename);
        this.mazeState = new MazeTwoState(locationGrid[startRow][startCol], 1);
    }
}