import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;


class SwitchListener extends NewLook implements ActionListener {

	public SwitchListener(SwitchingExample an) {
		super(an);
	}

	// Changes the Frame by adding one button
	public void actionPerformed(ActionEvent arg0) {
		JButton j = new JButton("Switch Back");
		an.getContentPane().add(j);
		j.addActionListener(new NewLook(an));
		an.validate();
		an.repaint();
	}
}