package prototipogym;
import prototipogym.view.ManUsuario;

public class ManUsuarioClass {
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ManUsuario().setVisible(true);
            }
        });
    }
}
