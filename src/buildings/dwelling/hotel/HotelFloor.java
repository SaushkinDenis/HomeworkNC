package buildings.dwelling.hotel;

import buildings.dwelling.DwellingFloor;
import interfaces.Space;
import java.util.ArrayList;

public class HotelFloor extends DwellingFloor{
    int amountStarsOnFloor = 1;

    public HotelFloor(ArrayList<Space> flats) {
        super(flats);
    }

    public HotelFloor(int amountJunction) {
        super(amountJunction);
    }

    public int getStarsOnFloor(){
        return this.amountStarsOnFloor;
    }
    
    public void setStarsOnFloor(int amountStarsOnFloor){
        this.amountStarsOnFloor = amountStarsOnFloor;
    }
    
    public String toString(){
        StringBuffer junction = new StringBuffer();
        junction.append("HotelFloor");
        junction.append(" [");
        junction.append(this.getStarsOnFloor());
        junction.append(", ");
        junction.append(this.getAmountJunctionsOnFloor());
        junction.append(", ");
        
        for (int i = 0; i <= this.getAmountJunctionsOnFloor(); i++){
            junction.append(this.getJunction(i).toString());
            if(!(i == this.getAmountJunctionsOnFloor())) junction.append(", ");
        }
        
        junction.append("]");
        return junction.toString();
    }
    
}
