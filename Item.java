public class Item {
    protected String Name;
    protected Double Price;
    protected Double BasePrice;

    Item(String name, Double price) {
        this.Name = name;
        this.Price = price;
        this.BasePrice = price;
    }

    Item(String name) {
        this.Name = name;
        this.Price = 0.0;
    }

    public void SetPrice(Double price) { Price = price; }
}
