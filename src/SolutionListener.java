import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Stack;
import java.util.concurrent.ExecutionException;


class SolutionListener implements ActionListener {


    private State startState;
    private Location goalLocation;
    private JButton[][] allButtons;

    public SolutionListener(State startState, Location goalLocation, JButton[][] allButtons) {
        this.startState = startState;
        this.goalLocation = goalLocation;
        this.allButtons = allButtons;
    }


    // removes all the components are reinitializes the Frame


    @Override
    public void actionPerformed(ActionEvent e) {
        Solver solver = new Solver(this.goalLocation);
        Stack<State> solution = solver.solve(this.startState);

		SwingWorker sw1 = new SwingWorker()
		{

			@Override
			protected String doInBackground() throws Exception
			{

				while (!solution.isEmpty()) {

					State temp = solution.pop();
					int row = temp.getUserLocation().getRow();
					int col = temp.getUserLocation().getCol();
					allButtons[row][col].setBackground(Color.PINK);

					Thread.sleep(1000);

				}

				String res = "Finished Execution";
				return res;
			}

		};

		sw1.execute();

    }
}