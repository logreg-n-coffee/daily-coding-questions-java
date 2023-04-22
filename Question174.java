/*
Describe and give an example of each of the following types of polymorphism:

Ad-hoc polymorphism
Parametric polymorphism
Subtype polymorphism
 */

import java.util.ArrayList;
import java.util.List;

public class Question174 {
    /*
        Ad-hoc polymorphism, also known as function overloading,
        occurs when multiple functions have the same name but different parameter lists.
        The appropriate function is chosen based on the number and types of arguments provided at the call site.
     */
    static class AdHocPolymorphism {
        public static void main(String[] args) {
            System.out.println(add(1, 2)); // prints 3
            System.out.println(add(1.5, 2.3)); // prints 3.8
        }

        static int add(int a, int b) {
            return a + b;
        }

        static double add(double a, double b) {
            return a + b;
        }
    }

    /*
        Parametric polymorphism, also known as generics in Java,
        allows functions or classes to work with multiple types without
        specifying the exact type during implementation.
        The actual type is provided during the instantiation or invocation of the generic function or class.
     */
    static class ParametricPolymorphism {
        public static void main(String[] args) {
            List<Integer> intList = new ArrayList<>();
            intList.add(1);
            intList.add(2);
            System.out.println(sum(intList));  // prints 3

            List<Double> doubleList = new ArrayList<>();
            doubleList.add(1.5);
            doubleList.add(2.3);
            System.out.println(sum(doubleList));  // prints 3.8
        }

        static <T extends Number> double sum(List<T> numbers) {
            double sum = 0;
            for (T number : numbers) {
                sum += number.doubleValue();
            }
            return sum;
        }
    }

    /*
        Subtype polymorphism, also known as inheritance or interface polymorphism,
        allows a reference variable of a superclass or interface to hold an object of a subclass or implementation.
        This allows the program to decide at runtime which implementation to call, based on the object's actual type.
     */
    interface Animal {
        void makeSound();
    }

    static class Dog implements Animal {
        @Override
        public void makeSound() {
            System.out.println("Woof!");
        }
    }

    static class Cat implements Animal {
        @Override
        public void makeSound() {
            System.out.println("Meow!");
        }
    }

    static class SubtypePolymorphism {
        public static void main(String[] args) {
            Animal myDog = new Dog();
            Animal myCat = new Cat();
            myDog.makeSound();  // prints "Woof!"
            myCat.makeSound();  // prints "Meow!"
        }
    }
}
