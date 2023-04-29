package ru.geekbrains.sample01;

import java.util.ArrayList;
import java.util.Random;

/**
     Домашняя работа, задача:
     ========================

     a. Даны классы Fruit, Apple extends Fruit, Orange extends Fruit;
     b. Класс Box, в который можно складывать фрукты. Коробки условно сортируются по типу фрукта,
     поэтому в одну коробку нельзя сложить и яблоки, и апельсины;
     c. Для хранения фруктов внутри коробки можно использовать ArrayList;
     d. Сделать метод getWeight(), который высчитывает вес коробки, зная вес одного фрукта и их количество:
     вес яблока – 1.0f, апельсина – 1.5f (единицы измерения не важны);
     e. Внутри класса Box сделать метод compare(), который позволяет сравнить текущую коробку с той, которую
     подадут в compare() в качестве параметра. true – если их массы равны, false в противоположном случае.
     Можно сравнивать коробки с яблоками и апельсинами;
     f. Написать метод, который позволяет пересыпать фрукты из текущей коробки в другую.
     Помним про сортировку фруктов: нельзя яблоки высыпать в коробку с апельсинами.
     Соответственно, в текущей коробке фруктов не остается, а в другую перекидываются объекты, которые были в первой;
     g. Не забываем про метод добавления фрукта в коробку.
 */
public class Homework {
    public static void main(String[] args) {
        ArrayList<Apple> arr = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            arr.add(i, new Apple());
            
        }
        Box<Apple> box = new Box(arr);
        System.out.println(box.getWeight());
        box.<Apple>addFruitsToArray();
        // Box <Apple> boxWithApples01 = new Box<Apple>(new ArrayList<Apple>());
        // boxWithApples01.addFruitsToArray();
        // Box <Apple> boxWithApples02 = new Box(new ArrayList<Apple>());
        // boxWithApples02.addFruitsToArray();
        // Box <Apple> boxWithApples03 = new Box(new ArrayList<Apple>());
        // boxWithApples03.addFruitsToArray();
        // Box<Orange> boxWithOranges01 = new Box(new ArrayList<Orange>());
        // boxWithOranges01.addFruitsToArray();
        // Box<Orange> boxWithOranges02 = new Box(new ArrayList<Orange>());
        // boxWithOranges02.addFruitsToArray();
        // Box<Orange> boxWithOranges03 = new Box(new ArrayList<Orange>());
        // boxWithOranges03.addFruitsToArray();
        // Box<Orange> boxWithOranges04 = new Box(new ArrayList<Orange>());
        // boxWithOranges04.addFruitsToArray();
        
    }
}

abstract class Fruit{

    private final float weight;

    public float getWeight() {
        return weight;
    }

    public Fruit(float weight) {
        this.weight = weight;
    }
}

class Apple extends Fruit{

    public Apple() {
        super(1.0f);
    }

    @Override
    public String toString() {
        return String.format("Яблоко, весом %f", this.getWeight());
    }
}

class Orange extends Fruit{
    public Orange(){
        super(1.5f);
    }

    @Override
    public String toString() {
        return String.format("Апельсин, весом %f", this.getWeight());
    }
}

class Box <T extends Fruit>{

    private ArrayList<T> fruits;

    public Box(ArrayList fruits){
        this.fruits = fruits;
    }

    public addFruitsToArray(){
        Random rand = new Random();
        int amount = rand.nextInt(3,12);
        for (int i = 0; i < amount; i++) {
            this.fruits.add(new T());
        }
        System.out.println(this.fruits.get(0).toString() + amount + "штук");
    }


    public double getWeight(){
        double weight = 0;
        for (T fruit : fruits) {
            weight += fruit.getWeight();
        }
        return weight;
    }

    public int howMany(){
        int count = 0;
        for (T fruit : fruits) {
            count++;
        }
        return count;
    }

    public boolean compare(Box anotherBox){
        return (this.getWeight() == anotherBox.getWeight());
    }

    public double pourInto(Box<T> anotherBox){
        return this.getWeight() + anotherBox.getWeight();

    }

}

