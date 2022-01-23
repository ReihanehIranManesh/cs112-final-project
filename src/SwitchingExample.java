import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.*;

public class SwitchingExample extends JFrame{

	Container contentPane;
	public void init() {

		contentPane = this.getContentPane();

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		contentPane.setLayout(new FlowLayout());

		contentPane.add(new SwitchPanel());

		JButton jb = new JButton("Switch");

		contentPane.add(jb);
		jb.addActionListener(new SwitchLook(this));

		JButton jb1 = new JButton("One");
		
		
		contentPane.add(jb1);
		contentPane.add(new JButton("Two"));
		contentPane.add(new JButton("Three"));


	}

	public void init2() {

		Container contentPane = this.getContentPane();

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		int rows = 7;
		int cols = 7;

		contentPane.setLayout(new GridLayout(rows + 1,cols));

		this.setSize(new Dimension(1500, 1500));
		this.setLocationRelativeTo(null);


		JButton jb1;
		String fileName = "board.dat";

		File f = new File(fileName);

		try {
			int count = 0;
			Scanner sc = new Scanner(f);

			while (sc.hasNext()) {
				String s = sc.next();
				jb1 =  new JButton(s);
				jb1.setOpaque(true);
				jb1.setFont(new Font("Arial", Font.BOLD, 60));
				jb1.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

				if(count == 0){
					jb1.setBackground(Color.RED);
					jb1.setForeground(Color.YELLOW);
					contentPane.add(jb1);
					count++;
					continue;
				}
				if (count % 2 == 0)
					jb1.setBackground(new Color(154, 205, 50));
				else
					jb1.setBackground(new Color(0,128,128));

				jb1.setForeground(Color.BLACK);

				contentPane.add(jb1);
				count++;
			}
			sc.close();
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		jb1 =  new JButton("GOAL");
		jb1.setBackground(Color.RED);

		jb1.setFont(new Font("Arial", Font.BOLD, 60));
		jb1.setForeground(Color.BLACK);
		jb1.setBorder(BorderFactory.createLineBorder(Color.BLACK, 4));

		contentPane.add(jb1);

//
//		JButton jb2 = new JButton("Take Back");
//		contentPane.add(jb2);
//		jb2.addActionListener(new NewLook(this));


		JPanel secondPanel = new JPanel(new FlowLayout());

		JButton jb2 = new JButton("Take Back");
		secondPanel.add(jb2);
		this.getContentPane().add(secondPanel);
		jb2.addActionListener(new NewLook(this));

//		JButton changer = new JButton("Add Button");
//		changer.addActionListener(new SwitchListener(this));
//		contentPane.add(changer);

	}


	public static void main(String[] args) {

		SwitchingExample thisOne = new SwitchingExample();
		thisOne.init();

		thisOne.pack();
		thisOne.setVisible(true);
		thisOne.setSize(new Dimension(750,500));
		thisOne.setLocationRelativeTo(null);
	}

}