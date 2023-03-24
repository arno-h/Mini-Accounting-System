public class IdDoesNotExistException extends Exception {
    private final String message;

    public IdDoesNotExistException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "IdDoesNotExistException{" +
                "message='" + message + '\'' +
                '}';
    }
}
