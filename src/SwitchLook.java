import java.awt.event.ActionListener;


class SwitchLook extends NewLook implements ActionListener {

	public SwitchLook(SwitchingExample an) {
		super(an);
	}

	public void specialDo() {
		an.init2();
	}
}