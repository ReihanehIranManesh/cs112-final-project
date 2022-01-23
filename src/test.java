import java.util.ArrayList;
import java.util.Scanner;

public class test {
    public static void main(String[] args){
        Maze m = new Maze(0,0,6,4, 7, 7);
        while (true)
        {
            System.out.println("Enter your next move: ");
            Scanner kb = new Scanner(System.in);
            String input = kb.nextLine();
            m.setState(input);

            if (m.getCurrentState().getRow() == m.getGoalLocation().getRow() &&
                    m.getCurrentState().getCol() == m.getGoalLocation().getCol() &&
                    m.getCurrentState().getContent() == m.getGoalLocation().getContent()) {
                break;
            }

//            System.out.println(m.getCurrentState().getRow());
//            System.out.println(m.getGoalLocation().getRow());
//            System.out.println(m.getCurrentState().getCol());
//            System.out.println(m.getGoalLocation().getCol());
//            System.out.println(m.getCurrentState().getContent());
//            System.out.println(m.getGoalLocation().getContent());


        }
        System.out.println("Solved");
    }
}
