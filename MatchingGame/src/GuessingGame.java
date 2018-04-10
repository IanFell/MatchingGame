import java.applet.Applet;
import java.awt.Button;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Main class for guessing game.
 * 
 * @author Ian Fell
 *
 */
public class GuessingGame extends Applet {

	// Needed to sign applet.
	private static final long serialVersionUID = 54321;

	// Starting X, Y coordinates.
	private final int START_X = 20;
	private final int START_Y = 40;

	// Number of rows and columns.
	private final int ROWS = 4;
	private final int COLS = 4;

	// Box dimensions.
	private final int BOX_WIDTH  = 100;
	private final int BOX_HEIGHT = 100;

	// Used to keep track of boxes that have been matched.
	private boolean matchedBoxes[][];

	// Used to keep track of two boxes that have been clicked.
	private MaskableBox chosenBoxes[];

	// Number of matches user has made.
	int matches = 0;

	// Initial 'upside down' boxes.
	private MaskableBox boxes[][];

	// Array of available box colors.
	private Color boxColors[][];

	// Button to reset for new game.
	private Button resetButton;

	/**
	 * Initialize game.
	 */
	public void init() {
		setSize(600, 600);
		boxes       = new MaskableBox[ROWS][COLS];
		boxColors   = new Color[ROWS][COLS];
		resetButton = new Button("Reset Colors");
		resetButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				randomizeColors();
				buildBoxes();
				repaint();
			}
		});
		add(resetButton);
		randomizeColors();
		buildBoxes();
	}

	/**
	 * @param Graphics g
	 */
	public void paint(Graphics g) {
		// Loop through the boxes rows
		for (int row = 0; row < boxes.length; row++) {
			// Loop through each rows columns.
			for (int col = 0; col < boxes[row].length; col++) {
				// If we are at a clicked box.
				if (boxes[row][col].isClicked()) {
					// Un click it
					boxes[row][col].setClicked(false);
					// Only do game logic on boxes that haven't been matched.
					if (!matchedBoxes[row][col]) {
						gameLogic(boxes[row][col]);
					}
				}
			}
		}
		// Loop through the boxes and draw them.
		for (int row = 0; row < boxes.length; row++) {
			for (int col = 0; col < boxes[row].length; col++) {
				boxes[row][col].draw(g);
			}
		}
		g.drawString("MATCHING GAME", 100, 20);
		g.drawString("MATCHES:   " + matches, 200, 550);
	}

	/**
	 * 
	 * @param MaskableBox box
	 */
	private void gameLogic(MaskableBox box){
		if ((chosenBoxes[0] != null)&&(chosenBoxes[1] != null)) { 
			// We need to check to see if their colors match.
			if (chosenBoxes[0].getBackColor() == chosenBoxes[1].getBackColor()) {
				/**
				 * We need to set each of their corresponding matchedBoxes elements
				 * to true so game logic won't be called if those boxes are clicked
				 * again.
				 */
				matches++;
				for(int i = 0; i < chosenBoxes.length; i++) {
					for(int row = 0; row < boxes.length; row++) {
						for(int col = 0; col < boxes[row].length; col++) {
							if (boxes[row][col] == chosenBoxes[i]) {
								matchedBoxes[row][col] = true;
								break;
							}
						}
					}
				}
			}
			else { 
				// The background colors do not match, set the masks to true.
				chosenBoxes[0].setMask(true);
				chosenBoxes[1].setMask(true);
			}

			/**
			 * Reset the chosenBoxes array elements to null and 
			 * set the chosenBoxes array to equal a brand new array of size 2.
			 */
			chosenBoxes = new MaskableBox[2];
		}

		if (chosenBoxes[0] == null) {
			/**
			 * We have no boxes already chosen.
			 * Set the FIRST chosenBoxes equal to the parameter of the method.
			 * 
			 */
			chosenBoxes[0] = box;
			chosenBoxes[0].setMask(false);
			return;
		}
		else { 
			if (chosenBoxes[1] == null) {
				/**
				 * We have one box already chosen.
				 * Set the SECOND chosenBoxes equal to the parameter of the method.
				 * Set mask value to false to see color.
				 */
				chosenBoxes[1] = box;
				chosenBoxes[1].setMask(false);

			}
		}
	}

	/**
	 * Removes mouse listeners from boxes.
	 */
	private void removeMouseListeners() {
		for(int row = 0; row < boxes.length; row ++) {
			for(int col = 0; col < boxes[row].length; col++) {
				removeMouseListener(boxes[row][col]);
			}
		}
	}

	/**
	 * Build game board (all the boxes).
	 */
	private void buildBoxes() {
		// Need to clear any chosen boxes when building new array.
		chosenBoxes = new MaskableBox[2];
		// Create a new matchedBoxes array.
		matchedBoxes = new boolean [ROWS][COLS];

		removeMouseListeners();
		for(int row = 0; row < boxes.length; row++) {
			for(int col = 0; col < boxes[row].length; col++) {
				boxes[row][col] = 
						new MaskableBox(
								START_X + col * BOX_WIDTH,
								START_Y + row * BOX_HEIGHT,
								BOX_WIDTH,
								BOX_HEIGHT,
								Color.gray,
								boxColors[row][col],
								true,
								this, 
								true
								);

				//Every time we create a new Box it has to be masked
				boxes[row][col].setMask(true);;
				addMouseListener(boxes[row][col]);
			}
		}
	}

	/**
	 * Randomizes box colors.
	 */
	private void randomizeColors() {
		int[] chosenColors = {0, 0, 0, 0, 0, 0, 0, 0};
		Color[] availableColors = { 
				Color.red, 
				Color.blue, 
				Color.green,
				Color.yellow, 
				Color.cyan, 
				Color.magenta, 
				Color.pink, 
				Color.orange
		};
		for(int row = 0; row < boxes.length; row++) {
			for(int col = 0; col < boxes[row].length; col++) {
				for(;;) {
					int rnd = (int)(Math.random() * 8);
					if(chosenColors[rnd] < 2) {
						chosenColors[rnd]++;
						boxColors[row][col] = availableColors[rnd];
						break;
					}
				}
			}
		}
	}
}

