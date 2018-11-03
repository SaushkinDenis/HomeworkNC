package buildings.offices;

import static homework4.Buildings.deserializeBuilding;
import static homework4.Buildings.inputBuilding;
import static homework4.Buildings.outputBuilding;
import static homework4.Buildings.readBulding;
import static homework4.Buildings.serializeBuilding;
import static homework4.Buildings.writeBuidingFormat;
import static homework4.Buildings.writeBuilding;

import interfaces.Building;
import interfaces.Floor;
import interfaces.Space;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Creator {

    public static void main(String... args){
    }
    
    public void create() throws InvalidSpaceAreaException, InvalidRoomsCountException{
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
            dwellingFloor = new OfficeFloor(flats); // Этаж
            dwellingFloors.add(dwellingFloor); // Массив этажей
            dwelling = new OfficeBuild(dwellingFloors); // Подъезд
            

////              --------  Добавление квартиры на каждый этаж (+ обработчик) ------------
//        try{
//            int numberNewFlat = 0;
//            dwellingFloor.addJunction(numberNewFlat, new Office());
//        } catch (SpaceIndexOutOfBoundsException ex){
//            System.out.println(ex.getMessage());
//            System.out.println(ex.getNumberFlat());
//        } 
//
//           --------  Удаление квартиры с каждого этажа (+ обработчик) ------------
//            try{
//                int numderDelOffice = 0;
//                dwellingFloor.delJunction(numderDelOffice);
//            } catch (SpaceIndexOutOfBoundsException ex){
//                System.out.println(ex.getMessage());
//                System.out.println(ex.getNumberFlat());
//            }


// ------------------
            System.out.println("\nFloor.Info:");
            System.out.println("Amount of the offices on floor - " + dwellingFloor.getAmountJunctionsOnFloor() + " qty.");
            System.out.println("Total area of the offices on floor - " + dwellingFloor.getTotalAreaJunctionOnFloor() + " sq.m.");
            System.out.println("Total rooms of the offices on floor - " + dwellingFloor.getAmountRoomsOnFloor() + " qty.");
            System.out.println("Best space of the offices on floor - " + dwellingFloor.getBestSpace().getArea() + " qty.");

//            dwellingFloor.addJunction(dwellingFloor.getAmountJunctionOnFloor(), new Office());
//            System.out.println(" New amount of the flats on floor - " + dwellingFloor.getAmountJunctionOnFloor() + " qty.");
//
//            dwellingFloor.delJunction(dwellingFloor.getAmountJunctionOnFloor());
//            System.out.println(" New amount of the flats on floor - " + dwellingFloor.getAmountJunctionOnFloor() + " qty.");            
//            
            System.out.println();
            for(Space f : building.get(i)){
                System.out.println("Office #" + j + " : Area - " + f.getArea() + " sq.m., Amount room - " + f.getAmountRoom() + " qty.");
                j++; 
            }
            System.out.println("----------------------------------------------");    
        }

//              --------  Добавление офиса на определенный этаж (+ обработчик) ------------
//        try{
//            int numberNewFlatInBuild = 10;
//            dwelling.addJunctionInBuild(numberNewFlatInBuild, new Office());    
//        } catch (SpaceIndexOutOfBoundsException ex){
//            System.out.print(ex.getMessage()+ ": ");
//            System.out.println(ex.getNumberFlat());
//        } 
//
//              --------  Обнуление офиса на определенном этаже (+ обработчик) ------------    
//        try{
//            int numberNewFlatInBuild = 10;
//            dwelling.delJunctionInBuild(numberNewFlatInBuild);    
//        } catch (SpaceIndexOutOfBoundsException ex){
//            System.out.print(ex.getMessage()+ ": ");
//            System.out.println(ex.getNumberFlat());
//        }
        
//          ------------------- Вывод информации по подъезду --------------------------    

            System.out.println("\nDwelling.Info");
            System.out.println("Amount of the floors - " + dwelling.getTotalAmountFloor() + " qty.");
            System.out.println("Amount of the offices - " + dwelling.getTotalAmountJunction() + " qty.");
            System.out.println("Area of the offices in building - " + dwelling.getTotalAreaJunction() + " sq.m.");
            System.out.println("Amount of the rooms in building - " + dwelling.getTotalAmountRooms() + " qty.");
            System.out.println("Best space of the offices in building - " + dwelling.getBestSpace().getArea() + " sq.m.");
            
//            System.out.println("Massive of the floors - " + dwelling.getMassiveFloors() + " qty.");

//            dwelling.addFlatInBuild(dwelling.getTotalAmountFlat(), new Flat());
//            System.out.println("New amount of the offices - " + dwelling.getTotalAmountFlat() + " qty.");
//            Flat f = building.get(dwelling.getTotalAmountFloor()).get(dwellingFloor.getAmountFlatOnFloor()-2);
//            System.out.println("New office #" + j + " : Area - " + f.getArea() + " sq.m., Amount room - " + f.getAmountRoom() + " qty.");
            
            System.out.println("Sort offices by area - " + dwelling.getSortMassiveAreaJunction());
    
//         -------------- Вывод отсортированного массива площадей офисов ---------------------
//            System.out.print("Sort offices by area: ");
//            for (Object i: dwelling.getSortMassiveAreaJunction()){
//                System.out.print("[" + i + "] ");
//            }
            
//         ---------------------- Вывод объекта этажа по его номеру (+ обработчик) ------------------------------
//            try{
//                int numberFloor = 1;
//                System.out.println(dwelling.getJunctionFloor(numberFloor));
//            } catch (FloorIndexOutOfBoundsException ex){
//                System.out.print(ex.getMessage()+ ": ");
//                System.out.println(ex.getNumberFloor());
//            }


//         ---------------------- Изменение объекта этажа по его номеру и переданному этажу (+ обработчик) ------------------------------        
//            try{
//                int numberFloor = 1;
//                Floor newDwellingFloor = null;
//                dwelling.setJunctionFloor(numberFloor, newDwellingFloor);
//            } catch (FloorIndexOutOfBoundsException ex){
//                System.out.print(ex.getMessage()+ ": ");
//                System.out.println(ex.getNumberFloor());
//            }


//         ---------------------- Вывод объекта квартиры по его номеру (+ обработчик) ------------------------------        
//            try{
//                int numberFlatInBuild = 0;
//                System.out.println(dwelling.getJunctionInBuild(numberFlatInBuild));
//            }catch(SpaceIndexOutOfBoundsException ex){
//                System.out.print(ex.getMessage()+ ": ");
//                System.out.println(ex.getNumberFlat());
//            }


//         ---------------------- Вывод объекта офиса по его номеру (+ обработчик) ------------------------------        
//            try{
//                int numberFlatInBuild = 0;
//                Space office = new Office();
//                dwelling.setJunctionInBuild(numberFlatInBuild, office);
//            }catch(SpaceIndexOutOfBoundsException ex){
//                System.out.print(ex.getMessage()+ ": ");
//                System.out.println(ex.getNumberFlat());
//            } 


// -------------------- writeBuilding readBulding --------------------------
//        try {
//            BufferedWriter in = new BufferedWriter(new FileWriter("in"));
//            writeBuilding(dwelling, in);
//        } catch (FileNotFoundException ex) {
//            System.out.println("Error occurred with stream!");
//        } catch (IOException ex) {
//            System.out.println(ex.getMessage());
//        }
//        
//        try {
//            BufferedReader out = new BufferedReader(new FileReader("out"));
//            Building newBuilding = readBulding(out);
//        } catch (FileNotFoundException ex) {
//            System.out.println(ex.getMessage());
//        } catch (IOException ex) {
//            System.out.println(ex.getMessage());
//        }
//        
//--------------- OutputStream InputStream --------------
//        try (FileOutputStream out = new FileOutputStream("out")){
//            outputBuilding(dwelling, out);
//        } catch (FileNotFoundException ex) {
//            System.out.println(ex.getMessage());
//        } catch (IOException ex) {
//            System.out.println(ex.getMessage());
//        }
//        
//        try(FileInputStream fileInputStream = new FileInputStream("out")) {
//            inputBuilding(fileInputStream);
//        } catch (FileNotFoundException ex) {
//            System.out.println(ex.getMessage());
//        } catch (IOException ex) {
//            System.out.println(ex.getMessage());
//        }
//               
//----------- Сериализация ------------------
//        try (FileOutputStream out = new FileOutputStream("out")){
//            serializeBuilding(dwelling, out);
//        } catch (FileNotFoundException ex) {
//            System.out.println(ex.getMessage());
//        } catch (IOException ex) {
//            System.out.println(ex.getMessage());
//        }
//        
//        try (FileInputStream fileInputStream = new FileInputStream("out")){
//            deserializeBuilding(fileInputStream);
//        } catch (FileNotFoundException ex) {
//            System.out.println(ex.getMessage());
//        } catch (IOException ex) {
//            System.out.println(ex.getMessage());
//        }
//
// ---------------- writeBuidingFormat (Writer out)--------------
//        try (BufferedWriter out = new BufferedWriter(new FileWriter("out"))){
//            writeBuidingFormat(dwelling, out);
//        }
//        catch (FileNotFoundException ex) {
//            System.out.println(ex.getMessage());
//        }
//        catch (IOException ex) {
//            System.out.println(ex.getMessage());
//        }
    }
    
    static ArrayList getMassivJunction(){ // Создание рандомного массива квартир на этаже
        ArrayList nList = new ArrayList();
        int AmountFlat = 1 + (int) (Math.random() * 10);
        
        for (int i = 0; i <= AmountFlat; i++){
            nList.add(new Office (250 + (int) (Math.random() * 2500), (1 + (int) (Math.random() * 10))));
        }
        
        return nList;
    }
}
class SpaceIndexOutOfBoundsException extends IndexOutOfBoundsException{
    private int numberOffice;
    
    public int getNumberFlat(){
        return numberOffice;
    }
    
    public SpaceIndexOutOfBoundsException(String message, int number){   
        super(message);
        numberOffice = number;
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
    private int numberOffice;
    
    public int getSpaceOffice(){
        return numberOffice;
    }
    
    public InvalidSpaceAreaException(String message, int number){   
        super(message);
        numberOffice = number;
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