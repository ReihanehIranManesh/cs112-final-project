import java.awt.*;
import java.awt.event.ActionListener;


class SwitchMazeOne extends NewLook implements ActionListener {

	public SwitchMazeOne(Maze an) {
		super(an);
	}

	public void specialDo() {
		an.drawMaze();
	}
}