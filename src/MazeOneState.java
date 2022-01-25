import java.util.ArrayList;
import java.util.List;

public class MazeOneState extends State<Integer, Void> {


    public MazeOneState(Location<Integer> userLocation) {
        super(userLocation);

    }

    public List<Transition<Integer, Void>> getTransitions(int mazeRow, int mazeCol, Location<Integer>[][] locationsGrid) {
        List<Transition<Integer, Void>> transitions = new ArrayList<>();
        int rowRight = this.userLocation.getRow();
        int colRight = this.userLocation.getCol() + this.userLocation.getContent();
        if (colRight < mazeCol) {
            MazeOneTransition rightTransition = new MazeOneTransition(locationsGrid[rowRight][colRight]);
            transitions.add(rightTransition);
        }

        int rowLeft = this.userLocation.getRow();
        int colLeft = this.userLocation.getCol() - this.userLocation.getContent();
        if (colLeft >= 0) {
            MazeOneTransition leftTransition = new MazeOneTransition(locationsGrid[rowLeft][colLeft]);
            transitions.add(leftTransition);

        }


        int rowUp = this.userLocation.getRow() - this.userLocation.getContent();
        int colUp = this.userLocation.getCol();
        if (rowUp >= 0) {
            MazeOneTransition upTransition = new MazeOneTransition(locationsGrid[rowUp][colUp]);
            transitions.add(upTransition);
        }

        int rowDown = this.userLocation.getRow() + this.userLocation.getContent();
        int colDown = this.userLocation.getCol();
        if (rowDown < mazeRow) {
            MazeOneTransition downTransition = new MazeOneTransition(locationsGrid[rowDown][colDown]);
            transitions.add(downTransition);

        }

        return transitions;
    }
}
