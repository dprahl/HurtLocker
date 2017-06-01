import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/**
 * Created by danielprahl on 5/31/17.
 */
public class ShoppingListHandler {
    private ArrayList<InventoryItem> items;
    private ShoppingListEntry Milk;
    private ShoppingListEntry Bread;
    private ShoppingListEntry Cookies;
    private ShoppingListEntry Apples;
    private int errorCount;


    public ShoppingListHandler(){
        this.items = new ArrayList<InventoryItem>(30);
        Milk = new ShoppingListEntry("   Milk");
        Bread = new ShoppingListEntry("  Bread");
        Cookies = new ShoppingListEntry("Cookies");
        Apples = new ShoppingListEntry(" Apples");
        errorCount = 0;
    }

    private void populateItems(String rawInput){
        String[] separated = JerkSONParser.separateInput(rawInput);
        String name, price, type, expiration;
        for (String line : separated) {
            try {
                name = JerkSONParser.getValue(line, JerkSONParser.NAME);
                price = JerkSONParser.getValue(line, JerkSONParser.PRICE);
                type = JerkSONParser.getValue(line, JerkSONParser.TYPE);
                expiration = JerkSONParser.getValue(line, JerkSONParser.EXPIRATION);
                InventoryItem item = new InventoryItem(name, price, type, expiration);
                items.add(item);
            }
            catch (PatternSyntaxException e){
                errorCount++;
            }
        }// end of for loop
    }

    private void populateEntries(){
        ShoppingListEntry entry;
        String name;
        String price;
        for (InventoryItem item : items) {
            name = item.getName();
            price = item.getPrice();
            entry = getEntry(name);
            entry.addOneToCount();
            entry.addPrice(price);
        }
    }

    public ShoppingListEntry getEntry(String name){
        Pattern milkPattern = Pattern.compile("\\s*M.*");
        Pattern breadPattern = Pattern.compile("\\s*B.*");
        Pattern cookiesPattern = Pattern.compile("\\s*C.*");
        Pattern applesPattern = Pattern.compile("\\s*a.*");
        Matcher milkMatcher = milkPattern.matcher(name);
        Matcher breadMatcher = breadPattern.matcher(name);
        Matcher cookiesMatcher = cookiesPattern.matcher(name);
        Matcher applesMatcher = applesPattern.matcher(name);
        if(milkMatcher.find()) {
            return Milk;
        }else if(breadMatcher.find()){
            return Bread;
        }else if(cookiesMatcher.find()){
            return Cookies;
        }else if(applesMatcher.find()){
            return Apples;
        }else {
            return null;
        }
    }

    private String entryToString(ShoppingListEntry entry){
        String entryString = "name: "+ entry.getName() +"\t\tseen: "+ entry.getItemCount() +" times\n";
        entryString += "=============\t\t=============\n";
        entryString += "Price:   "+ entry.getFirstPrice() +"\t\tseen: "+ entry.getFirstPriceCount() +" times\n";
        entryString += "-------------\t\t-------------\n";
        if(entry.getSecondPriceCount() > 0){
            entryString += "Price:   " + entry.getSecondPrice() + "\t\tseen: " + entry.getSecondPriceCount() + " times\n";
        }
        return entryString;
    }

    private String allEntriesToString(){
        String milk = entryToString(Milk) + "\n";
        String bread = entryToString(Bread) + "\n";
        String cookies = entryToString(Cookies) + "\n";
        String apples = entryToString(Apples) + "\n";
        String errors = "Errors   \t\t\tseen: "+ errorCount +" times\n";
        return milk + bread + cookies + apples + errors;
    }

    public String createShoppingList(String rawInput){
        populateItems(rawInput);
        populateEntries();
        return allEntriesToString();
    }
}
