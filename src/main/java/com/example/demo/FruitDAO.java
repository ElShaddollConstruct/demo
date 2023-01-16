package com.example.demo;



import java.util.ArrayList;

public interface FruitDAO {
    ArrayList<Fruit> getFruitList();
    boolean addFruit(Fruit fruit);
    boolean modifyFruit(Fruit fruit);
    Fruit getFruitByName(String fruit_name);
    boolean delFruit(String fruit_name);

    Fruit getFruitById(int fruit_id);
}
