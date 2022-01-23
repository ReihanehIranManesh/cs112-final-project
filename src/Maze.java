//import java.util.ArrayList;
//
//public class Maze {
//
//    private Location startLocation;
//    private Location goalLocation;
//    private static int mazeRows;
//    private static int mazeCols;
////    public static ArrayList<State> states = new ArrayList<>();
//    private State currentState;
//
//    public Maze(Location startLocation, Location goalLocation, int mazeRows, int mazeCols) {
//        this.startLocation = startLocation;
//        this.goalLocation = goalLocation;
//        this.mazeRows = mazeRows;
//        this.mazeCols = mazeCols;
//        currentState = new State(startLocation);
//    }
//
//    public Location createLocation(int r, int c, int con) {
//        return new Location(r, c, con);
//    }
//
//    public StartLocation getStartLocation(){return sl;}
//
//    public GoalLocation getGoalLocation(){return gl;}
//
//    public State getCurrentState()
//    {
//        return currentState;
//    }
//    public static int getMazeRow(){return mazeRow;}
//
//    public static int getMazeCol(){return mazeCol;}
//
//    public void setCurrentState(State s)
//    {
//        this.currentState = s;
//    }
//    public void setState(String info){
//
//        String[] s = info.split(" ");
//        Transition temp = new Transition( Integer.parseInt(s[0]), Integer.parseInt(s[1]), Integer.parseInt(s[2]));
//
//        if (temp.checkTransition(getCurrentState()) != null)
//        {
//            this.setCurrentState(temp.checkTransition(getCurrentState()));
//            System.out.println("Success");
//        }
//        else {
//            System.out.println("Try again");
//        }
//
////        check()
//    }
//}
