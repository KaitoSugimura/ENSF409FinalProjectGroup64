/**
@author Danny Duong, Kaito Sugimura, Kevin Johnson, Joshua Walters
@version 1.0
@since 1.0
*/

/*
 * Throws exception for cases where a hamper is expected
 * to have clients but has none.
 */
public class HamperHasNoClientsException extends Exception{
    /**
     * Constructor 
     * prints exception message.
     */
    public HamperHasNoClientsException(){
        super("The Hamper has no clients");
    }
}
