package com.example.demo;

public class Fruit {
    int fruit_id;
    String fruit_name;
    int price;
    int number;
    int total;
    String remark;
    public Fruit(){};
    public Fruit(int fruit_id,String fruit_name,int price,int number,int total,String remark)
    {
        this.fruit_id=fruit_id;
        this.fruit_name=fruit_name;
        this.price=price;
        this.number=number;
        this.total=total;
        this.remark=remark;
    }

    public void setFruit_id(int fruit_id) {
        this.fruit_id = fruit_id;
    }

    public int getFruit_id() {
        return fruit_id;
    }

    public String getFruit_name() {
        return fruit_name;
    }

    public int getPrice() {
        return price;
    }

    public int getNumber() {
        return number;
    }

    public int getTotal() {
        return total;
    }

    public String getRemark() {
        return remark;
    }



    public void setFruit_name(String fruit_name) {
        this.fruit_name = fruit_name;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
