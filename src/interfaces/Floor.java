package interfaces;

import java.util.ArrayList;

public interface Floor {
    
    ArrayList<Space> junction = new ArrayList();

    int getAmountJunctionsOnFloor();
    
    int getTotalAreaJunctionOnFloor();
    
    int getAmountRoomsOnFloor();
    
    ArrayList<Space> getMassiveJunction();
    
    Space getJunction(int numberJunction);
    
    void setJunction(int numberJunction, Space junction);
    
    void addJunction(int nextNumberJunction, Space junction);
    
    void delJunction(int numberJunction);
    
    Space getBestSpace();
    
//    Object clone() throws CloneNotSupportedException;
    
}
