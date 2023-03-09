import java.util.*;

public class MainClass {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) throws Exception {

        Item i1 = new Item(100, 20);

        Supplier supp1 = new Supplier("supp1001", "ABC", "0502375262", "abc@abc.com", 1001, 789);
        Purchase pur1 = new Purchase(100, 2000, new Date(2002, 12, 23), supp1, i1, "Card", new Date(2013, 26, 12), 800.36, 10.45);

        ArrayList<Supplier> supplierList = new ArrayList<>();
        ArrayList<Purchase> purchaseList = new ArrayList<>();
        purchaseList.add(pur1);
        supplierList.add(supp1);

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
                        Purchase p1 = purchaseManager.addPurchase(purchaseList, supplierList);
                        purchaseList.add(p1); // remove if not call by refernece
                        break;
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                }

                case 2:
                    int index = purchaseManager.removePurchase(purchaseList);
                    if (index == -1) {
                        //System.out.println("Unsuccessful. Purchase order does not exist");
                    } else purchaseList.remove(index);
                    break;

                case 3:
                    purchaseManager.viewPurchase(purchaseList);
                    break;

                case 4: {
                    try {
                        Supplier s1 = SupplierManager.addSupplier(supplierList);
                        supplierList.add(s1); // remove if not call by refernece
                        break;
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                }

                case 5:
                    int pos = SupplierManager.deleteSupplier(supplierList);
                    if (pos == -1) {
                        System.out.println("Supplier Doesnt Exist");
                    } else supplierList.remove(pos);
                    break;

                case 6:
                    SupplierManager.viewSupplier(supplierList);
                    break;

                default:
                    System.out.println("Enter a value between 1-6");
                    System.exit(0);
            } //switch end
        } //while end
    } //Main End

} //Class end
