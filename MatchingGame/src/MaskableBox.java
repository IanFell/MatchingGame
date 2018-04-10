import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;

/**
 * Class for masking boxes.  
 * A masked box is one that has not been clicked.
 * 
 * @author Ian Fell
 *
 */
public class MaskableBox extends ClickableBox {

	// Determines if box should be masked.
	private boolean mask;

	// Color to mask box.
	private Color maskColor;

	// Container to hold components.
	Container parent;

	/**
	 * Constructor.
	 * 
	 * @param int x
	 * @param int y
	 * @param int width
	 * @param int height
	 * @param Color borderColor
	 * @param Color backColor
	 * @param boolean drawBorder
	 * @param Container parent
	 */
	public MaskableBox(
			int x, 
			int y, 
			int width, 
			int height, 
			Color borderColor,
			Color backColor, 
			boolean drawBorder, 
			Container parent,
			boolean mask
			) {
		super(x, y, width, height, borderColor, backColor, drawBorder, parent);
		this.parent = parent;
	}

	/**
	 * 
	 * @param Graphics g
	 */
	public void draw(Graphics g) {
		if(mask) {
			setOldColor(g.getColor());
			g.setColor(maskColor);
			g.fillRect(getX(), getY(), getWidth(), getHeight());
			if(isDrawBorder()) {
				g.setColor(getBorderColor());
				g.drawRect(getX(), getY(), getWidth(), getHeight());
			}
			g.setColor(getOldColor());
		} else {
			super.draw(g);
		}
	}

	/**
	 * 
	 * @return boolean
	 */
	public boolean isMask() {
		return mask;
	}

	/**
	 * 
	 * @param boolean mask
	 */
	public void setMask(boolean mask) {
		this.mask = mask;
	}

	/**
	 * 
	 * @return Color
	 */
	public Color getMaskColor() {
		return maskColor;
	}

	/**
	 * 
	 * @param Color maskColor
	 */
	public void setMaskColor(Color maskColor) {
		this.maskColor = maskColor;
	}
}        

