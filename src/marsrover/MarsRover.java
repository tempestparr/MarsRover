package marsrover;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import marsrover.Plateau.InvalidSizeException;

/**
 *
 * @author Tempest
 */
public class MarsRover {

    /**
     * Main method of the class
     * 
     * Assumes that the input comes from the text file named input.txt located
     * in the same folder as the program.
     * 
     * In case of incorrect input or file not found the method outputs error
     * messages to the console.
     * 
     * In case of successful run, outputs final rover positions to the console.
     * 
     * @param args  - not using command line arguments so those will be ignored
     * @throws marsrover.Rover.InvalidInputException
     */
    public static void main(String[] args) throws Rover.InvalidInputException {
        Scanner input = null;
        String inFileName = "input.txt";

        List<Rover> roversList = new ArrayList<>();

        try {
            input = new Scanner(new File(inFileName));
        } catch (FileNotFoundException e) {
            System.out.println("Can't open file - " + e);
        }

        if (input != null) {
            // process first line for plateau initialization
            if (input.hasNextLine()) {
                Plateau plateau = initializePlateau(input);
                
                // process the rest of the file for rovers and commands
                initializeRovers(input, roversList, plateau);

                // move rovers using the stored commands
                moveRovers(plateau, roversList);

                // output the final positions of the rovers to the console
                printRoverPositions(roversList);
            } else {
                System.out.println("Input file does not contain plateau top-right coordinates!");
                System.out.println("Exiting program now. Please try again.");
                System.exit(1);
            }
        }
    }

    /**
     * Outputs the locations of the rovers to the console
     * 
     * @param roversList - list of Rover objects
     */
    public static void printRoverPositions(List<Rover> roversList) {
        for (int i = 0; i < roversList.size(); i++) {
            Rover r = roversList.get(i);
            System.out.println(r.getPosition());
        }
    }

    /**
     * Initialize the plateau object based on the values in the file.
     * 
     * @param input - the Scanner object opened on the input file
     * @return Plateau object with size based on values from the input file
     */
    public static Plateau initializePlateau(Scanner input) {
        Plateau plateau = null;
        try {
            String[] tokens = input.nextLine().trim().split(" ");
            if (tokens.length != 2) {
                throw new InvalidSizeException("Plateau requires top and right coordinates!");
            } else {
                int x = Integer.parseInt(tokens[0]);
                int y = Integer.parseInt(tokens[1]);
                plateau = new Plateau(x, y);
            }
        } catch (InvalidSizeException e) {
            System.out.println(e + ": Unable to initialize plateau.");
        }
        return plateau;
    }

    /**
     * Parses the input file and stores the initial locations of rovers and
     * their move commands into Rover objects that are stored in the roversList.
     * 
     * @param input - the Scanner object opened on the input file
     * @param roversList - a list of Rover objects
     * @param p - the Plateau object to be 
     * @throws marsrover.Rover.InvalidInputException 
     */
    public static void initializeRovers(Scanner input, List<Rover> roversList, Plateau p) throws Rover.InvalidInputException {
        int roverId = 0;
        
        while (input.hasNextLine()) {
            String line = input.nextLine();

            // get the next line if the last one was empty
            if (line.equals("")) {
                line = input.nextLine();
            }

            String[] tokens = line.trim().split(" ");

            int x = Integer.parseInt(tokens[0]);
            int y = Integer.parseInt(tokens[1]);
            char direction = tokens[2].toUpperCase().charAt(0);
            // check if the rover can be placed at that location
            if (Rover.validLocation(x, y, p, roversList)) {
                // create rover and add it to the list
                Rover rover = new Rover(x, y, direction, roverId);
                roversList.add(rover);
                roverId++;

                // Skip over empty line after rover position and the command line.
                // Assume that every rover has a line following it with commands.
                line = input.nextLine();
                if (line.equals("")) {
                    line = input.nextLine();
                }
                rover.setMoveCommands(line);
            } else {
                System.out.println("Could not deploy rover at " + line);
                // Skip over the move commands of the rover that couldn't be deployed.
                input.nextLine();
                input.nextLine();
            }
        }
    }
    
    /**
     * Execute rover movement commands on each rover in the roversList.
     * 
     * @param p - the initialized Plateau object
     * @param roversList - list of Rover objects
     */
    public static void moveRovers(Plateau p, List<Rover> roversList) {
        for (Rover rover : roversList) {
            rover.executeMovementCommands(p, roversList);
        }
    }
}
