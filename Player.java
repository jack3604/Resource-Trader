import java.util.LinkedHashMap;
import java.util.Map;

public class Player {
    protected Double Money;
    protected City HomeCity;
    protected City CurrentCity;
    protected Location CurrentLocation;
    // inventory with item and quantities held
    protected Map<Item, Integer> Inventory;

    Player(Double money, City homeCity) {
        this.Money = money;
        this.HomeCity = homeCity;
        this.CurrentCity = homeCity;
        this.CurrentLocation = CurrentCity.Locations.get("Home");
        InitInventory();
    }

    private void InitInventory() {
        this.Inventory = new LinkedHashMap<>();
        Inventory.put(new Item("Dirt"), 0);
        Inventory.put(new Item("Sand"), 0);
        Inventory.put(new Item("Wood"), 0);
        Inventory.put(new Item("Stone"), 0);
    }

    public void SetLocation(Location newLocation) {
        this.CurrentLocation = newLocation;
    }

    public void MakePurchase(Item item, int quantity) {
        if (this.Money >= quantity * item.Price) {
            this.Money = RoundDouble(this.Money - (quantity * item.Price));
            for (Item i : this.Inventory.keySet()) {
                if (i.Name.equals(item.Name)) {
                    this.Inventory.replace(i, this.Inventory.get(i) + quantity);
                    this.CurrentLocation.ItemMap.replace(item, CurrentLocation.ItemMap.get(item) - quantity);
                }
            }
        }
    }

    public void MakeOffer(Item item, int quantity) {
        for (Item i : this.Inventory.keySet()) {
            if (i.Name.equals(item.Name)) {
                if (this.Inventory.get(i) >= quantity) {
                    this.Money = RoundDouble(this.Money + (quantity * item.Price));
                    this.Inventory.replace(i, this.Inventory.get(i) - quantity);
                    this.CurrentLocation.ItemMap.replace(item, CurrentLocation.ItemMap.get(item) + quantity);
                }
            }
        }
    }

    private Double RoundDouble(Double d) {
        return Math.round(d * 100.0) / 100.0;
    }
}
