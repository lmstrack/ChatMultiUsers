
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
public class MensagemPrivada implements Serializable, Mensagem{
    
    private String conteudo;
    private String remetente;
    private String destinatario;

    public MensagemPrivada(String conteudo, String remetente, String destinatario) {
        this.conteudo = conteudo;
        this.remetente = remetente;
        this.destinatario = destinatario;
    }

    public String getRemetente() {
        return remetente;
    }

    public void setRemetente(String remetente) {
        this.remetente = remetente;
    }

    public String getDestinatario() {
        return destinatario;
    }

    public void setDestinatario(String destinatario) {
        this.destinatario = destinatario;
    }

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
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
