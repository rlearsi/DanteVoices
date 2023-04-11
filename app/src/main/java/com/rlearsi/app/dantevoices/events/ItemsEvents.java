package com.rlearsi.app.dantevoices.events;

import java.io.Serializable;

public class ItemsEvents implements Serializable {

    private int id;
    private String title, file_name;

    public ItemsEvents(int id, String title, String file_name){

        this.id             = id;
        this.title          = title;
        this.file_name      = file_name;

    }

    public int getId(){ return this.id; }
    public String getTitle(){ return this.title; }
    public String getFileName() { return this.file_name; }

    @Override
    public boolean equals(Object o){
        return this.id == ((ItemsEvents)o).id;
    }

    @Override
    public int hashCode(){
        return this.id;
    }
}