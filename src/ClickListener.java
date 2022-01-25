import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.*;


class ClickListener<T, U> implements ActionListener {

	static boolean gameOver = false;
	static JButton previousJb;
	Maze<T, U> an;
	Location<T> buttonLocation;
	JButton jb;


	public ClickListener(Maze<T, U> an, Location<T> buttonLocation, JButton jb) {
		this.an = an;
		this.buttonLocation = buttonLocation;
		this.jb = jb;
		ClickListener.gameOver = false;
	}

	public void specialDo() {
		an.init();
	}


	// removes all the components are reinitializes the Frame
	@Override
	public void actionPerformed(ActionEvent e) {

		if (ClickListener.gameOver) {
			JOptionPane.showMessageDialog(this.an, "Game Over", "InfoBox: ", JOptionPane.INFORMATION_MESSAGE);
			return;
		}

		System.out.println(an.getMazeState());
		List<Transition<T,U>> transitions = an.getMazeState().getTransitions(this.an.getMazeRow(), this.an.getMazeCol(), this.an.getLocationGrid());

		for (Transition<T, U> transition : transitions) {

			// Important Note: Doing identity check because there are no duplicate locations anywhere

			if (!transition.canTransit(an.getMazeState())) {
				continue;
			}

			State<T, U> newState = transition.transit(an.getMazeState());

			if (newState.getUserLocation() == buttonLocation) {


				if (transition.getTarget().isGoal()) {
					an.getContentPane().removeAll();
					specialDo();
					an.validate();
					an.repaint();
					JOptionPane.showMessageDialog(this.an, "You won", "InfoBox: ", JOptionPane.INFORMATION_MESSAGE);
					return;
				}

				an.setMazeState(newState);
				an.getMazeState().setUserLocation(buttonLocation);
				System.out.println(buttonLocation);
				jb.setBackground(Color.RED);
				jb.setForeground(Color.YELLOW);
				if (ClickListener.previousJb != null) {
					ClickListener.previousJb.setForeground(Color.BLACK);
				}

				if (newState.isGameOver()) {
					JOptionPane.showMessageDialog(this.an, "Game Over", "InfoBox: ", JOptionPane.INFORMATION_MESSAGE);
					ClickListener.gameOver = true;
				}

				ClickListener.previousJb = this.jb;
				return;
			}


		}
		JOptionPane.showMessageDialog(this.an, "Choose a different location", "InfoBox: ", JOptionPane.INFORMATION_MESSAGE);
	}
}