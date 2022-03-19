public class Client extends Nutrition {
    private final ClientType TYPE;
    private Boolean handicapped;

    /* THIS IS NOT IMPLEMENTED PROPERLY AT ALL!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
     * temporary test to see if others are working
     */

    public Client(ClientType type){
        super(type);
        this.TYPE = type;
        handicapped = false;
    }

    public Client(ClientType type, boolean handicapped){
        super(type);
        this.TYPE = type;
        this.handicapped = handicapped;
    }

    public ClientType getType(){
        return this.TYPE;
    }

    public Boolean isHandicapped(){
        return this.handicapped;
    }



}
