import java.awt.*;
import java.util.ArrayList;

class ProgramLogic {
    private ProgramInterface programInterface;
    private ArrayList<SpaceObjects.Planet> allPlanets = new ArrayList<>();
    private boolean pause = false;
    private static final int STANDARD_SPEED = 1;
    private static final int MAX_SPEED = 100;
    private static final double SUB_SPEED = 0.1;
    private static final int STANDARD_MASS = 1;
    private double coefficientOfSpeed = STANDARD_SPEED;
    private boolean start = false;

    //Method to assign jFrame
    public void setProgramInterface(ProgramInterface programInterface) {
        this.programInterface = programInterface;
    }

    //Run of the process
    public void launchApplication() {
        while (true) {
            if (start) {
                if (!pause) {
                    for (int i = 0; i < allPlanets.size(); i++) {
                        coordinates(allPlanets.get(i));
                        allPlanets.get(i).angle += allPlanets.get(i).coefficientOfMass * coefficientOfSpeed * 0.00005;
                    }
                }
            }
            programInterface.repaint();
        }
    }

    //Logic of buttons
    /*Package private*/ void logicPause() {
        if (pause && start)
            pause = false;
        else pause = true;
    }

    /*Package private*/ void logicStart() {
        if (!start) {
            start = true;
            pause = false;
            addStandardPlanets();
        } else {
            start = false;
            coefficientOfSpeed = STANDARD_SPEED;
            pause = true;
            allPlanets.clear();
        }
    }

    /*Package private*/ void logicSlower() {
        if (coefficientOfSpeed > STANDARD_SPEED) coefficientOfSpeed -= STANDARD_SPEED;
        else if (coefficientOfSpeed - SUB_SPEED > 0 && coefficientOfSpeed <= STANDARD_SPEED)
            coefficientOfSpeed -= SUB_SPEED;
    }

    /*Package private*/ void logicFaster() {
        if (coefficientOfSpeed > STANDARD_SPEED && coefficientOfSpeed < MAX_SPEED) coefficientOfSpeed += STANDARD_SPEED;
        else if (coefficientOfSpeed <= STANDARD_SPEED) coefficientOfSpeed += SUB_SPEED;
    }

    /*Package private*/ void logicNormal() {
        for (SpaceObjects.Planet planet : allPlanets) {
            planet.coefficientOfMass = STANDARD_MASS;
        }
        coefficientOfSpeed = STANDARD_SPEED;
    }

    //Add 6 standard planets
    private void addStandardPlanets() {
        addPlanet(new SpaceObjects.Planet(10, 1, Color.BLUE, 1));
        addPlanet(new SpaceObjects.Planet(20, 2, Color.ORANGE, 2));
        addPlanet(new SpaceObjects.Planet(30, 3, Color.PINK, 3));
        addPlanet(new SpaceObjects.Planet(40, 4, Color.CYAN, 4));
        addPlanet(new SpaceObjects.Planet(45, 5, Color.DARK_GRAY, 5));
        addPlanet(new SpaceObjects.Planet(50, 6, Color.GREEN, 6));
    }

    //Add 1 planet
    public void addPlanet(SpaceObjects.Planet planet) {
        allPlanets.add(planet);
    }

    //Method to find coordinates x,y of a planet
    public static void coordinates(SpaceObjects.Planet planet) {
        double psi = (planet.angle * Math.PI / 180 / planet.remoteness);
        double fi = Math.atan2(planet.a * Math.sin(psi), planet.b * Math.cos(psi));
        planet.y = planet.a * Math.cos(fi) + 450 - planet.size / 2 + 25;
        planet.x = planet.b * Math.sin(fi) + 575 - planet.size / 2 + 25;
    }

    //Getters of info
    public boolean getStart() {
        return start;
    }

    public boolean getPause() {
        return pause;
    }

    public double getCoefficientOfSpeed() {
        return coefficientOfSpeed;
    }

    public ArrayList<SpaceObjects.Planet> getAllPlanets() {
        return allPlanets;
    }

    //Setters of info
    public void setSize(int number, int size) {
        allPlanets.get(number).size = size;
    }

    public void setCoefficientOfMass(int number, double coefficient) {
        allPlanets.get(number).coefficientOfMass = coefficient;
    }

    public void setColor(int number, Color color) {
        allPlanets.get(number).color = color;
    }
}