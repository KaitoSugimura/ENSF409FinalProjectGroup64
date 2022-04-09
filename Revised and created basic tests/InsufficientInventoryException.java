/**
@author Danny Duong, Kaito Sugimura, Kevin Johnson, Joshua Walters
@version 1.9
@since 1.0
*/

/*
class InsufficientInventoryException - throws an exception for cases when there are not enough
items in the inventory for the attempted operations.
*/
public class InsufficientInventoryException extends Exception {
    //public methods
    /**
     * InsufficientInventoryException() - prints exception with message
     */
    public InsufficientInventoryException(){
        super("Insufficient resources in Inventory");
    }

    /**
     * InsufficientInventoryException() - prints exception with custom message
     */
    public InsufficientInventoryException(String message){
        super(message);
    }
}
