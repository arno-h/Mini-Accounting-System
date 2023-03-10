import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class PurchaseManager {

    private Scanner scanner;

    public PurchaseManager() {
        scanner = new Scanner(System.in);
    }

    public Purchase addPurchase(ArrayList<Purchase> purchases, ArrayList<Supplier> suppliers) throws Exception {
        InputHelper inputHelper = new InputHelper();

        System.out.println("*** Add Purchase ***");

        System.out.println("Enter Purchase Number: ");
        int purchaseNo = inputHelper.readInt(1, 999, "Invalid purchase Number");
        for (Purchase purchase : purchases) {
            if (purchase.getPurchaseNo() == purchaseNo) {
                throw new Exception("Purchase order already exists");
            }
        }

        System.out.println("Enter TRN No.");
        int trn_number = inputHelper.readInt(100000, 999999, "TRN number should be of 6 digits");

        System.out.println("*Date*");
        Date purchaseDate = inputHelper.readDate("Purchase Date");

        System.out.println("Enter Supplier ID");
        String id = inputHelper.readString("Supplier cannot be empty/blank");
        int count = 0;
        for (Supplier supplier : suppliers) {
            if ((supplier.getSupplierId().equals(id))) {
                break;
            }
            count++;
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
        Date purchaseDueDate = inputHelper.readDate("Payment Due Date");
        if (purchaseDueDate.before(purchaseDate)) {
            throw new Exception("Unsuccessful. Purchase date should be before the Payment Due Date.");
        }

        System.out.println("Enter total cost : ");
        double cost = inputHelper.readDouble();
        double vat = 0.05 * cost;

        return new Purchase(
                purchaseNo, trn_number, purchaseDate, suppliers.get(count),
                itemObject, mode, purchaseDueDate, cost, vat);
    }

    public int removePurchase(ArrayList<Purchase> list) {
        System.out.println("*** Remove Purchase ***");
        System.out.println("Enter Purchase Number");

        scanner.nextLine();
        String number = scanner.nextLine();
        //If input empty
        if (number.isEmpty() || number.equals(" ")) {
            System.out.println("Unsuccessful. Cannot leave Purchase No field empty");
            return -1;
        }

        try {
            int temp = Integer.parseInt(number);

            //Wrong Purchase Number format (should be a 3-digit number
            if (temp > 999) {
                System.out.println("Unsuccessful. Invalid purchase Number");
                return -1;
            }

            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).getPurchaseNo() == temp) {
                    System.out.println("Purchase order No " + temp + " Deleted\n");
                    return i;
                }
            }
            System.out.println("Unsuccessful. Purchase order does not exist");
        }
        //If String given for Purchase Number
        catch (NumberFormatException e) {
            System.out.println("Unsuccessful. Invalid purchase Number Format");
        }

        return -1;
    }

    public void viewPurchase(ArrayList<Purchase> list) {
        System.out.println("*** View Purchase ***");
        System.out.println("Enter Purchase Number");

        scanner.nextLine();
        String number = scanner.nextLine();

        //If input empty
        if (number.isEmpty() || number.equals(" ")) {
            System.out.println("Unsuccessful. Cannot leave Purchase No field empty");
            return;
        }

        try {
            int temp = Integer.parseInt(number);

            //Wrong Purchase Number format (should be a 3-digit number
            if (temp > 999) {
                System.out.println("Unsuccessful. Invalid purchase Number");
                return;
            }

            for (Purchase purchase : list) {
                if (purchase.getPurchaseNo() == temp) {
                    System.out.println("Purchase Information");
                    System.out.println(purchase.toString());
                    return;
                }
            }
            System.out.println("Unsuccessful. Purchase order does not exist");
        }
        //If String given for Purchase Number
        catch (NumberFormatException e) {
            System.out.println("Unsuccessful. Invalid purchase Number Format");
        }
    } //Function View Purchase End
}
