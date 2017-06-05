import org.apache.commons.io.IOUtils;

import java.io.FileNotFoundException;
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
        printToFile(listHandler.createShoppingList(output));

    }

    private static void printToFile(String text) {
        try{
            out = new PrintWriter("ShoppingList.txt");
            out.println(text);
        }
        catch (FileNotFoundException e){
            System.out.println("writing to file failed, this should never happen");
        }
        finally {
            out.close();
        }
    }
}
