public class Main {
    public static void main(String[] arg){
        var app = new Application();
        try{
            app.addHamper();
            app.addClient(0, ClientType.ADULT_MALE, 2);
        } catch (HamperHasNoClientsException e){
            e.printStackTrace();
        }

        try{
            app.addHamper();
            app.addClient(1, ClientType.ADULT_FEMALE, 2);
            app.removeClient(1, ClientType.ADULT_FEMALE);
        } catch (HamperHasNoClientsException e){
            e.printStackTrace();
        }

        try{
            app.addHamper();
            app.addClient(2, ClientType.ADULT_MALE, 1);
            app.addClient(2, ClientType.ADULT_FEMALE, 1);
            app.addClient(2, ClientType.CHILD_OVER_8, 1);
            app.addClient(2, ClientType.CHILD_UNDER_8, 1);
        } catch (HamperHasNoClientsException e){
            e.printStackTrace();
        }

        app.calculateOrder();
        app.toString();

    }
}
