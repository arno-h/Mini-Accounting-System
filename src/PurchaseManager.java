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
        // purchase number
        scanner.nextLine();

        int purchaseNo = inputHelper.readInt(1, 999,
                "Unsuccessful. Invalid purchase Number");
        for (Purchase purchase : purchases) {
            if (purchase.getPurchaseNo() == purchaseNo) {
                throw new Exception("Unsuccessful. Purchase order already exists");
            }
        }

        System.out.println("Enter TRN No.");
        int trn_number = inputHelper.readInt(100000, 999999,
                "Unsuccessful. TRN number should be of 6 digits");

        // Date
        System.out.println("*Date*");

        System.out.println("Enter Day: ");
        int day = inputHelper.readInt(1, 31, "Unsuccessful. Invalid purchase date.");
        System.out.println("Enter Month: ");
        int month = inputHelper.readInt(1, 12, "Unsuccessful. Invalid purchase date.");
        System.out.println("Enter Year: ");
        int year = inputHelper.readInt(2000, 2100, "Unsuccessful. Invalid purchase date.");

        Date purchaseDate = new Date(year, month, day);

        // Supplier ID
        System.out.println("Enter Supplier ID");
        scanner.nextLine();
        String id = scanner.nextLine();

        //If supplier field is empty
        if (id.isEmpty() || id.equals(" ")) {
            throw new Exception("Error: No supplier details displayed, the input field cannot be empty/blank");
        }
        int count = 0;
        for (Supplier supplier : suppliers) {
            count++;
            if ((supplier.getSupplierId().equals(id))) {
                break;
            }
        }

        // Item No
        System.out.println("Enter Item No : ");
        int itemno = inputHelper.readInt(1, 999999,
                "Adding Purchase Unsuccessful. ItemNo field is empty");

        //Item quanitity
        System.out.println("Enter item quantity : ");
        int quantity = inputHelper.readInt(1, 999999,
                "Unsuccessful. Quantity should be in numerical values");

        // Item object creation
        Item itemObject = new Item(itemno, quantity);

        //Payment mode
        System.out.println("Enter Payment Mode : ");
        String mode = scanner.nextLine();
        if (mode.isEmpty() || mode.equals(" ")) {
            throw new Exception("Unsuccessful. Mode of payment should be entered (Blank/Empty)");
        }
        if (!(mode.equals("card") || mode.equals("cheque") || mode.equals("bank transfer"))) {
            throw new Exception("Unsuccessful. Mode of payment should be either of card / cheque / bank transfer");
        }

        //Payment Due Date
        System.out.println("*Payment Due Date*");
        System.out.println("Enter Day: ");
        day = inputHelper.readInt(1, 31, "Unsuccessful. Invalid purchase date.");
        System.out.println("Enter Month: ");
        month = inputHelper.readInt(1, 12, "Unsuccessful. Invalid purchase date.");
        System.out.println("Enter Year: ");
        year = inputHelper.readInt(2000, 2100, "Unsuccessful. Invalid purchase date.");
        Date purchaseDueDate = new Date(year, month, day);
        if (purchaseDueDate.before(purchaseDate)) {
            throw new Exception("Unsuccessful. Purchase date should be before the Payment Due Date.");
        }

        //total cost
        System.out.println("Enter total cost : ");
        scanner.nextLine();
        String tempcost = scanner.nextLine();

        if (tempcost.isEmpty() || tempcost.equals(" ")) {
            throw new Exception("Unsuccessful. Cost of the PO should be entered");
        }
        double cost = Double.parseDouble(tempcost);

        // vat amount
        double vat = 0.05 * cost;

        // newPurchase Creation
        Purchase newPurchase = new Purchase(purchaseNo, trn_number, purchaseDate, suppliers.get(count), itemObject, mode, purchaseDueDate, cost, vat);
        return newPurchase;
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
