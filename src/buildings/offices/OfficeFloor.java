package buildings.offices;

import interfaces.Floor;
import interfaces.Space;

import java.io.Serializable;
import java.util.ArrayList;

public class OfficeFloor implements Floor, Serializable, Cloneable{
    int amountOfficesOnFloor; // Количество квартир на этаже 
    ArrayList<Space> offices; // Массив квартир на этаже [Квартира] [Квартира] []
        
    public OfficeFloor(int amountJunction){
        this.amountOfficesOnFloor = amountJunction;
    }
    
    public OfficeFloor(ArrayList<Space> offices){
        this.offices = offices;
    }
        
    @Override
    public int getAmountJunctionsOnFloor(){
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
    
    @Override
    public String toString(){
        StringBuffer junction = new StringBuffer();
        junction.append("OfficeFloor");
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
        return ((OfficeFloor)object).offices == this.offices;
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
        for (int i = 0; i < offices.size(); i++)
            arr.add((Space)offices.get(i).clone());
        return new OfficeFloor(arr); 
    }
}
