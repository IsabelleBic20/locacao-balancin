package Main;

import locacaobalancim.view.ClienteCadastroView;
import locacaobalancim.view.CadastraBalancim;
import locacaobalancim.view.LocacaoView;
import locacaobalancim.view.PrincipalView;

public class LocacaoBalancim {

     public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> {
            new PrincipalView().setVisible(true);
        });
    }
}
