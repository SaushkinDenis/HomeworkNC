package homework4;

public class Exceptions {
    
}

class InexchangeableSpacesException{
    private int firstRoom;
    private int secondRoom;
    private String message;
    
    public int getFirstRoom(){
        return firstRoom;
    }
    
    public int getSecondRoom(){
        return secondRoom;
    }
    
    public String getMessage(){
        return message;
    }
    
    public InexchangeableSpacesException(String message, int firstRoom, int secondRoom){   
        this.message = message;
        this.firstRoom = firstRoom;
        this.secondRoom = secondRoom;
    }
}

class InexchangeableFloorsException{
    private int firstFloor;
    private int secondFloor;
    private String message;
    
    public int getFirstRoom(){
        return firstFloor;
    }
    
    public int getSecondRoom(){
        return secondFloor;
    }
    
    public String getMessage(){
        return message;
    }
    
    public InexchangeableFloorsException(String message, int firstFloor, int secondFloor){   
        this.message = message;
        this.firstFloor = firstFloor;
        this.secondFloor = secondFloor;
    }
}