package marsrover;

import java.util.List;

/**
 *
 * @author Tempest
 */
public class Rover {
    private int xCoord;
    private int yCoord;
    private char direction;
    private final int id;
    private String moveCommands;
    private final String cardinalDirections = "NESW";
    private final String validMovements = "LRM";

    /**
     * Constructs a Rover object.
     * 
     * @param x - X coordinate of the rover
     * @param y - Y coordinate of the rover
     * @param direction - cardinal direction the rover is facing
     * @param id - the rover ID number
     * @throws marsrover.Rover.InvalidInputException - thrown if given invalid
     * cardinal direction
     */
    public Rover(int x, int y, char direction, int id) throws InvalidInputException {
        this.xCoord = x;
        this.yCoord = y;
        if (cardinalDirections.indexOf(direction) == -1) {
            throw new InvalidInputException("Direction must be N, S, E, or W.");
        }
        this.direction = direction;
        this.id = id;
        this.moveCommands = "";
    }

    /**
     * Gets the X coordinate of the rover.
     * 
     * @return integer representing the X coordinate of the rover.
     */
    public int getX() {
        return xCoord;
    }

    /**
     * Gets the Y coordinate of the rover.
     * 
     * @return integer representing the Y coordinate of the rover.
     */
    public int getY() {
        return yCoord;
    }

    /**
     * Gets the cardinal direction of the rover.
     * 
     * @return 'N', 'W', 'S', or 'E'
     */
    public char getDirection() {
        return direction;
    }
    
    /**
     * Returns a string representing the position and direction of the rover.s
     * 
     * @return String of format "x y D" where x and y are decimal numbers
     * representing X and Y coordinates of the rover and D is a capital letter
     * N, W, S, or E, representing the cardinal direction where the rover is
     * pointing
     */
    public String getPosition() {
        return this.xCoord + " " + this.yCoord + " " + this.direction;
    }

    /**
     * Get ID number of the rover.
     * 
     * @return integer representing the ID number of the rover.
     */
    public int getId() {
        return id;
    }
    
    /**
     * Get the move commands for the rover.
     * 
     * @return String of characters M, L, and/or R.
     */
    public String getMoveCommands() {
        return moveCommands;
    }
    
    /**
     * Accepts a string with move commands and stores them in a field in the
     * Rover object.
     * 
     * @param moveCommands String of characters M, L, and/or R.
     * @throws marsrover.Rover.InvalidInputException if the input string has
     * any characters other than M, L, and R.
     */
    public void setMoveCommands(String moveCommands) throws InvalidInputException {
        for (int i = 0; i < moveCommands.length(); i++) {
            if (validMovements.indexOf(moveCommands.charAt(i)) == -1) {
                throw new InvalidInputException("Movement command must be L, R, or M.");
            }
        }
        this.moveCommands = moveCommands.toUpperCase();
    }
    
    /**
     * Static method checking whether the location is valid given an initialized
     * Plateau object and a list of rovers.
     * 
     * A location is considered valid if it's within the bounds of the plateau
     * and no rover is already located at that location.
     * 
     * @param x X coordinate of the location to test
     * @param y Y coordinate of the location to test
     * @param p the initialized Plateau object
     * @param roversList list of Rover objects
     * @return true if the location is valid, otherwise false.
     */
    public static boolean validLocation(int x, int y, Plateau p, List<Rover> roversList) {
        int width = p.getWidth();
        int height = p.getHeight();
        boolean result = false;
        if (x >= 0 && y >= 0 && x < width && y < height) {
            result = true;
        }
        
        for (Rover r : roversList) {
            if (r.getX() == x && r.getY() == y) {
                return false;
            }
        }
        return result;
    }

    /**
     * Turn the rover 90 degrees to the left.
     */
    public void turnLeft() {
        switch (direction) {
            case 'N':
                direction = 'W';
                break;
            case 'W':
                direction = 'S';
                break;
            case 'S':
                direction = 'E';
                break;
            case 'E':
                direction = 'N';
                break;
        }
    }
    
    /**
     * Turn the rover 90 degrees to the right.
     */
    public void turnRight() {
        switch (direction) {
            case 'N':
                direction = 'E';
                break;
            case 'E':
                direction = 'S';
                break;
            case 'S':
                direction = 'W';
                break;
            case 'W':
                direction = 'N';
                break;
        }
    }
    
    /**
     * Move the rover forward.
     * 
     * If facing North or South, change the y position.
     * If facing East or West, change the x position.
     * If the rover attempts to move outside of the plateau bounds or to a
     * location occupied by another rover, then don't move forward.
     * 
     * @param p initialized Plateau object
     * @param roversList list of Rover objects
     */
    public void moveForward(Plateau p, List<Rover> roversList) {
        switch (direction) {
            case 'N':
                if (validLocation(xCoord, yCoord + 1, p, roversList)) {
                    yCoord++;
                }
                break;
            case 'S':
                if (validLocation(xCoord, yCoord - 1, p, roversList)) {
                    yCoord--;
                }
                break;
            case 'E':
                if (validLocation(xCoord + 1, yCoord, p, roversList)) {
                    xCoord++;
                }
                break;
            case 'W':
                if (validLocation(xCoord - 1, yCoord, p, roversList)) {
                    xCoord--;
                }
                break;
        }
    }
    
    /**
     * Executes the rover's movement commands.
     * 
     * @param p initialized Plateau object
     * @param roversList list of Rover objects
     */
    public void executeMovementCommands(Plateau p, List<Rover> roversList) {
        for (int i = 0; i < moveCommands.length(); i++) {
            char command = moveCommands.charAt(i);
            switch (command) {
                case 'L':
                    turnLeft();
                    break;
                case 'R':
                    turnRight();
                    break;
                case 'M':
                    moveForward(p, roversList);
                    break;
            }
        }
    }

    /**
     * Exception thrown when provided unexpected input, such as rover direction
     * that is not one of N, W, S, or E, or a movement command that is not one
     * of L, M, or R.
     */
    public static class InvalidInputException extends Exception {

        public InvalidInputException(String message) {
            System.out.println(message);
        }
    }
}
