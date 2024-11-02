import java.util.LinkedHashMap;
import java.util.Map;

public class Location {
    protected String Name;
    // map of items with item and quantity
    protected Map<Item, Integer> ItemMap;
    protected Map<String, Integer> BaseQuantityMap;

    Location(String name) {
        if (name.toLowerCase().contains("store")) {
            InitBaseQuantityMap();
            InitItemMap();
        }
        this.Name = name;
    }

    private void InitItemMap() {
        this.ItemMap = new LinkedHashMap<>();
        ItemMap.put(new Item("Dirt", 0.1), BaseQuantityMap.get("Dirt"));
        ItemMap.put(new Item("Sand", 1.0), BaseQuantityMap.get("Sand"));
        ItemMap.put(new Item("Wood", 2.0), BaseQuantityMap.get("Wood"));
        ItemMap.put(new Item("Stone", 5.0), BaseQuantityMap.get("Stone"));
    }

    private void InitBaseQuantityMap() {
        this.BaseQuantityMap = new LinkedHashMap<>();
        BaseQuantityMap.put("Dirt", 10_000);
        BaseQuantityMap.put("Sand", 1_000);
        BaseQuantityMap.put("Wood", 500);
        BaseQuantityMap.put("Stone", 100);
    }
}
