package buildingsFlats;

import interfaces.Building;
import interfaces.Floor;
import interfaces.Space;

import java.io.Serializable;
import java.util.ArrayList;

public class Dwelling implements Building, Serializable, Cloneable{

    ArrayList<Floor> dwellingFloors;
    
    public Dwelling(int amountFloors, ArrayList amountFlatsOnFloors){
        this.dwellingFloors = new ArrayList(amountFloors);
    }
    
    public Dwelling(ArrayList<Floor> dwellingFloors){
        this.dwellingFloors = dwellingFloors;
    } 
    
    @Override
    public int getTotalAmountFloor(){
        return this.dwellingFloors.size();
    }
    
    @Override
    public int getTotalAmountJunction(){
        int totalAmountFlats = 0; 
        for (int i = 0; i <= getTotalAmountFloor()-1; i++){
            totalAmountFlats += this.dwellingFloors.get(i).getAmountJunctionsOnFloor();
        }
        return totalAmountFlats;  
    }
    
    @Override
    public int getTotalAreaJunction(){
        int totalAreaFlats = 0; 
        for (int i = 0; i <= getTotalAmountFloor()-1; i++){
            totalAreaFlats += this.dwellingFloors.get(i).getTotalAreaJunctionOnFloor();
        }
        return totalAreaFlats;  
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
    public Space getJunctionInBuild(int findNumberFlatInBuild) throws SpaceIndexOutOfBoundsException {
        int numberFlatInBuild = 0;
        Space findFlat = new Flat();
        boolean flag = true;
        for(Floor df: this.dwellingFloors){            
            for(Space f: df.junction){
                numberFlatInBuild++;
                if (numberFlatInBuild == findNumberFlatInBuild){
                    findFlat = f;
                    flag = false;
                    break;
                }
            }
        }
        if(flag) throw new SpaceIndexOutOfBoundsException("Подъезд.Получение: Квартира с таким номером не существует", findNumberFlatInBuild);
        return findFlat;
    }
    
    @Override
    public void setJunctionInBuild(int numberJunctionInBuild, Space flat) throws SpaceIndexOutOfBoundsException{
        int findNumberFlatInBuild = 0, numberFloor = 0, i = 0;
        Floor findDf = null;
        boolean flag = true;
        for(Floor df: this.dwellingFloors){   
            i++;
            for(Space f: df.junction){
                numberJunctionInBuild++;
                if (findNumberFlatInBuild == numberJunctionInBuild){
                    findDf = df;
                    numberFloor = i;
                    flag = false;
                    break;
                }
            }        
        }
        
        if(flag) throw new SpaceIndexOutOfBoundsException("Подъезд.Изменение: Квартира с таким номером не существует", findNumberFlatInBuild);
        findDf.addJunction(numberJunctionInBuild, flat);
        this.dwellingFloors.set(numberFloor, findDf);
    }
    
    @Override
    public void addJunctionInBuild(int nextNumberJunctionInBuild, Space flat) throws SpaceIndexOutOfBoundsException{
        int numberFlatInBuild = 0, numberFloor = 0, i = 0, newNumberFlat = 0;
        Floor findDf = null;
        boolean flag = true;
        for(Floor df: this.dwellingFloors){   
            i++;
            newNumberFlat = 0;
            for(Space f: df.junction){
                numberFlatInBuild++;
                newNumberFlat++;
                if (numberFlatInBuild <= nextNumberJunctionInBuild){
                    findDf = df;
                    numberFloor = i;
                    flag = false;
                }
            }        
        }
        if(flag) throw new SpaceIndexOutOfBoundsException("Подъезд.Создание: Квартира с данным номером отсутствует ", nextNumberJunctionInBuild);
        findDf.addJunction(newNumberFlat-1, flat);
        this.dwellingFloors.set(numberFloor-1, findDf);
    }
    
    @Override
    public void delJunctionInBuild(int nextNumberJunctionInBuild) throws SpaceIndexOutOfBoundsException{
        int numberFlatInBuild = 0, numberFloor = 0, i = 0;
        Floor findDf = null;
         boolean flag = false;
        for(Floor df: this.dwellingFloors){   
            i++;
            for(Space f: df.junction){
                numberFlatInBuild++;
                if (numberFlatInBuild <= nextNumberJunctionInBuild){
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
        Space findFlat = new Flat();

        for(int i = 0; i <= this.dwellingFloors.size() - 1; i++){
            for(int j = 0; j <= this.dwellingFloors.get(i).getAmountJunctionsOnFloor() - 1; j++){
                int area = this.dwellingFloors.get(i).getJunction(j).getArea();
                if (area >= bestArea){
                bestArea = area;
                findFlat = this.dwellingFloors.get(i).getJunction(j);
            }
        }
               
        }
        return findFlat;
    }

    @Override
    public ArrayList getSortMassiveAreaJunction(){
        ArrayList<Integer> sortAreaFlats = new ArrayList();
        boolean flag = true;
        int change;
        for(int i = 0; i <= this.dwellingFloors.size() - 1; i++){
            for(int j = 0; j <= this.dwellingFloors.get(i).getAmountJunctionsOnFloor() - 1; j++){
                sortAreaFlats.add(this.dwellingFloors.get(i).getJunction(j).getArea());
            }   
        }   
        int i = 0;
        while(flag){
            flag = false;
            for(i  = 0; i < sortAreaFlats.size() - 1; i++){
                if(sortAreaFlats.get(i) < sortAreaFlats.get(i + 1)){
                    change = sortAreaFlats.get(i);
                    sortAreaFlats.set(i, sortAreaFlats.get(i + 1));
                    sortAreaFlats.set(i+1, change);
                    flag = true;
                }

            }
        }
        return sortAreaFlats;
    }
    
    @Override
    public String toString(){
        StringBuffer junction = new StringBuffer();
        junction.append("Dwelling");
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
        return ((Dwelling)object).dwellingFloors == this.dwellingFloors;
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
    public Object clone(){
        return new Dwelling(getMassiveFloors());
    }
}   