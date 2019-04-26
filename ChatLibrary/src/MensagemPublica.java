
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
public class MensagemPublica implements Serializable, Mensagem{

    private String conteudo;
    private String remetente;

    public MensagemPublica(String conteudo, String remetente) {
        this.conteudo = conteudo;
        this.remetente = remetente;
    }

    public String getConteudo() {
        return conteudo;
    }

    public String getRemetente() {
        return remetente;
    }
    
    @Override
    public String toString() {
        return (remetente +": " + conteudo + "\n");
    }
    
    @Override
    public void executar(FormCliente f) {
        f.chatAppend(this.toString());
    }
    
}
