import org.junit.Test;

import java.awt.*;
import java.awt.event.InputEvent;

import static org.junit.Assert.*;

public class PlanetSystemTest {
    PlanetSystem planetSystem;

    @Test
    public void dataTest() {
        SpaceObjects.Planet planet = new SpaceObjects.Planet(50, 1, Color.ORANGE, 1);
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

        Thread thread = new Thread() {
            public void run() {
                try {
                    planetSystem = new PlanetSystem(true);
                    planetSystem.start();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        thread.start();

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
        assertTrue(planetSystem.programLogic.getStart());

        //Pause button
        robot.mouseMove(1600, 140 + 90);
        Thread.sleep(100);
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
        Thread.sleep(100);
        assertTrue(planetSystem.programLogic.getPause());

        //Play
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
        Thread.sleep(100);
        assertFalse(planetSystem.programLogic.getPause());

        //Slower button
        robot.mouseMove(1600 - 100,  230 + 40);
        Thread.sleep(100);
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
        Thread.sleep(100);
        assertEquals(planetSystem.programLogic.getCoefficientOfSpeed(), 0.95, 0.000000);

        //Faster button
        robot.mouseMove(1500 + 150,  230 + 40);
        Thread.sleep(100);
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
        Thread.sleep(100);
        assertEquals(planetSystem.programLogic.getCoefficientOfSpeed(), 1.15, 0.000000);

        //Normal button
        robot.mouseMove(1650 - 100,  330 + 40);
        Thread.sleep(100);
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
        Thread.sleep(100);
        assertEquals(planetSystem.programLogic.getCoefficientOfSpeed(), 1, 0.00000);

        //ScaleUp button
        robot.mouseMove(1550 - 50,  430 + 40);
        Thread.sleep(100);
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
        Thread.sleep(100);
        assertEquals(planetSystem.programLogic.getScaleChange(), 1);

        //Left button
        robot.mouseMove(1550 + 50,  550 + 40);
        Thread.sleep(100);
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
        Thread.sleep(100);
        assertEquals(planetSystem.programLogic.getHorizontalChange(), 1);

        //Down button
        robot.mouseMove(1550,  590 + 40);
        Thread.sleep(100);
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
        Thread.sleep(100);
        assertEquals(planetSystem.programLogic.getVerticalChange(), 1);

        //Left button
        robot.mouseMove(1550 - 50,  550 + 40);
        Thread.sleep(100);
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
        Thread.sleep(100);
        assertEquals(planetSystem.programLogic.getHorizontalChange(), 0);

        //Up button
        robot.mouseMove(1550,  510 + 40);
        Thread.sleep(100);
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
        Thread.sleep(100);
        assertEquals(planetSystem.programLogic.getVerticalChange(), 0);

        //ScaleDown button
        robot.mouseMove(1550 + 50,  430 + 40);
        Thread.sleep(100);
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
        Thread.sleep(100);
        assertEquals(planetSystem.programLogic.getScaleChange(), 0);

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
        assertEquals(planetSystem.programLogic.getAllPlanets().get(0).size, 23);
        Thread.sleep(100);


        robot.mouseMove(1385, 100);
        Thread.sleep(100);
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        Thread.sleep(100);
        robot.mouseMove(1465, 100);
        Thread.sleep(100);
        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
        Thread.sleep(100);
        assertEquals(planetSystem.programLogic.getAllPlanets().get(0).coefficientOfMass, 0.2, 0.00000);

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
        assertEquals(planetSystem.programLogic.getAllPlanets().get(0).color, Color.WHITE);
    }
}
