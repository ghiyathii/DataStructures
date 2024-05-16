import java.util.*;
import java.io.*;


class Product {
    private static Scanner scanner; // Scanner object for user input
    private static boolean running; // Flag to control the main loop
    private static LinkedList<Features> shoppingCart; // Linked list to store items in the shopping cart
    private static ProductCatalog productCatalog; // it show the available products

    // to display the menu
    private static void displayMenu() {
        System.out.println("------ N11 Product ------");
        System.out.println("1. Add new product");
        System.out.println("2. Find product");
        System.out.println("3. Update product");
        System.out.println("4. Remove product from the catalog");
        System.out.println("5. Add product to cart");
        System.out.println("6. Remove product from cart");
        System.out.println("7. Calculate cart total");
        System.out.println("8. Process order");
        System.out.println("0. Exit");
        System.out.print("Enter your choice: ");
    }
    // Main method
    public static void main(String[] args) {
        loadData("mytextfile.txt"); // Load data from file
        running = true; // I Set running flag to true

        while(running) {
            displayMenu(); // Display menu


            try {
                int choice = scanner.nextInt(); // To Read user choice
                scanner.nextLine();
                switch (choice) {
                    case 0:
                        running = false;
                        System.out.println("Exited N11 Platform");
                        break;
                    case 1:
                        addNewProduct();
                        break;
                    case 2:
                        findProduct();
                        break;
                    case 3:
                        updateProduct();
                        break;
                    case 4:
                        removeProduct();
                        break;
                    case 5:
                        addToCart();
                        break;
                    case 6:
                        removeFromCart();
                        break;
                    case 7:
                        calculateCartTotal();
                        break;
                    case 8:
                        processOrder();
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } catch (InputMismatchException var2) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine();//clears the input buffer
            }
        }

        saveData("mytextfile.txt");
    }

    private static void loadData(String myfiletext) {
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(myfiletext)); // to create ObjectInputStream

            try {
                try {
                    shoppingCart = (LinkedList)ois.readObject(); //To read shopping cart data
                } catch (IOException e) {
                    throw new RuntimeException(e);
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
                productCatalog = (ProductCatalog)ois.readObject(); //To read product catalog data
                System.out.println("Data loaded successfully.");
            } catch (Throwable var5) { //suppressed exception to the original exception
                try {
                    ois.close(); // to close the ObjectInputStream
                } catch (Throwable var4) {
                    var5.addSuppressed(var4); //if an exception occurs during the close
                }

                throw var5; //if there exception occurred in loading process
            }

            ois.close();
        } catch (ClassNotFoundException | IOException var6) {
            System.out.println("No existing data found.");
        }

    }

    private static void saveData(String myfiletext) {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(myfiletext));
            //This prepares the stream for writing objects to the file.

            try {
                oos.writeObject(shoppingCart); // Write shopping cart data
                oos.writeObject(productCatalog);
                System.out.println("Data saved successfully.");
            } catch (Throwable var5) {
                try {
                    oos.close();
                } catch (Throwable var4) {
                    var5.addSuppressed(var4);
                }

                throw var5; //code for further handling or logging
            }

            oos.close(); //closed to release system resources.
        } catch (IOException var6) { //occurs during the process of creating or writing to the file
            System.out.println("Error saving data.");
        }

    }

    private static void addNewProduct() {
        try {
            System.out.println("Enter product details:");
            System.out.print("Product Name: ");
            String productName = scanner.nextLine();
            System.out.print("Manufacturer: ");
            String manufacturer = scanner.nextLine();
            System.out.print("Model Number: ");
            String modelNumber = scanner.nextLine();
            System.out.print("Serial Number: ");
            String serialNumber = scanner.nextLine();
            System.out.print("Product Description: ");
            String productDescription = scanner.nextLine();
            System.out.print("Price: ");
            double price = scanner.nextDouble();
            scanner.nextLine();// Consume newline character after reading double
            System.out.print("Availability (true/false): ");
            boolean availability = scanner.nextBoolean();
            scanner.nextLine(); // Consume newline character after reading boolean
            System.out.print("Category (comma-separated): ");
            String categories = scanner.nextLine();
            System.out.print("Inventory Quantity: ");
            int inventoryQuantity = scanner.nextInt();
            scanner.nextLine();  // Consume newline character after reading int
            System.out.print("Previous Versions (comma-separated): ");
            String previousVersions = scanner.nextLine();
            System.out.print("Related Products (comma-separated): ");
            String relatedProducts = scanner.nextLine();
            Features newProduct = new Features(productName, manufacturer, modelNumber, serialNumber,
                    productDescription, price, availability, categories, inventoryQuantity, previousVersions,
                    relatedProducts);
            // Create a new Features object with the entered details
            shoppingCart.add(newProduct);
            // Add the new product to the shopping cart
            System.out.println("New product added successfully.");
        } catch (InputMismatchException var13) { // Handle input mismatch exceptions
            System.out.println("Invalid input. Please enter the correct data type.");
            scanner.nextLine();
        }

    }

    private static void findProduct() {
        try {
            System.out.println("Select search criteria:");
            System.out.println("1. By name");
            System.out.println("2. By manufacturer");
            System.out.println("3. By category");
            int searchChoice = scanner.nextInt(); // Read the user's search choice
            scanner.nextLine();
            switch (searchChoice) {
                case 1:
                    System.out.print("Enter product name to search: ");
                    String productName = scanner.nextLine();
                    quickSortByName(shoppingCart, 0, shoppingCart.size() - 1);
                    // Sort the shopping cart by product name
                    int index = binarySearchByName(productName);
                    // Perform a binary search by product name
                    if (index != -1) {
                        displayProductDetails(shoppingCart.get(index));
                        // Display product details if found
                    } else {
                        System.out.println("Product not found.");
                        // if not found print "Product not found."
                    }
                    break;
                case 2:
                    System.out.print("Enter manufacturer name to search: ");
                    String manufacturerName = scanner.nextLine();
                    quickSortByManufacturer(shoppingCart, 0, shoppingCart.size() - 1);
                    // Sort the shopping cart by manufacturer
                    index = binarySearchByManufacturer(manufacturerName);
                    if (index != -1) {
                        displayProductDetails(shoppingCart.get(index));
                    } else {
                        System.out.println("Product not found.");
                    }
                    break;
                case 3:
                    System.out.print("Enter category to search: ");
                    String categoryName = scanner.nextLine();
                    quickSortByCategory(shoppingCart, 0, shoppingCart.size() - 1);
                    index = binarySearchByCategory(categoryName);
                    if (index != -1) {
                        displayProductDetails(shoppingCart.get(index));
                    } else {
                        System.out.println("Product not found.");
                    }
                    break;
                default: // if expression does not match any case
                    System.out.println("Invalid choice.");
            }
        } catch (InputMismatchException var4) { // Handle input mismatch exceptions
            System.out.println("Invalid input. Please enter the correct data type.");
            scanner.nextLine(); // Clear input buffer
        }
    }

    private static void quickSortByName(List<Features> arr, int low, int high) {
        if (low < high) { //checks if the indices low and high are valid for partitioning the array
            int pi = partitionByName(arr, low, high); //to partition the array around a pivot element
            quickSortByName(arr, low, pi - 1);
            quickSortByName(arr, pi + 1, high);
        }
    }

    private static int partitionByName(List<Features> arr, int low, int high) {
        //This method partitions the array around a pivot element based on the name of the features
        String pivot = arr.get(high).getProductName();
        int i = (low - 1);//for swapping elements during partitioning
        for (int j = low; j < high; j++) { //starts a loop from index low to high - 1
            if (arr.get(j).getProductName().compareToIgnoreCase(pivot) < 0) {
                // check the current Features object is less than the pivot
                i++; //increment the index i
                swap(arr, i, j);//swap the Features objects at indices i and j if the condition is true
            }
        }
        swap(arr, i + 1, high);
        return i + 1;
    }

    private static void swap(List<Features> arr, int i, int j) {
        Features temp = arr.get(i);
        arr.set(i, arr.get(j));
        arr.set(j, temp);
    }

    private static int binarySearchByName(String productName) {
        int left = 0;
        int right = shoppingCart.size() - 1;
        // initialize the left and right pointers

        while(left <= right) {
            //starts a loop until the left pointer is less than or equal to the right pointer.
            int mid = left + (right - left) / 2;
            String midName = ((Features)shoppingCart.get(mid)).getProductName();
            //calculate the middle index
            int comparison = midName.compareToIgnoreCase(productName);
            //compares the name of the Features object at the middle index with the specified productName
            if (comparison == 0) {
                return mid;
                //If the comparison result is zero
                //the method returns the middle index.
            }

            if (comparison < 0) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return -1;
        // product is not present in the file
    }


    private static void quickSortByManufacturer(List<Features> arr, int low, int high) {
        if (low < high) {
            int pi = partitionByManufacturer(arr, low, high);
            quickSortByManufacturer(arr, low, pi - 1);
            quickSortByManufacturer(arr, pi + 1, high);
        }
    }

    private static int partitionByManufacturer(List<Features> arr, int low, int high) {
        String pivot = arr.get(high).getManufacturer();
        int i = (low - 1);
        for (int j = low; j < high; j++) {
            if (arr.get(j).getManufacturer().compareToIgnoreCase(pivot) < 0) {
                i++;
                swap(arr, i, j);
            }
        }
        swap(arr, i + 1, high);
        return i + 1;
    }

    private static void quickSortByCategory(List<Features> arr, int low, int high) {
        if (low < high) {
            int pi = partitionByCategory(arr, low, high);
            quickSortByCategory(arr, low, pi - 1);
            quickSortByCategory(arr, pi + 1, high);
        }
    }

    private static int partitionByCategory(List<Features> arr, int low, int high) {
        String pivot = arr.get(high).getCategories();
        int i = (low - 1);
        for (int j = low; j < high; j++) {
            if (arr.get(j).getCategories().compareToIgnoreCase(pivot) < 0) {
                i++;
                swap(arr, i, j);
            }
        }
        swap(arr, i + 1, high);
        return i + 1;
    }


    private static int binarySearchByManufacturer(String manufacturerName) {
        int left = 0;
        int right = shoppingCart.size() - 1;

        while(left <= right) {
            int mid = left + (right - left) / 2;
            String midManufacturer = ((Features)shoppingCart.get(mid)).getManufacturer();
            int comparison = midManufacturer.compareToIgnoreCase(manufacturerName);
            if (comparison == 0) {
                return mid;
            }

            if (comparison < 0) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return -1;
    }

    private static int binarySearchByCategory(String categoryName) {
        int left = 0;

        int mid;
        for(int right = shoppingCart.size() - 1; left <= right; right = mid - 1) {
            mid = left + (right - left) / 2;
            String midCategories = ((Features)shoppingCart.get(mid)).getCategories();
            if (midCategories.contains(categoryName)) {
                return mid;
            }
        }

        return -1;
    }


    private static void updateProduct() {
        try {
            System.out.print("Enter product name to update: ");
            String updateName = scanner.nextLine();
            //enter the name of the product they want to update
            boolean found = false;
            Iterator var2 = shoppingCart.iterator();
            //to iterate through the shoppingCart

            while(var2.hasNext()) {
                Features product = (Features)var2.next();
                //retrieved one by one
                if (product.getProductName().equalsIgnoreCase(updateName)) {
                    //check name of the current product matches the name entered by the user
                    System.out.println("Product found. Select attribute to update:");
                    System.out.println("1. Price");
                    System.out.println("2. Availability");
                    System.out.println("3. Description");
                    int choice = scanner.nextInt();
                    scanner.nextLine();
                    switch (choice) {
                        case 1:
                            System.out.print("Enter new price: ");
                            double newPrice = scanner.nextDouble();
                            product.setPrice(newPrice);
                            System.out.println("Price updated successfully.");
                            break;
                        case 2:
                            System.out.print("Enter new availability (true/false): ");
                            boolean newAvailability = scanner.nextBoolean();
                            scanner.nextLine();
                            product.setAvailability(newAvailability);
                            System.out.println("Availability updated successfully.");
                            break;
                        case 3:
                            System.out.print("Enter new description: ");
                            String newDescription = scanner.nextLine();
                            product.setProductDescription(newDescription);
                            System.out.println("Description updated successfully.");
                            break;
                        default:
                            System.out.println("Invalid choice.");
                    }

                    found = true;
                    break;
                }
            }

            if (!found) {
                //if its not found
                System.out.println("Product not found.");
            }
        } catch (InputMismatchException var9) {
            System.out.println("Invalid input. Please enter the correct data type.");
            scanner.nextLine();
            // to prints an error message and clears the scanner input buffer
        }

    }

    private static void removeProduct() {
        try {
            System.out.print("Enter product name to remove: ");
            String removeName = scanner.nextLine();
            boolean removed = false;
            Iterator<Features> iterator = shoppingCart.iterator();

            while(iterator.hasNext()) {
                Features product = (Features)iterator.next();
                if (product.getProductName().equalsIgnoreCase(removeName)) {
                    iterator.remove();
                    System.out.println("Product removed successfully.");
                    removed = true;
                    break;
                }
            }

            if (!removed) {
                System.out.println("Product not found.");
            }
        } catch (InputMismatchException var4) {
            System.out.println("Invalid input. Please enter the correct data type.");
            scanner.nextLine();
        }

    }

    private static void addToCart() {
        try {
            System.out.print("Enter product name to add to cart: ");
            String productName = scanner.nextLine();
            boolean found = false;
            Iterator var2 = shoppingCart.iterator();

            while(var2.hasNext()) {
                Features product = (Features)var2.next();
                if (product.getProductName().equalsIgnoreCase(productName)) {
                    shoppingCart.add(product);
                    System.out.println("Product added to cart successfully.");
                    found = true;
                    break;
                }
            }

            if (!found) {
                System.out.println("Product not found.");
            }
        } catch (InputMismatchException var4) {
            System.out.println("Invalid input. Please enter the correct data type.");
            scanner.nextLine();
        }

    }

    private static void removeFromCart() {
        try {
            System.out.print("Enter product name to remove from cart: ");
            String productName = scanner.nextLine();
            boolean removed = false;
            Iterator<Features> iterator = shoppingCart.iterator();

            while(iterator.hasNext()) {
                Features product = (Features)iterator.next();
                if (product.getProductName().equalsIgnoreCase(productName)) {
                    iterator.remove();
                    System.out.println("Product removed from cart successfully.");
                    removed = true;
                    break;
                }
            }

            if (!removed) {
                System.out.println("Product not found in cart.");
            }
        } catch (InputMismatchException var4) {
            System.out.println("Invalid input. Please enter the correct data type.");
            scanner.nextLine();
        }

    }

    private static void calculateCartTotal() {
        try {
            double total = 0.0;

            Features product;
            for(Iterator var2 = shoppingCart.iterator(); var2.hasNext(); total += product.getPrice()) {
                product = (Features)var2.next();
            }

            System.out.println("Total price of items in cart: " + total);
        } catch (InputMismatchException var4) {
            System.out.println("Invalid input. Please enter the correct data type.");
            scanner.nextLine();
        }

    }

    private static void processOrder() {
    }

    private static void displayProductDetails(Features product) {
        PrintStream var10000 = System.out;
        String var10001 = product.getProductName();
        var10000.println("Product Name: " + var10001);
        var10000 = System.out;
        var10001 = product.getManufacturer();
        var10000.println("Manufacturer: " + var10001);
        var10000 = System.out;
        var10001 = product.getModelNumber();
        var10000.println("Model Number: " + var10001);
        var10000 = System.out;
        var10001 = product.getSerialNumber();
        var10000.println("Serial Number: " + var10001);
        var10000 = System.out;
        var10001 = product.getProductDescription();
        var10000.println("Product Description: " + var10001);
        var10000 = System.out;
        double var1 = product.getPrice();
        var10000.println("Price: " + var1);
        System.out.println("Availability: " + (product.isAvailability() ? "Available" : "Not Available"));
        System.out.println("Categories: " + String.valueOf(product.getCategories()));
        System.out.println("Inventory Quantity: " + product.getInventoryQuantity());
        System.out.println("Previous Versions: " + String.valueOf(product.getPreviousVersions()));
        System.out.println("Related Products: " + String.valueOf(product.getRelatedProducts()));
    }

    static {
        scanner = new Scanner(System.in);
        running = false;
        shoppingCart = new LinkedList();
        productCatalog = new ProductCatalog();
    }
}