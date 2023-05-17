import db.QueryExecution;
import view.GUI;

public class Hello {
    
    public static void main(String[] args) {
        new GUI(new QueryExecution()).display();
    }
}
