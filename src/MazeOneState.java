import java.util.ArrayList;
import java.util.List;

public class MazeOneState extends State{


    public MazeOneState(Location userLocation) {
        super(userLocation);
    }

    private List<Transition> getTransitions(int mazeRow, int mazeCol, Location[][] locationsGrid) {
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

        return transitions;
    }
}
