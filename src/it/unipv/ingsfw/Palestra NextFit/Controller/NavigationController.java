package Controller;

import javax.swing.JFrame;

public class NavigationController {
	private JFrame currentFrame;

	public void setFrame(JFrame currentFrame) {
		this.currentFrame = currentFrame;
	}

	public void indietro(JFrame previousFrame) {
		if (currentFrame != null) {
			currentFrame.dispose();
		}
		previousFrame.setVisible(true);
		setFrame(previousFrame);
	}
}
