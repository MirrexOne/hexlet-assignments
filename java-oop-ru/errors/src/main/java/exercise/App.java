package exercise;

public class App {
    public static void printSquare(Circle circle) {
        double square;
        try {
            square = circle.getSquare();
            System.out.println((int) Math.ceil(square));
        } catch (NegativeRadiusException e) {
            System.out.print("Не удалось посчитать площадь\n");
        } finally {
            System.out.print("Вычисление окончено");
        }
    }
}
