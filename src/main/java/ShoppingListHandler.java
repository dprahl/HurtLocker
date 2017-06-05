import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/**
 * Created by danielprahl on 5/31/17.
 */
public class ShoppingListHandler {

    // inner class
    public class ShoppingListEntry {
        private String name;
        private int count;
        private String firstPrice;
        private int firstPriceCount;
        private String secondPrice;
        private int secondPriceCount;

        public ShoppingListEntry(String name){
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public int getItemCount() {
            return count;
        }

        public void addOneToCount(){
            this.count++;
        }

        public String getFirstPrice() {
            return firstPrice.toString();
        }

        public int getFirstPriceCount() {
            return firstPriceCount;
        }

        public String getSecondPrice() {
            return secondPrice.toString();
        }

        public int getSecondPriceCount() {
            return secondPriceCount;
        }

        public void addPrice(String price){
            if(this.firstPrice != null){
                Pattern pattern = Pattern.compile(price);
                Matcher matcher = pattern.matcher(firstPrice);
                if(matcher.matches()) {
                    firstPriceCount++;
                }else if(secondPrice == null){
                    secondPrice = price;
                    secondPriceCount++;
                }else{
                    secondPriceCount++;
                }
            }else{
                firstPrice = price;
                firstPriceCount++;
            }
        }
    }

    private int errorCount;
    //private ArrayList<ShoppingListEntry> entries;
  /*  4 private class variables instead of list  */
    private ShoppingListEntry Milk;
    private ShoppingListEntry Bread;
    private ShoppingListEntry Cookies;
    private ShoppingListEntry Apples;

    public ShoppingListHandler(){
        errorCount = 0;
        //this.entries = new ArrayList<ShoppingListEntry>();
          Milk = new ShoppingListEntry("   Milk");
          Bread = new ShoppingListEntry("  Bread");
          Cookies = new ShoppingListEntry("Cookies");
          Apples = new ShoppingListEntry(" Apples");

    }

    private ArrayList<InventoryItem> populateItems(String rawInput){
        ArrayList<InventoryItem> itemsList = new ArrayList<InventoryItem>(30);
        String name, price, type, expiration;
        String[] separated = JerkSONParser.separateInput(rawInput);
        for (String currentLine : separated) {
            try {
                name = JerkSONParser.getValue(currentLine, JerkSONParser.NAME);
                price = JerkSONParser.getValue(currentLine, JerkSONParser.PRICE);
                type = JerkSONParser.getValue(currentLine, JerkSONParser.TYPE);
                expiration = JerkSONParser.getValue(currentLine, JerkSONParser.EXPIRATION);
                InventoryItem item = new InventoryItem(name, price, type, expiration);
                itemsList.add(item);
            }
            catch (PatternSyntaxException e){
                errorCount++;
            }
        }
        return itemsList;
    }

    private void mapEntriesFromItems(ArrayList<InventoryItem> itemsList){
        ShoppingListEntry workingEntry;
        for (InventoryItem currentItem : itemsList) {
            workingEntry = getEntry(currentItem.getName());
            workingEntry.addOneToCount();
            workingEntry.addPrice(currentItem.getPrice());
        }
    }

    // WARNING: NOT SCALABLE
    // this is an ugly workaround to using String.equals() which is disallowed/ also,
    // should pull entries from a list, not just one of 4 private class variables
    private ShoppingListEntry getEntry(String name){
        Pattern milkPattern = Pattern.compile("\\s*[Mm].*");
        Pattern breadPattern = Pattern.compile("\\s*[Bb].*");
        Pattern cookiesPattern = Pattern.compile("\\s*[Cc].*");
        Pattern applesPattern = Pattern.compile("\\s*[Aa].*");
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

    private String entryToFormattedString(ShoppingListEntry entry){
        String entryString = "name: "+ entry.getName() +"\t\t seen: "+ entry.getItemCount() +" times\n";
        entryString += "=============\t\t =============\n";
        entryString += "Price:   "+ entry.getFirstPrice() +"\t\t seen: "+ entry.getFirstPriceCount() +" times\n";
        entryString += "-------------\t\t -------------\n";
        if(entry.getSecondPriceCount() > 0){
            String times = entry.getSecondPriceCount() > 1 ? " times" : " time";
            entryString += "Price:   " + entry.getSecondPrice() + "\t\t seen: " + entry.getSecondPriceCount() + times +"\n";
        }
        return entryString;
    }

    // to be proper, this should iterate over a list of entries,
    // not just 4 private class variables
    private String allEntriesToString(){
        String milk = entryToFormattedString(Milk) + "\n";
        String bread = entryToFormattedString(Bread) + "\n";
        String cookies = entryToFormattedString(Cookies) + "\n";
        String apples = entryToFormattedString(Apples) + "\n";
        String errors = "Errors   \t\t\t seen: "+ errorCount +" times";
        return milk + bread + cookies + apples + errors;
    }

    public String createShoppingList(String rawInput){
        mapEntriesFromItems(populateItems(rawInput));
        return allEntriesToString();
    }
}
