import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.Map;
import java.util.Scanner;

public class PurchaseManager {

    private final InputHelper inputHelper;
    private final PrintWriter out;

    public PurchaseManager(Scanner scanner, PrintWriter out) {
        inputHelper = new InputHelper(scanner);
        this.out = out;
    }

    public void addPurchase(Map<Integer, Purchase> purchases, Map<String, Supplier> suppliers) throws Exception {
        out.println("*** Add Purchase ***");

        out.println("Enter Purchase Number: ");
        int purchaseNo = inputHelper.readInt(1, 999, "Invalid purchase Number");
        if (purchases.containsKey(purchaseNo)) {
            throw new Exception("Purchase order already exists");
        }

        out.println("Enter TRN No.");
        int trn_number = inputHelper.readInt(100000, 999999, "TRN number should be of 6 digits");

        out.println("*Date*");
        LocalDate purchaseDate = inputHelper.readDate("Purchase Date");

        out.println("Enter Supplier ID");
        String id = inputHelper.readString("Supplier cannot be empty/blank");
        if (!suppliers.containsKey(id)) {
            throw new Exception("Supplier does not exist");
        }

        out.println("Enter Item No : ");
        int itemNo = inputHelper.readInt(1, 999999, "Unsuccessful. ItemNo field needs to be between 1..999999");
        out.println("Enter item quantity : ");
        int quantity = inputHelper.readInt(1, 999999, "Unsuccessful. Quantity needs to be between 1..999999");
        Item itemObject = new Item(itemNo, quantity);

        out.println("Enter Payment Mode : ");
        String mode = inputHelper.readString("Unsuccessful. Mode of payment cannot be blank/empty");
        if (!(mode.equals("card") || mode.equals("cheque") || mode.equals("bank transfer"))) {
            throw new Exception("Unsuccessful. Mode of payment should be either of card / cheque / bank transfer");
        }

        out.println("*Payment Due Date*");
        LocalDate purchaseDueDate = inputHelper.readDate("Payment Due Date");
        if (purchaseDueDate.isBefore(purchaseDate)) {
            throw new Exception("Unsuccessful. Purchase date should be before the Payment Due Date.");
        }

        out.println("Enter total cost : ");
        double cost = inputHelper.readDouble();
        double vat = 0.05 * cost;

        purchases.put(purchaseNo, new Purchase(
                purchaseNo, trn_number, purchaseDate, suppliers.get(id),
                itemObject, mode, purchaseDueDate, cost, vat));
    }

    public boolean removePurchase(Map<Integer, Purchase> purchases) throws Exception {
        out.println("*** Remove Purchase ***");
        out.println("Enter Purchase Number");
        int purchaseId = inputHelper.readInt(1, 999, "Purchase number must be between 1..999");
        if (purchases.containsKey(purchaseId)) {
            purchases.remove(purchaseId);
            out.println("Purchase order No " + purchaseId + " Deleted\n");
            return true;
        }
        out.println("Unsuccessful. Purchase order does not exist");
        return false;
    }

    public void viewPurchase(Map<Integer, Purchase> purchases) throws Exception {
        out.println("*** View Purchase ***");
        out.println("Enter Purchase Number");
        int purchaseNo = inputHelper.readInt(1, 999, "Purchase number must be between 1..999");
        if (purchases.containsKey(purchaseNo)) {
            out.println("Purchase Information");
            out.println(purchases.get(purchaseNo));
            return;
        }
        out.println("Unsuccessful. Purchase order does not exist");
    }
}
