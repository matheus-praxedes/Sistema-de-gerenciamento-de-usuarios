package infra;

import bussiness.model.User;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.InfraException;

public class UserPersistence{

    
    public void saveUsers(Map<String,User> users) throws InfraException{
        
        File file = new File("data.bin");
        try{
            ObjectOutputStream out = new  ObjectOutputStream( new FileOutputStream(file));
            out.writeObject(users);
            out.close();
        
        }catch(FileNotFoundException e){
            e.printStackTrace();
            throw new InfraException("File not found");
        }catch(IOException e){
            e.printStackTrace();
            throw new InfraException("IO problem");
        } 
    }
    public Map<String,User> loadUsers() throws InfraException{
    
        Map<String,User> users = new HashMap<String,User>();
        File file = new File("data.bin");
        ObjectInputStream input = null;
        InputStream in = null;
        
        if(!file.exists()){   
            saveUsers(users);
        }
        try{
            in = new FileInputStream(file);
            input = new ObjectInputStream(in);
            users = (Map<String,User>) input.readObject();
            in.close();
        }catch(FileNotFoundException e){
            e.printStackTrace();
            throw new InfraException("File not found");
        }catch(IOException e){
            e.printStackTrace();
            throw new InfraException("IO problem");
        } catch (ClassNotFoundException e) {
             e.printStackTrace();
            throw new InfraException("Class not found");
        }
        return users;
    }
}

