import java.util.*;

public class MainClass {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) throws Exception {

        Item i1 = new Item(100,20);
        Item i2 = new Item(200,30);
        Item i3 = new Item(300,10);
        Item i4 = new Item(400,15);

        ArrayList<Item> items = new ArrayList<>();
        items.add(i1); items.add(i2); items.add(i3); items.add(i4);

        Supplier supp1 = new Supplier(  "supp101","ABC","0502375262","abc@abc.com",1001,789);
        Purchase pur1 = new Purchase(700,2000,new Date(2002,12,23),supp1,items,"Card",new Date(2013,26,12),800.36,10.45);

        ArrayList<Supplier> supplierList = new ArrayList<>();
        supplierList.add(supp1);
        ArrayList<Purchase> purchaseList = new ArrayList<>();
        purchaseList.add(pur1);

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
        int kk = 0;
        while (kk++ < 3) {
            int choice;
            System.out.println("Enter your choice (1-6)");
            choice = sc.nextInt();
            ArrayList<Supplier> supplist = new ArrayList<>();
            switch (choice) {
                case 1:
                    addPurchase(purchaseList, supplierList);
                    break;
                case 2:
                    int index = removePurchase(purchaseList);
                    if (index == -1) {
                        System.out.println("Purchase Number Doesnt Exist");
                    } else purchaseList.remove(index);
                    break;
                case 3:
                    viewPurchase(purchaseList);
                    break;
                case 4: {
                    Supplier s1 = addSupplier(supplist);
                    supplist.add(s1); // remove if not call by refernece
                    break;
                }

                case 5:
                    int pos = deleteSupplier(supplierList);
                    if (pos == -1) {
                        System.out.println("Supplier Doesnt Exist");
                    } else supplierList.remove(pos);

                    break;
                case 6:
                    viewSupplier(supplierList);
                    break;
                default:
                    System.out.println("Enter a value between 1-6");
                    break;
            }//switch end
        }//while end
    }

    /*
    Purchase
     - Successful
     - Unsuccessful. Purchase order details already exists (id exists)
     - Unsuccessful. Purchase ID should be entered (Blank/Empty)
     - Unsuccessful. Purchase date should be before the payment due date.
     - Unsuccessful. Mode of payment should be entered (Blank/Empty)
     - Unsuccessful. Mode of payment should be either of card / cheque / bank transfer
     - Unsuccessful. Invalid purchase date.
     - Unsuccessful. Quantity should be in numerical values
     - Unsuccessful. Quantity should be entered
     - Unsuccessful. TRN number should be of 6 digits
     - Unsuccessful. Supplier ID should be entered
     - Unsuccessful. Item Number should be present
     - Unsuccessful. Cost of the PO should be entered
     - Unsuccessful. Payment due date should be entered
     */

    public static void addPurchase(ArrayList<Purchase> list, ArrayList<Supplier> list2) {
        System.out.println("*** Add Purchase ***");
        System.out.println("Enter Purchase Number: ");
        // purchase number
        sc.nextLine();
        String number = sc.nextLine();

        //If input empty
        if (number.isEmpty() || number.equals(" ")) {
            System.out.println("Unsuccessful. Cannot leave Purchase No field empty");
            return;
        }

        try {
            int purchaseNo = Integer.parseInt(number);

            //Wrong Purchase Number format (should be a 3-digit number)
            if (purchaseNo > 999) {
                System.out.println("Unsuccessful. Invalid purchase Number");
                return;
            }

            for (Purchase purchase : list) {
                if (purchase.getPurchaseNo() == purchaseNo) {
                    System.out.println("Unsuccessful. Purchase order already exists");
                    return;
                }
            }
        }
        //If String given for Purchase Number
        catch (java.lang.NumberFormatException e) {
            System.out.println("Unsuccessful. Invalid purchase Number Format");
        }
        // purchase number

        // TRN no.
        System.out.println("Enter TRN No.");
        sc.nextLine();
        int trn_number = sc.nextInt();
        if (trn_number <= 0) {
            System.out.println("Unsuccessful. TRN number should be of 6 digits");
            return;
        }
        int noOfDigits = String.valueOf(trn_number).length();
        if (noOfDigits != 6) {
            System.out.println("Unsuccessful. TRN number should be of 6 digits");
            return;
        }
        //TRN no.

        // Date
        System.out.println("*Date*");
        System.out.println("Enter Day: ");
        sc.nextLine();
        int day = sc.nextInt();

        System.out.println("Enter Month: ");
        int month = sc.nextInt();

        System.out.println("Enter Year: ");
        int year = sc.nextInt();
        // Date

        // Supplier ID
        System.out.println("Enter Supplier ID");
        sc.nextLine();
        String id = sc.nextLine();

        //If supplier field is empty
        if(id.isEmpty() || id.equals(" ")) {
            System.out.println("Error: No supplier details displayed, the input field cannot be empty/blank");
            return;
        }

        for (Supplier supplier : list2) {
            if (supplier.getSupplierId().equals(id)) {
                System.out.println("Read/View Unsuccessful: Supplier ID does not exist");
                return;
            }
        }

        // Item No
        System.out.println("Enter Item No : ");
        String itemno = sc.nextLine();

        //If input empty
        if (itemno.isEmpty() || itemno.equals(" ")) {
            System.out.println("Adding Purchase Unsuccessful. ItemNo field is empty");
            return;
        }

        //Item quanitity
        System.out.println("Enter Item quantity : ");
        sc.nextLine();
        String quanitity_temp = sc.next();

        if (quanitity_temp.isEmpty() || quanitity_temp.equals(" ")) {
            System.out.println("Unsuccessful. Cannot leave Purchase No field empty");
            return;
        }

        try {
            int quantity = Integer.parseInt(quanitity_temp);

        }
        //If String given for Purchase Number
        catch (java.lang.NumberFormatException e) {
            System.out.println("Unsuccessful. Quantity should be in numerical values");
        }



        //Payment mode
        System.out.println("Enter Payment Mode : ");
        String mode = sc.nextLine();

        //Payment Due Date
        System.out.println("Enter Payment Due Date : ");
        int due_day = sc.nextInt();
        int due_month = sc.nextInt();
        int due_year = sc.nextInt();

        //total cost
        System.out.println("Enter total cost : " );
        double cost = sc.nextDouble();

        double VAT = 0.05*cost;
    }


    public static int removePurchase(ArrayList<Purchase> list) {
        System.out.println("*** Remove Purchase ***");
        System.out.println("Enter Purchase Number");

        sc.nextLine();
        String number = sc.nextLine();
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

            for (int i=0; i<list.size(); i++) {
                if(list.get(i).getPurchaseNo()==temp)
                {
                    System.out.println("Purchase order No "+temp+" Deleted\n");
                    return i;
                }
            }
            System.out.println("Unsuccessful. Purchase order does not exist");
        }
        //If String given for Purchase Number
        catch (java.lang.NumberFormatException e) {
            System.out.println("Unsuccessful. Invalid purchase Number Format");
        }

        return -1;
    }

    public static void viewPurchase(ArrayList<Purchase> list) {
        System.out.println("*** View Purchase ***");
        System.out.println("Enter Purchase Number");

        sc.nextLine();
        String number = sc.nextLine();

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
        catch (java.lang.NumberFormatException e) {
            System.out.println("Unsuccessful. Invalid purchase Number Format");
        }
    }

    public static Supplier addSupplier(ArrayList<Supplier> suparr) throws Exception{
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the supplier details: ");
        System.out.println("Enter supplierID: ");
        String suppID = sc.nextLine();

        for(int i=0;i<suparr.size();i++) {
            try {
                if (suparr.get(i).getSupplierId() == suppID) {
                    throw new IdDoesNotExistException("Id already exists");
                }


            }
            catch(IdDoesNotExistException e){
                System.out.println(e.toString());
            }
        }

        //If supplier field is empty
        try {
            if (suppID.isEmpty() || suppID.equals(" ")) {
                throw new Exception("Error: The Supplier ID input field cannot be empty/blank");


            }
        }
        catch(Exception e){
            System.out.println(e.toString());
        }

        System.out.println("Enter companyName: ");


        String companyName= sc.nextLine();
            try {
                if (companyName.isEmpty() || companyName.equals(" ")) {
                    throw new Exception("Error: The company name input field cannot be empty/blank");


                }
            }
            catch(Exception e){
                System.out.println(e.toString());
            }
        System.out.println("Enter Contact Number: ");
         String number= sc.nextLine();

            try {
                if (number.isEmpty() || number.equals(" ")) {
                    throw new Exception("Error: The company name input field cannot be empty/blank");


                }
            }
            catch(Exception e){
                System.out.println(e.toString());
            }


        System.out.println("Enter email: ");
         String email= sc.nextLine();


            try {
                if (email.isEmpty() || email.equals(" ")) {
                    throw new Exception("Error: The email input field cannot be empty/blank");


                }
            }
            catch(Exception e){
                System.out.println(e.toString());
            }
        System.out.println("Enter tradeLicenseNo: ");
         int tradeLicenseNo = sc.nextInt();

         String texttradeLicense = Integer.toString(tradeLicenseNo);
            try {
                if (texttradeLicense.isEmpty() || texttradeLicense.equals(" ")) {
                    throw new Exception("Error: The trade license Number input field cannot be empty/blank");


                }
            }
            catch(Exception e){
                System.out.println(e.toString());
            }
        System.out.println("Enter VAT registration Number: ");
         int vatRn = sc.nextInt();
         String vattext = Integer.toString(vatRn);
        try {
            if (vattext.isEmpty() || vattext.equals(" ")) {
                throw new Exception("Error: The VAT Rno Number input field cannot be empty/blank");


            }
        }
        catch(Exception e){
            System.out.println(e.toString());
        }

         Supplier s1 = new Supplier(suppID,companyName, number, email, tradeLicenseNo, vatRn);
         suparr.add(s1);
         System.out.println("Supplier added successfully");
         return s1;

    }

    /*
    Delete Supplier
     - Supplier successfully deleted
     - Supplier does not exist in the database so it isn't deleted.
     - Error: The input field cannot be empty/blank.
     */
    public static int deleteSupplier(ArrayList<Supplier> list) {
        System.out.println("*** delete Supplier ***");
        System.out.println("Enter Supplier ID");
        sc.nextLine();
        String id = sc.nextLine();

        //If supplier field is empty
        if(id.isEmpty() || id.equals(" ")) {
            System.out.println("Error: The input field cannot be empty/blank.");
            return -1;
        }

        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getSupplierId().equals(id)) {
                System.out.println("Supplier successfully deleted");
                return i;
            }
        }

        System.out.println("Supplier does not exist in the database so it isn't deleted.");
        return -1;
    }

    public static void viewSupplier(ArrayList<Supplier> list) {
        System.out.println("*** View Supplier ***");
        System.out.println("Enter Supplier ID");
        sc.nextLine();
        String id = sc.nextLine();

        //If supplier field is empty
        if(id.isEmpty() || id.equals(" ")) {
            System.out.println("Error: No supplier details displayed, the input field cannot be empty/blank");
            return;
        }

        for (Supplier supplier : list) {
            if (supplier.getSupplierId().equals(id)) {
                System.out.println("Supplier Information");
                System.out.println(supplier.toString());
                return;
            }
        }

        System.out.println("Read/View Unsuccessful: Supplier ID does not exist");
    }
}