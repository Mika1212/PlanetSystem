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
        double x;
        double y;
        int a;
        int b;
        int size;
        int remoteness;
        double time;
        double speedDivider;
        Color color;

        public Planet(int a, int b, int size, int remoteness, Color color) {
            this.a = a;
            this.b = b;
            this.size = size;
            this.remoteness = remoteness;
            this.speedDivider = 1;
            this.time = 0;
            this.color = color;
        }
    }
}
