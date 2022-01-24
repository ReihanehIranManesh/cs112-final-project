import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import javax.swing.*;

public abstract class Maze<T> extends JFrame {

    public Location<T>[][] getLocationGrid() {
        return locationGrid;
    }

    protected final Location<T>[][] locationGrid;
    protected State mazeState;

    Container contentPane;

    public int getMazeRow() {
        return mazeRow;
    }

    public int getMazeCol() {
        return mazeCol;
    }

    protected int mazeRow;
    protected int mazeCol;
    protected int startRow;
    protected int startCol;
    protected int goalRow;
    protected int goalCol;

    public Maze() throws HeadlessException {

        File f = new File("board.dat");
        Scanner sc = null;
        try {
            sc = new Scanner(f);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String[] s = sc.nextLine().split(" ");
        this.mazeRow = Integer.parseInt(s[0]);
        this.mazeCol = Integer.parseInt(s[1]);
        String[] start = sc.nextLine().split(" ");
        String[] goal = sc.nextLine().split(" ");
        this.startRow = Integer.parseInt(start[0]);
        this.startCol = Integer.parseInt(start[1]);
        this.goalRow = Integer.parseInt(goal[0]);
        this.goalCol = Integer.parseInt(goal[1]);


        List<T> contents = populateContents(sc);


        sc.close();

        this.locationGrid = Location.createLocationGrid(mazeRow, mazeCol, contents, startRow, startCol, goalRow, goalCol);


    }

    public abstract List<T> populateContents(Scanner sc);

    abstract public String getContentText(T content);

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


        contentPane.setLayout(new GridLayout(mazeRow + 1, mazeCol));

        this.setSize(new Dimension(1500, 1500));
        this.setLocationRelativeTo(null);

        int count = 0;


        JButton[][] allButtons = new JButton[mazeRow][mazeCol];


        for (int i = 0; i < mazeRow; i++) {
            for (int j = 0; j < mazeCol; j++) {

                count++;

                T content = locationGrid[i][j].getContent();
                JButton jb1 = new JButton(getContentText(content));
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
        solveButton.addActionListener(new SolutionListener<T>(mazeState, locationGrid[goalRow][goalCol], allButtons, this));
        secondPanel.add(solveButton);


        JButton hintButton = new JButton("Hint");
        hintButton.addActionListener(new HintListener<T>(mazeState, locationGrid[goalRow][goalCol], allButtons, this));
        secondPanel.add(hintButton);


        JButton jb2 = new JButton("Take Back");
        secondPanel.add(jb2);
        this.getContentPane().add(secondPanel);
        jb2.addActionListener(new NewLook(this));


//		JButton changer = new JButton("Add Button");
//		changer.addActionListener(new SwitchListener(this));
//		contentPane.add(changer);

    }


    public static void main(String[] args) {

        Maze<Integer> thisOne = new MazeOne();

        thisOne.init();

        thisOne.pack();
        thisOne.setVisible(true);
        thisOne.setSize(new Dimension(750, 500));
        thisOne.setLocationRelativeTo(null);
    }

}