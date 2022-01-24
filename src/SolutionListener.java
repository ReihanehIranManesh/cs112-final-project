import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Stack;
import java.util.concurrent.ExecutionException;


class SolutionListener<T> implements ActionListener {


	Maze<T> an;
    private final State<T> startState;
    private final Location<T> goalLocation;
    private final JButton[][] allButtons;

    public SolutionListener(State<T> startState, Location<T> goalLocation, JButton[][] allButtons, Maze<T> an) {
        this.startState = startState;
        this.goalLocation = goalLocation;
        this.allButtons = allButtons;
		this.an = an;
    }


    // removes all the components are reinitializes the Frame


    @Override
    public void actionPerformed(ActionEvent e) {
        Solver<T> solver = new Solver<T>(this.goalLocation);
        Stack<State<T>> solution = solver.solve(this.startState, an);

		SwingWorker sw1 = new SwingWorker()
		{

			@Override
			protected String doInBackground() throws Exception
			{

				while (!solution.isEmpty()) {

					State<T> temp = solution.pop();
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