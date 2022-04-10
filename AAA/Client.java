public class Client extends Nutrition {
    private final ClientType TYPE;
    private final Boolean HANDICAPPED; // Still wondering if we need to identify handicapped clients

    /* THIS IS NOT IMPLEMENTED PROPERLY AT ALL!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
     * temporary test to see if others are working
     */

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

    public ClientType getType() {
        return this.TYPE;
    }

    public Boolean isHandicapped() {
        return this.HANDICAPPED;
    }
}
