import java.util.ArrayList;

public class Location {
    private int row;
    private int col;
    private int content;

    private ArrayList<Transition> transitions = new ArrayList<>();

    public Location(int r, int c, int con) {
        this.row = r;
        this.col = c;
        this.content = con;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public int getContent() {
        return content;
    }

    public ArrayList<Transition> getTransitions() {
        return transitions;
    }

//    public void buildTransitions(){


//        if (Transition.checkTransition(s))
//        {
//
//        }


}
