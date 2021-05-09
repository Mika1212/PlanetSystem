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
    static int numberOfPlanets = 6;
    static ArrayList<Planet> allPlanets = new ArrayList<>();


    //Class for all planets
    public static class Planet {
        double x;
        double y;
        int a;
        int b;
        int size;
        int remoteness;
        Color color;

        public Planet(int a, int b, int size, int remoteness, Color color) {
            this.a = a;
            this.b = b;
            this.size = size;
            this.remoteness = remoteness;
            this.color = color;
        }
    }

    //Method to find coordinates x,y of a planet
    public static void coordinates(Planet planet, int i) {
            double psi = (i * Math.PI / 180 / planet.remoteness);
            double fi = Math.atan2(planet.a * Math.sin(psi), planet.b * Math.cos(psi));
            planet.y = planet.a * Math.cos(fi) + 455;
            planet.x = planet.b * Math.sin(fi) + 580;
    }
    
    public static void main(String[] args) throws InterruptedException {
        JFrame jFrame = getFrame();
        int[] speed = {10};

        //buttons
        boolean[] pause = {false};
        Button pauseButton = new Button("Pause");
        pauseButton.setSize(110,30);
        pauseButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                pause[0] = true;
            }
        });

        Button playButton = new Button("Play");
        playButton.setSize(110,30);
        playButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                pause[0] = false;
            }
        });

        Button speedX05 = new Button("Click to speed down");
        speedX05.setSize(110,30);
        speedX05.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                if (speed[0] < 100) speed[0] += 3;
            }
        });

        Button speedX2 = new Button("Click to speed up");
        speedX2.setSize(110,30);
        speedX2.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                if (speed[0] > 10 ) speed[0] -= speed[0] / 10;
                else if (speed[0] > 1) speed[0] -= 1;
            }
        });

        Button speedNormal = new Button("Back to normal");
        speedNormal.setSize(110,30);
        speedNormal.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                speed[0] = 10;
            }
        });

        pauseButton.setLocation(1270,100);
        playButton.setLocation(1270,200);
        speedX2.setLocation(1200,300);
        speedX05.setLocation(1320,300);
        speedNormal.setLocation(1270,340);

        jFrame.add(pauseButton);
        jFrame.add(playButton);
        jFrame.add(speedX05);
        jFrame.add(speedX2);
        jFrame.add(speedNormal);
        jFrame.add(new MyComponent());

        //All planets (I can change it to .txt file)
        allPlanets.add(new Planet(75, 150, 40, 1, Color.BLUE));
        allPlanets.add(new Planet(150, 225, 50, 2, Color.ORANGE));
        allPlanets.add(new Planet(225, 300, 50, 3, Color.PINK));
        allPlanets.add(new Planet(300, 375, 50, 4, Color.CYAN));
        allPlanets.add(new Planet(375, 450, 50, 5, Color.DARK_GRAY));
        allPlanets.add(new Planet(450, 525, 50, 6, Color.GREEN));

        //Run of the process of painting
        int i = 0;
        double[] checker = new double[6];
        while (true) {
            if (!pause[0]) {
                int j = 0;
                i++;
                for (Planet planet : allPlanets) {
                    coordinates(planet, i);
                    checker[j] = planet.x;
                    j++;
                }

                if (Arrays.stream(checker).allMatch(number -> number == checker[0])) i = 0;

                Thread.sleep(speed[0]);
            }
            jFrame.repaint();
        }
    }

    static class MyComponent extends JComponent {

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

            g2.setPaint(Color.yellow);
            Ellipse2D star = new Ellipse2D.Double(610, 435, 80, 80);
            g2.fill(star);

            g2.setColor(Color.WHITE);
            for (int i = 0; i < numberOfPlanets; i++) {
                Ellipse2D orbit = new Ellipse2D.Double(450 - i * 75, 400 - i *75, 300 + i * 150, 150 + i * 150);
                g2.draw(orbit);
            }

            for (Planet planet: allPlanets) {
                g2.setColor(planet.color);
                Ellipse2D planetToDraw = new Ellipse2D.Double(planet.x, planet.y, planet.size, planet.size);
                g2.fill(planetToDraw);
            }
        }
    }

    //Init method to create frame
    static JFrame getFrame() {
        JFrame jFrame = new JFrame();
        jFrame.setBackground(Color.LIGHT_GRAY);
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
