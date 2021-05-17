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
        public static final int constA = 75;
        public static final int constB = 150;

        double x;
        double y;
        int a;
        int b;
        int size;
        int remoteness;
        double time;
        double speedDivider;
        Color color;

        public Planet(int size, int remoteness, Color color) {
            if (PlanetSystem.allPlanets.size() == 0) {
                this.a = constA;
                this.b = constB;
            } else {
                this.a = PlanetSystem.allPlanets.get(PlanetSystem.allPlanets.size() - 1).a + 75;
                this.b = PlanetSystem.allPlanets.get(PlanetSystem.allPlanets.size() - 1).b + 75;
            }
            this.size = size;
            this.remoteness = remoteness;
            this.speedDivider = 1;
            this.time = 0;
            this.color = color;
        }
    }
}
