import java.awt.*;

public class SpaceObjects {

    public static class Star {
        double x;
        double y;
        int size;
        Color color;

        public Star(double x, double y, int size, Color color) {
            this.x = x + size / 2;
            this.y = y - size / 2;
            this.size = size;
            this.color = color;
        }
    }

    public static class Planet {
        private static final int constA = 75;
        private static final int constB = 150;

        double x;
        double y;
        int a;
        int b;
        int size;
        int remoteness;
        int number;
        double angle;
        double coefficientOfMass;
        Color color;

        public Planet(int size, int remoteness, Color color, int number) {
            this.number = number;
            this.a = constA * number;
            this.b = this.a + 75;
            this.size = size;
            this.remoteness = remoteness;
            this.coefficientOfMass = 1;
            this.angle = 0;
            this.color = color;
        }
    }
}
