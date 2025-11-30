package lab3.controller;

import lab3.model.Shape;
import lab3.view.ShapeView;

import java.util.Arrays;
import java.util.Comparator;

public class ShapeController {
    private final Shape[] shapes;
    private final ShapeView view;

    public ShapeController(Shape[] shapes, ShapeView view) {
        this.shapes = shapes;
        this.view = view;
    }

    public void showAll() {
        view.displayMessage("Усі фігури:");
        view.displayShapes(shapes);
    }

    public void sortByArea() {
        Arrays.sort(shapes, Comparator.comparingDouble(Shape::calcArea));
        view.displayMessage("Сортування за площею:");
        view.displayShapes(shapes);
    }

    public void sortByColor() {
        Arrays.sort(shapes, Comparator.comparing(Shape::getShapeColor));
        view.displayMessage("Сортування за кольором:");
        view.displayShapes(shapes);
    }

    public void totalArea() {
        double sum = Arrays.stream(shapes)
                .mapToDouble(Shape::calcArea).sum();
        view.displayMessage("Сумарна площа всіх фігур: " + sum + "\n");
    }

    public void totalAreaByType(Class<?> type) {
        double sum = Arrays.stream(shapes)
                .filter(s -> s.getClass() == type)
                .mapToDouble(Shape::calcArea)
                .sum();

        view.displayMessage("Сумарна площа " + type.getSimpleName() + ": " + sum + "\n");
    }
}
