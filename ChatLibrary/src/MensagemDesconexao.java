
import java.io.Serializable;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author LUCASSTRACK
 */
public class MensagemDesconexao implements Serializable, Mensagem{

    String username;
    
    MensagemDesconexao(String username) {
        this.username = username;
    }
    
    @Override
    public String toString() {
        return ("Usuário " + username + " desconectou.\n");
    }

    @Override
    public void executar(FormCliente f) {
        f.chatAppend(this.toString());
        f.removeUser(username);
    }
    
}
