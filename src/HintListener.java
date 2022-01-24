import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Stack;


class HintListener implements ActionListener {


    private State startState;
    private Location goalLocation;
    private JButton[][] allButtons;

    public HintListener(State startState, Location goalLocation, JButton[][] allButtons) {
        this.startState = startState;
        this.goalLocation = goalLocation;
        this.allButtons = allButtons;
    }


    // removes all the components are reinitializes the Frame


    @Override
    public void actionPerformed(ActionEvent e) {
        Solver solver = new Solver(this.goalLocation);
        Stack<State> solution = solver.solve(this.startState);

        int row;
        int col;
        Color prevColor;
        solution.pop();
        if (!solution.isEmpty()) {

            State temp = solution.pop();
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