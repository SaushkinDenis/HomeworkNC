package buildingsFlats;

import static homework4.Buildings.deserializeBuilding;
import static homework4.Buildings.inputBuilding;
import static homework4.Buildings.outputBuilding;
import static homework4.Buildings.serializeBuilding;
import interfaces.Building;
import interfaces.Floor;
import interfaces.Space;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;


import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Creator {

    public static void main(String... args) throws InvalidSpaceAreaException, InvalidRoomsCountException{
        ArrayList<ArrayList<Space>> building = new ArrayList(); // Создание здания (Массива [этажей] массивов квартир) для работы с Dwelling
        ArrayList<Floor> dwellingFloors = new ArrayList(); // Создание этажей (Массива этажей массивов квартир) для работы с DwellingFloor
        ArrayList<Space> flats; // Создание квартир (Массив квартир) для работы с Flat
        
        Floor dwellingFloor; // Этаж
        Building dwelling = null; // Подъезд
        
// Создание этажей здания и заполнение их 
        int amountFloors = 1 + (int) (Math.random() * 10);
        for (int i = 0; i <= amountFloors; i++){
            building.add(getMassivJunction());
        }
// ----------------------------

        int j = 0;
        for(int i = 1; i <= amountFloors; i++){
            System.out.println("\t\tFloor #" + i + " :");
//  Изменение-оформление [типов] Подъезд <-> Этаж <-> Квартира
            flats =  building.get(i); // Массив квартир
            dwellingFloor = new DwellingFloor(flats); // Этаж
            dwellingFloors.add(dwellingFloor); // Массив этажей
            dwelling = new Dwelling(dwellingFloors); // Подъезд
            

//              --------  Добавление квартиры на каждый этаж (+ обработчик) ------------
//        try{
//            int numberNewFlat = 0;
//            dwellingFloor.addJunction(numberNewFlat, new Flat());
//        } catch (SpaceIndexOutOfBoundsException ex){
//            System.out.println(ex.getMessage());
//            System.out.println(ex.getNumberFlat());
//        } 
//
//           --------  Удаление квартиры с каждого этажа (+ обработчик) ------------
//            try{
//                int numderDelFlat = 0;
//                dwellingFloor.delJunction(numderDelFlat);
//            } catch (SpaceIndexOutOfBoundsException ex){
//                System.out.println(ex.getMessage());
//                System.out.println(ex.getNumberFlat());
//            }


// ------------------
            System.out.println("\nFloor.Info:");
            System.out.println("Amount of the flats on floor - " + dwellingFloor.getAmountJunctionOnFloor() + " qty.");
            System.out.println("Total area of the flats on floor - " + dwellingFloor.getTotalAreaJunctionOnFloor() + " sq.m.");
            System.out.println("Total rooms of the flats on floor - " + dwellingFloor.getAmountRoomsOnFloor() + " qty.");
            System.out.println("Best space of the flats on floor - " + dwellingFloor.getBestSpace().getArea() + " qty.");

//            dwellingFloor.addFlat(dwellingFloor.getAmountFlatOnFloor(), new Flat());
//            System.out.println(" New amount of the flats on floor - " + dwellingFloor.getAmountFlatOnFloor() + " qty.");

//            dwellingFloor.delFlat(dwellingFloor.getAmountFlatOnFloor());
//            System.out.println(" New amount of the flats on floor - " + dwellingFloor.getAmountFlatOnFloor() + " qty.");            
            
            System.out.println();
            for(Space f : building.get(i)){
                System.out.println("Flat #" + j + " : Area - " + f.getArea() + " sq.m., Amount room - " + f.getAmountRoom() + " qty.");
                j++; 
            }
            System.out.println("----------------------------------------------");    
        }

//              --------  Добавление квартиры на определенный этаж (+ обработчик) ------------
//        try{
//            int numberNewFlatInBuild = 10;
//            dwellingBuilding.addJunctionInBuild(numberNewFlatInBuild, new Flat());    
//        } catch (SpaceIndexOutOfBoundsException ex){
//            System.out.print(ex.getMessage()+ ": ");
//            System.out.println(ex.getNumberFlat());
//        } 

//              --------  Обнуление квартиры на определенном этаже (+ обработчик) ------------    
//        try{
//            int numberNewFlatInBuild = 10;
//            dwellingBuilding.delJunctionInBuild(numberNewFlatInBuild);    
//        } catch (SpaceIndexOutOfBoundsException ex){
//            System.out.print(ex.getMessage()+ ": ");
//            System.out.println(ex.getNumberFlat());
//        }
        
//          ------------------- Вывод информации по подъезду --------------------------    

            System.out.println("\nDwelling.Info");
            System.out.println("Amount of the floors - " + dwelling.getTotalAmountFloor() + " qty.");
            System.out.println("Amount of the flats - " + dwelling.getTotalAmountJunction() + " qty.");
            System.out.println("Area of the flats in building - " + dwelling.getTotalAreaJunction() + " sq.m.");
            System.out.println("Amount of the rooms in building - " + dwelling.getTotalAmountRooms() + " qty.");
            System.out.println("Best space of the flats in building - " + dwelling.getBestSpace().getArea() + " sq.m.");
            
//            System.out.println("Massive of the floors - " + dwelling.getMassiveFloors() + " qty.");

//            dwelling.addFlatInBuild(dwelling.getTotalAmountFlat(), new Flat());
//            System.out.println("New amount of the flats - " + dwelling.getTotalAmountFlat() + " qty.");
//            Flat f = building.get(dwelling.getTotalAmountFloor()).get(dwellingFloor.getAmountFlatOnFloor()-2);
//            System.out.println("New flat #" + j + " : Area - " + f.getArea() + " sq.m., Amount room - " + f.getAmountRoom() + " qty.");
            
            System.out.println("Sort flats by area - " + dwelling.getSortMassiveAreaJunction());
    
//         -------------- Вывод отсортированного массива площадей офисов ---------------------
//            System.out.print("Sort offices by area: ");
//            for (int i: dwellingBuilding.getSortMassiveAreaJunction()){
//                System.out.print("[" + i + "] ");
//            }
            
//         ---------------------- Вывод объекта этажа по его номеру (+ обработчик) ------------------------------
//            try{
//                int numberFloor = 1;
//                System.out.println(dwellingBuilding.getJunctionFloor(numberFloor));
//            } catch (FloorIndexOutOfBoundsException ex){
//                System.out.print(ex.getMessage()+ ": ");
//                System.out.println(ex.getNumberFloor());
//            }
       
            //         ---------------------- Изменение объекта этажа по его номеру и переданному этажу (+ обработчик) ------------------------------        
//            try{
//                int numberFloor = 1;
//                DwellingFloor dwellingFloor = null;
//                dwellingBuilding.setJunctionFloor(numberFloor, dwellingFloor);
//            } catch (FloorIndexOutOfBoundsException ex){
//                System.out.print(ex.getMessage()+ ": ");
//                System.out.println(ex.getNumberFloor());
//            }


//         ---------------------- Вывод объекта квартиры по его номеру (+ обработчик) ------------------------------        
//            try{
//                int numberFlatInBuild = 0;
//                System.out.println(dwellingBuilding.getJunctionInBuild(numberFlatInBuild));
//            }catch(SpaceIndexOutOfBoundsException ex){
//                System.out.print(ex.getMessage()+ ": ");
//                System.out.println(ex.getNumberFlat());
//            }


//         ---------------------- Вывод объекта офииса по его номеру (+ обработчик) ------------------------------        
//            try{
//                int numberFlatInBuild = 0;
//                Flat office = new Flat();
//                dwellingBuilding.setJunctionInBuild(numberFlatInBuild, office);
//            }catch(SpaceIndexOutOfBoundsException ex){
//                System.out.print(ex.getMessage()+ ": ");
//                System.out.println(ex.getNumberFlat());
//            }  
               
        try {FileOutputStream out = new FileOutputStream("out");
            outputBuilding(dwelling, out);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Creator.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            FileInputStream fileInputStream = new FileInputStream("out");
            inputBuilding(fileInputStream);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Creator.class.getName()).log(Level.SEVERE, null, ex);
        }
               
//----------- Сериализация ------------------
//        try {FileOutputStream out = new FileOutputStream("out");
//            serializeBuilding(dwelling, out);
//        } catch (FileNotFoundException ex) {
//            Logger.getLogger(Creator.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        
//        try {
//            FileInputStream fileInputStream = new FileInputStream("out");
//            deserializeBuilding(fileInputStream);
//        } catch (FileNotFoundException ex) {
//            Logger.getLogger(Creator.class.getName()).log(Level.SEVERE, null, ex);
//        }
        
    }
    
    static ArrayList getMassivJunction(){ // Создание рандомного массива квартир на этаже
        ArrayList nList = new ArrayList();
        int AmountFlat = 1 + (int) (Math.random() * 10);
        
        for (int i = 0; i <= AmountFlat; i++){
            nList.add(new Flat (30 + (int) (Math.random() * 100), (1 + (int) (Math.random() * 5))));
        }
        
        return nList;
    }
}
class SpaceIndexOutOfBoundsException extends IndexOutOfBoundsException{
    private int numberFlat;
    
    public int getNumberFlat(){
        return numberFlat;
    }
    
    public SpaceIndexOutOfBoundsException(String message, int number){   
        super(message);
        numberFlat = number;
    }
}

class FloorIndexOutOfBoundsException extends IndexOutOfBoundsException{
    private int numberFloor;
    
    public int getNumberFloor(){
        return numberFloor;
    }
    
    public FloorIndexOutOfBoundsException(String message, int number){   
        super(message);
        numberFloor = number;
    }
}

class InvalidSpaceAreaException extends IllegalArgumentException{
    private int numberFlat;
    
    public int getSpaceOffice(){
        return numberFlat;
    }
    
    public InvalidSpaceAreaException(String message, int number){   
        super(message);
        numberFlat = number;
    }
}

class InvalidRoomsCountException extends IllegalArgumentException{
    private int AmountRooms;
    
    public int getAmountRooms(){
        return AmountRooms;
    }
    
    public InvalidRoomsCountException(String message, int number){   
        super(message);
        AmountRooms = number;
    }
}