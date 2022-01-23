import java.awt.*;

import javax.swing.*;

public class MenuAndJPanelExample extends JFrame {

	public void init() {

		Container contentPane = this.getContentPane();

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		contentPane.setLayout(new FlowLayout());

		JPanel jp1 = new JPanel();
		jp1.setLayout(new GridLayout(2,2));
		
		
		FunPanel[] funPanels = new FunPanel[4];
		
		contentPane.add(jp1);

		for (int i=0; i< 4; i++) {
			funPanels[i]= new FunPanel();
			jp1.add(funPanels[i]);
		}
		
		JPanel jp2 = new JPanel();
		jp2.setLayout(new FlowLayout());
		
		jp2.add(new JLabel("Left"));
		JButton changer = new JButton("Change Things");
		
		jp2.add(changer);

		jp2.add(new JLabel("Right"));

		contentPane.add(jp2);
		
		ButtonToMenuListener fl = new ButtonToMenuListener(funPanels,changer);

		changer.addActionListener(fl);

		}

	public static void main(String[] args) {

		MenuAndJPanelExample thisOne = new MenuAndJPanelExample();
		thisOne.init();

		thisOne.pack();
		thisOne.setVisible(true);
		thisOne.setSize(new Dimension(500,500));
	}
}
