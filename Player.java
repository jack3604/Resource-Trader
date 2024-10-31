import java.util.LinkedHashMap;
import java.util.Map;

public class Player {
    protected Double Money;
    protected City HomeCity;
    protected City CurrentCity;
    protected Location CurrentLocation;
    protected Map<Item, Integer> Inventory;

    Player(Double money, City homeCity) {
        this.Money = money;
        this.HomeCity = homeCity;
        this.CurrentCity = homeCity;
        this.CurrentLocation = CurrentCity.Locations.get("Home");
    }

    private void InitInventory() {
        this.Inventory = new LinkedHashMap<Item, Integer>();
        Inventory.put(new Item("Dirt"), 0);
        Inventory.put(new Item("Sand"), 0);
        Inventory.put(new Item("Wood"), 0);
        Inventory.put(new Item("Stone"), 0);
    }

    public void SetLocation(Location newLocation) {
        this.CurrentLocation = newLocation;
    }
}
