import java.awt.*;
import java.awt.event.ActionListener;


class SwitchMazeTwo extends NewLook implements ActionListener {

	public SwitchMazeTwo(Maze an) {
		super(an);
	}

	public void specialDo() {
		an.drawMaze();
	}
}