
import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;


public class MensagemArquivo implements Serializable, Mensagem{

    private String nome;
    private byte[] conteudo;
    private String destinatario;
    private String remetente;

    public String getDestinatario() {
        return destinatario;
    }

    public String getRemetente() {
        return remetente;
    }

    public MensagemArquivo(String nome, byte[] conteudo,  String remetente, String destinatario) {
        this.nome = nome;
        this.conteudo = conteudo;
        this.remetente = remetente;
        this.destinatario = destinatario;
    }

    @Override
    public String toString() {
        return (remetente +" enviou um arquivo \"" + nome + "\". Salvo em C:\\Temp.\n");
    }
    
    @Override
    public void executar(FormCliente f) {
        try {
            FileOutputStream fos = new FileOutputStream("C:\\Temp\\"+nome);
            fos.write(conteudo);
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        f.chatAppend(this.toString());
    }
}
