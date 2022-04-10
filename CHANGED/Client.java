/**
@author Danny Duong, Kaito Sugimura, Kevin Johnson, Joshua Walters
@version 1.5
@since 1.0
*/

/*
 * class to create the Client data type
 * Extends Nutrition and holds data for ClientType's nutritional values
*/
public class Client extends Nutrition {

    private final ClientType TYPE;

    /**
     * Constructor
     * @param type ClientType enum
     */
    public Client(ClientType type) {
        super(type);
        this.TYPE = type;
    }

    // Getter
    public ClientType getType() {
        return this.TYPE;
    }
}
