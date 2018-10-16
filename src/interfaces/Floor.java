package interfaces;

import java.util.ArrayList;

public interface Floor {

    public ArrayList<Space> flats = new ArrayList();

    int getAmountJunctionOnFloor();
    
    int getTotalAreaJunctionOnFloor();
    
    int getAmountRoomsOnFloor();
    
    ArrayList<Space> getMassiveJunction();
    
    Space getJunction(int numberJunction);
    
    void setJunction(int numberJunction, Space junction);
    
    void addJunction(int nextNumberJunction, Space junction);
    
    void delJunction(int numberJunction);
    
    Space getBestSpace();
}
