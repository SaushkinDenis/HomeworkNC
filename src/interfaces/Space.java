package interfaces;

public interface Space {

    int getAmountRoom();
    
    void setAmountRoom(int amountRoom);
    
    int getArea();
    
    void setArea(int area);
    
    String toString();
    
    int hashCode();
    
    Object clone();
}
