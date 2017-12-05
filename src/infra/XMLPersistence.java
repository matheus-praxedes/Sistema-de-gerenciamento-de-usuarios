package infra;

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
import java.beans.XMLEncoder;
import java.beans.XMLDecoder;

public class XMLPersistence <T> implements Persistence <T>{

    private String fileName;
    
    public XMLPersistence(String file){
        
        fileName = file;
    }
    @Override
    public void save(Map<String,T> objects) throws InfraException{
        
        File file = new File(fileName);
        try{
            ObjectOutputStream out = new ObjectOutputStream( new FileOutputStream(file));
            XMLEncoder encoder = new XMLEncoder(out);
            encoder.writeObject(objects);
            encoder.close();
            out.close();
        
        }catch(FileNotFoundException e){
            throw new InfraException("File not found");
        }catch(IOException e){
            throw new InfraException("IO problem");
        } 
    }
    
    @Override
    public Map<String,T> load() throws InfraException{
    
        Map<String,T> objects = new TreeMap<>();
        File file = new File(fileName);
        InputStream in = null;
        
        if(!file.exists()){   
            save(objects);
        }
        try{
            in = new FileInputStream(file);
            XMLDecoder decoder = new XMLDecoder(in);
            objects = (Map<String, T>) decoder.readObject();
            decoder.close();
            in.close();
        }catch(FileNotFoundException e){
            throw new InfraException("File not found");
        }catch(IOException e){
            throw new InfraException("IO problem");
        }
        
        return objects;
    }
}

