/**
 * Created by danielprahl on 5/31/17.
 */
public class ShoppingListEntry {
    private String name;
    private int count;
    private double firstPrice;
    private int firstPriceCount;
    private double secondPrice;
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

    public double getFirstPrice() {
        return firstPrice;
    }

    public int getFirstPriceCount() {
        return firstPriceCount;
    }

    public double getSecondPrice() {
        return secondPrice;
    }

    public int getSecondPriceCount() {
        return secondPriceCount;
    }

    public void addPrice(double price){
        if(this.firstPrice == 0){
            this.firstPrice = price;
            this.firstPriceCount++;
        }else if(this.secondPrice == 0){
            this.secondPrice = price;
            this.secondPriceCount++;
        }
    }
}
