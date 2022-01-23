import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;


public class ButtonToMenuListener implements ActionListener {

	private JButton myButton;
	JPopupMenu  menu;
	FunPanel[] fPanels;
	
	public ButtonToMenuListener(FunPanel[] panel, JButton clicker) {
		myButton = clicker;
		fPanels = panel;
		
		// create a popup menu
		menu = new JPopupMenu("Menu");
		ColorChangeListener scl = new ColorChangeListener(fPanels);
		
        // create a menu item
		JMenuItem colors = new JMenuItem("Change Colors");
        // add the menu item to the menu
		menu.add(colors);
        // add a listener to the menu item
		colors.addActionListener(scl);
		
		 // create a menu item
		JMenuItem bump = new JMenuItem("Bump");
        // add the menu item to the menu
		menu.add(bump);
        // add a listener to the menu item
		bump.addActionListener(scl);
        
        
        menu.add(new JMenuItem("Never Mind"));        
	}

	public void actionPerformed(ActionEvent e) {
		
		menu.show(myButton,myButton.getWidth()/2,myButton.getHeight()/2);
	}


}
