public class Main {
    public static void main(String[] arg){
        var app = new Application();
        app.addHamper();
        app.addClient(ClientType.ADULT_MALE, 2);

        app.addHamper();
        app.addClient(ClientType.ADULT_FEMALE, 2);

        app.addHamper();
        app.addClient(ClientType.ADULT_MALE, 1);
        app.addClient(ClientType.ADULT_FEMALE, 1);
        app.addClient(ClientType.CHILD_OVER_8, 1);
        app.addClient(ClientType.CHILD_UNDER_8, 1);

        app.calculateOrder();
        app.toString();

    }
}
