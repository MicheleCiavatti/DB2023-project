import db.Controller;
import view.GUI;

public class Hello {
    
    public static void main(String[] args) {
        new GUI(new Controller()).display();
    }
}
