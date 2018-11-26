package sales;

import javafx.beans.property.*;

public class SalesData {

    private final StringProperty itemId;
    private final StringProperty itemName;
    private final StringProperty itemDescription;
    private final IntegerProperty itemQuantity;
    private final DoubleProperty itemPrice;
    private final DoubleProperty itemAmount;
//    private final StringProperty date;
//    private final StringProperty time;



    public SalesData(String itemId, String itemName, String itemDescription, int itemQuantity, double itemPrice, double itemAmount) {

        this.itemId = new SimpleStringProperty(itemId);
        this.itemName = new SimpleStringProperty(itemName);
        this.itemDescription = new SimpleStringProperty(itemDescription);
        this.itemQuantity = new SimpleIntegerProperty(itemQuantity);
        this.itemPrice = new SimpleDoubleProperty(itemPrice);
        this.itemAmount = new SimpleDoubleProperty(itemAmount);

    }

    public String getItemId() {
        return itemId.get();
    }

    public StringProperty itemIdProperty() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId.set(itemId);
    }

    public String getItemName() {
        return itemName.get();
    }

    public StringProperty itemNameProperty() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName.set(itemName);
    }

    public String getItemDescription() {
        return itemDescription.get();
    }

    public StringProperty itemDescriptionProperty() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription.set(itemDescription);
    }

    public int getItemQuantity() {
        return itemQuantity.get();
    }

    public IntegerProperty itemQuantityProperty() {
        return itemQuantity;
    }

    public void setItemQuantity(int itemQuantity) {
        this.itemQuantity.set(itemQuantity);
    }

    public double getItemPrice() {
        return itemPrice.get();
    }

    public DoubleProperty itemPriceProperty() {
        return itemPrice;
    }

    public void setItemPrice(double itemPrice) {
        this.itemPrice.set(itemPrice);
    }

    public double getItemAmount() {
        return itemAmount.get();
    }

    public DoubleProperty itemAmountProperty() {
        return itemAmount;
    }

    public void setItemAmount(double itemAmount) {
        this.itemAmount.set(itemAmount);
    }
}
