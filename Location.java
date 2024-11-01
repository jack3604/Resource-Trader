import java.util.LinkedHashMap;
import java.util.Map;

public class Location {
    protected String Name;
    // map of items with item and quantity
    protected Map<Item, Integer> ItemMap;

    Location(String name) {
        if (name.toLowerCase().contains("store")) {
            InitItemMap();
        }
        this.Name = name;
    }

    private void InitItemMap() {
        this.ItemMap = new LinkedHashMap<>();
        ItemMap.put(new Item("Dirt", 0.1), 10_000);
        ItemMap.put(new Item("Sand", 1.0), 1_000);
        ItemMap.put(new Item("Wood", 2.0), 100);
        ItemMap.put(new Item("Stone", 5.0), 25);
    }
}
