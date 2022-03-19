public class Client extends Nutrition {
    private final ClientType TYPE;
    private Boolean handicapped;

    /* THIS IS NOT IMPLEMENTED PROPERLY AT ALL!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
     * temporary test to see if others are working
     */

    public Client(String type){
        super(type);
        
        
        ClientType tp = null;
        /*
        for (ClientType t : ClientType.values()) {
            if (type.toUpperCase().equals(t.toString())){
                tp = t;
            }
        }
        */
        this.TYPE = tp;
        //throw new IllegalArgumentException();
    }

    public String getType(){
        return this.TYPE.toString();
    }

    public Boolean isHandicapped(){
        return this.handicapped;
    }



}
