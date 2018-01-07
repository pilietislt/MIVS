package mivs.services;


import java.io.*;

public class IOService {


    public static void writeObjectToFile(Object obj, String filename){
        ObjectOutputStream outputStream = null;
        try {
            outputStream = new ObjectOutputStream(new FileOutputStream(filename));
            outputStream.writeObject(obj);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public static Object readObjectFromFile(String fileName) throws FileNotFoundException {
        try (
                ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(fileName))
        ) {
            return  inputStream.readObject();
        }  catch (FileNotFoundException e) {
            throw new FileNotFoundException();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }

    }

}
