import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class TestPurchaseManager {
    @Test
    public void testRemovePurchase() throws Exception {
        // given
        Scanner scanner = new Scanner("65\n");
        PurchaseManager purchaseManager = new PurchaseManager(scanner);
        Map<Integer, Purchase> purchaseMap = new HashMap<>();
        purchaseMap.put(1, new Purchase(1, 123, LocalDate.now(),
                new Supplier(), new Item(), "card", LocalDate.now(),
                100.0, 20.0));
        purchaseMap.put(65, new Purchase(65, 123, LocalDate.now(),
                new Supplier(), new Item(), "card", LocalDate.now(),
                100.0, 20.0));
        // when
        purchaseManager.removePurchase(purchaseMap); // remove 65
        // then
        Assert.assertFalse(purchaseMap.containsKey(65));
        Assert.assertTrue(purchaseMap.containsKey(1));
    }
}
