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
        Milk = new ShoppingListEntry("Milk");
        Bread = new ShoppingListEntry("Bread");
        Cookies = new ShoppingListEntry("Cookies");
        Apples = new ShoppingListEntry("Apples");
        errorCount = 0;
    }

    public void populateItems(String rawInput){
        String[] seperated = JerkSONParser.separateInput(rawInput);
        String name, price, type, expiration;
        for (String line : seperated) {
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

    public void populateEntries(){
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
        Pattern milkPattern = Pattern.compile("M.*");
        Pattern breadPattern = Pattern.compile("B.*");
        Pattern cookiesPattern = Pattern.compile("C.*");
        Pattern applesPattern = Pattern.compile("a.*");
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

    public String entryToString(ShoppingListEntry entry){
        return "name:  "+ entry.getName() +" seen:  "+ entry.getItemCount() +" times";
    }

    public String allEntriesToString(){
        String combined = "";
        combined += entryToString(Milk) + "\n";
        combined += entryToString(Bread) + "\n";
        combined += entryToString(Cookies) + "\n";
        combined += entryToString(Apples) + "\n";
        combined += "errors:  "+ errorCount +" seen";
        return combined;
    }

}
