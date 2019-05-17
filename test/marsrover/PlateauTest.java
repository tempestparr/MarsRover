package marsrover;

import marsrover.Plateau.InvalidSizeException;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Tempest
 */
public class PlateauTest {

    /**
     * Test of getWidth method, of class Plateau.
     */
    @Test
    public void testGetWidth() {
        try {
            Plateau instance = new Plateau(4, 3);
            int expResult = 5;
            int result = instance.getWidth();
            assertEquals(expResult, result);
        } catch (Plateau.InvalidSizeException ex) {
            ex.printStackTrace();
        }
    }
    
    /**
     * Test of getWidth method, of class Plateau.
     */
    @Test
    public void testGetWidthWithZero() {
        try {
            Plateau instance = new Plateau(0, 3);
            int expResult = 1;
            int result = instance.getWidth();
            assertEquals(expResult, result);
        } catch (Plateau.InvalidSizeException ex) {
            ex.printStackTrace();
        }
    }
    
    /**
     * Test of getWidth method, of class Plateau.
     * @throws marsrover.Plateau.InvalidSizeException
     */
    @Test (expected=InvalidSizeException.class)
    public void checkExpectedInvalidSizeExceptionForWidth() throws InvalidSizeException {
        Plateau p = new Plateau(-1, 4);
        fail("checkExpectedInvalidSizeExceptionForWidth failed");
    }

    /**
     * Test of getHeight method, of class Plateau.
     */
    @Test
    public void testGetHeight() {
        try {
            Plateau instance = new Plateau(8, 6);
            int expResult = 7;
            int result = instance.getHeight();
            assertEquals(expResult, result);
        } catch (Plateau.InvalidSizeException ex) {
            ex.printStackTrace();
        }
    }
    
     /**
     * Test of getHeight method, of class Plateau.
     */
    @Test
    public void testGetHeightWithZero() {
        try {
            Plateau instance = new Plateau(8, 0);
            int expResult = 1;
            int result = instance.getHeight();
            assertEquals(expResult, result);
        } catch (Plateau.InvalidSizeException ex) {
            ex.printStackTrace();
        }
    }
    
     /**
     * Test of getHeight method, of class Plateau.
     * @throws marsrover.Plateau.InvalidSizeException
     */
    @Test (expected=InvalidSizeException.class)
    public void checkExpectedInvalidSizeExceptionForHeight() throws InvalidSizeException {
        Plateau p = new Plateau(4, -4);
        fail("checkExpectedInvalidSizeExceptionForWidth failed");
    }
}
