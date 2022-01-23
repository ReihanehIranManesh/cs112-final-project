import java.util.ArrayList;

public class Maze {

    private StartLocation sl;
    private GoalLocation gl;
    private static int mazeRow;
    private static int mazeCol;
//    public static ArrayList<State> states = new ArrayList<>();
    private State currentState;

    public Maze(int startRow, int startCol, int goalRow, int goalCol, int mr, int mc) {
        mazeRow = mr;
        mazeCol = mc;
        sl = new StartLocation(startRow, startCol, 6);
        gl = new GoalLocation(goalRow, goalCol, 4);
        currentState = sl;
//        states.add(sl);
    }

    public Location createLocation(int r, int c, int con) {
        return new Location(r, c, con);
    }

    public StartLocation getStartLocation(){return sl;}

    public GoalLocation getGoalLocation(){return gl;}

    public State getCurrentState()
    {
        return currentState;
    }
    public static int getMazeRow(){return mazeRow;}

    public static int getMazeCol(){return mazeCol;}

    public void setCurrentState(State s)
    {
        this.currentState = s;
    }
    public void setState(String info){

        String[] s = info.split(" ");
        Transition temp = new Transition( Integer.parseInt(s[0]), Integer.parseInt(s[1]), Integer.parseInt(s[2]));

        if (temp.checkTransition(getCurrentState()) != null)
        {
            this.setCurrentState(temp.checkTransition(getCurrentState()));
            System.out.println("Success");
        }
        else {
            System.out.println("Try again");
        }

//        check()
    }
}
