package homework4;
import interfaces.Building;
import interfaces.Floor;
import interfaces.Space;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.Writer;
import java.io.StreamTokenizer;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Buildings {
    
    public static void outputBuilding (Building building, OutputStream out){
        
        try {
            out.write(building.getTotalAmountFloor());
            
            for(Floor f : building.getMassiveFloors()){
                out.write(f.getAmountJunctionOnFloor());
            
                for(Space s: f.getMassiveJunction()){
                   out.write(s.getAmountRoom());
                   out.write(s.getArea());
                }
            }
            out.close();
        } 
        catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        
    }
    
    public static void inputBuilding (InputStream in){
        try {
            for(int i = 0; i <= in.available(); i++)
                System.out.print(in.read()+" ");
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public static void writeBuilding (Building building, Writer in){
        
        try {
            in.write(building.getTotalAmountFloor());
            
            for(Floor f : building.getMassiveFloors()){
                in.write(f.getAmountJunctionOnFloor());
            
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
            objectOutputStream.close();
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
            in.close();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        } catch (ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
        }
       return building;
    }
    
    public static void writeBuidingFormat(Building building, Writer in){

        try {
            PrintWriter printStream = new PrintWriter(in);
            printStream.println(building); 
            readBuidingFormat(printStream);
            in.close();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }   
    }   
    
    public static void readBuidingFormat(PrintWriter printStream){
        
        printStream.printf("%-10s%-10s%-10s%-10s%-10s%-10s%n", "Здание", "Подъезд", "Этаж", "Помещение", "Количество комнат", "Площадь");
        printStream.printf("%-10d%-10d%-10d%-10d%-10d%-10d%n",1,2,3,4,5,6);
        
        printStream.format(format, args);
        printStream.
        printStream.printf("Flat: %-10d% Area: -10d% Amount room: -10d%n",printStream.,2,3);
    }
     
}