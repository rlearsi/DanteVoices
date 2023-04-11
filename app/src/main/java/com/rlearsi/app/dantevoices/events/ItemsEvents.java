package com.rlearsi.app.dantevoices.events;

import java.io.Serializable;

public class ItemsEvents implements Serializable {

    private int id;
    private String title;

    public ItemsEvents(int id, String title){

        this.id             = id;
        this.title          = title;

    }

    public int getId(){ return this.id; }
    public String getTitle(){ return this.title; }

    @Override
    public boolean equals(Object o){
        return this.id == ((ItemsEvents)o).id;
    }

    @Override
    public int hashCode(){
        return this.id;
    }
}