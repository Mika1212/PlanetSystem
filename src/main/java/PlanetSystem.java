import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;

public class PlanetSystem {

    static int initX = 100;
    static int initY = 100;
    static int numberOfPlanets = 8;

    public static void main(String[] args) throws InterruptedException {
        JFrame jFrame = getFrame();
        jFrame.add(new MyComponent());

        int i = 0;
        while (true) {
            System.out.println("First");
            Thread.sleep(500);
            System.out.println("Second");

            i++;
            if (i<2) {
                initX += 100;
                initY += 100;
            }

            if (i>2) {
                initX += 100;
            }

            if (i>5) {

                initY += 100;
            }

            jFrame.repaint();
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
            Ellipse2D star = new Ellipse2D.Double(450, 425, 100, 100);
            g2.fill(star);

            g2.setColor(Color.WHITE);
            for (int i = 0; i < numberOfPlanets; i++) {
                Ellipse2D orbit = new Ellipse2D.Double(400 - i * 50, 375 - i * 50, 200 + i * 100, 200 + i * 100);
                g2.draw(orbit);
            }

            g2.setColor(Color.blue);

            Line2D line2D = new Line2D.Double(initX, initY, initX + 100, initY + 100);
            g2.draw(line2D);
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
