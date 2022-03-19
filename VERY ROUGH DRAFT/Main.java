public class Main {
    public static void main(String[] arg){
        var app = new Application();
        app.addHamper();
        app.addClient("Adult Male", 2);
        app.toString();

        app.addHamper();
        app.addClient("Adult Male", 2);
        app.toString();

        app.addHamper();
        app.addClient("Adult Male", 2);
        app.toString();

        app.calculateOrder();
        app.toString();

    }
}
