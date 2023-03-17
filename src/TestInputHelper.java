import org.junit.Assert;
import org.junit.Test;

import java.util.Scanner;

import org.mockito.Mockito;

public class TestInputHelper {
    @Test
    public void testReadIntWithinRange() throws Exception {
        // given
        Scanner scanner = new Scanner("75\n");
        InputHelper inputHelper = new InputHelper(scanner);
        // when
        int value = inputHelper.readInt(50, 100, "out of range");
        // then
        Assert.assertEquals(75, value);
    }

    @Test
    public void testReadIntWithinRange2() throws Exception {
        // given
        Scanner scanner = Mockito.mock(Scanner.class);
        Mockito.when(scanner.nextInt()).thenReturn(75).thenReturn(50).thenReturn(100);
        InputHelper inputHelper = new InputHelper(scanner);
        // when
        int value = inputHelper.readInt(50, 100, "out of range");
        // then
        Assert.assertEquals(75, value);
        Mockito.verify(scanner, Mockito.times(1)).nextLine();

        value = inputHelper.readInt(50, 100, "out of range");
        Assert.assertEquals(50, value);
        value = inputHelper.readInt(50, 100, "out of range");
        Assert.assertEquals(100, value);

        Mockito.verify(scanner, Mockito.times(3)).nextLine();
    }

    @Test(expected = IllegalStateException.class)
    public void testIOError() throws Exception {
        // given
        Scanner scanner = Mockito.mock(Scanner.class);
        // simuliert Exception beim Aufruf von nextInt()
        Mockito.when(scanner.nextInt()).thenThrow(new IllegalStateException());
        InputHelper inputHelper = new InputHelper(scanner);

        // when
        int value = inputHelper.readInt(50, 100, "out of range");

        // never reached
    }
}
