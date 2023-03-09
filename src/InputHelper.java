import java.util.Scanner;

public class InputHelper {
    private Scanner scanner;

    public InputHelper() {
        scanner = new Scanner(System.in);
    }

    public int readInt(int lowerBound, int upperBound, String errorMessage) throws Exception {
        int value = scanner.nextInt();
        if (value < lowerBound || value > upperBound) {
            throw new Exception(errorMessage);
        }
        return value;
    }

}
