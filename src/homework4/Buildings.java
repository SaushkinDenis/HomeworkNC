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
import java.io.Reader;
import java.io.Writer;
import java.io.StreamTokenizer;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Buildings {
    
    public static void outputBuilding (Building building, OutputStream out){
        DataOutputStream dout = new DataOutputStream(out);
        
        try {
            dout.write(building.getTotalAmountFloor());
            
            for(Floor f : building.getMassiveFloors()){
                dout.write(f.getAmountJunctionsOnFloor());
            
                for(Space s: f.getMassiveJunction()){
                   dout.write(s.getAmountRoom());
                   dout.write(s.getArea());
                }
            }
            dout.close();
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
                        building = new ArrayList((int) dis.read());
                        nextJun++;
                        break;
                        
                    case 1:
                        floors = new ArrayList((int) dis.read());
                        nextJun++;
                        amointJun = (int) dis.read();
                        break;
                        
                    case 2:
                        if (amointJun != 0){
                            junction.setAmountRoom((int) dis.read());
                            amointJun--;
                            nextJun++;  
                        }
                        else {
                            nextJun = 1;
                            building.add(floors);
                        }
                        break;
                        
                    case 3:
                        junction.setArea((int) dis.read());
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
    
    public static void writeBuilding (Building building, Writer in){
        
        try {
            in.write(building.getTotalAmountFloor());
            
            for(Floor f : building.getMassiveFloors()){
                in.write(f.getAmountJunctionsOnFloor());
            
                for(Space s: f.getMassiveJunction()){
                   in.write(s.getAmountRoom());
                   in.write(s.getArea());
                }
            }
            in.close();
        } 
        catch (IOException ex) {
            System.out.println(ex.getMessage());
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
    in.close();
    return  (Building) building;
    }

    public static void serializeBuilding (Building building, OutputStream out){
        
        try {
            ObjectOutputStream objectOutputStream;
            objectOutputStream = new ObjectOutputStream(out);
            objectOutputStream.writeObject(building);
            //objectOutputStream.close();
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
            objectInputStream.close();
            //in.close();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        } catch (ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
        }
       return building;
    }
    
    public static void writeBuidingFormat(Building building, OutputStream out){
        DataOutputStream dout = new DataOutputStream(out);
        Formatter formatter = new Formatter();
        
        try {
            int numberJunc = 0;
            formatter.format("Количество этажей: %d", building.getTotalAmountFloor());
            dout.write(building.getTotalAmountFloor());
            for(int j = 1; j <= building.getTotalAmountFloor(); j++){
                formatter.format("Количество помещений на этаже: %d", building.getFloor(j).getAmountJunctionsOnFloor());
                dout.write(building.getFloor(j).getAmountJunctionsOnFloor());
                for(int i = 0; i <= building.getMassiveFloors().get(j).getAmountJunctionsOnFloor(); i++){
                    formatter.format("Помещение " + numberJunc + ", количество комнат: %d", building.getMassiveFloors().get(j).getJunction(i).getAmountRoom());
                    dout.write(building.getMassiveFloors().get(j).getJunction(i).getAmountRoom());
                    formatter.format("Помещение №" + numberJunc + ", площадь: %d",building.getMassiveFloors().get(j).getJunction(i).getArea());
                    dout.write(building.getMassiveFloors().get(j).getJunction(i).getArea());
                    numberJunc++;
                }
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());    
        }
    }


    
    public static Building readBuldingFormat(Scanner scanner){
        Formatter formatter = new Formatter();
        ArrayList<ArrayList<Space>> building = new ArrayList();
        
//        String s = scanner.toString();
//        s.split(":");
//        scanner.hasNextInt();
//        int k = 0, j = 0;
//        
//        try {
//            for(int i = 0; i <= dis.available(); i++){
//                switch (k){     
//                    
//                        formatter.format("Количество этажей: %d", in.read());
//                        k++;
//                        break;
//
//                    case 1: 
//                        formatter.format("Количество помещений на этаже: %d", in.read());
//                        k++;
//                        j = in.read();
//                        break;
//
//                    case 2:
//                        if (j != 0){
//                            formatter.format("Помещение " + i + ", количество комнат: %d", in.read());
//                            j--;
//                            k++;
//                        } 
//                        else {
//                            k = 1;
//                        }
//                        break;
//
//                    case 3: 
//                        formatter.format("Помещение №" + i + ", площадь: %d", in.read());
//                        k = 2;
//                        break;
//                }  
//        }
//            
//            
//            
//            
//            
//            
//            
//// ---------------        (НЕОБХОДИМ ЦИКЛ ЧТЕНИЯ)
//            int Floors = in.readInt();
//            int AmountJunctionOnFloor = in.readInt();
//            int AmountRoom = in.readInt();
//            int getArea = in.readInt();
//           
//            System.out.format("Floors: %s  AmountJunctionOnFloor: %d  AmountRoom: %f  getArea: %b", 
//                    Floors, AmountJunctionOnFloor, AmountRoom, getArea);
//        }
//        catch(IOException ex){
//              
//            System.out.println(ex.getMessage());
//        }  
    return (Building) building;
    }      
}