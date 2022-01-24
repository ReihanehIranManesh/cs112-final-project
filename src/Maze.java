import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import javax.swing.*;

public class Maze extends JFrame {


    private State mazeState;

    Container contentPane;


    public State getMazeState() {
        return mazeState;
    }

    public void setMazeState(State mazeState) {
        this.mazeState = mazeState;
    }

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

        try {
            File f = new File("board.dat");
            Scanner sc = new Scanner(f);
            String[] s = sc.nextLine().split(" ");
            int mazeRow = Integer.parseInt(s[0]);
            int mazeCol = Integer.parseInt(s[1]);
            String[] start = sc.nextLine().split(" ");
            String[] goal = sc.nextLine().split(" ");
            int startRow = Integer.parseInt(start[0]);
            int startCol = Integer.parseInt(start[1]);
            int goalRow = Integer.parseInt(goal[0]);
            int goalCol = Integer.parseInt(goal[1]);

            List<Integer> contents = new LinkedList<>();

            JButton[][] allButtons = new JButton[mazeRow][mazeCol];

            while (sc.hasNextInt()) {
                contents.add(sc.nextInt());
            }
            sc.close();

            Location[][] locationGrid = Location.createLocationGrid(mazeRow, mazeCol, contents, startRow, startCol, goalRow, goalCol);

            this.mazeState = new State(locationGrid[startRow][startCol]);

            contentPane.setLayout(new GridLayout(mazeRow + 1, mazeCol));

            this.setSize(new Dimension(1500, 1500));
            this.setLocationRelativeTo(null);

            int count = 0;

            for (int i = 0; i < mazeRow; i++) {
                for (int j = 0; j < mazeCol; j++) {

                    count++;

                    int content = locationGrid[i][j].getContent();
                    JButton jb1 = new JButton("" + content);
                    jb1.setOpaque(true);
                    jb1.setFont(new Font("Arial", Font.BOLD, 60));
                    jb1.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
                    jb1.addActionListener(new ClickListener(this, locationGrid[i][j], jb1));

                    if (i == startRow && j == startCol) {
                        jb1.setBackground(Color.RED);
                        jb1.setForeground(Color.YELLOW);
                        contentPane.add(jb1);
                        allButtons[i][j] = jb1;
                        ClickListener.previousJb = jb1;
                        continue;
                    }

                    if (i == goalRow && j == goalCol) {
                        jb1.setText("GOAL");
                        jb1.setBackground(Color.RED);

                        jb1.setFont(new Font("Arial", Font.BOLD, 50));
                        jb1.setForeground(Color.BLACK);
                        jb1.setBorder(BorderFactory.createLineBorder(Color.BLACK, 4));
                        contentPane.add(jb1);
                        allButtons[i][j] = jb1;
                        continue;

                    }
                    if (count % 2 == 1)
                        jb1.setBackground(new Color(154, 205, 50));
                    else
                        jb1.setBackground(new Color(0, 128, 128));

                    jb1.setForeground(Color.BLACK);

                    contentPane.add(jb1);
                    allButtons[i][j] = jb1;

                }

            }


            JPanel secondPanel = new JPanel(new FlowLayout());

            JButton solveButton = new JButton("Solve");
            solveButton.addActionListener(new SolutionListener(mazeState, locationGrid[goalRow][goalCol], allButtons));
            secondPanel.add(solveButton);


            JButton jb2 = new JButton("Take Back");
            secondPanel.add(jb2);
            this.getContentPane().add(secondPanel);
            jb2.addActionListener(new NewLook(this));


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }



//		JButton changer = new JButton("Add Button");
//		changer.addActionListener(new SwitchListener(this));
//		contentPane.add(changer);

    }


    public static void main(String[] args) {

        Maze thisOne = new Maze();
        thisOne.init();

        thisOne.pack();
        thisOne.setVisible(true);
        thisOne.setSize(new Dimension(750, 500));
        thisOne.setLocationRelativeTo(null);
    }

}