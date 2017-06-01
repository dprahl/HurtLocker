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
        return firstPrice;
    }

    public int getFirstPriceCount() {
        return firstPriceCount;
    }

    public String getSecondPrice() {
        return secondPrice;
    }

    public int getSecondPriceCount() {
        return secondPriceCount;
    }

    public void addPrice(String price){
        if(firstPrice == null){
            firstPrice = price;
        }else if(secondPrice == null) {
            secondPrice = price;
        }

        if(firstPrice == price) {
            firstPriceCount++;
        }else if(secondPrice == price){
            secondPriceCount++;
        }
    }
}
