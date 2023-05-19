import db.Controller;
import view.GUI;

public class Main {
    
    public static void main(String[] args) {
        new GUI(new Controller()).display();
    }
}
