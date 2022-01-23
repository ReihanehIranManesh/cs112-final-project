import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;


public class SwitchPanel extends JPanel implements ActionListener {

	int number = 25;
	Color[] cs;
	int offset = 0;

	public SwitchPanel() {
		cs = new Color[4];

		cs[0] = new Color(255,0,0);
		cs[1] = new Color(0,255,0);
		cs[2] = new Color(0,0,255);
		cs[3] = new Color(0,255,255);

		setPreferredSize(new Dimension(400, 400));
	}

	public void changeColors() {

		for (int j = 0; j<4; j++) {

			cs[j] = new Color((int) (Math.random()*255),
					(int) (Math.random()*255),
					(int) (Math.random()*255));

		}
	}

	public void bump() {
		offset++;
		repaint();
	}

	public void paintComponent(Graphics g) {

		super.paintComponent(g);

		int x, y;


		for (int i=0; i<number; i++) {

			g.setColor(cs[i%4]);

			x = 150 + (int) (150*Math.sin((i+offset)*Math.PI*2.0/number));
			y = 150 + (int) (150*Math.cos((i+offset)*Math.PI*2.0/number));

			g.fillOval(x,y,20,20);

		}

	}



	public void actionPerformed(ActionEvent arg0) {
		changeColors();
		repaint();

	}



}
