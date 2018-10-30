package buildingsOffices;

import interfaces.Space;

import java.io.Serializable;

public class Office implements Space, Serializable, Cloneable{
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
    
    public String toString(){
        StringBuffer junction = new StringBuffer();
        junction.append("Office");
        junction.append(" [");
        junction.append(this.amountRoom);
        junction.append(", ");
        junction.append(this.area);
        junction.append("]");
        return junction.toString();
    }
    
    @Override
    public boolean equals(Object object){
       return ((Office)object).amountRoom == this.amountRoom || ((Office)object).area == this.area;
    }
    
    @Override
    public int hashCode(){
        byte a = (byte) this.getAmountRoom();
        byte b = (byte) this.getArea();
        return a^b;
    }

    @Override
    public Object clone(){
        Office newOffice = new Office();
        newOffice.amountRoom = this.getAmountRoom();
        newOffice.area = this.getArea();
        return newOffice;
    }
}