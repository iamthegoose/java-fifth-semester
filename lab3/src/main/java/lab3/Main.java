package lab3;

import lab3.model.*;
import lab3.view.ShapeView;
import lab3.controller.ShapeController;

public class Main {
    public static void main(String[] args) {

        Shape[] shapes = {
                new Rectangle("Red", 3, 4),
                new Circle("Blue", 2),
                new Triangle("Green", 3, 5),
                new Rectangle("Black", 5, 6),
                new Circle("Yellow", 4),
                new Triangle("White", 6, 2),
                new Rectangle("Red", 1, 2),
                new Circle("Blue", 3),
                new Triangle("Green", 4, 4),
                new Rectangle("Pink", 7, 3),
        };

        ShapeView view = new ShapeView();
        ShapeController controller = new ShapeController(shapes, view);

        controller.showAll();
        controller.totalArea();
        controller.totalAreaByType(Rectangle.class);
        controller.totalAreaByType(Circle.class);
        controller.totalAreaByType(Triangle.class);

        controller.sortByArea();
        controller.sortByColor();
    }
}
