import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class TestPurchaseManager {

    private Map<Integer, Purchase> purchaseMap;

    @Before
    public void setupMap() {
        purchaseMap = new HashMap<>();
        purchaseMap.put(1, new Purchase(1, 123, LocalDate.now(),
                new Supplier(), new Item(), "card", LocalDate.now(),
                100.0, 20.0));
        purchaseMap.put(65, new Purchase(65, 123, LocalDate.now(),
                new Supplier(), new Item(), "card", LocalDate.now(),
                100.0, 20.0));
    }

    @Test
    public void testRemovePurchase() throws Exception {
        // given
        Scanner scanner = new Scanner("65\n");
        PrintWriter printWriter = new PrintWriter(OutputStream.nullOutputStream());
        PurchaseManager purchaseManager = new PurchaseManager(scanner, printWriter);
        // when
        boolean result = purchaseManager.removePurchase(purchaseMap); // remove 65
        // then
        Assert.assertTrue(result);
        Assert.assertFalse(purchaseMap.containsKey(65));
        Assert.assertTrue(purchaseMap.containsKey(1));
    }

    @Test
    public void testRemovePurchaseFails() throws Exception {
        // given
        Scanner scanner = new Scanner("42\n");
        StringWriter out = new StringWriter();
        PrintWriter printWriter = new PrintWriter(out);
        PurchaseManager purchaseManager = new PurchaseManager(scanner, printWriter);
        // when
        boolean result = purchaseManager.removePurchase(purchaseMap); // try to remove 42
        // then
        Assert.assertFalse(result);
        String message = out.toString();
        Assert.assertTrue(message.contains("Unsuccessful."));
    }
}
