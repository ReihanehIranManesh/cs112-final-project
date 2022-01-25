import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Stack;
import java.util.concurrent.ExecutionException;


class SolutionListener<T, U> implements ActionListener {


	Maze<T, U> an;
    private final State<T, U> startState;
    private final Location<T> goalLocation;
    private final JButton[][] allButtons;

    public SolutionListener(State<T, U> startState, Location<T> goalLocation, JButton[][] allButtons, Maze<T, U> an) {
        this.startState = startState;
        this.goalLocation = goalLocation;
        this.allButtons = allButtons;
		this.an = an;
    }


    // removes all the components are reinitializes the Frame


    @Override
    public void actionPerformed(ActionEvent e) {
        Solver<T, U> solver = new Solver<T, U>(this.goalLocation);
        Stack<State<T, U>> solution = solver.solve(this.an.getMazeState(), an);

		SwingWorker sw1 = new SwingWorker()
		{

			@Override
			protected String doInBackground() throws Exception
			{

				while (!solution.isEmpty()) {

					State<T, U> temp = solution.pop();
					int row = temp.getUserLocation().getRow();
					int col = temp.getUserLocation().getCol();
					allButtons[row][col].setBackground(Color.CYAN);
					Thread.sleep(500);
					allButtons[row][col].setBackground(Color.PINK);
					Thread.sleep(800);

				}

				String res = "Finished Execution";
				return res;
			}

		};

		sw1.execute();

    }
}