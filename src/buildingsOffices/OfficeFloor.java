package buildingsOffices;

import interfaces.Floor;
import interfaces.Space;

import java.io.Serializable;
import java.util.ArrayList;

public class OfficeFloor implements Floor, Serializable{
    int amountOfficesOnFloor; // Количество квартир на этаже 
    ArrayList<Space> offices; // Массив квартир на этаже [Квартира] [Квартира] []
        
    public OfficeFloor(int amountJunction){
        this.amountOfficesOnFloor = amountJunction;
    }
    
    public OfficeFloor(ArrayList<Space> offices){
        this.offices = offices;
    }
        
    @Override
    public int getAmountJunctionOnFloor(){
        return this.offices.size();
    }

    @Override
    public int getTotalAreaJunctionOnFloor(){
        int totalArea = 0;
        for(Space f: this.offices){
            totalArea += f.getArea();
        }
        return totalArea;
    }

    @Override
    public int getAmountRoomsOnFloor(){
        int totalAmountRoom = 0;
        for(Space f: this.offices){
            totalAmountRoom += f.getAmountRoom();
        }
        return totalAmountRoom;
    }

    @Override
    public ArrayList<Space> getMassiveJunction(){
        return this.offices;
    } 

    @Override
    public Space getJunction(int numberJunction){
        return this.offices.get(numberJunction);
    }

    @Override
    public void setJunction(int numberJunction, Space flat){
        this.offices.set(numberJunction, flat);
    }
    
    @Override
    public void addJunction(int nextNumberJunction, Space flat){
        this.offices.add(nextNumberJunction, flat);
    }

    @Override
    public void delJunction(int numberJunction){
        this.offices.remove(numberJunction);
    }

    @Override
    public Space getBestSpace(){
        int bestArea = 0;
        Space findOffice = new Office();
        
        for(int i = 0; i <= this.offices.size()-1; i++){
            if (this.offices.get(i).getArea() >= bestArea){
                bestArea = this.offices.get(i).getArea();
                findOffice = this.offices.get(i);
            }
        }
        return findOffice;
    }
}
