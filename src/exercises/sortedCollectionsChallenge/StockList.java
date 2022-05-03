package exercises.sortedCollectionsChallenge;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class StockList {
    private final Map<String, StockItem> list;

    public StockList() {
        list = new LinkedHashMap<>();
    }

    public int addStock(StockItem item) {
        if (item != null) {
            // check if already have quantities of this item
            StockItem inStock = list.getOrDefault(item.getName(), item);
            // if there are already stock on this item, adjust quantity
            if (inStock != item) {
                item.adjustStock(inStock.quantityInStock());
            }

            list.put(item.getName(), item);
            return item.quantityInStock();
        }
        return 0;
    }

//    modified by including reverseStock() method
    public int sellStock(String item, int quantity) {
        StockItem inStock = list.getOrDefault(item, null);

        if ((inStock != null && inStock.reserveStock(quantity))) {
            return quantity;
        }
        return 0;
    }

    public StockItem get(String key) {
        return list.get(key);
    }

    public Map<String, StockItem> Items() {
        return Collections.unmodifiableMap(list);
    }

    public Map<String, Double> PriceList() {
        Map<String, Double> prices = new LinkedHashMap<>();
        for (Map.Entry<String, StockItem> item : list.entrySet()) {
            prices.put(item.getKey(), item.getValue().getPrice());
        }
        return Collections.unmodifiableMap(prices);
    }

    @Override
    public String toString() {
        String s = "\nStock List\n";
        double totalCost = 0.0;
        for (Map.Entry<String, StockItem> item : list.entrySet()) {
            StockItem stockItem = item.getValue();

            double itemValue = stockItem.getPrice() * stockItem.quantityInStock();

            s = s + stockItem + ". There are " + stockItem.quantityInStock() + " in stock.";
            s = s + " Value of items " + String.format("%.2f", itemValue)  + "\n";
            totalCost += itemValue;
        }

        return s + "Total stock value " + totalCost;
    }
}
