import java.util.LinkedHashMap;
import java.util.Map;

public class City {
    protected String Name;
    protected Map<String, Location> Locations;
    protected boolean HomeCity;

    City(String name, boolean home) {
        this.Name = name;
        this.HomeCity = home;
        InitLocations();
    }

    private void InitLocations() {
        this.Locations = new LinkedHashMap<>();

        if (this.HomeCity) {
            Locations.put("Home", new Location("Home"));
        }

        Locations.put("Store", new Location("The Store"));
        Locations.put("Mine", new Location("The Mine"));
        Locations.put("Discount", new Location("The Discount Store"));
        Locations.put("Train", new Location("The Train Station"));
    }
}
