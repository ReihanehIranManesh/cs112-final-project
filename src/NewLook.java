import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


class NewLook implements ActionListener {

	LandingPage an;
	LandingPage prev;

	public NewLook(LandingPage an) {
		this.an = an;
	}

	public NewLook(LandingPage an, LandingPage prev) {
		this.an = an;
		this.prev = prev;

	}

	public void specialDo() {
		an.init();
	}

	// removes all the components are reinitializes the Frame
	public void actionPerformed(ActionEvent e) {
		if (this.prev != null) {
			prev.setVisible(false);
		}
		an.getContentPane().removeAll();
		specialDo();
		an.validate();
		an.setVisible(true);
		an.repaint();
	}
}