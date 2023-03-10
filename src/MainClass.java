import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class MainClass {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) throws Exception {

        Item i1 = new Item(100, 20);

        Supplier supp1 = new Supplier("supp1001", "ABC", "0502375262", "abc@abc.com", 1001, 789);
        Purchase pur1 = new Purchase(100, 2000, LocalDate.of(2002, 12, 23), supp1, i1, "Card", LocalDate.of(2013, 12, 26), 800.36, 10.45);

        Map<String, Supplier> suppliers = new HashMap<>();
        Map<Integer, Purchase> purchases = new HashMap<>();
        purchases.put(pur1.getPurchaseNo(), pur1);
        suppliers.put(supp1.getSupplierId(), supp1);

        System.out.println("""
                Accounting System
                *******Purchases*******
                \t1. Add purchase
                \t2. Remove purchase
                \t3. View purchase
                *******Supplier*******
                \t4. Add Supplier
                \t5. Remove Supplier
                \t6. View Supplier
                """);
        int choice = 0;

        PurchaseManager purchaseManager = new PurchaseManager();

        while (true) {
            System.out.println("Enter your choice (1-6)");
            choice = sc.nextInt();
            switch (choice) {
                case 1: {
                    try {
                        Purchase purchase = purchaseManager.addPurchase(purchases, suppliers);
                        purchases.put(purchase.getPurchaseNo(), purchase);
                        break;
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                }

                case 2:
                    int index = purchaseManager.removePurchase(purchases);
                    if (index != -1) {
                        purchases.remove(index);
                    }
                    break;

                case 3:
                    purchaseManager.viewPurchase(purchases);
                    break;

                case 4: {
                    try {
                        Supplier supplier = SupplierManager.addSupplier(suppliers);
                        suppliers.put(supplier.getSupplierId(), supplier);
                        break;
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                }

                case 5:
                    String id = SupplierManager.deleteSupplier(suppliers);
                    if (id == null) {
                        System.out.println("Supplier Doesnt Exist");
                    } else suppliers.remove(id);
                    break;

                case 6:
                    SupplierManager.viewSupplier(suppliers);
                    break;

                default:
                    System.out.println("Enter a value between 1-6");
                    System.exit(0);
            }
        }
    }
}
