package marsrover;

import java.util.ArrayList;
import java.util.List;
import marsrover.Rover.InvalidInputException;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Tempest
 */
public class RoverTest {

    /**
     * Test of setMoveCommands method, of class Rover.
     * @throws marsrover.Rover.InvalidInputException
     */
    @Test (expected=InvalidInputException.class)
    public void testConstructorExpectedInvalidException() throws Rover.InvalidInputException {
        Rover instance = new Rover(3, 2, 'F', 0);
        fail("testConstructorExpectedInvalidException failed");
    }

    /**
     * Test of getX method, of class Rover.
     * @throws marsrover.Rover.InvalidInputException
     */
    @Test
    public void testGetX() throws Rover.InvalidInputException {
        Rover instance = new Rover(3, 2, 'N', 0);
        int expResult = 3;
        int result = instance.getX();
        assertEquals(expResult, result);
    }

    /**
     * Test of getY method, of class Rover.
     * @throws marsrover.Rover.InvalidInputException
     */
    @Test
    public void testGetY() throws Rover.InvalidInputException {
        Rover instance = new Rover(3, 2, 'N', 0);
        int expResult = 2;
        int result = instance.getY();
        assertEquals(expResult, result);
    }

    /**
     * Test of getDirection method, of class Rover.
     * @throws marsrover.Rover.InvalidInputException
     */
    @Test
    public void testGetDirection() throws Rover.InvalidInputException {
        Rover instance = new Rover(3, 2, 'N', 0);
        char expResult = 'N';
        char result = instance.getDirection();
        assertEquals(expResult, result);
    }

    /**
     * Test of getPosition method, of class Rover.
     * @throws marsrover.Rover.InvalidInputException
     */
    @Test
    public void testGetPosition() throws Rover.InvalidInputException {
        Rover instance = new Rover(3, 2, 'N', 0);
        String expResult = "3 2 N";
        String result = instance.getPosition();
        assertEquals(expResult, result);
    }

    /**
     * Test of getId method, of class Rover.
     * @throws marsrover.Rover.InvalidInputException
     */
    @Test
    public void testGetId() throws Rover.InvalidInputException {
        Rover instance = new Rover(3, 2, 'N', 0);
        int expResult = 0;
        int result = instance.getId();
        assertEquals(expResult, result);
    }

    /**
     * Test of setMoveCommands method, of class Rover.
     * @throws marsrover.Rover.InvalidInputException
     */
    @Test
    public void testSetAndGetMoveCommands() throws Rover.InvalidInputException {
        String moveCommands = "LRM";
        Rover instance = new Rover(3, 2, 'N', 0);
        instance.setMoveCommands(moveCommands);
        String result = instance.getMoveCommands();
        assertEquals("LRM", result);
    }

    /**
     * Test of setMoveCommands method, of class Rover.
     * @throws marsrover.Rover.InvalidInputException
     */
    @Test (expected=InvalidInputException.class)
    public void testSetMoveCommandsExpectedInvalidException() throws Rover.InvalidInputException {
        String moveCommands = "A";
        Rover instance = new Rover(3, 2, 'N', 0);
        instance.setMoveCommands(moveCommands);
        fail("testSetMoveCommandsExpectedInvalidException failed");
    }

    /**
     * Test of validLocation method, of class Rover.
     * @throws marsrover.Plateau.InvalidSizeException
     * @throws marsrover.Rover.InvalidInputException
     */
    @Test
    public void testValidLocationWithinBounds() throws Rover.InvalidInputException, Plateau.InvalidSizeException {
        Plateau p = new Plateau(5,5);
        Rover r = new Rover(3, 2, 'N', 0);
        List<Rover> roversList = new ArrayList<>();
        roversList.add(r);
        boolean result = Rover.validLocation(2, 3, p, roversList);
        assertEquals(true, result);
    }

    /**
     * Test of validLocation method, of class Rover.
     * @throws marsrover.Plateau.InvalidSizeException
     * @throws marsrover.Rover.InvalidInputException
     */
    @Test
    public void testValidLocationOutsideBounds() throws Rover.InvalidInputException, Plateau.InvalidSizeException {
        Plateau p = new Plateau(5,5);
        Rover r = new Rover(3, 2, 'N', 0);
        List<Rover> roversList = new ArrayList<>();
        roversList.add(r);
        boolean result = Rover.validLocation(6, 7, p, roversList);
        assertEquals(false, result);
    }

    /**
     * Test of validLocation method, of class Rover.
     * @throws marsrover.Plateau.InvalidSizeException
     * @throws marsrover.Rover.InvalidInputException
     */
    @Test
    public void testValidLocationRoverCollision() throws Rover.InvalidInputException, Plateau.InvalidSizeException {
        Plateau p = new Plateau(5,5);
        Rover r = new Rover(3, 2, 'N', 0);
        List<Rover> roversList = new ArrayList<>();
        roversList.add(r);
        boolean result = Rover.validLocation(3, 2, p, roversList);
        assertEquals(false, result);
    }
    
    /**
     * Test of turnLeft method, of class Rover.
     * @throws marsrover.Rover.InvalidInputException
     */
    @Test
    public void testTurnLeft() throws Rover.InvalidInputException {
        Rover instance = new Rover(3, 2, 'N', 0);
        instance.turnLeft();
        assertEquals('W', instance.getDirection());
        instance.turnLeft();
        assertEquals('S', instance.getDirection());
        instance.turnLeft();
        assertEquals('E', instance.getDirection());
        instance.turnLeft();
        assertEquals('N', instance.getDirection());
    }

    /**
     * Test of turnRight method, of class Rover.
     * @throws marsrover.Rover.InvalidInputException
     */
    @Test
    public void testTurnRight() throws Rover.InvalidInputException {
        Rover instance = new Rover(3, 2, 'N', 0);
        instance.turnRight();
        assertEquals('E', instance.getDirection());
        instance.turnRight();
        assertEquals('S', instance.getDirection());
        instance.turnRight();
        assertEquals('W', instance.getDirection());
        instance.turnRight();
        assertEquals('N', instance.getDirection());
    }

    /**
     * Test of moveForward method, of class Rover.
     * @throws marsrover.Plateau.InvalidSizeException
     * @throws marsrover.Rover.InvalidInputException
     */
    @Test
    public void testMoveForward() throws Plateau.InvalidSizeException, Rover.InvalidInputException {
        Plateau p = new Plateau(5,5);
        List<Rover> roversList = new ArrayList<>();
        Rover instance = new Rover(3, 2, 'N', 0);
        Rover obstacle = new Rover(3, 4, 'N', 0);
        roversList.add(instance);
        roversList.add(obstacle);
        instance.moveForward(p, roversList);
        assertEquals("3 3 N", instance.getPosition());
        instance.moveForward(p, roversList);
        assertEquals("3 3 N", instance.getPosition());
    }

    /**
     * Test of executeMovementCommands method, of class Rover.
     * @throws marsrover.Plateau.InvalidSizeException
     * @throws marsrover.Rover.InvalidInputException
     */
    @Test
    public void testExecuteMovementCommands() throws Plateau.InvalidSizeException, Rover.InvalidInputException {
        Plateau p = new Plateau(5,5);
        List<Rover> roversList = new ArrayList<>();
        Rover instance = new Rover(3, 2, 'N', 0);
        instance.setMoveCommands("MLMLMRM");
        instance.executeMovementCommands(p, roversList);
        assertEquals("1 2 W", instance.getPosition());
    }
}
