import org.junit.Before;
import org.junit.Test;

import java.awt.*;
import java.awt.event.InputEvent;

import static org.junit.Assert.*;

public class PlanetSystemTest {

    @Before
    public void setUp() {
        Thread thread = new Thread() {
            public void run() {
                try {
                    PlanetSystem.main();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        ProgramLogic.allPlanets.clear();
        thread.start();
    }

    @Test
    public void dataTest() {
        SpaceObjects.Planet planet = new SpaceObjects.Planet(50, 1, Color.ORANGE);
        ProgramLogic.coordinates(planet);
        assertEquals(planet.x, 575.0, 0.0000);
        assertEquals(planet.y, 525.0, 0.0000);
        planet.angle += 90;
        ProgramLogic.coordinates(planet);
        assertEquals(planet.x, 725.0, 0.0000);
        assertEquals(planet.y, 450.0, 0.0000);
        assertNotEquals(planet.x, 575.0, 0.0000);
        assertNotEquals(planet.y, 525.0, 0.0000);
    }

    @Test
    public void testButtons() throws AWTException, InterruptedException {

        GraphicsEnvironment graphicsEnvironment = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice graphicsDevice = graphicsEnvironment.getDefaultScreenDevice();

        Thread.sleep(1000);
        Robot robot = new Robot(graphicsDevice);



        //Start button
        robot.mouseMove(1000 + 600, 1000 - 860);
        Thread.sleep(100);
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
        Thread.sleep(100);
        assertTrue(ProgramLogic.start[0]);

        //Pause button
        robot.mouseMove(1600, 140 + 90);
        Thread.sleep(100);
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
        Thread.sleep(100);
        assertTrue(ProgramLogic.pause[0]);

        //Play
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
        Thread.sleep(100);
        assertFalse(ProgramLogic.pause[0]);

        //Slower button
        robot.mouseMove(1600 - 100,  230 + 40);
        Thread.sleep(100);
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
        Thread.sleep(100);
        assertEquals(ProgramLogic.coefficientOfSpeed[0], 0.95, 0.000000);

        //Faster button
        robot.mouseMove(1500 + 150,  230 + 40);
        Thread.sleep(100);
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
        Thread.sleep(100);
        assertEquals(ProgramLogic.coefficientOfSpeed[0], 1.15, 0.000000);

        //Normal button
        robot.mouseMove(1650 - 100,  330 + 40);
        Thread.sleep(100);
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
        Thread.sleep(100);
        assertEquals(ProgramLogic.coefficientOfSpeed[0], 1, 0.00000);

        //ScaleUp button
        robot.mouseMove(1550 - 50,  430 + 40);
        Thread.sleep(100);
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
        Thread.sleep(100);
        assertEquals(ProgramLogic.scaleChange, 1);

        //Left button
        robot.mouseMove(1550 + 50,  550 + 40);
        Thread.sleep(100);
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
        Thread.sleep(100);
        assertEquals(ProgramLogic.horizontalChange, 1);

        //Down button
        robot.mouseMove(1550,  590 + 40);
        Thread.sleep(100);
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
        Thread.sleep(100);
        assertEquals(ProgramLogic.verticalChange, 1);

        //Left button
        robot.mouseMove(1550 - 50,  550 + 40);
        Thread.sleep(100);
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
        Thread.sleep(100);
        assertEquals(ProgramLogic.horizontalChange, 0);

        //Up button
        robot.mouseMove(1550,  510 + 40);
        Thread.sleep(100);
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
        Thread.sleep(100);
        assertEquals(ProgramLogic.verticalChange, 0);

        //ScaleDown button
        robot.mouseMove(1550 + 50,  430 + 40);
        Thread.sleep(100);
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
        Thread.sleep(100);
        assertEquals(ProgramLogic.scaleChange, 0);

        //Customize button
        robot.mouseMove(1550 + 50,  730 + 40);
        Thread.sleep(100);
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
        Thread.sleep(100);
        robot.mouseMove(1120, 100);
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        robot.mouseMove(1200, 100);
        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
        Thread.sleep(500);
        assertEquals(ProgramLogic.allPlanets.get(0).size, 23);
        Thread.sleep(100);


        robot.mouseMove(1385, 100);
        Thread.sleep(100);
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        Thread.sleep(100);
        robot.mouseMove(1465, 100);
        Thread.sleep(100);
        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
        Thread.sleep(100);
        assertEquals(ProgramLogic.allPlanets.get(0).coefficientOfMass, 0.2, 0.00000);

        //Colors
        robot.mouseMove(1655,  105);
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
        Thread.sleep(100);
        robot.mouseMove(1695,  145);
        Thread.sleep(100);
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
        Thread.sleep(500);
        assertEquals(ProgramLogic.allPlanets.get(0).color, Color.WHITE);
    }
}
