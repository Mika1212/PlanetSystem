import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.LocalTime;
import java.util.ArrayList;

public class PlanetSystem {
    private final ProgramLogic programLogic;

    public static void main(String[] args) throws InterruptedException {
        PlanetSystem planetSystem = new PlanetSystem(false);
    }

    //For the tests
    public void start() { programLogic.launchApplication(); }

    //Constructor of main class
    //boolean test is for test
    public PlanetSystem(boolean test) throws InterruptedException {
        programLogic = new ProgramLogic();
        ProgramInterface programInterface = new ProgramInterface(programLogic);
        if (!test) programLogic.launchApplication();
    }

    public ProgramLogic getProgramLogic() { return programLogic; }
}

class ProgramInterface {
    JFrame jFrame = new JFrame();
    private final ProgramLogic programLogic;

    //Constructor
    public ProgramInterface(ProgramLogic programLogic) throws InterruptedException {
        this.programLogic = programLogic;
        this.setUp();
        this.addButtons();
        programLogic.logicGetJFrame(jFrame);
    }

    //Interface buttons
    public void addButtons() throws InterruptedException {
        JPanel buttons = new JPanel();
        buttons.setLocation(new Point(1200, 0));
        buttons.setSize(250, 1000);
        buttons.setVisible(true);

        //buttons
        Button pauseButton = new Button("Play/Pause");
        pauseButton.setBounds(70, 150, 110, 30);
        pauseButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                programLogic.pauseButton();
            }
        });

        Button speedX05 = new Button("Slower");
        speedX05.setBounds(10, 200, 110, 30);
        speedX05.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                programLogic.slowerButton();
            }
        });

        Button speedX2 = new Button("Faster");
        speedX2.setBounds(130, 200, 110, 30);
        speedX2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                programLogic.fasterButton();
            }
        });

        Button backToNormalButton = new Button("Back to normal");
        backToNormalButton.setBounds(70, 300, 110, 30);
        backToNormalButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                programLogic.normalButton();
            }
        });

        Button scaleUpButton = new Button("ScaleUp");
        scaleUpButton.setBounds(10, 400, 110, 30);
        scaleUpButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                programLogic.scaleUpButton();
            }
        });

        Button scaleDownButton = new Button("ScaleDown");
        scaleDownButton.setBounds(130, 400, 110, 30);
        scaleDownButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                programLogic.scaleDownButton();
            }
        });

        Button rightButton = new Button("Move right");
        rightButton.setBounds(130, 520, 110, 30);
        rightButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                programLogic.rightButton();
            }
        });

        Button leftButton = new Button("Move left");
        leftButton.setBounds(10, 520, 110, 30);
        leftButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                programLogic.leftButton();
            }
        });

        Button upButton = new Button("Move up");
        upButton.setBounds(70, 480, 110, 30);
        upButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                programLogic.upButton();
            }
        });

        Button downButton = new Button("Move down");
        downButton.setBounds(70, 560, 110, 30);
        downButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                programLogic.downButton();
            }
        });

        Button customization = new Button("Customization");
        customization.setBounds(70, 700, 110, 30);
        customization.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                programLogic.customizeButton();
            }
        });

        Button startButton = new Button("Start/Delete");
        startButton.setBounds(70, 70, 110, 30);
        startButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                programLogic.startButton();
            }
        });

        buttons.add(startButton);

        buttons.add(pauseButton);
        buttons.add(speedX05);
        buttons.add(speedX2);
        buttons.add(backToNormalButton);

        buttons.add(scaleDownButton);
        buttons.add(scaleUpButton);
        buttons.add(rightButton);
        buttons.add(leftButton);
        buttons.add(upButton);
        buttons.add(downButton);
        buttons.add(customization);

        Thread.sleep(300);
        jFrame.add(buttons);
    }

    //Customization of the planets
    public static void launchCustomization(ArrayList<SpaceObjects.Planet> allPlanets) {
        JFrame frame = new JFrame();
        frame.setTitle("Planet customisation");
        frame.toFront();
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setBounds(965, 50, 720, 370);

        frame.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));

        for (int i = 0; i < allPlanets.size(); i++) {
            int finalI = i;

            Label nameLabel = new Label("Planet # " + (finalI + 1));
            Label sizeLabel = new Label("Size:");
            Label speedLabel = new Label("Mass:");
            Label colorLabel = new Label("Color:");

            JSlider planetSliderOfSize = new JSlider(JSlider.HORIZONTAL, 1, 50, 2);
            {
                planetSliderOfSize.setMajorTickSpacing(7);
                planetSliderOfSize.setMinorTickSpacing(1);
                planetSliderOfSize.setPaintTicks(true);
                planetSliderOfSize.setPaintLabels(true);

                planetSliderOfSize.addChangeListener(new ChangeListener() {
                    @Override
                    public void stateChanged(ChangeEvent e) {
                        JSlider source = (JSlider) e.getSource();
                        if (!source.getValueIsAdjusting() && allPlanets.size() > finalI) {
                            allPlanets.get(finalI).size = source.getValue();
                        }
                    }
                });
            }

            JSlider planetSliderOfMass = new JSlider(JSlider.HORIZONTAL, 1, 10, 5);
            {
                planetSliderOfMass.setMajorTickSpacing(3);
                planetSliderOfMass.setMinorTickSpacing(1);
                planetSliderOfMass.setValue(1);
                planetSliderOfMass.setPaintTicks(true);
                planetSliderOfMass.setPaintLabels(true);
                planetSliderOfMass.addChangeListener(new ChangeListener() {
                    @Override
                    public void stateChanged(ChangeEvent e) {
                        JSlider source = (JSlider) e.getSource();
                        if (!source.getValueIsAdjusting() && allPlanets.size() > finalI)
                            allPlanets.get(finalI).coefficientOfMass = 1.0 / source.getValue();

                    }
                });
            }

            Button colorButton = new Button();
            colorButton.setBackground(allPlanets.get(i).color);
            colorButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    JFrame frame = new JFrame();
                    frame.setTitle("Planet color");
                    frame.toFront();
                    frame.setVisible(true);
                    frame.setResizable(false);
                    frame.setBounds(1670, 100, 175, 120);
                    frame.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));

                    Button whiteButton = new Button();
                    whiteButton.setBackground(Color.WHITE);
                    whiteButton.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            allPlanets.get(finalI).color = Color.WHITE;
                            colorButton.setBackground(Color.WHITE);
                        }
                    });

                    Button blackButton = new Button();
                    blackButton.setBackground(Color.BLACK);
                    blackButton.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            allPlanets.get(finalI).color = Color.BLACK;
                            colorButton.setBackground(Color.BLACK);
                        }
                    });

                    Button cyanButton = new Button();
                    cyanButton.setBackground(Color.CYAN);
                    cyanButton.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            allPlanets.get(finalI).color = Color.CYAN;
                            colorButton.setBackground(Color.CYAN);
                        }
                    });

                    Button pinkButton = new Button();
                    pinkButton.setBackground(Color.PINK);
                    pinkButton.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            allPlanets.get(finalI).color = Color.PINK;
                            colorButton.setBackground(Color.PINK);
                        }
                    });

                    Button darkGreyButton = new Button();
                    darkGreyButton.setBackground(Color.DARK_GRAY);
                    darkGreyButton.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            allPlanets.get(finalI).color = Color.DARK_GRAY;
                            colorButton.setBackground(Color.DARK_GRAY);
                        }
                    });

                    Button lightGrayButton = new Button();
                    lightGrayButton.setBackground(Color.LIGHT_GRAY);
                    lightGrayButton.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            allPlanets.get(finalI).color = Color.LIGHT_GRAY;
                            colorButton.setBackground(Color.LIGHT_GRAY);
                        }
                    });

                    Button magentaButton = new Button();
                    magentaButton.setBackground(Color.MAGENTA);
                    magentaButton.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            allPlanets.get(finalI).color = Color.MAGENTA;
                            colorButton.setBackground(Color.MAGENTA);
                        }
                    });

                    Button greenButton = new Button();
                    greenButton.setBackground(Color.GREEN);
                    greenButton.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            allPlanets.get(finalI).color = Color.GREEN;
                            colorButton.setBackground(Color.GREEN);
                        }
                    });

                    Button yellowButton = new Button();
                    yellowButton.setBackground(Color.YELLOW);
                    yellowButton.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            allPlanets.get(finalI).color = Color.YELLOW;
                            colorButton.setBackground(Color.YELLOW);
                        }
                    });

                    Button blueButton = new Button();
                    blueButton.setBackground(Color.BLUE);
                    blueButton.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            allPlanets.get(finalI).color = Color.BLUE;
                            colorButton.setBackground(Color.BLUE);
                        }
                    });

                    Button orangeButton = new Button();
                    orangeButton.setBackground(Color.ORANGE);
                    orangeButton.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            allPlanets.get(finalI).color = Color.ORANGE;
                            colorButton.setBackground(Color.ORANGE);
                        }
                    });

                    Button redButton = new Button();
                    redButton.setBackground(Color.RED);
                    redButton.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            allPlanets.get(finalI).color = Color.RED;
                            colorButton.setBackground(Color.RED);
                        }
                    });

                    frame.add(whiteButton);
                    frame.add(blackButton);
                    frame.add(cyanButton);
                    frame.add(pinkButton);
                    frame.add(darkGreyButton);
                    frame.add(lightGrayButton);
                    frame.add(magentaButton);
                    frame.add(greenButton);
                    frame.add(yellowButton);
                    frame.add(blueButton);
                    frame.add(orangeButton);
                    frame.add(redButton);
                }
            });


            frame.add(nameLabel);
            frame.add(sizeLabel);
            frame.add(planetSliderOfSize);
            frame.add(speedLabel);
            frame.add(planetSliderOfMass);
            frame.add(colorLabel);
            frame.add(colorButton);
        }
    }

    //Static method to use in launchApplication
    public static void repaint(JFrame jFrame){
        jFrame.repaint(0, 0, 1210, 1000);
    }

    //Command methods
    public void scaleUp(Graphics2D g2) {
        g2.scale(2, 2);
    }
    public void right(Graphics2D g2) {
        g2.translate(-100, 0);
    }
    public void down(Graphics2D g2) {
        g2.translate(0, -100);
    }

    //Method where is concentrated everything connected to screen view change
    public void change(Graphics2D g2) {
        programLogic.change();
        for (int i = 0; i < programLogic.getScaleChange(); i++) {
            programLogic.scaleUp();
            scaleUp(g2);
        }
        for (int i = 0; i < programLogic.getHorizontalChange(); i++) {
            programLogic.right();
            right(g2);
        }
        for (int i = 0; i < programLogic.getVerticalChange(); i++) {
            programLogic.down();
            down(g2);
        }
    }

    //The painting
    public class MyComponent extends JComponent {
        @Override
        public void paint(Graphics g) {
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            File file = new File("background2.jpg");
            BufferedImage image = null;
            try {
                image = ImageIO.read(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
            g2.drawImage(image, null, 0, 0);

            g2.setColor(Color.WHITE);
            g.setFont(new Font("TimesRoman", Font.PLAIN, 18));
            g2.drawString("Time from the start: " + (int) programLogic.getSystemTimeSeconds() + " seconds", 15, 20);
            g2.drawString("Ratio: 1 to 20 000 km", 15, 45);

            change(g2);

            g2.setPaint(Color.yellow);
            Ellipse2D star = new Ellipse2D.Double(610, 435, 80, 80);
            g2.fill(star);

            g2.setColor(Color.WHITE);
            for (int i = 0; i < programLogic.getAllPlanets().size(); i++) {
                Ellipse2D orbit = new Ellipse2D.Double(450 - i * 75, 400 - i * 75, 300 + i * 150, 150 + i * 150);
                g2.draw(orbit);
            }

            g.setFont(new Font("TimesRoman", Font.PLAIN, 14));
            for (SpaceObjects.Planet planet : programLogic.getAllPlanets()) {
                g2.setColor(Color.WHITE);
                g2.drawString("(" + (int) (planet.x + planet.size / 2) + ", " + (int) (planet.y + planet.size / 2) + ")",
                        (int) planet.x, (int) planet.y);
                g2.setColor(planet.color);
                Ellipse2D planetToDraw = new Ellipse2D.Double(planet.x, planet.y, planet.size, planet.size);
                g2.fill(planetToDraw);
            }

        }
    }

    //Init
    private void setUp() {
        jFrame.setTitle("PlanetSystem Simulator");
        jFrame.toFront();
        jFrame.setVisible(true);
        jFrame.setResizable(false);
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension dimension = toolkit.getScreenSize();
        jFrame.setBounds((dimension.width - 1450) / 2, (dimension.height - 1000) / 3, 1450, 1000);
        jFrame.add(new MyComponent());
    }

}

class ProgramLogic {
    private ArrayList<SpaceObjects.Planet> allPlanets = new ArrayList<>();
    private int[] pointOfView = {0, 0, 1400, 1000};
    private int scaleChange = 0;
    private int horizontalChange = 0;
    private int verticalChange = 0;
    private boolean[] pause = {false};
    private static final int CONST_OF_SPEED = 1;
    private double[] coefficientOfSpeed = {CONST_OF_SPEED};
    private boolean[] start = {false};
    private double systemTimeSecondsStart;
    private double systemTimeSeconds;
    private JFrame jFrame;

    //Method to assign jFrame
    public void logicGetJFrame(JFrame jFrame) {
        this.jFrame = jFrame;
    }

    //Run of the process
    public void launchApplication() {
        while (true) {
            if (start[0]) {
                systemTimeSeconds = LocalTime.now().toSecondOfDay() - systemTimeSecondsStart;
                if (!pause[0]) {
                    for (int i = 0; i < allPlanets.size(); i++) {
                        coordinates(allPlanets.get(i));
                        allPlanets.get(i).angle += allPlanets.get(i).coefficientOfMass * coefficientOfSpeed[0] * 0.00005;
                    }
                }
            }
            ProgramInterface.repaint(jFrame);
        }
    }

    //Method to find out what time is it
    private void setUpTime() {
        systemTimeSecondsStart = LocalTime.now().toSecondOfDay();
    }

    //Screen scale and move
    public void scaleUp(){
        for (int i = 0; i < pointOfView.length; i++) {
            pointOfView[i] /= 2;
        }
    }
    public void right(){
        pointOfView[2] += 100;
        pointOfView[0] += 100;
    }
    public void down() {
        pointOfView[1] += 100;
        pointOfView[3] += 100;
    }
    public void change() {
        pointOfView[0] = 0;
        pointOfView[1] = 0;
        pointOfView[2] = 1200;
        pointOfView[3] = 1000;
    }

    //Buttons
    void pauseButton(){
        if (pause[0] && start[0])
            pause[0] = false;
        else pause[0] = true;
    }
    void startButton(){
        if (!start[0]) {
            start[0] = true;
            pause[0] = false;
            setUpTime();
            addStandardPlanets();
        } else {
            start[0] = false;
            coefficientOfSpeed[0] = CONST_OF_SPEED;
            pause[0] = true;
            systemTimeSecondsStart = LocalTime.now().toSecondOfDay();
            allPlanets.clear();
        }
    }
    void slowerButton(){
        if (coefficientOfSpeed[0] > 1) coefficientOfSpeed[0] -= 1;
        else if (coefficientOfSpeed[0] - 0.1 > 0 && coefficientOfSpeed[0] <= 1) coefficientOfSpeed[0] -= 0.05;
    }
    void fasterButton(){
        if (coefficientOfSpeed[0] > 1 && coefficientOfSpeed[0] < 15) coefficientOfSpeed[0] += 1;
        else if (coefficientOfSpeed[0] <= 1) coefficientOfSpeed[0] += 0.2;
    }
    void normalButton(){
        for (SpaceObjects.Planet planet : allPlanets) {
            planet.coefficientOfMass = 1;
        }
        verticalChange = 0;
        horizontalChange = 0;
        scaleChange = 0;
        change();
        coefficientOfSpeed[0] = CONST_OF_SPEED;
    }
    void scaleUpButton(){
        scaleChange += 1;
    }
    void scaleDownButton(){
        if (scaleChange == 1) {
            scaleChange = 0;
            horizontalChange = 0;
            verticalChange = 0;
        } else if (scaleChange > 0) scaleChange -= 1;
    }
    void leftButton(){
        if (pointOfView[0] - 100 >= 0) {
            horizontalChange -= 1;
        }
    }
    void upButton(){
        if (pointOfView[1] - 100 >= 0) {
            verticalChange -= 1;
        }
    }
    void rightButton(){
        if (pointOfView[2] + 100 <= 1200) {
            horizontalChange += 1;
        }
    }
    void downButton(){
        if (pointOfView[3] + 100 <= 1000) {
            verticalChange += 1;
        }
    }

    //Launching customization
    public void customizeButton(){
        ProgramInterface.launchCustomization(allPlanets);
    }

    //Add 6 standard planets
    private void addStandardPlanets(){
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
    public boolean getStart() { return start[0]; }
    public boolean getPause() { return pause[0]; }
    public double getCoefficientOfSpeed() { return coefficientOfSpeed[0]; }
    public int getScaleChange() { return scaleChange; }
    public int getVerticalChange() { return verticalChange; }
    public int getHorizontalChange() { return horizontalChange; }
    public double getSystemTimeSeconds() { return systemTimeSeconds; }
    public ArrayList<SpaceObjects.Planet> getAllPlanets() { return allPlanets; }
}