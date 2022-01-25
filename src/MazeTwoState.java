import java.util.ArrayList;
import java.util.List;

public class MazeTwoState extends State<MazeTwoContent> {

    public int getVelocity() {
        return velocity;
    }

    private int velocity;
    public MazeTwoState(Location<MazeTwoContent> userLocation, int velocity) {
        super(userLocation);
        this.velocity = velocity;
    }

    public List<Transition<MazeTwoContent>> getTransitions(int mazeRow, int mazeCol, Location<MazeTwoContent>[][] locationsGrid) {

        List<Transition<MazeTwoContent>> transitions = new ArrayList<>();
        MazeTwoContent curContent = userLocation.getContent();
        int curVelocity = userLocation.getContent().getVelocity();
        for (String direction : curContent.getDirections()) {
            int row = userLocation.getRow();
            int col = userLocation.getCol();
            if (direction.equals("S")) {
                col += curVelocity + this.velocity;
            } else if (direction.equals("E")) {
                row += curVelocity + this.velocity;
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
}
