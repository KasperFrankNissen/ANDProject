package com.example.andproject;

public class DatabaseModel {

    private int id;
    private String itemName;
    private String date;

    public DatabaseModel(int id, String itemName, String date) {
        this.id = id;
        this.itemName = itemName;
        this.date = date;
    }

    public DatabaseModel() {
    }

    @Override
    public String toString() {
        return "DatabaseItems{" +
                "id=" + id +
                ", itemName='" + itemName + '\'' +
                ", date='" + date + '\'' +
                '}';
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getItemName()
    {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
