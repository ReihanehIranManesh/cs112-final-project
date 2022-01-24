import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.*;


class ClickListener implements ActionListener {

	Maze an;
	Location buttonLocation;
	JButton jb;
	static JButton previousJb;

	public ClickListener(Maze an, Location buttonLocation, JButton jb) {
		this.an = an;
		this.buttonLocation = buttonLocation;
		this.jb = jb;
	}

	public void specialDo() {
		an.init();
	}


	// removes all the components are reinitializes the Frame
	@Override
	public void actionPerformed(ActionEvent e) {

		List<Transition> transitions = an.getMazeState().getUserLocation().getTransitions();

		for (Transition transition : transitions) {

			// Important Note: Doing identity check because there are no duplicate locations anywhere

			if (transition.getTarget() == buttonLocation) {
				if (transition.getTarget().isGoal()) {
					an.getContentPane().removeAll();
					specialDo();
					an.validate();
					an.repaint();
					JOptionPane.showMessageDialog(this.an, "You won", "InfoBox: ", JOptionPane.INFORMATION_MESSAGE);
					return;
				}


				an.getMazeState().setUserLocation(buttonLocation);
				System.out.println(buttonLocation);
				jb.setBackground(Color.RED);
				jb.setForeground(Color.YELLOW);
				if (ClickListener.previousJb != null) {
					ClickListener.previousJb.setForeground(Color.BLACK);
				}
				ClickListener.previousJb = this.jb;
				return;
			}
		}
		System.out.println("False");
	}
}