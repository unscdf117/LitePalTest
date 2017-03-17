package com.unsc.a117.litepaltest;

import org.litepal.crud.DataSupport;

/**
 * Created by Administrator on 2017/3/17.
 */
//需要增删改查 需要集成自DataSupport
public class Book extends DataSupport{
    private int id;
    private String name;
    private String author;
    private double price;
    private int pages;

    private String press; //新增出版社

    public int getId(){
        return id;
    }
    public void setId(int id){
        this.id = id;
    }
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }
    public String getAuthor(){
        return author;
    }
    public void setAuthor(String author){
        this.author = author;
    }
    public double getPrice(){
        return price;
    }
    public void setPrice(double price){
        this.price = price;
    }
    public int getPages(){
        return pages;
    }
    public void setPages(int pages){
        this.pages = pages;
    }
    public String getPress(){
        return press;
    }
    //新增press出版社的逻辑
    public void setPress(String press){
        this.press = press;
    }
}
