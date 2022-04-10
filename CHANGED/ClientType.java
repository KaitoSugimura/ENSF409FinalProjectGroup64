/**
@author Danny Duong, Kaito Sugimura, Kevin Johnson, Joshua Walters
@version 1.1
@since 1.0
*/

/*
 * enumerator ClientType - enum used to validate the constant client types for the Client class.
*/
public enum ClientType {
    ADULT_MALE{
        @Override
        public String toString(){
            return "Adult Male";
        }
    },
    ADULT_FEMALE{
        @Override
        public String toString(){
            return "Adult Female";
        }
    },
    CHILD_OVER_8{
        @Override
        public String toString(){
            return "Child over 8";
        }
    },
    CHILD_UNDER_8{
        @Override
        public String toString(){
            return "Child under 8";
        }
    };

    //Methods
    /**
     * toString - returns the type name as a string.
     */
    public abstract String toString();
}
