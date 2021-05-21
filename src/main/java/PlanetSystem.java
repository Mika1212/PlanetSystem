public class PlanetSystem {
    private final ProgramLogic programLogic;
    private final ProgramInterface programInterface;

    public static void main(String[] args) throws InterruptedException {
        PlanetSystem planetSystem = new PlanetSystem(false);
    }

    //For the tests
    public void start() { programLogic.launchApplication(); }

    //Constructor of main class
    //boolean test is for test
    public PlanetSystem(boolean test) throws InterruptedException {
        programLogic = new ProgramLogic();
        programInterface = new ProgramInterface(programLogic);
        if (!test) programLogic.launchApplication();
    }

    public ProgramLogic getProgramLogic() { return programLogic; }
    public ProgramInterface getProgramInterface() { return programInterface; }
}