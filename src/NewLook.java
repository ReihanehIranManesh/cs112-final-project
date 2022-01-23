import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


class NewLook implements ActionListener {

	SwitchingExample an;

	public NewLook(SwitchingExample an) {
		this.an = an;
	}

	public void specialDo() {
		an.init();
	}

	// removes all the components are reinitializes the Frame
	public void actionPerformed(ActionEvent e) {
		an.getContentPane().removeAll();
		specialDo();
		an.validate();
		an.repaint();
	}
}