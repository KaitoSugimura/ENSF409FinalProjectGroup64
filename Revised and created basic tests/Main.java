public class Main {
    public static void main(String[] arg) {
        var app = new Application();
        app.addHamper();
        app.addClient(0, ClientType.ADULT_MALE, 2);

        app.addHamper();
        app.addClient(1, ClientType.ADULT_FEMALE, 2);
        app.removeClient(1, ClientType.ADULT_FEMALE, 1);

        app.addHamper();
        app.addClient(2, ClientType.ADULT_MALE, 1);
        app.addClient(2, ClientType.ADULT_FEMALE, 1);
        app.addClient(2, ClientType.CHILD_OVER_8, 1);
        app.addClient(2, ClientType.CHILD_UNDER_8, 1);

        app.calculateOrder();
        app.toString();

    }
}
