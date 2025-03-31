package prototipogym;
import prototipogym.view.LoginFrame;

/*
Terminar todos los mantenimientos
terminar y las consultas
y menu principal
*/

public class PrototipoGym {
    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> {
           new LoginFrame().setVisible(true);
        });
    }
}