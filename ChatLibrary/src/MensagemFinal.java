
import java.io.IOException;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author LUCASSTRACK
 */
public class MensagemFinal  implements Serializable, Mensagem{
    String username;

    MensagemFinal(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return ("Você se desconectou.\n");
    }
    
    @Override
    public void executar(FormCliente f) {
        f.enableConnectB();
        f.setIsConnected(false);
        f.chatAppend(this.toString());
        f.removeUser(username);
        try {
            f.s.shutdownInput();
            f.s.shutdownOutput();
            f.s.close();
            f.t.stop();
        } catch (Exception e) {
            e.printStackTrace();
        }        
    }
}
