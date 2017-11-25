package infra;

import business.model.User;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.TreeMap;
import java.util.Map;
import util.InfraException;

public class FilePersistence <T> implements Persistence <T>{

    private String fileName;
    
    public FilePersistence(String file){
        
        fileName = file;
    }
    public void save(Map<String,T> objects) throws InfraException{
        
        File file = new File(fileName);
        try{
            ObjectOutputStream out = new  ObjectOutputStream( new FileOutputStream(file));
            out.writeObject(objects);
            out.close();
        
        }catch(FileNotFoundException e){
            throw new InfraException("File not found");
        }catch(IOException e){
            throw new InfraException("IO problem");
        } 
    }
    public Map<String,T> load() throws InfraException{
    
        Map<String,T> objects = new TreeMap<String,T>();
        File file = new File(fileName);
        ObjectInputStream input = null;
        InputStream in = null;
        
        if(!file.exists()){   
            save(objects);
        }
        try{
            in = new FileInputStream(file);
            input = new ObjectInputStream(in);
            objects = (Map<String,T>) input.readObject();
            in.close();
        }catch(FileNotFoundException e){
            throw new InfraException("File not found");
        }catch(IOException e){
            throw new InfraException("IO problem");
        }catch (ClassNotFoundException e){
            throw new InfraException("Class not found");
        }
        return objects;
    }
}

