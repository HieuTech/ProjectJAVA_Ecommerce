package business.utils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class IOFile {
    public static final String USER_PATH = "src/business/data/user.txt";
    public static final String CATALOG_PATH = "src/business/data/catalog.txt";
    public static final String PRODUCT_PATH = "src/business/data/product.txt";
    public static final String ORDER_PATH = "src/business/data/order.txt";
    public static  final String CART_PATH = "src/business/data/cart.txt";

    public static <T> void writeToFile(String path, List<T> list) {
        File file = new File(path);
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        try {

            fos = new FileOutputStream(path,true);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(list);
            System.out.println("Write Successfully!");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {

            try {
                if (fos != null) {
                    fos.close();
                }
                if (oos!=null){
                    oos.close();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public static <T> T readPerData(String path){
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        T objects = null;
        try{

            fis = new FileInputStream(path);
            ois = new ObjectInputStream(fis);
            objects = (T) ois.readObject();

        }catch (EOFException e){

        }catch (IOException e){
            System.out.println("IO Exception");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        finally {
            try {
                if (fis != null) {
                    fis.close();
                }
                if (ois!=null){
                    ois.close();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return objects;
    }
    public static <T> List<T> readFromFile(String path) {
        List<T> list = new ArrayList<>();
        FileInputStream fis = null;
        ObjectInputStream ois = null;

        try {
            fis = new FileInputStream(path);
            ois = new ObjectInputStream(fis);
            list = (List<T>) ois.readObject();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }catch (EOFException e) {

        }catch(IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {

            try {
                if (fis != null) {
                    fis.close();
                }
                if (ois!=null){
                    ois.close();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return list;
    }
}