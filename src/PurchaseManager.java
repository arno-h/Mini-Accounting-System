import java.time.LocalDate;
import java.util.Map;

public class PurchaseManager {

    private final InputHelper inputHelper;

    public PurchaseManager() {
        inputHelper = new InputHelper();
    }

    public void addPurchase(Map<Integer, Purchase> purchases, Map<String, Supplier> suppliers) throws Exception {
        System.out.println("*** Add Purchase ***");

        System.out.println("Enter Purchase Number: ");
        int purchaseNo = inputHelper.readInt(1, 999, "Invalid purchase Number");
        if (purchases.containsKey(purchaseNo)) {
            throw new Exception("Purchase order already exists");
        }

        System.out.println("Enter TRN No.");
        int trn_number = inputHelper.readInt(100000, 999999, "TRN number should be of 6 digits");

        System.out.println("*Date*");
        LocalDate purchaseDate = inputHelper.readDate("Purchase Date");

        System.out.println("Enter Supplier ID");
        String id = inputHelper.readString("Supplier cannot be empty/blank");
        if (!suppliers.containsKey(id)) {
            throw new Exception("Supplier does not exist");
        }

        System.out.println("Enter Item No : ");
        int itemno = inputHelper.readInt(1, 999999, "Unsuccessful. ItemNo field needs to be between 1..999999");
        System.out.println("Enter item quantity : ");
        int quantity = inputHelper.readInt(1, 999999, "Unsuccessful. Quantity needs to be between 1..999999");
        Item itemObject = new Item(itemno, quantity);

        System.out.println("Enter Payment Mode : ");
        String mode = inputHelper.readString("Unsuccessful. Mode of payment cannot be blank/empty");
        if (!(mode.equals("card") || mode.equals("cheque") || mode.equals("bank transfer"))) {
            throw new Exception("Unsuccessful. Mode of payment should be either of card / cheque / bank transfer");
        }

        System.out.println("*Payment Due Date*");
        LocalDate purchaseDueDate = inputHelper.readDate("Payment Due Date");
        if (purchaseDueDate.isBefore(purchaseDate)) {
            throw new Exception("Unsuccessful. Purchase date should be before the Payment Due Date.");
        }

        System.out.println("Enter total cost : ");
        double cost = inputHelper.readDouble();
        double vat = 0.05 * cost;

        purchases.put(purchaseNo, new Purchase(
                purchaseNo, trn_number, purchaseDate, suppliers.get(id),
                itemObject, mode, purchaseDueDate, cost, vat));
    }

    public void removePurchase(Map<Integer, Purchase> purchases) throws Exception {
        System.out.println("*** Remove Purchase ***");
        System.out.println("Enter Purchase Number");
        int purchaseId = inputHelper.readInt(1, 999, "Purchase number must be between 1..999");
        if (purchases.containsKey(purchaseId)) {
            purchases.remove(purchaseId);
            System.out.println("Purchase order No " + purchaseId + " Deleted\n");
        }
        System.out.println("Unsuccessful. Purchase order does not exist");
    }

    public void viewPurchase(Map<Integer, Purchase> purchases) throws Exception {
        System.out.println("*** View Purchase ***");
        System.out.println("Enter Purchase Number");
        int purchaseNo = inputHelper.readInt(1, 999, "Purchase number must be between 1..999");
        if (purchases.containsKey(purchaseNo)) {
            System.out.println("Purchase Information");
            System.out.println(purchases.get(purchaseNo));
            return;
        }
        System.out.println("Unsuccessful. Purchase order does not exist");
    }
}
