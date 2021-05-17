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
import java.util.ArrayList;

public class PlanetSystem {
    static ArrayList<SpaceObjects.Planet> allPlanets = new ArrayList<>();
    private static int[] pointOfView = {0, 0, 1400, 1000};
    private static int scaleChange = 0;
    private static int horizontalChange = 0;
    private static int verticalChange = 0;
    private static boolean[] pause = {false};
    private static int[] speed = {10};
    private static boolean[] start = {false};
    private static int systemTimeDays = 0;
    private static int systemTimeMonths = 0;
    private static int systemTimeYears = 0;

    //Add 6 standard planets
    private static void addStandardPlanets(){
        addPlanet(new SpaceObjects.Planet(10, 1, Color.BLUE));
        addPlanet(new SpaceObjects.Planet(20, 2, Color.ORANGE));
        addPlanet(new SpaceObjects.Planet(30, 3, Color.PINK));
        addPlanet(new SpaceObjects.Planet(40, 4, Color.CYAN));
        addPlanet(new SpaceObjects.Planet(45, 5, Color.DARK_GRAY));
        addPlanet(new SpaceObjects.Planet(50, 6, Color.GREEN));
    }

    //Add 1 planet
    public static void addPlanet(SpaceObjects.Planet planet) {
        allPlanets.add(planet);
    }

    //Method to find coordinates x,y of a planet
    private static void coordinates(SpaceObjects.Planet planet) {
        double psi = (planet.time * Math.PI / 180 / planet.remoteness);
        double fi = Math.atan2(planet.a * Math.sin(psi), planet.b * Math.cos(psi));
        planet.y = planet.a * Math.cos(fi) + 450 - planet.size / 2 + 25;
        planet.x = planet.b * Math.sin(fi) + 575 - planet.size / 2 + 25;
    }

    //Customization of the planets
    private static void launchCustomization() {
        JFrame frame = new JFrame();
        frame.setTitle("Planet customisation");
        frame.toFront();
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setBounds(965, 50, 720, 370);

        frame.setLayout(new FlowLayout(FlowLayout.LEFT, 10 , 10));

        for (int i = 0; i < allPlanets.size(); i++) {
            int finalI = i;

            Label nameLabel = new Label("Planet # " + (finalI + 1));
            Label sizeLabel = new Label("Size:");
            Label speedLabel = new Label("Speed:");
            Label colorLabel = new Label("Color:");

            JSlider planetSliderOfSize = new JSlider(JSlider.HORIZONTAL, 1, 51, 2);
            {
                planetSliderOfSize.setMajorTickSpacing(10);
                planetSliderOfSize.setMinorTickSpacing(1);
                planetSliderOfSize.setPaintTicks(true);
                planetSliderOfSize.setPaintLabels(true);

                planetSliderOfSize.addChangeListener(new ChangeListener() {
                    @Override
                    public void stateChanged(ChangeEvent e) {
                        JSlider source = (JSlider) e.getSource();
                        if (!source.getValueIsAdjusting()&& allPlanets.size() > finalI) {
                            allPlanets.get(finalI).size = source.getValue();
                        }
                    }
                });
            }

            JSlider planetSliderOfSpeed = new JSlider(JSlider.HORIZONTAL, -100, 100, 5);
            {
                planetSliderOfSpeed.setMajorTickSpacing(50);
                planetSliderOfSpeed.setMinorTickSpacing(1);
                planetSliderOfSpeed.setValue(0);
                planetSliderOfSpeed.setPaintTicks(true);
                planetSliderOfSpeed.setPaintLabels(true);
                planetSliderOfSpeed.addChangeListener(new ChangeListener() {
                    @Override
                    public void stateChanged(ChangeEvent e) {
                        JSlider source = (JSlider) e.getSource();
                        if (!source.getValueIsAdjusting() && allPlanets.size() > finalI)
                            allPlanets.get(finalI).speedDivider = ((101 - source.getValue()) / 40.0);

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
                    frame.setLayout(new FlowLayout(FlowLayout.LEFT, 10 , 10));

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
            frame.add(planetSliderOfSpeed);
            frame.add(colorLabel);
            frame.add(colorButton);
        }
    }

    //Run of the process
    private static void launchApplication(JFrame jFrame) throws InterruptedException {
        while (true) {

            if (!pause[0]) {

                if (systemTimeDays == 30) {
                    systemTimeMonths++;
                    systemTimeDays = 0;
                }
                if (systemTimeMonths == 12) {
                    systemTimeYears++;
                    systemTimeMonths = 0;
                }

                systemTimeDays++;

                for (SpaceObjects.Planet planet : allPlanets) {
                    coordinates(planet);
                    planet.time += planet.speedDivider;
                }

                Thread.sleep(speed[0]);
            }
            jFrame.repaint(0, 0, 1210, 1000);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        JFrame jFrame = getFrame();

        JPanel buttons = new JPanel();
        buttons.setLocation(new Point(1200, 0));
        buttons.setSize(250, 1000);
        buttons.setVisible(true);

        jFrame.add(new MyComponent());

        //buttons
        Button pauseButton = new Button("Play/Pause");
        pauseButton.setBounds(70, 150, 110,30);
        pauseButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (pause[0] && start[0])
                pause[0] = false;
                else pause[0] = true;
            }
        });

        Button speedX05 = new Button("Slower");
        speedX05.setBounds(10, 200, 110,30);
        speedX05.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (speed[0] < 100) speed[0] += 3;
            }
        });

        Button speedX2 = new Button("Faster");
        speedX2.setBounds(130, 200,110,30);
        speedX2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (speed[0] > 10 ) speed[0] -= speed[0] / 10;
                else if (speed[0] > 1) speed[0] -= 1;
            }
        });

        Button speedNormal = new Button("Normal speed");
        speedNormal.setBounds(70, 240, 110,30);
        speedNormal.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                for (SpaceObjects.Planet planet: allPlanets) {
                    planet.speedDivider = 1;
                }
                speed[0] = 10;
            }
        });

        Button scaleUpButton = new Button("ScaleUp");
        scaleUpButton.setBounds(10, 400, 110,30);
        scaleUpButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                scaleChange += 1;
            }
        });

        Button scaleDownButton = new Button("ScaleDown");
        scaleDownButton.setBounds(130, 400, 110,30);
        scaleDownButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                if (scaleChange == 1) {
                    scaleChange = 0;
                    horizontalChange = 0;
                    verticalChange = 0;
                } else if (scaleChange > 0) scaleChange -= 1;
            }
        });

        Button rightButton = new Button("Move right");
        rightButton.setBounds(130, 520, 110,30);
        rightButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                if (pointOfView[2] + 100 <= 1200) {
                    horizontalChange += 1;
                }
            }
        });

        Button leftButton = new Button("Move left");
        leftButton.setBounds(10, 520, 110,30);
        leftButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                if (pointOfView[0] - 100 >= 0) {
                    horizontalChange -= 1;
                }
            }
        });

        Button upButton = new Button("Move up");
        upButton.setBounds(70, 480, 110,30);
        upButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                if (pointOfView[1] - 100 >= 0) {
                    verticalChange -= 1;
                }
            }
        });

        Button downButton = new Button("Move down");
        downButton.setBounds(70, 560, 110,30);
        downButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                if (pointOfView[3] + 100 <= 1000) {
                    verticalChange += 1;
                }
            }
        });

        Button customization = new Button("Customization");
        customization.setBounds(70, 700, 110,30);
        customization.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                launchCustomization();
            }
        });

        Button startButton = new Button("Start/Stop");
        startButton.setBounds(70, 70, 110,30);
        startButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                if (!start[0]) {
                    start[0] = true;
                    pause[0] = false;
                    addStandardPlanets();
                } else {
                    start[0] = false;
                    speed[0] = 10;
                    pause[0] = true;
                    systemTimeDays = 0;
                    systemTimeMonths = 0;
                    systemTimeYears = 0;
                    allPlanets.clear();
                }
            }
        });

        buttons.add(startButton);

        buttons.add(pauseButton);
        buttons.add(speedX05);
        buttons.add(speedX2);
        buttons.add(speedNormal);

        buttons.add(scaleDownButton);
        buttons.add(scaleUpButton);
        buttons.add(rightButton);
        buttons.add(leftButton);
        buttons.add(upButton);
        buttons.add(downButton);
        buttons.add(customization);

        Thread.sleep(300);
        jFrame.add(buttons);

        launchApplication(jFrame);
    }

    //Everything connected to painting
    static class MyComponent extends JComponent {

        //Command methods
        private void scaleUp(Graphics2D g2) {
            g2.scale(2, 2);
            for (int i = 0; i < pointOfView.length; i++) {
                pointOfView[i] /= 2;
            }
        }
        private void right(Graphics2D g2) {
            g2.translate(-100, 0);
            pointOfView[2] += 100;
            pointOfView[0] += 100;
        }
        private void down(Graphics2D g2) {
            g2.translate(0, -100);
            pointOfView[1] += 100;
            pointOfView[3] += 100;
        }

        //The painting
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
            g2.drawImage(image, null, 0 , 0);

            g2.setColor(Color.WHITE);
            g.setFont(new Font("TimesRoman", Font.PLAIN, 18));
            g2.drawString("Time from the start: " + systemTimeYears + " years, " + systemTimeMonths + " months",15, 20);
            g2.drawString("Ratio: 1 to 20 000", 15, 45);


            pointOfView[0] = 0;
            pointOfView[1] = 0;
            pointOfView[2] = 1200;
            pointOfView[3] = 1000;
            for (int i = 0; i < scaleChange; i++) scaleUp(g2);
            for (int i = 0; i < horizontalChange; i++) right(g2);
            for (int i = 0; i < verticalChange; i++) down(g2);

            g2.setPaint(Color.yellow);
            Ellipse2D star = new Ellipse2D.Double(610, 435, 80, 80);
            g2.fill(star);

            g2.setColor(Color.WHITE);
            for (int i = 0; i < allPlanets.size(); i++) {
                Ellipse2D orbit = new Ellipse2D.Double(450 - i * 75, 400 - i * 75, 300 + i * 150, 150 + i * 150);
                g2.draw(orbit);
            }

            for (SpaceObjects.Planet planet: allPlanets) {
                g2.setColor(planet.color);
                Ellipse2D planetToDraw = new Ellipse2D.Double(planet.x, planet.y, planet.size, planet.size);
                g2.fill(planetToDraw);
            }
        }
    }

    //Init method to create frame
    static JFrame getFrame() {
        JFrame jFrame = new JFrame();
        jFrame.setTitle("PlanetSystem Simulator");
        jFrame.toFront();
        jFrame.setVisible(true);
        jFrame.setResizable(false);
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension dimension = toolkit.getScreenSize();
        jFrame.setBounds((dimension.width - 1450)/2 , (dimension.height - 1000)/3, 1450, 1000);
        return jFrame;
    }
}