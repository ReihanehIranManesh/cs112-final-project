import java.awt.*;

import javax.swing.*;


public class FunPanel extends JPanel {

	int number = 25;
	Color[] cs;
	int offset = 0;

	public FunPanel() {
		cs = new Color[4];

		cs[0] = new Color(255,0,0);
		cs[1] = new Color(0,255,0);
		cs[2] = new Color(0,0,255);
		cs[3] = new Color(0,255,255);

		setPreferredSize(new Dimension(200, 200));
	}

	public void changeColors() {

		for (int j = 0; j<4; j++) {

			cs[j] = new Color((int) (Math.random()*255),
					(int) (Math.random()*255),
					(int) (Math.random()*255));

		}
		// if you remove this repaint, the colors do not change right away
		repaint();
	}

	public void bump() {
		offset++;
		repaint();
	}

	public void paintComponent(Graphics g) {

		super.paintComponent(g);

		int x, y;

		int size = 50;

		for (int i=0; i<number; i++) {

			g.setColor(cs[i%4]);

			x = size + (int) (size*Math.sin((i+offset)*Math.PI*2.0/number));
			y = size + (int) (size*Math.cos((i+offset)*Math.PI*2.0/number));

			g.fillOval(x,y,20,20);

		}

	}



}
