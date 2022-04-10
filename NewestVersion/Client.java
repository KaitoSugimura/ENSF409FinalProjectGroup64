/**
@author Danny Duong, Kaito Sugimura, Kevin Johnson, Joshua Walters
@version 1.5
@since 1.0
*/

/*
class Client - Holds data for clients and extends nutrition to hold their nutritional
needs.
*/
public class Client extends Nutrition {
    //Fields
    private final ClientType TYPE;
    private final Boolean HANDICAPPED; // Still wondering if we need to identify handicapped clients


    //Constructors
    public Client(ClientType type) {
        super(type);
        this.TYPE = type;
        this.HANDICAPPED = false;
    }

    public Client(ClientType type, boolean handicapped) {
        super(type);
        this.TYPE = type;
        this.HANDICAPPED = handicapped;
    }

    //Getters
    public ClientType getType() {
        return this.TYPE;
    }

    //public Methods
    
    /**
     * isHandicapped() - returns a boolean if the client is handicapped.
     * @return HANDICAPPED
     */
    public Boolean isHandicapped() {
        return this.HANDICAPPED;
    }
}
