import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;


public class LandingPage extends JFrame {

    Container contentPane;

    public LandingPage() throws HeadlessException {

    }

    public void init() {


        contentPane = this.getContentPane();

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        contentPane.setLayout(new FlowLayout());

        contentPane.add(new SwitchPanel());

        Maze<Integer, Void> mazeOne = new MazeOne(this);
        JButton jb1 = new JButton("One");
        designMenuButton(jb1);
        contentPane.add(jb1);
        jb1.addActionListener(new NewLook(mazeOne));

        Maze<MazeTwoContent, Integer> mazeTwo = new MazeTwo(this);
        JButton jb2 = new JButton("Two");
        designMenuButton(jb2);
        contentPane.add(jb2);
        jb2.addActionListener(new NewLook(mazeTwo));


        Maze<MazeThreeContent, Character> mazeThree = new MazeThree(this);
        JButton jb3 = new JButton("Three");
        designMenuButton(jb3);
        contentPane.add(jb3);
        jb3.addActionListener(new NewLook(mazeThree));

    }

    public static void main(String[] args) {

        LandingPage thisOne = new LandingPage();

        thisOne.init();
        thisOne.pack();
        thisOne.setVisible(true);
        thisOne.setLocationRelativeTo(null);
    }

    public void designMenuButton(JButton jb) {

        jb.setPreferredSize(new Dimension(90, 40));
        jb.setFont(new Font("Serif", Font.BOLD, 15));
    }

}