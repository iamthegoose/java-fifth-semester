package lab5.shapes;

public class Rectangle extends Shape {
    private double w, h;

    public Rectangle(double w, double h) {
        this.w = w;
        this.h = h;
    }

    @Override
    public double area() {
        return w * h;
    }

    @Override
    public String toString() {
        return "Rectangle " + w + "x" + h + ", area=" + area();
    }
}
