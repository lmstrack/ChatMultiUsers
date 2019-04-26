
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class ConnectedClient implements Runnable{
    Socket s;
    ObjectInputStream in;
    ObjectOutputStream out;
    Thread t;
    String username;

    ConnectedClient(Socket s) {
        this.s = s;
    }

    public boolean setup() {
        boolean naoExiste = true;
        try {
            in = new ObjectInputStream(s.getInputStream());
            out = new ObjectOutputStream(s.getOutputStream());
            MensagemInicial m = (MensagemInicial) this.readObject();
            
            if ( m != null ){
                for(ConnectedClient client:Servidor.clients){
                    if(m.username.equals(client.username)){
                        naoExiste = false;
                    }
                }

                if(naoExiste == false){
                    String c = "Oops... este nome de usuário já existe.";
                    MensagemPrivada p = new MensagemPrivada(c,  "Sistema", username);
                    sendObject(p);
                    in.close();
                    out.close();
                    s.close();
                } else{
                    this.username = m.username;
                    
                    ArrayList<String> users = new ArrayList();
                    for(ConnectedClient client:Servidor.clients){
                        users.add(client.username);
                    }
                    m.setUsers(users);
                    this.sendObject(m);
                    
                    MensagemConexao c = new MensagemConexao(m.username);
                    sendToAll(c, m.username);
                    
                    t = new Thread(this);
                    t.start();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return naoExiste;
    }
    
    private Object readObject(){
        Object obj = null;
        try{
            obj = in.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj;
    }

    private void sendObject(Object obj){
        try{
            out.writeObject(obj);
            out.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void sendToAll(Mensagem m, String remetente){
        for(ConnectedClient client:Servidor.clients){
            if(!remetente.equals(client.username)){
                client.sendObject(m);
            }
        }
    }
    
    private void sendToSomeone(Mensagem m, String destinatario){
        for(ConnectedClient client:Servidor.clients){
            if(destinatario.equals(client.username)){
                client.sendObject(m);
            }
        }
    }
    
    private void sendFile(Mensagem m) {
        MensagemArquivo mArquivo = (MensagemArquivo) m;
        if (mArquivo.getDestinatario() != null){
            this.sendToSomeone(m, mArquivo.getDestinatario());
        } else{
            this.sendToAll(m, mArquivo.getRemetente());
        }
    }
    
    private void disconnectUser(Mensagem m, String remetente){
        this.sendToAll(m, remetente);
        
        MensagemFinal mf = new MensagemFinal(remetente);   
        this.sendObject(mf);
        
        try {
            this.s.shutdownInput();
            this.s.shutdownOutput();
            this.s.close();
            Servidor.clients.remove(this);
            this.t.stop();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public void run() {
        Mensagem m;
        String classe;
        while((m = (Mensagem) readObject()) != null){
            classe = m.getClass().getSimpleName();
            switch(classe){
                case("MensagemArquivo"):
                    this.sendFile(m);
                    break;
              
                case("MensagemDesconexao"):
                    MensagemDesconexao d = (MensagemDesconexao) m;
                    this.disconnectUser(d, username);
                    break;
                    
                case("MensagemPrivada"):
                    MensagemPrivada p = (MensagemPrivada) m;
                    this.sendToSomeone(p, p.getDestinatario());
                    break;

                case("MensagemPublica"):
                    MensagemPublica mp = (MensagemPublica) m;
                    this.sendToAll(m, mp.getRemetente());
                    break;
            }
        }
    }
}
