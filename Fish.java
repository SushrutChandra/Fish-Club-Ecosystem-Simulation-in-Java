package fishPond;

import cmsc131Utilities.Random131;

/**
 * The state of a fish consists of its position in the pond (row and         
 * column), it's size, and the direction in which it is moving (UP, DOWN,    
 * LEFT, or RIGHT).                                                          
 * <p>                                                                 
 * A fish moves, eats other fish, eats plants, and shrinks over time.        
 * <p>                                                                                                                
 *                                                                           
 * @author Sushrut Chandra          
 */

public class Fish {

	/** Initial size of each fish when simulation begins */
	public static final int FISH_STARTING_SIZE = 100;

	/** Maximum size for a fish */
	public static final int MAX_FISH_SIZE = 1000;

	/** Code for "Left" fish direction */
	public static final int LEFT = 10;

	/** Code for "Right" fish direction */
	public static final int RIGHT = 11;

	/** Code for "Up" fish direction */
	public static final int UP = 12;

	/** Code for "Down" fish direction */
	public static final int DOWN = 13;

	/*State of this fish.  YOU MAY NOT ADD ANY FIELDS! */
	private int row, col, size, direction;

	/** Simply initializes the state of the fish with these parameters */
	public Fish(int row, int col, int size, int direction) {		
		this.row = row;
		this.col = col;
		this.size = size;
		this.direction = direction;
	}

	/** Standard copy constructor -- just copies the fields */
	public Fish(Fish x) {
		this(x.row, x.col, x.size, x.direction);
	}

	/** Fish size increased by nutritionalValue. */
	public void eat(int nutritionalValue) {
		size += nutritionalValue;
	}

	/** Returns true if size is greater than zero, false otherwise */
	public boolean isAlive() {
		return (size > 0);
	}

	/** Size is decreased by TWO.  */
	public void shrink() { 
		size -= 2;
	}

	/* This fish eats the other fish.  I.e. This fish's size is increased by
	 * the size of the fish "other".  The size of "other" is set to 0. */
	private void eat(Fish other) {
		size += other.size;
		other.size = 0;
	}

	/** Returns a string for the object */
	public String toString() {
		return "Size:" + size + " row:" + row + " col:" + col + 
				" direction:" + direction;
	}

	/** The current object battles the parameter (other). Whichever one is
	 *  larger eats the other by calling the private "eat" method.  In cases
	 *  where the sizes of the two fish are exactly equal, have the current
	 *  object win. */
	public void fight(Fish other) {
		if (this.size >= other.size) {
			this.eat(other);
		} else {
			other.eat(this);
		}
	}

	/**The fish's location (row or col) is adjusted by ONE unit, depending on the
	 * fish's current direction.  For example, if the current direction is "UP", 
	 * then the fish's row should be decremented.
	 * <p>
	 * If the fish's current direction is not equal to one of the static constants 
	 * UP, DOWN, LEFT, or RIGHT, then this method will throw an 
	 * IllegalFishDirectionException, passing the fish's direction to constructor. 
	 */
	public void move() {
		if (direction == UP) {
			row--;
		} else if (direction == DOWN) {
			row++;
		} else if (direction == RIGHT) {
			col++; 
		} else if (direction == LEFT) {
			col--;
		} else { //Throw exception
			throw new IllegalFishDirectionException(direction);
		}
	}

	/**The fish's direction is randomly determined (UP, DOWN, LEFT or RIGHT).  
	 * Sometimes the resulting direction will be the same as the original one.
	 * <p>
	 * <p>
	 * If the value is 0, set the direction to UP.  If 1, set to DOWN.  If 2, 
	 * set to LEFT.  If 3, set to RIGHT. */
	public void setRandomDirection() {

		switch (Random131.getRandomInteger(4)) {
		case 0:
			direction = UP;
			break;
		case 1:
			direction = DOWN;
			break;
		case 2:
			direction = LEFT;
			break;
		case 3:
			direction = RIGHT;
			break;
		}
	}

	/** Returns size */
	public int getSize() {
		return size;
	}

	/** Returns row */
	public int getRow() {
		return row;
	}

	/** Returns column */
	public int getCol() {
		return col;
	}

	/** Returns direction (UP, DOWN, LEFT, or RIGHT) */
	public int getDirection() {
		return direction;
	}
}
