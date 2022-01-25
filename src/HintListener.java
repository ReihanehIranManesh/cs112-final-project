import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Stack;


class HintListener<T, U> implements ActionListener {


    Maze<T, U> an;
    private final State<T, U> startState;
    private final Location<T> goalLocation;
    private final JButton[][] allButtons;

    public HintListener(State<T, U> startState, Location<T> goalLocation, JButton[][] allButtons, Maze<T, U> an) {
        this.startState = startState;
        this.goalLocation = goalLocation;
        this.allButtons = allButtons;
        this.an = an;
    }


    // removes all the components are reinitializes the Frame


    @Override
    public void actionPerformed(ActionEvent e) {
        Solver<T, U> solver = new Solver<T, U>(this.goalLocation);
        Stack<State<T, U>> solution = solver.solve(this.startState, an);

        int row;
        int col;
        Color prevColor;
        solution.pop();
        if (!solution.isEmpty()) {

            State<T, U> temp = solution.pop();
            row = temp.getUserLocation().getRow();
            col = temp.getUserLocation().getCol();
            prevColor = allButtons[row][col].getBackground();

        } else {
            return;
        }

        SwingWorker sw1 = new SwingWorker() {

            @Override
            protected String doInBackground() throws Exception {
                for (int i = 0; i < 3; i++) {

                    allButtons[row][col].setBackground(Color.RED);
                    Thread.sleep(200);
                    allButtons[row][col].setBackground(prevColor);
					Thread.sleep(200);
				}

                String res = "Finished Execution";
                return res;
            }

        };

        sw1.execute();

    }
}