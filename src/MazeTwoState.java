import java.util.ArrayList;
import java.util.List;

public class MazeTwoState extends State<MazeTwoContent, Integer> {

    public MazeTwoState(Location<MazeTwoContent> userLocation, int velocity) {
        super(userLocation);
        this.info = velocity;
    }

    public List<Transition<MazeTwoContent, Integer>> getTransitions(int mazeRow, int mazeCol, Location<MazeTwoContent>[][] locationsGrid) {

        List<Transition<MazeTwoContent, Integer>> transitions = new ArrayList<>();
        MazeTwoContent curContent = userLocation.getContent();
        int curVelocity = userLocation.getContent().getVelocity();
        for (String direction : curContent.getDirections()) {
            int row = userLocation.getRow();
            int col = userLocation.getCol();
            if (direction.equals("E")) {
                col += curVelocity + this.info;
            } else if (direction.equals("S")) {
                row += curVelocity + this.info;
            } else if (direction.equals("N")) {
                row = row - (curVelocity + this.info);
            } else if (direction.equals("W")) {
                col = col - (curVelocity + this.info);

            } else if (direction.equals("NE")) {
                row = row - (curVelocity + this.info);
                col += curVelocity + this.info;
            } else if (direction.equals("NW")) {
                row = row - (curVelocity + this.info);
                col = col - (curVelocity + this.info);

            } else if (direction.equals("SE")) {
                row += curVelocity + this.info;
                col += curVelocity + this.info;

            } else if (direction.equals("SW")) {
                row += curVelocity + this.info;
                col = col - (curVelocity + this.info);

            } else {
                throw new RuntimeException("direction not supported" + direction);
            }
            if (row < mazeRow && col < mazeCol && row >= 0 && col >= 0) {
                // We should set the content
                MazeTwoTransition transition = new MazeTwoTransition(locationsGrid[row][col]);
                transitions.add(transition);
            }
        }

        return transitions;
    }

    @Override
    public boolean isGameOver() {

        if (this.getUserLocation().getContent().getVelocity() + this.info <= 0) {
            return true;
        }
        return false;
    }
}
