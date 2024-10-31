public class Item {
    protected String Name;
    protected Double Price;

    Item(String name, Double price) {
        this.Name = name;
        this.Price = price;
    }

    Item(String name) {
        this.Name = name;
        this.Price = 0.0;
    }

    public Double getPrice() { return Price; }

    public void setPrice(Double price) { Price = price; }
}
