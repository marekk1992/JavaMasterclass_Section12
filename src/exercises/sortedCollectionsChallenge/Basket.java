package exercises.sortedCollectionsChallenge;

import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;

public class Basket {
    private final String name;
    private final Map<StockItem, Integer> list;

    public Basket(String name) {
        this.name = name;
        list = new TreeMap<>();
    }

    public String getName() {
        return name;
    }

    public int addToBasket(StockItem item, int quantity) {
        if ((item != null) && (quantity > 0)) {
            int inBasket = list.getOrDefault(item, 0);
            list.put(item, inBasket + quantity);
            return inBasket;
        }
        return 0;
    }

//    new method
    public int remove(StockItem item, int quantity) {
        int inBasket = list.getOrDefault(item, 0);
        if ((item != null) && (quantity <= inBasket)) {
            if ((inBasket - quantity == 0)) {
                list.remove(item);
            } else {
                list.replace(item, inBasket, inBasket - quantity);
            }
            return quantity;
        }
        return 0;
    }

//    new method
    public void checkout() {
        for (StockItem item : list.keySet()) {
            item.adjustStock(-item.getReservedQuantity());
            item.setReservedQuantity(0);
        }
        list.clear();
    }

    public Map<StockItem, Integer> Items() {
        return Collections.unmodifiableMap(list);
    }

    @Override
    public String toString() {
        String s = "\nShopping basket " + name + " contains " + list.size()
                + ((list.size() == 1) ? " item" : " items") + "\n";
        double totalCost = 0.0;
        for (Map.Entry<StockItem, Integer> item : list.entrySet()) {
            s = s + item.getKey() + ". " + item.getValue() + " reserved\n";
            totalCost += item.getKey().getPrice() * item.getValue();
        }
        return s + " Total cost " + String.format("%.2f", totalCost);
    }
}
