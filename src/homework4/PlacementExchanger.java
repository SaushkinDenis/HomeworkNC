package homework4;
import interfaces.Building;
import interfaces.Floor;
import interfaces.Space;

public class PlacementExchanger {
    
    static boolean exchangeRooms(Space junction1, Space junction2){
        boolean flag = false;
        if(junction1.getAmountRoom() == junction2.getAmountRoom() && junction1.getArea() == junction2.getArea()){
            flag = true;
        }
        return flag;
    }
    
    static boolean exchangeFloors(Floor junction1, Floor junction2){
        boolean flag = false;
        if(junction1.getAmountJunctionsOnFloor() == junction2.getAmountJunctionsOnFloor()&& junction1.getTotalAreaJunctionOnFloor() == junction2.getTotalAreaJunctionOnFloor()){
            flag = true;
        }
        return flag;
    }
    
    public static void exchangeFloorsRooms (Floor floor1, int index1, Floor floor2, int index2){
        Space space1, space2, change;
        space1 = floor1.getJunction(index1);
        space2 = floor2.getJunction(index2);
        if(exchangeRooms(space1, space2)){
           change = space1;
           space1 = space2;
           space2 = change;
        } 
    }
    
    public static void exchangeBuildingFloors(Building building1, int index1, Building building2, int index2){
        Floor floor1, floor2, change;
        floor1 = building1.getFloor(index1);
        floor2 = building2.getFloor(index2);
        if(exchangeFloors(floor1, floor2)){
           change = floor1;
           floor1 = floor2;
           floor2 = change;
        }  
    }
}
