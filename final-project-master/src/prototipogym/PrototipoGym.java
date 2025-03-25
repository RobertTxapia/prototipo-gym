package prototipogym;

import prototipogym.view.auth.LoginFrame;

public class PrototipoGym {
    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> {//holaaaa
           new LoginFrame().setVisible(true);
        });
    }
}
