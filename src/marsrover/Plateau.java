package marsrover;

/**
 *
 * @author Tempest
 */
public class Plateau {

    private int plateauWidth;
    private int plateauHeight;

    /**
     * Constructs the Plateau object.
     * 
     * @param topY - Y coordinate of the North-Easternmost point of the plateau
     * @param rightX - X coordinate of the North-Easternmost point of the
     * plateau
     * 
     * @throws marsrover.Plateau.InvalidSizeException if topX or topY is a
     * negative number
     */
    public Plateau(int topY, int rightX) throws InvalidSizeException {
        if (topY < 0 || rightX < 0) {
            throw new InvalidSizeException("Plateau top and right coordinates must be greater than 0!");
        }
        // add 1 to top and right values to get the width and height because we assume that:
        // a: a set of coordinates defines one grid block 
        // b: there must exist a plateau of at least size 1x1
        // c: the bottom left square is (0,0) and can be occupied by a rover
        // d: the square directly North from (x, y) is (x, y + 1)
        plateauWidth = topY + 1;
        plateauHeight = rightX + 1;
    }

    /**
     * Get width of the plateau
     * 
     * @return integer representing the width of the plateau
     */
    public int getWidth() {
        return this.plateauWidth;
    }

    /**
     * Get height of the plateau
     * 
     * @return integer representing the height of the plateau
     */
    public int getHeight() {
        return this.plateauHeight;
    }

    /**
     * Exception handler for invalid plateau size (negative dimension(s)).
     * 
     */
    public static class InvalidSizeException extends Exception {
        public InvalidSizeException(String message) {
            System.out.println(message);
        }
    }
}
