package buildings.dwelling.hotel;

import buildings.dwelling.Dwelling;
import interfaces.Floor;
import interfaces.Space;
import java.util.ArrayList;

public class Hotel extends Dwelling{
    
    public Hotel(int amountFloors, ArrayList amountFlatsOnFloors) {
        super(amountFloors, amountFlatsOnFloors);
    }

    public Hotel(ArrayList<Floor> dwellingFloors) {
        super(dwellingFloors);
    }
    
    int getStarsHotel(){
        int starsHotel = 0;
        for(Floor floor: this.getMassiveFloors()){
            HotelFloor hotelFloor = new HotelFloor(floor.getMassiveJunction());
            if(hotelFloor.getStarsOnFloor() > starsHotel){
                starsHotel = hotelFloor.getStarsOnFloor();
            }
        }
        return starsHotel;
    }
    
    public Space getBestSpace(){
        Space bestSpace = null;
        int starsHotel = 0;
        for(Floor floor: this.getMassiveFloors()){
            HotelFloor hotelFloor = new HotelFloor(floor.getMassiveJunction());
            if(hotelFloor.getStarsOnFloor() > starsHotel){
                starsHotel = hotelFloor.getStarsOnFloor();
                bestSpace = hotelFloor.getBestSpace();
            }
        }       
        return bestSpace;
    }
    
        public String toString(){
        StringBuffer junction = new StringBuffer();
        junction.append("HotelBuilding");
        junction.append(" [");
        junction.append(this.getStarsHotel());
        junction.append(", ");
        junction.append(this.getTotalAmountFloor());
        junction.append(", ");
        
        for (int i = 1; i <= this.getTotalAmountFloor(); i++){
            junction.append(this.getFloor(i).toString());
            if(!(i == this.getTotalAmountFloor())) junction.append(", ");
        }
        
        junction.append("]");
        return junction.toString();
    }
}