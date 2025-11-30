package lab3.view;

import lab3.model.Shape;

public class ShapeView {
    public void displayShapes(Shape[] shapes) {
        for (Shape s : shapes) {
            System.out.println(s);
        }
        System.out.println();
    }

    public void displayMessage(String msg) {
        System.out.println(msg);
    }
}
