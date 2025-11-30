package lab3.model;

public abstract class Shape implements Drawable {
    protected String shapeColor;

    public Shape(String shapeColor) {
        this.shapeColor = shapeColor;
    }

    public abstract double calcArea();

    @Override
    public void draw() {
        System.out.println(this);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() +
                " [color=" + shapeColor +
                ", area=" + calcArea() + "]";
    }

    public String getShapeColor() {
        return shapeColor;
    }

}
