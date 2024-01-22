import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

public class BottomPanel extends JPanel {
	public BottomPanel() {
		setUpPanel();
	}
	private void setUpPanel() {
		setPreferredSize(new Dimension(800,300));
		setBackground(Color.orange);
	}
}
