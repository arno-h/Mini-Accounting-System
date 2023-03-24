import java.util.Map;
import java.util.Scanner;

public class SupplierManager {
    public static Supplier addSupplier(Map<String, Supplier> suppliers) throws Exception {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the supplier details: ");
        System.out.println("Enter supplierID: ");
        String suppID = sc.nextLine();

        if (suppliers.containsKey(suppID)) {
            throw new IdDoesNotExistException("Error: Supplier not added, SupplierID already exists in the system.");
        }

        if (suppID.isEmpty() || suppID.equals(" ")) {
            throw new Exception("Error: The Supplier ID input field cannot be empty/blank");

        }

        System.out.println("Enter companyName: ");

        String companyName = sc.nextLine();

        if (companyName.isEmpty() || companyName.equals(" ")) {
            throw new Exception("Error: Supplier not added, Company Name is left blank");

        }

        System.out.println("Enter Contact Number: ");
        String number = sc.nextLine();

        if (number.isEmpty() || number.equals(" ")) {
            throw new Exception("Error: Supplier not added, Contact Details Number is left blank.");

        }

        if (!(number.startsWith("05")) || number.length() != 10) {
            throw new Exception("Error: Supplier not added, Number needs to be of the format “05XXXXXXXX” where X are numbers.");
        }

        System.out.println("Enter email: ");
        String email = sc.nextLine();

        if (email.isEmpty() || email.equals(" ")) {
            throw new Exception("Error: Supplier not added, Email is left blank.");

        }

        int atCount = 0;
        int atIndex = 0;
        for (int i = 0; i < email.length(); i++) {
            if (email.charAt(i) == '@') {
                atCount++;
                atIndex = i;
                break;

            }

        }

        if (atCount != 1 || !(email.substring(atIndex).contains(".")))
            throw new Exception("Error: Supplier not added, email isn’t in the correct format.The prefix appears to the left of the @ symbol. The domain appears to the right of the @ symbol");

        System.out.println("Enter tradeLicenseNo: ");
        int tradeLicenseNo = sc.nextInt();

        String textTradeLicense = Integer.toString(tradeLicenseNo);

        if (textTradeLicense.isEmpty() || textTradeLicense.equals(" ")) {
            throw new Exception("Error: Supplier not added, Trade Licence No. is left blank.");
        }

        int noOfDigitsTL = String.valueOf(tradeLicenseNo).length();

        if (noOfDigitsTL != 6)
            throw new Exception("Error: Supplier not added, Trade License number needs to be a 6 digit number.");

        System.out.println("Enter VAT registration Number: ");
        int vatRn = sc.nextInt();
        String vatText = Integer.toString(vatRn);

        if (vatText.isEmpty() || vatText.equals(" ")) {
            throw new Exception("Error: Supplier not added, VAT Registration Number is left blank");

        }

        int noOfDigitsVAT = String.valueOf(vatRn).length();

        if (noOfDigitsVAT != 7) {
            throw new Exception("Error: Supplier not added, VAT RN needs to be a 7 digit number.");
        }

        Supplier s1 = new Supplier(suppID, companyName, number, email, tradeLicenseNo, vatRn);
        suppliers.put(suppID, s1);
        System.out.println("Supplier added successfully");
        return s1;

    }

    public static String deleteSupplier(Map<String, Supplier> suppliers) {
        System.out.println("*** delete Supplier ***");
        System.out.println("Enter Supplier ID");
        MainClass.sc.nextLine();
        String id = MainClass.sc.nextLine();

        if (id.isEmpty() || id.equals(" ")) {
            System.out.println("Error: The input field cannot be empty/blank.");
            return null;
        }

        if (suppliers.containsKey(id)) {
            System.out.println("Supplier successfully deleted");
            return id;
        }

        System.out.println("Supplier does not exist in the database so it isn't deleted.");
        return null;
    }

    public static void viewSupplier(Map<String, Supplier> suppliers) {
        System.out.println("*** View Supplier ***");
        System.out.println("Enter Supplier ID");
        MainClass.sc.nextLine();
        String id = MainClass.sc.nextLine();

        if (id.isEmpty() || id.equals(" ")) {
            System.out.println("Error: No supplier details displayed, the input field cannot be empty/blank");
            return;
        }

        if (suppliers.containsKey(id)) {
            System.out.println("Supplier Information");
            System.out.println(suppliers.get(id));
            return;
        }
        System.out.println("Read/View Unsuccessful: Supplier ID does not exist");
    }
}
