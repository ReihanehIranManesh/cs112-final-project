import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class MazeTwo extends Maze<MazeTwoContent, Integer> {


    public MazeTwo() throws HeadlessException {
        super();
    }

    @Override
    public List<MazeTwoContent> populateContents(Scanner sc) {

        List<MazeTwoContent> contents = new LinkedList<>();
        while (sc.hasNext()) {

            String[] row = sc.next().split(" ");

            for (int i = 0; i < row.length; i++) {

                String[] cols = row[i].split("_");
                int velocity = Integer.parseInt(cols[cols.length - 1]);
                List<String> directions = new LinkedList<>();

                for (int j = 0; j < cols.length - 1; j++) {
                    directions.add(cols[j]);
                }

                MazeTwoContent mazeTwoContent = new MazeTwoContent(velocity, directions);
                System.out.println(mazeTwoContent);
                contents.add(mazeTwoContent);

            }
        }
        return contents;
    }

    @Override
    public String getContentText(MazeTwoContent content) {
        return "" + content;
    }

    @Override
    public Color getContentColor(MazeTwoContent content) {
        return Color.black;
    }

    @Override
    public void drawMaze() {
        this.initMaze("alice.dat");
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);

        Container contentPane = this.getContentPane();

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        contentPane.setLayout(new GridLayout(mazeRow + 1, mazeCol));

        this.setSize(new Dimension(1700, 1700));
        this.setLocationRelativeTo(null);

        int count = 1;

        JButton[][] allButtons = new JButton[mazeRow][mazeCol];


        for (int i = 0; i < mazeRow; i++) {
            for (int j = 0; j < mazeCol; j++) {
                count++;
                MazeTwoContent content = locationGrid[i][j].getContent();
                String contentText = getContentText(content);
                JButton jb1 = new JButton();
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
                setIconButton(jb1, contentText);

                jb1.setOpaque(true);
                jb1.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
                jb1.addActionListener(new ClickListener<>(this, locationGrid[i][j], jb1));
                if (i == startRow && j == startCol) {
                    jb1.setBackground(Color.RED);
                    jb1.setForeground(Color.YELLOW);
                    contentPane.add(jb1);
                    allButtons[i][j] = jb1;
                    ClickListener.previousJb = jb1;
                    continue;
                }

                if (isAlternateColors()) {

                    if (i % 2 == 0) {
                        if (count % 2 == 1)
                            jb1.setBackground(new Color(154, 205, 50));
                        else
                            jb1.setBackground(new Color(0, 128, 128));
                    } else {
                        if (count % 2 == 0)
                            jb1.setBackground(new Color(154, 205, 50));
                        else
                            jb1.setBackground(new Color(0, 128, 128));
                    }
                }


                contentPane.add(jb1);
                allButtons[i][j] = jb1;
            }
        }

        JPanel secondPanel = new JPanel();
        secondPanel.setLayout(new

                FlowLayout());


        JButton solveButton = new JButton("Solve");
        solveButton.addActionListener(new SolutionListener<>(this.mazeState, locationGrid[goalRow][goalCol], allButtons, this));

        designButton(solveButton);
        secondPanel.add(solveButton);


        JButton hintButton = new JButton("Hint");
        hintButton.addActionListener(new HintListener<>(this.mazeState, locationGrid[goalRow][goalCol], allButtons, this));

        designButton(hintButton);
        secondPanel.add(hintButton);

        JButton jb2 = new JButton("Take Back");
        jb2.addActionListener(new

                NewLook(this));

        designButton(jb2);
        jb2.setPreferredSize(new

                Dimension(160, 50));
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
        this.mazeState = new MazeTwoState(locationGrid[startRow][startCol], 1);
    }
}