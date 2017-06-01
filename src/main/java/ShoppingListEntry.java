import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by danielprahl on 5/31/17.
 */
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
