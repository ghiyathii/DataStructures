import java.io.Serializable;
import java.util.LinkedList;

class ProductCatalog implements Serializable {
    // to make class serialized.
    private LinkedList<Features> productList;
    //declares a private instance variable product List of type LinkedList

    public ProductCatalog() {
        this.productList = new LinkedList<>();
    }

    public void addNewProduct(Features product) {
        productList.add(product);
    }

    public Features findProductByName(String productName) {
        for (Features product : productList) {
            if (product.getProductName().equalsIgnoreCase(productName)) {
                //searches for a product by its name
                return product;
                //returns the first matching product found
            }
        }
        return null; // Product not found
    }

    public LinkedList<Features> findProductByManufacturer(String manufacturerName) {
        LinkedList<Features> productsByManufacturer = new LinkedList<>();
        for (Features product : productList) {
            if (product.getManufacturer().equalsIgnoreCase(manufacturerName)) {
                productsByManufacturer.add(product);
            }
        }
        return productsByManufacturer;
    }

    public LinkedList<Features> findProductByCategory(String categoryName) {
        LinkedList<Features> productsByCategory = new LinkedList<>();
        for (Features product : productList) {
            if (product.getCategories().contains(categoryName)) {
                productsByCategory.add(product);
            }
        }
        return productsByCategory;
    }

    public void updateProduct(Features updatedProduct) {
        for (int i = 0; i < productList.size(); i++) {
            if (productList.get(i).getProductName().equals(updatedProduct.getProductName())) {
                productList.set(i, updatedProduct);
                break;
            }
        }
    }

    public boolean removeProduct(String ProductName) {
        for (Features product : productList) {
            if (product.getProductName().equals(ProductName)) {
                productList.remove(product);
                return true;
            }
        }
        return false; // Product not found
    }

    // Shopping cart functionality

    public double calculateCartTotal(LinkedList<Features> cart) {
        double total = 0;
        for (Features product : cart) {
            total += product.getPrice();
        }
        return total;
    }

    public void processOrder(LinkedList<Features> cart) {
        System.out.println("Order processed successfully!");
    }
}