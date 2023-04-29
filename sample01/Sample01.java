package ru.geekbrains.sample01;

import java.util.Arrays;

public class Sample01 {

    public static void main(String[] args) {


        SimpleAccount simpleAccount = new SimpleAccount(5, 1000.15);
        int simpleAccountId = simpleAccount.getId();

        System.out.println(simpleAccount);

        UniversalAccount universalAccount1 = new UniversalAccount("AA11BB", 1100.15);
        System.out.println(universalAccount1);
        UniversalAccount universalAccount2 = new UniversalAccount(5001, 1100.15);
        System.out.println(universalAccount2);
        UniversalAccount universalAccount3 = new UniversalAccount(new CustomAccountIdentifier(1001, "BB"), 1100.15);
        System.out.println(universalAccount3);

        //CustomAccountIdentifier identifier =  (CustomAccountIdentifier)universalAccount2.getId();

        Account<Integer> account1 = new Account<>(1002, 1100.15);
        System.out.println(account1);
        int account1Id = account1.getId();

        Account<CustomAccountIdentifier> account2 = new Account<>(new CustomAccountIdentifier(1001, "BB"), 1100.15);
        System.out.println(account2);
        CustomAccountIdentifier account2Id = account2.getId();

        Integer[] numbers = {2, 4, 5 , 6, 7, 8, 9 } ;
        Object[] numbersNew = ArrayUtils.replaceTwoElements(numbers, 1, 2);
        String[] strs = { "AAA", "BBB", "CCCC", "DDDD" } ;
        String[] strsNew =  (String[]) ArrayUtils.replaceTwoElements(strs, 1, 2);
        System.out.println(Arrays.toString(numbersNew));
        System.out.println(Arrays.toString(strsNew));

        Integer[] aa = ArrayUtils.replaceTwoElementsV2(numbers, 2, 3);

    }

}

class ArrayUtils{

    public static <T> T[] replaceTwoElementsV2(T[] array, int index1, int index2){
        T[] newArray = array.clone();
        if (index1 >= array.length || index2 >= array.length || index1 < 0 || index2 < 0)
            return null;
        T obj = newArray[index1];
        newArray[index1] = newArray[index2];
        newArray[index2] = obj;
        return newArray;
    }
    public static Object[] replaceTwoElements(Object[] array, int index1, int index2){
        Object[] newArray = array.clone();
        if (index1 >= array.length || index2 >= array.length || index1 < 0 || index2 < 0)
            return null;
        Object obj = newArray[index1];
        newArray[index1] = newArray[index2];
        newArray[index2] = obj;
        return newArray;
    }

}

class Account<T>{

    private final T id;
    private double amount;

    public T getId() {
        return id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Account(T id, double amount) {
        this.id = id;
        this.amount = amount;
    }

    @Override
    public String toString() {
        return String.format("Сумма средств на счете #%s : %.2f руб.\n", id, amount);
    }


}

class UniversalAccount{
    private final Object id;
    private double amount;

    public Object getId() {
        return id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public UniversalAccount(Object id, double amount) {
        this.id = id;
        this.amount = amount;
    }

    @Override
    public String toString() {
        return String.format("Сумма средств на счете #%s : %.2f руб.\n", id, amount);
    }
}

class CustomAccountIdentifier{

    private int id;
    private String prefix;

    public int getId() {
        return id;
    }

    public String getPrefix() {
        return prefix;
    }

    public CustomAccountIdentifier(int id, String prefix) {
        this.id = id;
        this.prefix = prefix;
    }

    @Override
    public String toString() {
        return "CustomAccountIdentifier{" +
                "id=" + id +
                ", prefix='" + prefix + '\'' +
                '}';
    }
}

class SimpleAccount{

    private final int id;
    private double amount;

    public int getId() {
        return id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public SimpleAccount(int id, double amount) {
        this.id = id;
        this.amount = amount;
    }

    @Override
    public String toString() {
        return String.format("Сумма средств на счете #%s : %.2f руб.\n", id, amount);
    }
}
