import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class ColorChangeListener implements ActionListener {

	FunPanel[] an;
	
	public ColorChangeListener(FunPanel[] panel) {
		an = panel;	
	}

	
	public void actionPerformed(ActionEvent e) {
		String action = e.getActionCommand();
		if (action.equals("Change Colors"))
			for (FunPanel a : an)
				a.changeColors();
		else if (action.equals("Bump"))
			for (FunPanel a : an)
				a.bump();
		
	}

		
}
