/**
@author Danny Duong, Kaito Sugimura, Kevin Johnson, Joshua Walters
@version 1.0
@since 1.0
*/

/*
class HamperHasNoClientsexception - throws exception for cases where a hamper is expected
to have clients but has none.
*/
public class HamperHasNoClientsException extends Exception{
    //public methods
    /**
     * HamperHasNoClientsException() - prints exception message.
     */
    public HamperHasNoClientsException(){
        super("The Hamper has no clients");
    }
}
