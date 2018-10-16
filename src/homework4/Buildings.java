package homework4;
import interfaces.Building;
import interfaces.Floor;
import interfaces.Space;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
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
            System.out.println("Error occurred with stream!");
        }
        
    }
    
    public static void inputBuilding (InputStream in){
        System.out.println(in);
    }
    
    public static void writeBuilding (Building building, Writer out){
        
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
            System.out.println("Error occurred with stream!");
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
            System.out.println("Error occurred with serializable stream");
        }
    }
    
    public static Building deserializeBuilding (InputStream in){
        
        Building building = null;
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(in);
            building = (Building) objectInputStream.readObject();
            objectInputStream.close();
        } catch (IOException ex) {
            Logger.getLogger(Buildings.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Buildings.class.getName()).log(Level.SEVERE, null, ex);
        }
       return building;
    }
    
    public static void writeBuidingFormat(Building building, Writer out){
    
    }
}