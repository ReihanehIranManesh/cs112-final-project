import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class MazeThree extends Maze<MazeThreeContent, Character> {

    static Map<String, Character> arrowMapping = Map.of(
            "W", '\u21d0',
            "N", '\u21d1',
            "E", '\u21d2',
            "S", '\u21d3',
            "NW", '\u21d6',
            "NE", '\u21d7',
            "SE", '\u21d8',
            "SW", '\u21d9'

    );

    public MazeThree(LandingPage landingPage) throws HeadlessException {
        super(landingPage);
    }

    @Override
    public List<MazeThreeContent> populateContents(Scanner sc) {

        List<MazeThreeContent> contents = new LinkedList<>();
        while (sc.hasNext()) {

            String[] row = sc.next().split(" ");

            for (int i = 0; i < row.length; i++) {

                String[] cols = row[i].split("_");

                String direction = cols[0];
                char color = cols[1].charAt(0);
                MazeThreeContent mazeThreeContent = new MazeThreeContent(color, direction);
                System.out.println(mazeThreeContent);
                contents.add(mazeThreeContent);
            }
        }
        return contents;
    }

    @Override
    public void init() {

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

                MazeThreeContent content = locationGrid[i][j].getContent();
                JButton jb1 = new JButton(getContentText(content));
                jb1.setOpaque(true);
                jb1.setFont(new Font("Arial", Font.BOLD, 60));
                jb1.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
                jb1.addActionListener(new ClickListener<MazeThreeContent, Character>(this, locationGrid[i][j], jb1));

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

                jb1.setBackground(Color.white);

                jb1.setForeground(getContentColor(content));

                contentPane.add(jb1);
                allButtons[i][j] = jb1;
            }
        }

        JPanel secondPanel = new JPanel();
        secondPanel.setLayout(new FlowLayout());


        JButton solveButton = new JButton("Solve");
        solveButton.addActionListener(new SolutionListener<>(this.mazeState, locationGrid[goalRow][goalCol], allButtons, this));
        designButton(solveButton);
        secondPanel.add(solveButton);


        JButton hintButton = new JButton("Hint");
        hintButton.addActionListener(new HintListener<>(this.mazeState, locationGrid[goalRow][goalCol], allButtons, this));
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
    public String getContentText(MazeThreeContent content) {


        return "" + MazeThree.arrowMapping.get(content.getDirection());
    }

    @Override
    public Color getContentColor(MazeThreeContent content) {
        return content.getColor() == 'R' ? Color.red : Color.blue;
    }

    @Override
    public boolean isAlternateColors() {
        return false;
    }

    @Override
    public void initMaze(String filename) {
        super.initMaze(filename);
        this.mazeState = new MazeThreeState(locationGrid[startRow][startCol]);
    }
}