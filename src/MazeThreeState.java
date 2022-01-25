import java.util.ArrayList;
import java.util.List;

public class MazeThreeState extends State<MazeThreeContent, Character> {

    public MazeThreeState(Location<MazeThreeContent> userLocation, char color) {
        super(userLocation);
        this.info = color;
    }

    public MazeThreeState(Location<MazeThreeContent> userLocation) {
        super(userLocation);
        this.info = userLocation.getContent().getColor();
    }

    public List<Transition<MazeThreeContent, Character>> getTransitions(int mazeRow, int mazeCol, Location<MazeThreeContent>[][] locationsGrid) {

        List<Transition<MazeThreeContent, Character>> transitions = new ArrayList<>();
        MazeThreeContent curContent = userLocation.getContent();

        char curColor = userLocation.getContent().getColor();
        String direction = curContent.getDirection();

        final int row = userLocation.getRow();
        final int col = userLocation.getCol();

        for (int i = 1; i <= mazeCol; i++) {
            int newrow = row;
            int newcol = col;
            if (direction.equals("NE") || direction.equals("SE") || direction.equals("E")) {
                newcol = col + i;
            }
            if (direction.equals("NW") || direction.equals("SW") || direction.equals("W")) {
                newcol = col - i;
            }

            if (direction.equals("NE") || direction.equals("NW") || direction.equals("N")) {
                newrow = row - i;
            }

            if (direction.equals("SE") || direction.equals("SW") || direction.equals("S")) {
                newrow = row + i;
            }

            if (newrow < mazeRow && newrow >= 0 && newcol < mazeCol && newcol >= 0) {
                if (locationsGrid[newrow][newcol].getContent().getColor() != curColor) {
                    MazeThreeTransition transition = new MazeThreeTransition(locationsGrid[newrow][newcol]);
                    transitions.add(transition);
                }
                if (locationsGrid[newrow][newcol].isGoal()) {
                    MazeThreeTransition transition = new MazeThreeTransition(locationsGrid[newrow][newcol]);
                    transitions.add(transition);
                }
            }
        }

        return transitions;
    }

    @Override
    public boolean isGameOver() {

        return false;
    }
}
