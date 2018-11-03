package interfaces;

import java.util.ArrayList;

public interface Building {
    
    int getTotalAmountFloor();
    
    int getTotalAmountJunction();
    
    int getTotalAreaJunction();
    
    int getTotalAmountRooms();
    
    ArrayList<Floor> getMassiveFloors();
    
    Floor getFloor (int numberFloor);
    
    void setFloor (int numberFloor, Floor junctionFloor);
    
    Space getJunctionInBuild(int findNumberJunctionInBuild);
    
    void setJunctionInBuild(int numberJunctionInBuild, Space junction);
    
    void addJunctionInBuild(int nextNumberJunctionInBuild, Space junction);
    
    void delJunctionInBuild(int nextNumberJunctionInBuild);
    
    Space getBestSpace();
    
    ArrayList getSortMassiveAreaJunction();
    
    Object clone();
}
