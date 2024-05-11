package exercise;

public class Cottage implements Home {
    private double area;
    private int floorCount;

    public Cottage(double area, int floorCount) {
        this.area = area;
        this.floorCount = floorCount;
    }

    @Override
    public double getArea() {
        return area;
    }

    @Override
    public String toString() {
        return floorCount + " этажный " +
                "коттедж " +
                "площадью " + area
                + " метров";
    }
}
