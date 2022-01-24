import java.awt.event.ActionListener;


class SwitchMaze2 extends NewLook implements ActionListener {

	public SwitchMaze2(Maze an) {
		super(an);
	}

	public void specialDo() {
		an.init2();
	}
}