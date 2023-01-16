package com.example.demo;

import java.sql.*;
import java.util.ArrayList;

public class FruitDAOImp extends BaseDAO<Fruit> implements FruitDAO{
    @Override
    public ArrayList<Fruit> getFruitList() {
        String sql="select * from fruit_list";
        try {
            return selectALL(sql);
        } catch (SQLException|InstantiationException|IllegalAccessException|NoSuchFieldException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean addFruit(Fruit fruit) {
        String sql="insert into fruit_list values(?,?,?,?,?,?)";
        int flag=update(sql,fruit.getFruit_id(),fruit.getFruit_name(),fruit.getPrice(),fruit.getNumber(),fruit.getTotal(),fruit.getRemark());
        return flag>0;
    }

    @Override
    public boolean modifyFruit(Fruit fruit) {
        String sql="update fruit_list set number=? where fid=?";
        int flag=update(sql,fruit.getNumber(),fruit.getFruit_id());
        return flag>0;
    }

    @Override
    public Fruit getFruitByName(String fruit_name) {
        return null;
    }

    @Override
    public boolean delFruit(String fruit_name) {
        String sql="delete from fruit_list where fruit_name like ?";
        int flag=update(sql,fruit_name);
        return flag>0;
    }

    @Override
    public Fruit getFruitById(int fruit_id) {

        try {
            String sql="select * from fruit_list where fruit_id like ?";
            return select(sql,fruit_id);
        } catch (SQLException|NoSuchFieldException|IllegalAccessException|InstantiationException e) {
            throw new RuntimeException(e);
        }
    }
}
