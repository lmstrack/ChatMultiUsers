
import java.io.Serializable;
import java.net.Socket;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author LUCASSTRACK
 */
public class MensagemConexao implements Serializable, Mensagem{

    String username;

    MensagemConexao(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return ("Usuário " + username + " se conectou.\n");
    }

    @Override
    public void executar(FormCliente f) {
        f.chatAppend(this.toString());
        f.addUser(username);
    }
}
