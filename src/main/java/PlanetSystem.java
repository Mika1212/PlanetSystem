import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class PlanetSystem {
    static ArrayList<SpaceObjects.Planet> allPlanets = new ArrayList<>();
    static int[] pointOfView = {0, 0, 1400, 1000};
    static int scaleChange = 0;
    static int horizontalChange = 0;
    static int verticalChange = 0;


    //Method to find coordinates x,y of a planet
    public static void coordinates(SpaceObjects.Planet planet, int i) {
        double psi = (i * Math.PI / 180 / planet.remoteness);
        double fi = Math.atan2(planet.a * Math.sin(psi), planet.b * Math.cos(psi));
        planet.y = planet.a * Math.cos(fi) + 450 - planet.size / 2 + 25;
        planet.x = planet.b * Math.sin(fi) + 575 - planet.size / 2 + 25;
    }

    public static void main(String[] args) throws InterruptedException {
        JFrame jFrame = getFrame();
        JPanel buttons = new JPanel();

        buttons.setLocation(new Point(1200, 0));
        buttons.setSize(250, 1000);
        buttons.setVisible(true);

        jFrame.add(new MyComponent());


        //buttons
        boolean[] pause = {false};
        Button pauseButton = new Button("Play/Pause");
        pauseButton.setBounds(70, 100, 110,30);
        pauseButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (pause[0])
                pause[0] = false;
                else pause[0] = true;
            }
        });


        int[] speed = {10};
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
                if (pointOfView[2] + 100 <= 800) {
                    horizontalChange += 1;
                }
            }
        });

        Button leftButton = new Button("Move left");
        leftButton.setBounds(10, 520, 110,30);
        leftButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                if (pointOfView[2] - 100 > 0) {
                    horizontalChange -= 1;
                }
            }
        });

        Button upButton = new Button("Move up");
        upButton.setBounds(70, 480, 110,30);
        upButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                if (pointOfView[1] - 100 > 0) {
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

        Thread.sleep(100);
        jFrame.add(buttons);


        //All planets (I can change it to .txt file)
        allPlanets.add(new SpaceObjects.Planet(75, 150, 50, 1, Color.BLUE));
        allPlanets.add(new SpaceObjects.Planet(150, 225, 20, 2, Color.ORANGE));
        allPlanets.add(new SpaceObjects.Planet(225, 300, 30, 3, Color.PINK));
        allPlanets.add(new SpaceObjects.Planet(300, 375, 40, 4, Color.CYAN));
        allPlanets.add(new SpaceObjects.Planet(375, 450, 50, 5, Color.DARK_GRAY));
        allPlanets.add(new SpaceObjects.Planet(450, 525, 60, 6, Color.GREEN));

        //Run of the process of painting
        int i = 0;
        while (true) {

            if (!pause[0]) {
                i++;
                for (SpaceObjects.Planet planet : allPlanets) coordinates(planet, i);

                Thread.sleep(speed[0]);
            }
            jFrame.repaint(0, 0, 1210, 1000);
        }
    }

    static class MyComponent extends JComponent {

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