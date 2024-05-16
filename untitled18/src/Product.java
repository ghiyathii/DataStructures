import java.io.Serializable;
import java.util.*;

class Features implements Serializable {
    private String productName;
    private String manufacturer;
    private String modelNumber;
    private String serialNumber;
    private String productDescription;
    private double price;
    private boolean availability;
    private String categories;
    private int inventoryQuantity;
    private String previousVersions;
    private String relatedProducts;
    //Declare special instance variables that represent different attributes of the product.
    // Constructor
    public Features(String productName, String manufacturer, String modelNumber, String serialNumber,
                    String productDescription, double price, boolean availability, String categories,
                    int inventoryQuantity, String previousVersions, String relatedProducts) {
        //Initializes the feature class with the values available for each feature.
        this.productName = productName;
        this.manufacturer = manufacturer;
        this.modelNumber = modelNumber;
        this.serialNumber = serialNumber;
        this.productDescription = productDescription;
        this.price = price;
        this.availability = availability;
        this.categories = categories;
        this.inventoryQuantity = inventoryQuantity;
        this.previousVersions = previousVersions;
        this.relatedProducts = relatedProducts;
    }
    //assign the values passed as parameters

    // Getters and Setters
    // setter method sets a new value for that attribute
    // getter method returns the value of a specific attribute
    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getModelNumber() {
        return modelNumber;
    }

    public void setModelNumber(String modelNumber) {
        this.modelNumber = modelNumber;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isAvailability() {
        return availability;
    }

    public void setAvailability(boolean availability) {
        this.availability = availability;
    }

    public String getCategories() {
        return categories;
    }

    public void setCategories(String categories) {
        this.categories = categories;
    }

    public int getInventoryQuantity() {
        return inventoryQuantity;
    }

    public void setInventoryQuantity(int inventoryQuantity) {
        this.inventoryQuantity = inventoryQuantity;
    }

    public String getPreviousVersions() {
        return previousVersions;
    }

    public void setPreviousVersions(String previousVersions) {
        this.previousVersions = previousVersions;
    }

    public String getRelatedProducts() {
        return relatedProducts;
    }

    public void setRelatedProducts(String relatedProducts) {
        this.relatedProducts = relatedProducts;
    }
}