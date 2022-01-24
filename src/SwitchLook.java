import java.awt.event.ActionListener;


class SwitchLook extends NewLook implements ActionListener {

	public SwitchLook(Maze an) {
		super(an);
	}

	public void specialDo() {
		an.init2();
	}
}