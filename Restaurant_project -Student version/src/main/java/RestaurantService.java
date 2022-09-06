import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RestaurantService {
    private static List<Restaurant> restaurants = new ArrayList<>();

    public Restaurant findRestaurantByName(String restaurantName) throws restaurantNotFoundException {
        Optional<Restaurant> restaurantToFind=restaurants.stream().filter(restaurant -> restaurant.getName().equals(restaurantName)).findAny();
        if(restaurantToFind.isEmpty())
            throw new restaurantNotFoundException(restaurantName);
        else
        return restaurantToFind.get();
        //DELETE ABOVE STATEMENT AND WRITE CODE HERE
    }


    public Restaurant addRestaurant(String name, String location, LocalTime openingTime, LocalTime closingTime) {
        Restaurant newRestaurant = new Restaurant(name, location, openingTime, closingTime);
        restaurants.add(newRestaurant);
        return newRestaurant;
    }

    public Restaurant removeRestaurant(String restaurantName) throws restaurantNotFoundException {
        Restaurant restaurantToBeRemoved = findRestaurantByName(restaurantName);
        restaurants.remove(restaurantToBeRemoved);
        return restaurantToBeRemoved;
    }

    public List<Restaurant> getRestaurants() {
        return restaurants;
    }

    public  int calculateOrder(String restaurantName,List<String> orderedItems) throws restaurantNotFoundException {
        Restaurant restaurant=findRestaurantByName(restaurantName);
        int orderValue=0;
        for(Item item : restaurant.getMenu())
        {
            if(orderedItems.contains(item.getName())) {
                orderValue += item.getPrice();
            }
        }
        return orderValue;
    }
}
