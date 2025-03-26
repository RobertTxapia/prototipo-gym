package prototipogym;
import prototipogym.view.LoginFrame;

public class PrototipoGym {
    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> {
           new LoginFrame().setVisible(true);
        });
    }
}
