/**
 * Created by danielprahl on 5/31/17.
 */
public class InventoryItem {
    private String name;
    private String price;
    private String type;
    private String expiration;

    public InventoryItem(){
      /* leave all fields null */
        //this.name = "undefined";
        //this.price = "undefined";
        //this.type = "undefined";
        //this.expiration = "undefined";
    }

    public InventoryItem(String name, String price, String type, String expiration){
        this.name = name;
        this.price = price;
        this.type = type;
        this.expiration = expiration;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getExpiration() {
        return expiration;
    }

    public void setExpiration(String expiration) {
        this.expiration = expiration;
    }
}