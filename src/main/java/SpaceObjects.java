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
        Color color;

        public Planet(int a, int b, int size, int remoteness, Color color) {
            if (PlanetSystem.allPlanets.size() == 0) {
                this.a = a;
                this.b = b;
            } else {
                this.a = PlanetSystem.allPlanets.get(PlanetSystem.allPlanets.size() - 1).a + size / 2 +
                        PlanetSystem.allPlanets.get(PlanetSystem.allPlanets.size() - 1).size / 2;
                this.b = (int) (PlanetSystem.allPlanets.get(PlanetSystem.allPlanets.size() - 1).b + size / 1.5 +
                                        PlanetSystem.allPlanets.get(PlanetSystem.allPlanets.size() - 1).size / 1.5);
            }

            this.size = size;
            this.remoteness = remoteness;
            this.color = color;
        }
    }
}
