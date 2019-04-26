
import java.awt.List;
import java.io.Serializable;
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author LUCASSTRACK
 */
public class MensagemInicial  implements Serializable, Mensagem{
    String username;
    ArrayList<String> users = new ArrayList();

    MensagemInicial(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return ("Você se conectou.\n");
    }
    
    public void setUsers(ArrayList<String> users){
        this.users = users;
    }
    
    @Override
    public void executar(FormCliente f) {
        f.disableConnectB();
        f.setIsConnected(true);
        f.chatAppend(this.toString());
        f.loadUsers(users);
    }
}
