import java.awt.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class MazeThree extends Maze<MazeThreeContent, Character> {

    static Map<String, Character> arrowMapping = Map.of(
            "W", '\u21d0',
            "N", '\u21d1',
            "E", '\u21d2',
            "S", '\u21d3',
            "NW", '\u21d6',
            "NE", '\u21d7',
            "SE", '\u21d8',
            "SW", '\u21d9'

    );
    public MazeThree() throws HeadlessException {
        super();
    }

    @Override
    public List<MazeThreeContent> populateContents(Scanner sc) {

        List<MazeThreeContent> contents = new LinkedList<>();
        while (sc.hasNext()) {

            String[] row = sc.next().split(" ");

            for (int i = 0; i < row.length; i++) {

                String[] cols = row[i].split("_");

                String direction = cols[0];
                char color = cols[1].charAt(0);
                MazeThreeContent mazeThreeContent = new MazeThreeContent(color, direction);
                System.out.println(mazeThreeContent);
                contents.add(mazeThreeContent);
            }
        }
        return contents;
    }

    @Override
    public String getContentText (MazeThreeContent content) {


        return "" + MazeThree.arrowMapping.get(content.getDirection());
    }

    @Override
    public Color getContentColor(MazeThreeContent content) {
        return content.getColor() == 'R' ? Color.red : Color.blue;
    }

    @Override
    public boolean isAlternateColors() {
        return false;
    }

    @Override
    public void initMaze(String filename) {
        super.initMaze(filename);
        this.mazeState = new MazeThreeState(locationGrid[startRow][startCol]);
    }
}