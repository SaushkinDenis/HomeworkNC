package buildingsOffices;

import interfaces.Space;

import java.io.Serializable;

public class Office implements Space, Serializable{
    int area;
    int amountRoom;

    public Office(){
        this.area = 250;
        this.amountRoom = 1;
    }
    
    public Office(int area){
        this.area = area;
        this.amountRoom = 1;
    }
    
    public Office(int area, int amountRoom){
        this.area = area;
        this.amountRoom = amountRoom;
    }
    
    @Override
    public int getAmountRoom(){
        return this.amountRoom;
    }
    
    @Override
    public void setAmountRoom(int amountRoom){
        this.amountRoom = amountRoom;
    }
    
    @Override
    public int getArea(){
        return this.area;
    }
    
    @Override
    public void setArea(int area){
        this.area = area;
    }    
}