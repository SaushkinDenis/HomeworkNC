package buildings.dwelling;

import interfaces.Floor;
import interfaces.Space;

import java.io.Serializable;
import java.util.ArrayList;

public class DwellingFloor implements Floor, Serializable, Cloneable{
    int amountFlatOnFloor;
    ArrayList<Space> flats;
        
    public DwellingFloor(int amountJunction){
        this.amountFlatOnFloor = amountJunction;
    }
    
    public DwellingFloor(ArrayList<Space> flats){
        this.flats = flats;
    }
 
    @Override
    public int getAmountJunctionsOnFloor(){
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
    
    @Override
    public String toString(){
        StringBuffer junction = new StringBuffer();
        junction.append("DwellingFloor");
        junction.append(" [");
        junction.append(this.getAmountJunctionsOnFloor());
        junction.append(", ");
        
        for (int i = 0; i <= this.getAmountJunctionsOnFloor(); i++){
            junction.append(this.getJunction(i).toString());
            if(!(i == this.getAmountJunctionsOnFloor())) junction.append(", ");
        }
        
        junction.append("]");
        return junction.toString();
    }
    
    @Override
    public boolean equals(Object object){
        return ((DwellingFloor)object).flats == this.flats;
    }
    
    @Override
    public int hashCode(){
        byte a = (byte) this.getAmountJunctionsOnFloor();
        byte b = 0;
        for(Space s: this.getMassiveJunction()){
            b += (byte) s.hashCode();
        }
        return a^b;
    }
    
    @Override
    public Object clone(){
        ArrayList<Space> arr = new ArrayList<Space>();       
        for (int i = 0; i < flats.size(); i++)
            arr.add((Space)flats.get(i).clone());
        return new DwellingFloor(arr);  
    }
}
