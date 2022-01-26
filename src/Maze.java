import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

import javax.swing.*;

public abstract class Maze<T, U> extends LandingPage {

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
    protected LandingPage landingPage;

    public Maze(LandingPage landingPage) throws HeadlessException {
        this.landingPage = landingPage;
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


    public abstract boolean isAlternateColors();


    public void designMenuButton(JButton jb) {

        jb.setPreferredSize(new Dimension(90, 40));
        jb.setFont(new Font("Serif", Font.BOLD, 15));
    }

    public void designButton(JButton jb) {

        jb.setPreferredSize(new Dimension(100, 50));
        jb.setBackground(new Color(123, 104, 238));
        jb.setForeground(Color.WHITE);
        jb.setFont(new Font("Serif", Font.BOLD, 19));
    }

    public void setIconButton(JButton jb, String name) {

        String imageName = String.format("Pictures/%s.png", name);

        ImageIcon icon = new ImageIcon(imageName);
        Image image = icon.getImage();
        Image newimg = image.getScaledInstance(120, 120, Image.SCALE_SMOOTH);
        icon = new ImageIcon(newimg);
        jb.setIcon(icon);
    }

}