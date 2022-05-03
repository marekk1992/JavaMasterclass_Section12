package exercises.sortedCollectionsChallenge;

public class Main {

    private static StockList stockList = new StockList();

    public static void main(String[] args) {
        StockItem temp = new StockItem("bread", 0.86, 100);
        stockList.addStock(temp);

        temp = new StockItem("car", 12.50, 2);
        stockList.addStock(temp);

        temp = new StockItem("chair", 62.0, 10);
        stockList.addStock(temp);

        temp = new StockItem("cup", 0.50, 200);
        stockList.addStock(temp);
        temp = new StockItem("cup", 0.45, 7);
        stockList.addStock(temp);

        temp = new StockItem("door", 72.95, 4);
        stockList.addStock(temp);

        temp = new StockItem("juice", 2.50, 36);
        stockList.addStock(temp);

        temp = new StockItem("phone", 96.99, 35);
        stockList.addStock(temp);

        temp = new StockItem("towel", 2.40, 80);
        stockList.addStock(temp);

        temp = new StockItem("vase", 8.76, 40);
        stockList.addStock(temp);

        System.out.println(stockList);

        Basket myBasket = new Basket("MyBasket");
        sellItem(myBasket, "car", 1);
        System.out.println(myBasket);

        sellItem(myBasket, "car", 1);
        System.out.println(myBasket);

        sellItem(myBasket, "car", 1);
        sellItem(myBasket, "spanner", 5);
        System.out.println(myBasket);

        sellItem(myBasket, "juice", 4);
        sellItem(myBasket, "cup", 20);
        sellItem(myBasket, "bread", 1);

        unreserveItem(myBasket, "juice", 2);
        unreserveItem(myBasket, "car", 2);
        System.out.println(myBasket);
        myBasket.checkout();

        System.out.println(myBasket);

        Basket secondBasket = new Basket("AdditionalBasket");
        sellItem(secondBasket, "car", 1);
        sellItem(secondBasket, "bread", 89);
        unreserveItem(secondBasket, "bread", 5);
        System.out.println(secondBasket);
        secondBasket.checkout();
        System.out.println(secondBasket);
        System.out.println(stockList);

    }

    public static int sellItem(Basket basket, String item, int quantity) {
        // retrieve the item from stockList
        StockItem stockItem = stockList.get(item);
        if (stockItem == null) {
            System.out.println("We don`t sell " + item);
            return 0;
        }

        if (stockList.sellStock(item, quantity) != 0) {
            basket.addToBasket(stockItem, quantity);
            return quantity;
        }
        return 0;
    }

    //    new method
    public static int unreserveItem(Basket basket, String item, int quantity) {
        StockItem stockItem = stockList.get(item);
        if (stockItem == null) {
            System.out.println("There is no " + item + " in " + basket.getName());
            return 0;
        }

        if (stockItem.getReservedQuantity() >= quantity) {
            stockItem.reserveStock(-quantity);
            return basket.remove(stockItem, quantity);
        }

        return 0;
    }
}
