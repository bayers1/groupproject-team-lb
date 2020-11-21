package starter;


import java.awt.Color;

/* File: GraphicsPane.java
 * -----------------------
 * Like you did with your own graphics programs, simply
 * extend from GraphicsPane and implement
 * as little or as much of the mouse listeners that you need
 * for your own programs.  Notice however that in this situation
 * There is no access to the GraphicsProgram window.
 * Make sure to distinguish between your constructor
 * and using showContents and hideContents
 */

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public abstract class GraphicsPane implements Interfaceable {
	
	public static final int WINDOW_WIDTH = 1280;
	public static final int WINDOW_HEIGHT = 600;
	public static final int REG_BUTTON_HEIGHT = 50;
	public static final int REG_BUTTON_WIDTH = 80;
	public static final int OUTSIDE_PADDING = 30;
	public static final int REG_PADDING = 15;
	public static final int RIGHT_BOTTOM = (WINDOW_WIDTH-REG_BUTTON_WIDTH) - OUTSIDE_PADDING;
	public static final int LEFT_BOTTOM = OUTSIDE_PADDING;
	public static final int BOTTOM = (WINDOW_HEIGHT-REG_BUTTON_HEIGHT) - OUTSIDE_PADDING;
	public static final int CENTER = WINDOW_WIDTH / 2;
	public static final Color BUTTON_COLOR = new Color(230, 230, 250);
	public static final Color FONT_COLOR = new Color(0, 0, 20);
	
	@Override
	public abstract void showContents();

	@Override
	public abstract void hideContents();

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
	}

	/**
	 * changes the style or look of the button
	 * when it is hovered
	 * @param button
	 */
	public void hover(GButton button) {
		Color highlightColor = new Color(100, 100, 120);
		button.setFillColor(highlightColor);
		button.setColor(Color.WHITE);
	}
	
	/**
	 * sets the style of the buttons to it's default style 
	 * when it is not hovered
	 * @param button
	 */
	public void notHovered(GButton button) {
		button.setFillColor(BUTTON_COLOR);
		button.setColor(FONT_COLOR);
	}

}
