import java.util.LinkedHashMap;
import java.util.Map;

public class Location {
    protected String Name;
    // map of items with item and quantity
    protected Map<Item, Integer> ItemMap = new LinkedHashMap<>();

    Location(String name) {
        InitItemMap();
        this.Name = name;
    }

    private void InitItemMap() {
        ItemMap.put(new Item("Dirt", 0.1), 10_000);
        ItemMap.put(new Item("Sand", 1.0), 1_000);
        ItemMap.put(new Item("Wood", 2.0), 100);
        ItemMap.put(new Item("Stone", 5.0), 25);
    }
}
