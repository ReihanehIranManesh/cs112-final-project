import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class MazeOne extends Maze<Integer, Void> {


    public MazeOne(LandingPage landingPage) throws HeadlessException {
        super(landingPage);
    }

    @Override
    public List<Integer> populateContents(Scanner sc) {

        List<Integer> contents = new LinkedList<>();
        while (sc.hasNextInt()) {

            contents.add(sc.nextInt());
        }
        return contents;
    }

    @Override
    public String getContentText(Integer content) {
        return "" + content;
    }

    @Override
    public Color getContentColor(Integer content) {
        return Color.black;
    }

    @Override
    public void init() {

        this.initMaze("board.dat");

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

                Integer content = locationGrid[i][j].getContent();
                JButton jb1 = new JButton(getContentText(content));
                jb1.setOpaque(true);
                jb1.setFont(new Font("Arial", Font.BOLD, 60));
                jb1.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
                jb1.addActionListener(new ClickListener<Integer, Void>(this, locationGrid[i][j], jb1));

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

        JPanel secondPanel = new JPanel();
        secondPanel.setLayout(new FlowLayout());


        JButton solveButton = new JButton("Solve");
        solveButton.addActionListener(new SolutionListener<Integer, Void>(this.mazeState, locationGrid[goalRow][goalCol], allButtons, this));
        designButton(solveButton);
        secondPanel.add(solveButton);


        JButton hintButton = new JButton("Hint");
        hintButton.addActionListener(new HintListener<Integer, Void>(this.mazeState, locationGrid[goalRow][goalCol], allButtons, this));
        designButton(hintButton);
        secondPanel.add(hintButton);

        JButton jb2 = new JButton("Take Back");
        jb2.addActionListener(new NewLook(landingPage, this));
        designButton(jb2);
        jb2.setPreferredSize(new Dimension(160, 50));
        secondPanel.add(jb2);
        this.getContentPane().add(secondPanel);

    }

    @Override
    public boolean isAlternateColors() {
        return true;
    }

    @Override
    public void initMaze(String filename) {
        super.initMaze(filename);
        this.mazeState = new MazeOneState(locationGrid[startRow][startCol]);

    }
}