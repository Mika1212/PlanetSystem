import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;

public class PlanetSystem {

    static double x = 375;
    static double y = 475;
    static int numberOfPlanets = 8;
    static double maxAngle = Math.PI * 2;

    public static void main(String[] args) throws InterruptedException {
        JFrame jFrame = getFrame();
        jFrame.add(new MyComponent());

        double angle = 0;
        while (angle < maxAngle) {

            double radius = Math.sqrt(Math.pow(500 - x, 2) + Math.pow(500 - y, 2));
            angle+=0.01;
            x = radius * Math.cos(angle);
            y = radius * Math.sin(angle);

            System.out.println("radius = " + radius + "\n" + "x = " + x + " y = " + y);



            jFrame.repaint();
            Thread.sleep(50);
        }

    }

    static class MyComponent extends JComponent {

        @Override
        public void paint(Graphics g) {
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(Color.BLACK);
            g2.fillRect(0, 0, 1000, 1000);

            g2.setPaint(Color.yellow);
            Ellipse2D star = new Ellipse2D.Double(450, 450, 100, 100);
            g2.fill(star);

            g2.setColor(Color.WHITE);
            for (int i = 0; i < numberOfPlanets; i++) {
                Ellipse2D orbit = new Ellipse2D.Double(400 - i * 50, 400 - i * 50, 200 + i * 100, 200 + i * 100);
                g2.draw(orbit);
            }

            Line2D line400 = new Line2D.Double(500, 0, 500, 1000);
            g2.draw(line400);

            Line2D line375 = new Line2D.Double(0,500, 1000, 500);
            g2.draw(line375);

            g2.setColor(Color.blue);

            Ellipse2D planet = new Ellipse2D.Double(x, y, 50, 50);
            g2.fill(planet);

        }
    }

    static JFrame getFrame() {
        JFrame jFrame = new JFrame();
        jFrame.setVisible(true);
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension dimension = toolkit.getScreenSize();
        jFrame.setBounds((dimension.width - 1000)/2 , (dimension.height - 1000)/3, 1000, 1000);
        return jFrame;
    }
}
