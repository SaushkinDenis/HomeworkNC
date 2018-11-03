package buildings.dwelling;

import interfaces.Space;

import java.io.Serializable;

public class Flat implements Space, Serializable, Cloneable{
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
    
    public String toString(){
        StringBuffer junction = new StringBuffer();
        junction.append("Flat");
        junction.append(" [");
        junction.append(this.amountRoom);
        junction.append(", ");
        junction.append(this.area);
        junction.append("]");
        return junction.toString();
    }
    
    @Override
    public boolean equals(Object object){
        return ((Flat)object).amountRoom == this.amountRoom || ((Flat)object).area == this.area;
    }
    
    @Override
    public int hashCode(){
        byte a = (byte) this.getAmountRoom();
        byte b = (byte) this.getArea();
        return a^b;
    }
    
    @Override
    public Object clone(){
        Flat newFlat = new Flat();
        newFlat.amountRoom = this.getAmountRoom();
        newFlat.area = this.getArea();
        return newFlat;
    }
}