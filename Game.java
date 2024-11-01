import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class Game {
    protected final int SCREEN_WIDTH = 80;
    protected final int SCREEN_HEIGHT = 50;
    protected Map<String, City> Cities;

    protected Scanner InputScanner;
    protected String LastInput;

    protected Player CurrentPlayer;
    protected int DayCount;

    Game(Double startingMoney, String homeCityName) {
        this.InputScanner = new Scanner(System.in);                     // Game input scanner
        City homeCity = new City(homeCityName, true);             // Home city constructor to pass to player object, this is a custom city
        this.CurrentPlayer = new Player(startingMoney, homeCity);       // Main player object
        this.DayCount = 1;                                              // Init day count
        InitCities(homeCity);                                           // Init starting cities including home city passed
    }

    //////////////////////////
    // Default Methods      //
    //////////////////////////

    // get user input and trim whitespace
    public String GetInput() {
        this.LastInput = this.InputScanner.nextLine().trim();
        return LastInput;
    }

    // get user input and trim whitespace with a prompt passed.
    public String GetInput(String prompt) {
        System.out.print(prompt);
        this.LastInput = this.InputScanner.nextLine().trim();
        return LastInput;
    }

    private void InitCities(City homeCity) {
        this.Cities = new LinkedHashMap<>();
        Cities.put(homeCity.Name, homeCity);
        Cities.put("Tampa", new City("Tampa", false));
        Cities.put("Miami", new City("Miami", false));
        Cities.put("Orlando", new City("Orlando", false));
    }

    public String CapitalizeAllWords(String input) {
        if (input.isEmpty()) {
            return input;
        }

        String[] words = input.split(" ");
        StringBuilder result = new StringBuilder();
        for (String word : words) {
            result.append(Character.toUpperCase(word.charAt(0)));
            result.append(word.substring(1).toLowerCase());
            result.append(" ");
        }

        return result.toString().trim();
    }

    //////////////////////////
    // Game Methods         //
    //////////////////////////

    public void ChangeLocation() {
        CurrentPlayer.SetLocation(GetDestinationLocation());
    }

    public Location GetDestinationLocation() {
        boolean inputGood = false;
        while (!inputGood) {
            DrawChangeLocationScreen();
            String input = GetInput("Where?:  ");
            switch (input.toLowerCase()) {
                case "home":
                    if (CurrentPlayer.CurrentCity.HomeCity) {
                        return CurrentPlayer.CurrentCity.Locations.get("Home");
                    }
                    break;
                case "store":
                case "the store":
                    return CurrentPlayer.CurrentCity.Locations.get("Store");
                case "mine":
                case "the mine":
                    return CurrentPlayer.CurrentCity.Locations.get("Mine");
                case "discount":
                case "discount store":
                case "the discount store":
                    return CurrentPlayer.CurrentCity.Locations.get("Discount");
                case "train":
                case "the train":
                case "the train station":
                case "train station":
                    return CurrentPlayer.CurrentCity.Locations.get("Train");
                case "back":
                    // Break while loop and return current location
                    inputGood = true;
            }
        }

        // Don't change destination
        return CurrentPlayer.CurrentLocation;
    }

    public void ChangeCity() {
        if (CurrentPlayer.CurrentLocation.Name.equals("The Train Station")) {
            City destination = GetDestinationCity();

            if (destination != CurrentPlayer.CurrentCity) {
                DayCount++;
                CurrentPlayer.CurrentCity = destination;
            }
        }
    }

    public City GetDestinationCity() {
        boolean inputGood = false;
        while (!inputGood) {
            DrawChangeCityScreen();
            String input = GetInput("Where?:  ");

            String cityString = CapitalizeAllWords(input);
            if (Cities.containsKey(cityString)) {
                return Cities.get(cityString);
            } else if (input.equalsIgnoreCase("back")) {
                inputGood = true;
            }
        }

        return CurrentPlayer.CurrentCity;
    }

    public void OpenStore() {
        if (CurrentPlayer.CurrentLocation.Name.equals("The Store")) {
            boolean inputGood = false;
            while (!inputGood) {
                DrawStoreScreen();
                String input = GetInput("Buy or sell?:  ");

                if (input.equalsIgnoreCase("buy")) {
                    Item item = GetStoreInputItem();
                    if (item == null) {
                        continue;
                    }

                    int quantity = GetStoreInputQuantity();
                    if (quantity == 0) {
                        continue;
                    }

                    CurrentPlayer.MakePurchase(item, quantity);
                } else if (input.equalsIgnoreCase("sell")) {
                    Item item = GetStoreInputItem();
                    if (item == null) {
                        continue;
                    }

                    int quantity = GetStoreInputQuantity();
                    if (quantity == 0) {
                        continue;
                    }

                    CurrentPlayer.MakeOffer(item, quantity);
                } else if (input.equalsIgnoreCase("back")) {
                    inputGood = true;
                }
            }
        }
    }

    public int GetStoreInputQuantity() {
        boolean inputGood = false;
        while (!inputGood) {
            String input = GetInput("Enter quantity:  ");
            try {
                return Integer.parseInt(input);
            } catch (Exception ignored) {
                if (input.equalsIgnoreCase("back")) {
                    inputGood = true;
                }
            }
        }

        return 0;
    }

    public Item GetStoreInputItem() {
        boolean inputGood = false;
        while (!inputGood) {
            String input = GetInput("Enter item name: ");
            for (Item item : CurrentPlayer.CurrentLocation.ItemMap.keySet()) {
                if (input.equalsIgnoreCase(item.Name)) {
                    return item;
                } else if (input.equalsIgnoreCase("back")) {
                    inputGood = true;
                }
            }
        }

        return null;
    }

    //////////////////////////
    // Graphics Methods     //
    //////////////////////////

    public void ClearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public void DrawMainScreen() {
        String cityString = "City: " + CurrentPlayer.CurrentCity.Name;
        String moneyString = "Money: $" + CurrentPlayer.Money;
        String dayString = " Day: " + DayCount;
        String locationString = "Location: " + CurrentPlayer.CurrentLocation.Name;
        String header = cityString + " ".repeat(SCREEN_WIDTH - moneyString.length() - dayString.length() - locationString.length()) + locationString + "\n";
        header += moneyString + dayString;

        ClearScreen();
        System.out.println(header);
    }

    public void DrawChangeLocationScreen() {
        ClearScreen();

        String[] availableLocations = CurrentPlayer.CurrentCity.Locations.keySet().toArray(new String[0]);
        for (String location : availableLocations) {
            String locationName = CurrentPlayer.CurrentCity.Locations.get(location).Name;
            if (locationName.equals(CurrentPlayer.CurrentLocation.Name)) {
                System.out.println(locationName + " (You are here)" + "\n");
            } else {
                System.out.println(locationName + "\n");
            }
        }
    }

    public void DrawChangeCityScreen() {
        ClearScreen();

        String[] availableCities = this.Cities.keySet().toArray(new String[0]);
        for (String city : availableCities) {
            if (city.equals(CurrentPlayer.CurrentCity.Name)) {
                System.out.println(CurrentPlayer.CurrentCity.Name + " (You are here)" + "\n");
            } else {
                System.out.println(Cities.get(city).Name + "\n");
            }
        }
    }

    public void DrawStoreScreen() {
        ClearScreen();
        String header = "Money: $" + CurrentPlayer.Money;
        System.out.println(header);
        System.out.println("Your inventory:");

        for (Item item : CurrentPlayer.Inventory.keySet()) {
            int quantity = CurrentPlayer.Inventory.get(item);
            System.out.println(item.Name + ": " + quantity);
        }

        System.out.println("\nStore inventory:");
        Item[] availableItems = CurrentPlayer.CurrentLocation.ItemMap.keySet().toArray(new Item[0]);
        for (Item item : availableItems) {
            System.out.println("$" + item.Price + " " + item.Name + ": " + CurrentPlayer.CurrentLocation.ItemMap.get(item));
        }
    }
}
