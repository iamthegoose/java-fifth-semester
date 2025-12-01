package lab5.file;

import lab5.shapes.Shape;
import java.io.*;
import java.util.List;

public class ShapeStorage {

    public static void saveShapes(String path, List<Shape> shapes) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(path))) {
            oos.writeObject(shapes);
            System.out.println("Фігури збережено.");
        } catch (IOException e) {
            System.out.println("Помилка запису: " + e.getMessage());
        }
    }

    public static List<Shape> loadShapes(String path) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(path))) {
            return (List<Shape>) ois.readObject();
        } catch (Exception e) {
            System.out.println("Помилка читання: " + e.getMessage());
        }
        return null;
    }
}
