public class InsufficientInventoryException extends Exception {
    public InsufficientInventoryException(){
        super("Insufficient resources in Inventory");
    }

    public InsufficientInventoryException(String message){
        super(message);
    }
}
