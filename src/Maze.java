import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

import javax.swing.*;

public abstract class Maze<T, U> extends JFrame {

    public Location<T>[][] getLocationGrid() {
        return locationGrid;
    }

    protected Location<T>[][] locationGrid;
    protected State<T, U> mazeState;

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
    protected List<T> contents;

    public Maze() throws HeadlessException {

    }
    public void initMaze(String filename) {
        File f = new File(filename);
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

        this.contents = populateContents(sc);

        sc.close();

        this.locationGrid = Location.createLocationGrid(mazeRow, mazeCol, contents, startRow, startCol, goalRow, goalCol);

    }

    public abstract List<T> populateContents(Scanner sc);

    abstract public String getContentText(T content);

    abstract public Color getContentColor(T content);

    public State<T, U> getMazeState() {
        return mazeState;
    }

    public void setMazeState(State<T, U> mazeState) {
        this.mazeState = mazeState;
    }

    public void init() {

        contentPane = this.getContentPane();

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        contentPane.setLayout(new FlowLayout());

        contentPane.add(new SwitchPanel());

        JButton jb1 = new JButton("One");

        contentPane.add(jb1);
        jb1.addActionListener(new SwitchMazeOne(this));

        JButton jb2 = new JButton("Two");
        contentPane.add(jb2);

        JButton jb3 = new JButton("Three");

        contentPane.add(jb3);

    }

    public void init2() {

        this.initMaze("bull.dat");

        Container contentPane = this.getContentPane();

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        contentPane.setLayout(new GridLayout(mazeRow + 1, mazeCol));

        this.setSize(new Dimension(1700, 1700));
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
                jb1.addActionListener(new ClickListener<T, U>(this, locationGrid[i][j], jb1));

                if (i == startRow && j == startCol) {
                    if (isAlternateColors()) {
                        jb1.setBackground(Color.RED);
                        jb1.setForeground(Color.YELLOW);
                    } else {
                        jb1.setBackground(Color.LIGHT_GRAY);
                        jb1.setForeground(getContentColor(content));
                    }

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
                if (isAlternateColors()) {
                    if (count % 2 == 1)
                        jb1.setBackground(new Color(154, 205, 50));
                    else
                        jb1.setBackground(new Color(0, 128, 128));
                } else {
                    jb1.setBackground(Color.white);
                }

                jb1.setForeground(getContentColor(content));

                contentPane.add(jb1);
                allButtons[i][j] = jb1;
            }
        }

        JPanel secondPanel = new JPanel(new FlowLayout());

        JButton solveButton = new JButton("Solve");
        solveButton.addActionListener(new SolutionListener<T, U>(this.mazeState, locationGrid[goalRow][goalCol], allButtons, this));
        secondPanel.add(solveButton);


        JButton hintButton = new JButton("Hint");
        hintButton.addActionListener(new HintListener<T, U>(this.mazeState, locationGrid[goalRow][goalCol], allButtons, this));
        secondPanel.add(hintButton);


        JButton jb2 = new JButton("Take Back");
        secondPanel.add(jb2);
        this.getContentPane().add(secondPanel);
        jb2.addActionListener(new NewLook(this));


//		JButton changer = new JButton("Add Button");
//		changer.addActionListener(new SwitchListener(this));
//		contentPane.add(changer);

    }

    public abstract boolean isAlternateColors();

    public static void main(String[] args) {

        Maze<MazeThreeContent, Character> thisOne = new MazeThree();


        thisOne.init();

        thisOne.pack();
        thisOne.setVisible(true);
        thisOne.setSize(new Dimension(750, 500));
        thisOne.setLocationRelativeTo(null);
    }

}