import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class PlanetSystem {
    static int numberOfPlanets = 6;
    static ArrayList<Planet> allPlanets = new ArrayList<>();
    
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
    
    public static void coordinates(Planet planet, int i) {
            double psi = (i * Math.PI / 180 / planet.remoteness);
            double fi = Math.atan2(planet.a * Math.sin(psi), planet.b * Math.cos(psi));
            planet.y = planet.a * Math.cos(fi) + 455;
            planet.x = planet.b * Math.sin(fi) + 580;
    }
    
    public static void main(String[] args) throws InterruptedException {
        JFrame jFrame = getFrame();
        jFrame.add(new MyComponent());
        

        Planet earth = new Planet(75, 150, 40, 1, Color.BLUE);
        Planet planet2 = new Planet(150, 225, 50, 2, Color.ORANGE);
        Planet planet3 = new Planet(225, 300, 50, 3, Color.PINK);
        Planet planet4 = new Planet(300, 375, 50, 4, Color.CYAN);
        Planet planet5 = new Planet(375, 450, 50, 5, Color.DARK_GRAY);
        Planet planet6 = new Planet(450, 525, 50, 6, Color.GREEN);


        allPlanets.add(earth);
        allPlanets.add(planet2);
        allPlanets.add(planet3);
        allPlanets.add(planet4);
        allPlanets.add(planet5);
        allPlanets.add(planet6);

        int i = 0;
        while (true) {
            i++;
            for (Planet planet: allPlanets) coordinates(planet, i);

            Thread.sleep(10);
            jFrame.repaint();

        }
    }

    static class MyComponent extends JComponent {

        @Override
        public void paint(Graphics g) {
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            g2.setBackground(Color.blue);

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

    static JFrame getFrame() {
        JFrame jFrame = new JFrame();
        jFrame.setVisible(true);
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension dimension = toolkit.getScreenSize();
        jFrame.setBounds((dimension.width - 1000)/2 , (dimension.height - 1000)/3, 1200, 1000);
        return jFrame;
    }
}
