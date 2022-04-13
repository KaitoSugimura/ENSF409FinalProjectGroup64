/**
@author Danny Duong, Kaito Sugimura, Kevin Johnson, Joshua Walters
@version 1.1
@since 1.0
*/

/**
 * Throws an exception for cases when there are not enough
 * items in the inventory for the attempted operations.
 */
public class InsufficientInventoryException extends Exception {
    /**
     * Constructor
     * Prints exception with message
     */
    public InsufficientInventoryException(){
        super("Insufficient resources in Inventory");
    }
}
