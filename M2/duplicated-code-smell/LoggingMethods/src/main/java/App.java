import java.time.LocalDateTime;

public class App {
    public static void main(String[] args) {
        ScreenLogger myLogger = new ScreenLogger();
        Order[] orders = setupOrders();

        myLogger.timeStampedLog("Starting processing orders");

        for(Order o: orders) {
            myLogger.timeStampedLog("Processing order " + o.getOrderId());
            for(Item i: o.getItems()) {
                myLogger.logToScreen(o.getOrderId() + " Item: " + i.itemName);
            }
        }
        myLogger.timeStampedLog("Ending processing orders");

    }

    private static Order[] setupOrders() {
        Order[] setupOrders = new Order[3];
        Item item1 = new Item();
        item1.setId(1);
        item1.setItemName("Item One");
        Item item2 = new Item();
        item2.setId(2);
        item2.setItemName("Item Two");
        Item item3 = new Item();
        item3.setId(3);
        item3.setItemName("Item Three");
        Item item4 = new Item();
        item4.setId(4);
        item4.setItemName("Item Four");
        Item item5 = new Item();
        item5.setId(5);
        item5.setItemName("Item Five");

        for(int i = 0; i< setupOrders.length; i++) {
            setupOrders[i] = new Order();
            setupOrders[i].setOrderId(i+1);
            setupOrders[i].addItem(item1);
            setupOrders[i].addItem(item2);
            setupOrders[i].addItem(item3);
            setupOrders[i].addItem(item4);
            setupOrders[i].addItem(item5);
        }
        return setupOrders;
    }
}
