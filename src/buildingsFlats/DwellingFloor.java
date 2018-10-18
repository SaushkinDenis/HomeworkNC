package buildingsFlats;

import interfaces.Floor;
import interfaces.Space;

import java.io.Serializable;
import java.util.ArrayList;

public class DwellingFloor implements Floor, Serializable{
    int amountFlatOnFloor; // Количество квартир на этаже 
    ArrayList<Space> flats; // Массив квартир на этаже [Квартира] [Квартира] []
        
    public DwellingFloor(int amountJunction){
        this.amountFlatOnFloor = amountJunction;
    }
    
    public DwellingFloor(ArrayList<Space> flats){
        this.flats = flats;
    }
 
    @Override
    public int getAmountJunctionOnFloor(){
        return this.flats.size();
    }

    @Override
    public int getTotalAreaJunctionOnFloor(){
        int totalArea = 0;
        for(Space f: this.flats){
            totalArea += f.getArea();
        }
        return totalArea;
    }

    @Override
    public int getAmountRoomsOnFloor(){
        int totalAmountRoom = 0;
        for(Space f: this.flats){
            totalAmountRoom += f.getAmountRoom();
        }
        return totalAmountRoom;
    }

    @Override
    public ArrayList<Space> getMassiveJunction(){
        return this.flats;
    } 

    @Override
    public Space getJunction(int numberJunction){
        return this.flats.get(numberJunction);
    }

    @Override
    public void setJunction(int numberJunction, Space flat){
        this.flats.set(numberJunction, flat);
    }
    
    @Override
    public void addJunction(int nextNumberJunction, Space flat){
        this.flats.add(nextNumberJunction, flat);
    }

    @Override
    public void delJunction(int numberJunction){
        this.flats.remove(numberJunction);
    }

    @Override
    public Space getBestSpace(){
        int bestArea = 0;
        Space findFlat = new Flat();
        
        for(int i = 0; i <= this.flats.size()-1; i++){
            if (this.flats.get(i).getArea() >= bestArea){
                bestArea = this.flats.get(i).getArea();
                findFlat = this.flats.get(i);
            }
        }
        return findFlat;
    }
}
