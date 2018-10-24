package buildingsOffices;

import interfaces.Building;
import interfaces.Floor;
import interfaces.Space;

import java.io.Serializable;
import java.util.ArrayList;

public class OfficeBuild implements Building, Serializable, Cloneable{

    ArrayList<Floor> dwellingFloors; // Массив этажей [Массив квартир] [Массив квартир]
    
    public OfficeBuild(int amountFloors, ArrayList amountOfficesOnFloors){
        this.dwellingFloors = new ArrayList(amountFloors);
    }
    
    public OfficeBuild(ArrayList<Floor> dwellingFloors){
        this.dwellingFloors = dwellingFloors;
    }
   
    @Override
    public int getTotalAmountFloor(){
        return this.dwellingFloors.size();
    }
    
    @Override
    public int getTotalAmountJunction(){
        int totalAmountOffices = 0; 
        for (int i = 0; i <= getTotalAmountFloor()-1; i++){
            totalAmountOffices += this.dwellingFloors.get(i).getAmountJunctionsOnFloor();
        }
        return totalAmountOffices;  
    }
    
    @Override
    public int getTotalAreaJunction(){
        int totalAreaOffices = 0; 
        for (int i = 0; i <= getTotalAmountFloor()-1; i++){
            totalAreaOffices += this.dwellingFloors.get(i).getTotalAreaJunctionOnFloor();
        }
        return totalAreaOffices;  
    }
    
    @Override
    public int getTotalAmountRooms(){
        int totalAmountRooms = 0; 
        for (int i = 0; i <= getTotalAmountFloor()-1; i++){
            totalAmountRooms += this.dwellingFloors.get(i).getAmountRoomsOnFloor();
        }
        return totalAmountRooms;  
    }

    @Override
    public ArrayList<Floor> getMassiveFloors(){
        return this.dwellingFloors;
    }
    
    @Override
    public Floor getFloor (int numberFloor) throws  FloorIndexOutOfBoundsException{
        if(numberFloor > this.dwellingFloors.size()) throw new FloorIndexOutOfBoundsException("Подъезд.Вывод: Этажа с данным номером не существует", numberFloor);{
        return this.dwellingFloors.get(numberFloor);
        }
    }
    
    @Override
    public void setFloor (int numberFloor, Floor junctionFloor) throws FloorIndexOutOfBoundsException{
        if(numberFloor > this.dwellingFloors.size()) throw new FloorIndexOutOfBoundsException("Подъезд.Изменение: Этажа с данным номером не существует", numberFloor);
        this.dwellingFloors.set(numberFloor, junctionFloor);
    }
    
    @Override
    public Space getJunctionInBuild(int findNumberJunctionInBuild) throws SpaceIndexOutOfBoundsException {
        int numberOfficeInBuild = 0;
        Space findFlat = new Office();
        boolean flag = true;
        for(Floor df: this.dwellingFloors){            
            for(Space f: df.junction){
                numberOfficeInBuild++;
                if (numberOfficeInBuild == findNumberJunctionInBuild){
                    findFlat = f;
                    flag = false;
                    break;
                }
            }
        }
        if(flag) throw new SpaceIndexOutOfBoundsException("Подъезд.Получение: Квартира с таким номером не существует", findNumberJunctionInBuild);
        return findFlat;
    }
    
    @Override
    public void setJunctionInBuild(int numberJunctionInBuild, Space flat) throws SpaceIndexOutOfBoundsException{
        int findNumberOfficeInBuild = 0, numberFloor = 0, i = 0;
        Floor findDf = null;
        boolean flag = true;
        for(Floor df: this.dwellingFloors){   
            i++;
            for(Space f: df.junction){
                numberJunctionInBuild++;
                if (findNumberOfficeInBuild == numberJunctionInBuild){
                    findDf = df;
                    numberFloor = i;
                    flag = false;
                    break;
                }
            }        
        }
        
        if(flag) throw new SpaceIndexOutOfBoundsException("Подъезд.Изменение: Квартира с таким номером не существует", findNumberOfficeInBuild);
        findDf.addJunction(numberJunctionInBuild, flat);
        this.dwellingFloors.set(numberFloor, findDf);
    }
    
    @Override
    public void addJunctionInBuild(int nextNumberJunctionInBuild, Space flat) throws SpaceIndexOutOfBoundsException{
        int numberFlatInBuild = 0, numberFloor = 0, i = 0, newNumberOffice = 0;
        Floor findDf = null;
        boolean flag = true;
        for(Floor df: this.dwellingFloors){   
            i++;
            newNumberOffice = 0;
            for(Space f: df.junction){
                numberFlatInBuild++;
                newNumberOffice++;
                if (numberFlatInBuild <= nextNumberJunctionInBuild){
                    findDf = df;
                    numberFloor = i;
                    flag = false;
                }
            }        
        }
        if(flag) throw new SpaceIndexOutOfBoundsException("Подъезд.Создание: Квартира с данным номером отсутствует ", nextNumberJunctionInBuild);
        findDf.addJunction(newNumberOffice - 1, flat);
        this.dwellingFloors.set(numberFloor - 1, findDf);
    }
    
    @Override
    public void delJunctionInBuild(int nextNumberJunctionInBuild) throws SpaceIndexOutOfBoundsException{
        int numberOfficeInBuild = 0, numberFloor = 0, i = 0;
        Floor findDf = null;
         boolean flag = false;
        for(Floor df: this.dwellingFloors){   
            i++;
            for(Space f: df.junction){
                numberOfficeInBuild++;
                if (numberOfficeInBuild <= nextNumberJunctionInBuild){
                    findDf = df;
                    numberFloor = i;
                    flag = true;
                }
            }        
        }
        if(!flag) throw new SpaceIndexOutOfBoundsException("Подъезд.Удаление: Квартира с данным номером отсутствует ", nextNumberJunctionInBuild);
        findDf.delJunction(nextNumberJunctionInBuild);
        this.dwellingFloors.set(numberFloor, findDf);
    }
       
    @Override
    public Space getBestSpace(){
        int bestArea = 0;
        Space findOffice = new Office();

        for(int i = 0; i <= this.dwellingFloors.size() - 1; i++){
            for(int j = 0; j <= this.dwellingFloors.get(i).getAmountJunctionsOnFloor() - 1; j++){
                int area = this.dwellingFloors.get(i).getJunction(j).getArea();
                if (area >= bestArea){
                bestArea = area;
                findOffice = this.dwellingFloors.get(i).getJunction(j);
            }
        }
               
        }
        return findOffice;
    }

    @Override
    public ArrayList getSortMassiveAreaJunction(){
        ArrayList<Integer> sortAreaOffices = new ArrayList();
        boolean flag = true;
        int change;
        for(int i = 0; i <= this.dwellingFloors.size() - 1; i++){
            for(int j = 0; j <= this.dwellingFloors.get(i).getAmountJunctionsOnFloor() - 1; j++){
                sortAreaOffices.add(this.dwellingFloors.get(i).getJunction(j).getArea());
            }   
        }   
        int i = 0;
        while(flag){
            flag = false;
            for(i  = 0; i < sortAreaOffices.size() - 1; i++){
                if(sortAreaOffices.get(i) < sortAreaOffices.get(i + 1)){
                    change = sortAreaOffices.get(i);
                    sortAreaOffices.set(i, sortAreaOffices.get(i + 1));
                    sortAreaOffices.set(i+1, change);
                    flag = true;
                }

            }
        }
        return sortAreaOffices;
    }

    @Override
    public String toString(){
        StringBuffer junction = new StringBuffer();
        junction.append("OfficeBuilding");
        junction.append(" [");
        junction.append(this.getTotalAmountFloor());
        junction.append(", ");
        
        for (int i = 1; i <= this.getTotalAmountFloor(); i++){
            junction.append(this.dwellingFloors.get(i).toString());
            if(!(i == this.getTotalAmountFloor())) junction.append(", ");
        }
        
        junction.append("]");
        return junction.toString();
    }
    
    @Override
    public boolean equals(Object object){
        if(object.getClass() == this.getClass() || object.getClass().getDeclaredFields()== this.getClass().getDeclaredFields()) return true;
        else return false;
    }
    
    @Override
    public int hashCode(){
        byte a = (byte) this.getTotalAmountFloor();
        byte b = 0;
        for(Floor f: this.getMassiveFloors()){
            b += (byte) f.hashCode();
        }
        return a^b;
    }
    
    @Override
    public Object clone() throws CloneNotSupportedException{
        return (OfficeBuild) super.clone();   
    }
}   