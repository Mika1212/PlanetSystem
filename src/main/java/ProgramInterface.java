import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

class ProgramInterface {
    JFrame jFrame = new JFrame();
    private final ProgramLogic programLogic;
    private int[] pointOfView = {0, 0, 1400, 1000};
    private int scaleChange = 0;
    private int horizontalChange = 0;
    private int verticalChange = 0;

    //Constructor
    public ProgramInterface(ProgramLogic programLogic) throws InterruptedException {
        this.programLogic = programLogic;
        this.setUp();
        this.addButtons();
        programLogic.setProgramInterface(this);
    }

    //Interface buttons
    private void addButtons() throws InterruptedException {
        JPanel buttons = new JPanel();
        buttons.setLocation(new Point(1200, 0));
        buttons.setSize(250, 1000);
        buttons.setVisible(true);

        //buttons
        Button pauseButton = new Button("Play/Pause");
        pauseButton.setBounds(70, 150, 110, 30);
        pauseButton.addActionListener(e -> programLogic.logicPause());

        Button speedX05 = new Button("Slower");
        speedX05.setBounds(10, 200, 110, 30);
        speedX05.addActionListener(e -> programLogic.logicSlower());

        Button speedX2 = new Button("Faster");
        speedX2.setBounds(130, 200, 110, 30);
        speedX2.addActionListener(e -> programLogic.logicFaster());

        Button backToNormalButton = new Button("Back to normal");
        backToNormalButton.setBounds(70, 300, 110, 30);
        backToNormalButton.addActionListener(e -> programLogic.logicNormal());

        Button scaleUpButton = new Button("ScaleUp");
        scaleUpButton.setBounds(10, 400, 110, 30);
        scaleUpButton.addActionListener(e -> scaleChange += 1);

        Button scaleDownButton = new Button("ScaleDown");
        scaleDownButton.setBounds(130, 400, 110, 30);
        scaleDownButton.addActionListener(e -> {
            if (scaleChange == 1) {
                scaleChange = 0;
                horizontalChange = 0;
                verticalChange = 0;
            } else if (scaleChange > 0) scaleChange -= 1;
        });

        Button rightButton = new Button("Move right");
        rightButton.setBounds(130, 520, 110, 30);
        rightButton.addActionListener(e -> {
            if (pointOfView[2] + 100 <= 1200) {
                horizontalChange += 1;
            }
        });

        Button leftButton = new Button("Move left");
        leftButton.setBounds(10, 520, 110, 30);
        leftButton.addActionListener(e -> {
            if (pointOfView[0] - 100 >= 0) {
                horizontalChange -= 1;
            }
        });

        Button upButton = new Button("Move up");
        upButton.setBounds(70, 480, 110, 30);
        upButton.addActionListener(e -> {
            if (pointOfView[1] - 100 >= 0) {
                verticalChange -= 1;
            }
        });

        Button downButton = new Button("Move down");
        downButton.setBounds(70, 560, 110, 30);
        downButton.addActionListener(e -> {
            if (pointOfView[3] + 100 <= 1000) {
                verticalChange += 1;
            }
        });

        Button customization = new Button("Customization");
        customization.setBounds(70, 700, 110, 30);
        customization.addActionListener(e -> launchCustomization());

        Button startButton = new Button("Start/Delete");
        startButton.setBounds(70, 70, 110, 30);
        startButton.addActionListener(e -> programLogic.logicStart());

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

        Thread.sleep(500);
        jFrame.add(buttons);
    }

    //Customization of the planets
    public void launchCustomization() {
        JFrame frame = new JFrame();
        frame.setTitle("Planet customisation");
        frame.toFront();
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setBounds(965, 50, 720, 370);

        frame.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));

        for (int i = 0; i < programLogic.getAllPlanets().size(); i++) {
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
                planetSliderOfSize.addChangeListener(e -> {
                    JSlider source = (JSlider) e.getSource();
                    if (!source.getValueIsAdjusting() && programLogic.getAllPlanets().size() > finalI) {
                        programLogic.setSize(finalI, source.getValue());
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
                planetSliderOfMass.addChangeListener(e -> {
                    JSlider source = (JSlider) e.getSource();
                    if (!source.getValueIsAdjusting() && programLogic.getAllPlanets().size() > finalI)
                        programLogic.setCoefficientOfMass(finalI, 1.0 / source.getValue());

                });
            }

            Button colorButton = new Button();
            colorButton.setBackground(programLogic.getAllPlanets().get(i).color);
            colorButton.addActionListener(e -> {
                JFrame frame1 = new JFrame();
                frame1.setTitle("Planet color");
                frame1.toFront();
                frame1.setVisible(true);
                frame1.setResizable(false);
                frame1.setBounds(1670, 100, 175, 120);
                frame1.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));

                Button whiteButton = new Button();
                whiteButton.setBackground(Color.WHITE);
                whiteButton.addActionListener(e1 -> {
                    programLogic.setColor(finalI, Color.WHITE);
                    colorButton.setBackground(Color.WHITE);
                });

                Button blackButton = new Button();
                blackButton.setBackground(Color.BLACK);
                blackButton.addActionListener(e12 -> {
                    programLogic.setColor(finalI, Color.BLACK);
                    colorButton.setBackground(Color.BLACK);
                });

                Button cyanButton = new Button();
                cyanButton.setBackground(Color.CYAN);
                cyanButton.addActionListener(e13 -> {
                    programLogic.setColor(finalI, Color.CYAN);
                    colorButton.setBackground(Color.CYAN);
                });

                Button pinkButton = new Button();
                pinkButton.setBackground(Color.PINK);
                pinkButton.addActionListener(e14 -> {
                    programLogic.setColor(finalI, Color.PINK);
                    colorButton.setBackground(Color.PINK);
                });

                Button darkGreyButton = new Button();
                darkGreyButton.setBackground(Color.DARK_GRAY);
                darkGreyButton.addActionListener(e15 -> {
                    programLogic.setColor(finalI, Color.DARK_GRAY);
                    colorButton.setBackground(Color.DARK_GRAY);
                });

                Button lightGrayButton = new Button();
                lightGrayButton.setBackground(Color.LIGHT_GRAY);
                lightGrayButton.addActionListener(e16 -> {
                    programLogic.setColor(finalI, Color.LIGHT_GRAY);
                    colorButton.setBackground(Color.LIGHT_GRAY);
                });

                Button magentaButton = new Button();
                magentaButton.setBackground(Color.MAGENTA);
                magentaButton.addActionListener(e17 -> {
                    programLogic.setColor(finalI, Color.MAGENTA);
                    colorButton.setBackground(Color.MAGENTA);
                });

                Button greenButton = new Button();
                greenButton.setBackground(Color.GREEN);
                greenButton.addActionListener(e18 -> {
                    programLogic.setColor(finalI, Color.GREEN);
                    colorButton.setBackground(Color.GREEN);
                });

                Button yellowButton = new Button();
                yellowButton.setBackground(Color.YELLOW);
                yellowButton.addActionListener(e19 -> {
                    programLogic.setColor(finalI, Color.YELLOW);
                    colorButton.setBackground(Color.YELLOW);
                });

                Button blueButton = new Button();
                blueButton.setBackground(Color.BLUE);
                blueButton.addActionListener(e110 -> {
                    programLogic.setColor(finalI, Color.BLUE);
                    colorButton.setBackground(Color.BLUE);
                });

                Button orangeButton = new Button();
                orangeButton.setBackground(Color.ORANGE);
                orangeButton.addActionListener(e111 -> {
                    programLogic.setColor(finalI, Color.ORANGE);
                    colorButton.setBackground(Color.ORANGE);
                });

                Button redButton = new Button();
                redButton.setBackground(Color.RED);
                redButton.addActionListener(e112 -> {
                    programLogic.setColor(finalI, Color.RED);
                    colorButton.setBackground(Color.RED);
                });

                frame1.add(whiteButton);
                frame1.add(blackButton);
                frame1.add(cyanButton);
                frame1.add(pinkButton);
                frame1.add(darkGreyButton);
                frame1.add(lightGrayButton);
                frame1.add(magentaButton);
                frame1.add(greenButton);
                frame1.add(yellowButton);
                frame1.add(blueButton);
                frame1.add(orangeButton);
                frame1.add(redButton);
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

    //Command methods
    private void scaleUp(Graphics2D g2) {
        for (int i = 0; i < pointOfView.length; i++) {
            pointOfView[i] /= 2;
        }
        g2.scale(2, 2);
    }

    private void right(Graphics2D g2) {
        pointOfView[2] += 100;
        pointOfView[0] += 100;
        g2.translate(-100, 0);
    }

    private void down(Graphics2D g2) {
        pointOfView[1] += 100;
        pointOfView[3] += 100;
        g2.translate(0, -100);
    }

    //Method where is concentrated everything connected to the screen view change
    private void change(Graphics2D g2) {
        pointOfView[0] = 0;
        pointOfView[1] = 0;
        pointOfView[2] = 1200;
        pointOfView[3] = 1000;
        for (int i = 0; i < scaleChange; i++) {
            scaleUp(g2);
        }
        for (int i = 0; i < horizontalChange; i++) {
            right(g2);
        }
        for (int i = 0; i < verticalChange; i++) {
            down(g2);
        }
    }

    //The painting
    private class MyComponent extends JComponent {
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
            g2.drawString("Ratio: 1 to 20 000 km", 15, 20);

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

    //Init method
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

    public void repaint() {
        jFrame.repaint(0, 0, 1210, 1000);
    }

    //Getters of info
    public int getScaleChange() {
        return scaleChange;
    }

    public int getVerticalChange() {
        return verticalChange;
    }

    public int getHorizontalChange() {
        return horizontalChange;
    }
}

