import org.apache.commons.io.IOUtils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

public class Main {
    public static PrintWriter out;

    public String readRawDataToString() throws Exception{
        ClassLoader classLoader = getClass().getClassLoader();
        String result = IOUtils.toString(classLoader.getResourceAsStream("RawData.txt"));
        return result;
    }

    public static void main(String[] args) throws Exception{
        String output = (new Main()).readRawDataToString();
        ShoppingListHandler listHandler = new ShoppingListHandler();
        //System.out.println(listHandler.createShoppingList(output));
        printToFile(listHandler.createShoppingList(output));

    }

    public static void printToFile(String text) {
        try{
            out = new PrintWriter("ShoppingList.txt");
            out.println(text);
        }
        catch (FileNotFoundException e){
            // do nothing
        }
        finally {
            out.close();
        }
    }
}
