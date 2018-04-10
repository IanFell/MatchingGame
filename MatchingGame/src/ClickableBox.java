import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Container;

/**
 * Class for a box that can be clicked.
 * 
 * @author Ian Fell
 *
 */
public class ClickableBox extends MouseAdapter {

	// X, Y position and dimensions of box.
	private int x, y, width, height;

	// Necessary box colors.
	private Color borderColor, backColor, oldColor;

	// Determines if we should draw border around box.
	private boolean drawBorder;

	// Determines if box has been clicked.
	private boolean clicked;

	// Container to hold components.
	private Container parent;

	/**
	 * Constructor.
	 * 
	 * @param int       x
	 * @param int       y
	 * @param int       width
	 * @param int       height
	 * @param Color     borderColor
	 * @param Color     backColor
	 * @param booelan   drawBorder
	 * @param Container parent
	 */
	public ClickableBox(
			int x, 
			int y, 
			int width, 
			int height, 
			Color borderColor,
			Color backColor, 
			boolean drawBorder, 
			Container parent
			) {
		this.x           = x;
		this.y           = y;
		this.width       = width;
		this.height      = height;
		this.borderColor = borderColor;
		this.backColor   = backColor;
		this.drawBorder  = drawBorder;
		this.parent      = parent;
	}

	/**
	 * Draw boxes.
	 * 
	 * @param Graphics g
	 */
	public void draw(Graphics g) {
		oldColor = g.getColor();
		g.setColor(backColor);
		g.fillRect(x, y, width, height);
		if(drawBorder) {
			g.setColor(borderColor);
			g.drawRect(x, y, width, height);
		}
		g.setColor(oldColor);
	}

	/**
	 * If mouse is released, set click to true and repaint to change box color.
	 */
	public void mouseReleased(MouseEvent e) {
		if(x < e.getX() && e.getX() < x + width && y < e.getY() && e.getY() < y + height) {
			clicked = true;
			parent.repaint();
		}
	}

	/**
	 * 
	 * @return boolean
	 */
	public boolean isClicked() {
		return clicked;
	}

	/**
	 * 
	 * @param boolean clicked
	 */
	public void setClicked(boolean clicked) {
		this.clicked = clicked;
	}

	/**
	 * 
	 * @return int
	 */
	public int getX() {
		return x;
	}

	/**
	 * 
	 * @param int x
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * 
	 * @return int
	 */
	public int getY() {
		return y;
	}

	/**
	 * 
	 * @param int y
	 */
	public void setY(int y) {
		this.y = y;
	}

	/**
	 * 
	 * @return int
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * 
	 * @param int width
	 */
	public void setWidth(int width) {
		this.width = width;
	}

	/**
	 * 
	 * @return int
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * 
	 * @param int height
	 */
	public void setHeight(int height) {
		this.height = height;
	}

	/**
	 * 
	 * @return Color
	 */
	public Color getBorderColor() {
		return borderColor;
	}

	/**
	 * 
	 * @param Color borderColor
	 */
	public void setBorderColor(Color borderColor) {
		this.borderColor = borderColor;
	}

	/**
	 * 
	 * @return Color
	 */
	public Color getBackColor() {
		return backColor;
	}

	/**
	 * 
	 * @param Color backColor
	 */
	public void setBackColor(Color backColor) {
		this.backColor = backColor;
	}

	/**
	 * 
	 * @return Color
	 */
	public Color getOldColor() {
		return oldColor;
	}

	/**
	 * 
	 * @param Color oldColor
	 */
	public void setOldColor(Color oldColor) {
		this.oldColor = oldColor;
	}

	/**
	 * 
	 * @return boolean
	 */
	public boolean isDrawBorder() {
		return drawBorder;
	}

	/**
	 * 
	 * @param boolean drawBorder
	 */
	public void setDrawBorder(boolean drawBorder) {
		this.drawBorder = drawBorder;
	}
}

