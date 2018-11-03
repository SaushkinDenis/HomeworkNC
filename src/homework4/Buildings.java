package homework4;
import interfaces.Building;
import interfaces.Floor;
import interfaces.Space;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.Writer;
import java.io.StreamTokenizer;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.Scanner;

public class Buildings {
    
    public static void outputBuilding (Building building, OutputStream out){
        DataOutputStream dout = new DataOutputStream(out);
        
        try {
            dout.writeInt(building.getTotalAmountFloor());
            
            for(Floor f : building.getMassiveFloors()){
                dout.write(f.getAmountJunctionsOnFloor());
            
                for(Space s: f.getMassiveJunction()){
                   dout.write(s.getAmountRoom());
                   dout.write(s.getArea());
                }
            }
        } 
        catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        
    }
    
    public static Building inputBuilding (InputStream in){
        DataInputStream dis = new DataInputStream(in);
        ArrayList<ArrayList<Space>> building = new ArrayList();
        ArrayList<Space> floors = new ArrayList();
        Space junction = null;
        int nextJun = 0, amointJun = 0;
        
        try {
            for(int i = 0; i <= dis.available(); i++){
                switch (nextJun){
                    case 0:
                        building = new ArrayList(dis.readInt());
                        nextJun++;
                        break;
                        
                    case 1:
                        floors = new ArrayList((int) dis.readInt());
                        nextJun++;
                        amointJun = (int) dis.readInt();
                        break;
                        
                    case 2:
                        if (amointJun != 0){
                            junction.setAmountRoom((int) dis.readInt());
                            amointJun--;
                            nextJun++;  
                        }
                        else {
                            nextJun = 1;
                            building.add(floors);
                        }
                        break;
                        
                    case 3:
                        junction.setArea((int) dis.readInt());
                        nextJun = 2;
                        floors.add(junction);
                        break;
                }
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    return  (Building) building;
    }
    
    public static void writeBuilding (Building building, Writer oin){
        PrintWriter in = new PrintWriter(oin);
        in.write(building.getTotalAmountFloor());
        for(Floor f : building.getMassiveFloors()){
            in.write(f.getAmountJunctionsOnFloor());
            
            for(Space s: f.getMassiveJunction()){
                in.write(s.getAmountRoom());
                in.write(s.getArea());
            }
        }
    }
    
    public static Building readBulding (Reader in) throws IOException{
        StreamTokenizer build = new StreamTokenizer(in);
        ArrayList<ArrayList<Space>> building = new ArrayList();
        ArrayList<Space> floors = new ArrayList();
        Space junction = null;
        int i = 0, j = 0;
        
        while  (build.nextToken() != StreamTokenizer.TT_EOF)   {
            if (build.ttype == StreamTokenizer.TT_NUMBER)
                switch (i){       

                    case 0:
                        building = new ArrayList((int) build.nval);
                        i++;
                        break;

                    case 1: 
                        floors = new ArrayList((int) build.nval);
                        i++;
                        j = (int) build.nval;
                        break;

                    case 2:
                        if (j != 0){
                            junction.setAmountRoom((int) build.nval);
                            j--;
                            i++;
                        } 
                        else {
                            i = 1;
                            building.add(floors);
                        }
                        break;

                    case 3: 
                        junction.setArea((int) build.nval);
                        i = 2;
                        floors.add(junction);
                        break;
                }  
        }
    return  (Building) building;
    }

    public static void serializeBuilding (Building building, OutputStream out){
        
        try {
            ObjectOutputStream objectOutputStream;
            objectOutputStream = new ObjectOutputStream(out);
            objectOutputStream.writeObject(building);

        } 
        catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public static Building deserializeBuilding (InputStream in){
        
        Building building = null;
        
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(in);
            building = (Building) objectInputStream.readObject();

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        } catch (ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
        }
       return building;
    }
    
    public static void writeBuidingFormat(Building building, Writer out){
        Formatter formatter = new Formatter(out);

        int numberJunc = 0;
        formatter.format("Количество этажей: %d.", building.getTotalAmountFloor());
        
        for(int j = 1; j <= building.getTotalAmountFloor(); j++){
            formatter.format("Количество помещений на этаже: %d.", building.getFloor(j).getAmountJunctionsOnFloor());
            
            for(int i = 0; i <= building.getMassiveFloors().get(j).getAmountJunctionsOnFloor(); i++){
                formatter.format("Помещение № %d, количество комнат: %d.", numberJunc, building.getMassiveFloors().get(j).getJunction(i).getAmountRoom());
                formatter.format("Помещение № %d, площадь: %d.", numberJunc, building.getMassiveFloors().get(j).getJunction(i).getArea());
                numberJunc++;
            }
        }
    }
    
    public static Building readBuldingFormat(Scanner scanner){
        ArrayList<Space> floors;
        Space junction = null;
        
        System.out.println("Количество этажей: ");
        ArrayList<ArrayList<Space>> building = new ArrayList(scanner.nextInt());

        for(int numberFloor = 1; numberFloor <= building.size(); numberFloor++){
            System.out.printf("Количество помещений на %d этаже: ", numberFloor);
            floors = new ArrayList(scanner.nextInt());
            
            for(int numberJunc = 0; numberJunc <= floors.size(); numberJunc++){
                System.out.printf("Помещение № %d, количество комнат: ", numberJunc);
                junction.setAmountRoom(scanner.nextInt());
                System.out.printf("Помещение № %d, площадь: ", numberJunc);
                junction.setArea(scanner.nextInt());
 
                floors.add(numberJunc, junction);
            }
            
            building.add(numberFloor, floors);
        }
        
        return (Building) building;
    }      
}