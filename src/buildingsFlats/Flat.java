package buildingsFlats;

import interfaces.Space;

import java.io.Serializable;

public class Flat implements Space, Serializable{
    int area;
    int amountRoom;

    public Flat(){
        this.area = 50;
        this.amountRoom = 2;
    }
    
    public Flat(int area){
        this.area = area;
        this.amountRoom = 2;
    }
    
    public Flat(int area, int amountRoom){
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