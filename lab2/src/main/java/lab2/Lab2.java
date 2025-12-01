package lab2;

import com.google.gson.Gson;
import java.util.Objects;

public class Lab2 {
    public static void main(String[] args) {
        Gson gson = new Gson();

        Person original = new Person("Shevchenko", "Andriy", 19);
        String json = gson.toJson(original);
        Person restored = gson.fromJson(json, Person.class);

        System.out.println("JSON: " + json);
        System.out.println("Are equal? " + original.equals(restored));
    }
}

class Person {
    private String lastName;
    private String firstName;
    private int age;

    public Person(String lastName, String firstName, int age) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.age = age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person p = (Person) o;
        return age == p.age &&
               Objects.equals(lastName, p.lastName) &&
               Objects.equals(firstName, p.firstName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lastName, firstName, age);
    }
}

