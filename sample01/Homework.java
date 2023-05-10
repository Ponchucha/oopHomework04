package ru.geekbrains.sample01;

import java.util.ArrayList;
import java.util.Arrays;
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
        Box<Fruit> randomBox01 = new Box(createRandomFriutArray()); // чтобы был какой-то смысл в этих всех проверках, сделала изначальные коробки со смешанным содержимым
        //System.out.println(randomBox01.toString());  
        Box<Fruit> randomBox02 = new Box(createRandomFriutArray()); 
        Box<Fruit> randomBox03 = new Box(createRandomFriutArray());
        
        Box<Apple> appleBox01 = new Box(createAppleArray());
        //System.out.println(appleBox01.toString());
        Box<Apple> appleBox02 = new Box(new ArrayList<Apple>(Arrays.asList(new Apple(), new Apple(), new Apple())));
        //System.out.println(appleBox02.toString());

        Box<Orange> orangeBox01 = new Box(new ArrayList<Orange>(Arrays.asList(new Orange(), new Orange())));
        //System.out.println(orangeBox01.toString());
        Box<Orange> orangeBox02 = new Box(new ArrayList<Orange>());// пустая
        //System.out.println(orangeBox02.toString());

       System.out.println(appleBox02.compare(orangeBox01)); 
       System.out.println(appleBox01.compare(orangeBox02)); // сравнивает. Не вижу смысла делать компаратор, здесь буллево значение, достаточно равернства

        randomBox01.pourInto(appleBox02);// яблоки пересыпали, апельсины оставили
        randomBox01.pourInto(orangeBox01);//теперь randomBox01 - пустая
        randomBox02.pourInto(randomBox01);//пересыпется только тот тип фруктов, который попал в коробку первым
        randomBox03.pourInto(orangeBox02);// в пустую коробку для апельсинов пересыпятся только апельсины
        appleBox01.pourInto(orangeBox01);//яблоки в апельсины не пересыпятся
        orangeBox01.getContainment().clear();//а если она будет пуста
        appleBox01.pourInto(orangeBox01);// почему-то пересыпается... Но выходит, что яблоки стали апельсинами?
        // Поясню: я пыталась использовать try/catch, чтобы обработать это исключение, но ошибки не возникло, компилятор съел этот фокус
        orangeBox01.pourInto(orangeBox02);//хоть яблоки и сошли за апельсины на предыдущем шаге, здесь они работают, как яблоки
        /*условию задачи это вроде бы не противоречит, но в перспективе может вызвать проблемы
        я не придумала способа сравнить тип коробки с типом содержимого. Буду благодарна за совет
        getclass для всех коробок возвращает Box, а содержимое можно проверить, только если коробка не пуста.
    System.out.println(appleBox01.getClass().equals(appleBox02.getClass()));
    System.out.println(appleBox01.getClass().equals(orangeBox02.getClass()));
        */

        appleBox01.getContainment().clear();
        appleBox01.addFruits(new ArrayList<Orange>(Arrays.asList(new Orange(), new Orange()))); // вот и в коробку из-под яблок апельсины складываются, если она пустая
        appleBox02.addFruits(new ArrayList<Orange>(Arrays.asList(new Orange(), new Orange()))); // но если в ней что-то уже есть, то отсев работает
    }

    static ArrayList<Fruit> createRandomFriutArray (){
        Random rand = new Random();
        ArrayList<Fruit> array  = new ArrayList<>();
        for (int i = 0; i < rand.nextInt(3,5); i++) {
            if(rand.nextInt(2) == 0){
            array.add(i, new Apple());  
            }
            else{
                array.add(i, new Orange());
            }        
        }
        return array;  
    }

    static ArrayList<Apple> createAppleArray (){
        Random rand = new Random();
        ArrayList<Apple> array  = new ArrayList<>();
        for (int i = 0; i < rand.nextInt(3,5); i++) {
            array.add(i, new Apple());  
            }
        return array;  
    }

    static ArrayList<Orange> createOrangeAArray (){ // тут два одинаковых метода, различающихся только типом передаваемых и возвращаемых данных
        Random rand = new Random();                 // можно ли как-то передать тип, в качестве переменной? Или это опять создание экземпляров, 
        ArrayList<Orange> array  = new ArrayList<>(); //и в обобщённом методе так делать нельзя?
        for (int i = 0; i < rand.nextInt(3,5); i++) { //просто нарушается принцип dry
            array.add(i, new Orange());  
            }
        return array;  
    }


}

abstract class Fruit{

    protected final float weight;

    public float getWeight() {
        return weight;
    }

    public Fruit(float weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return String.format("Фрукт, весом %.2f кг", this.weight);
    }
}

class Apple extends Fruit{

    public Apple() {
        super(1.0f);
    }

    @Override
    public String toString() {
        return String.format("Яблоко, весом %.2f", this.weight);
    }
}

class Orange extends Fruit{
    public Orange(){
        super(1.5f);
    }

    @Override
    public String toString() {
        return String.format("Апельсин, весом %.2f", this.weight);
    }
}

class Box <T extends Fruit>{

    private ArrayList<T> fruits;

    public Box(ArrayList fruits){
        this.fruits = fruits;
    }

    public ArrayList<T> getContainment(){
        return fruits;
    }

    public double getWeight(){
        double weight = 0;
        for (T fruit : fruits) {
            weight += fruit.getWeight();
        }
        return weight;
    }

    public <newT extends Fruit> void addFruits(ArrayList <newT> arr){
        int count = 0;
        for (newT fruit : arr) {            
            if(fruits.isEmpty() || fruit.getClass().equals(fruits.get(0).getClass())){
                fruits.add((T)fruit);
                count ++;
            }            
        }
        if (count == 0){
            System.out.println("В этой коробке уже лежат фрукты другого типа");
        }
        else{
            System.out.println("В коробку добавлено " + count + " фруктов");
            System.out.println("Теперь там вот что: \n" + this.fruits.toString());
            System.out.println("\n");
        }
    }

    public int howMany(){
        return fruits.size();
    }

    @Override
    public String toString() {
        if(this.fruits.isEmpty()){
            return "Коробка пуста, вес равен 0";
        }
        else{
            String result = "Вес коробки "+ String.format("%.2f", this.getWeight()) +" кг, содержимое: ";
            for (T fruit : fruits) {
                result += " | " + fruit.toString();
            }
            result += " |";
            return result;
        }
    }

    public boolean compare(Box anotherBox){
        return (this.getWeight() == anotherBox.getWeight());
    }

    public <anotherT extends Fruit> void pourInto(Box<anotherT> anotherBox){
        System.out.println("Первая коробка: " + this.toString());
        System.out.println("Вторая коробка: " + anotherBox.toString());
        int count = 0;
        if(! this.fruits.isEmpty()){
            if(anotherBox.getContainment().isEmpty()){
                anotherBox.getContainment().add((anotherT)this.fruits.remove(0));
                count = 1;
            }     
            for (int i = 0; i < this.getContainment().size(); i++) {                
                if(anotherBox.getContainment().get(0).getClass().equals(this.fruits.get(i).getClass())){//если класс нулевого элемента совпадает с добавляемым
                    anotherBox.getContainment().add((anotherT)this.fruits.remove(i));
                    i--;
                    count++;
                }
            }            
            System.out.println("Пересыпали " + count + " фруктов во вторую коробку. \nТеперь там вот что:\n" + anotherBox.toString());
            System.out.println("А в первой коробке осталось " + this.fruits.size() + " фруктов:\n" + this.toString());
        }
        else {
            System.out.println("Нечего пересыпать.");
        }
        System.out.println("\n");
        
    }
}

