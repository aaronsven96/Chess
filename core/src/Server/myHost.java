package Server;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net;
import com.badlogic.gdx.net.ServerSocket;
import com.badlogic.gdx.net.ServerSocketHints;
import com.badlogic.gdx.net.Socket;
import com.badlogic.gdx.net.SocketHints;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

import static com.badlogic.gdx.Net.Protocol.TCP;

public class myHost {

    public void makeSocket() {
        ServerSocketHints hints=new ServerSocketHints();
        ServerSocket server = Gdx.net.newServerSocket(TCP, 100,  hints);
        SocketHints hint=new SocketHints();
        Socket socket =server.accept(hint);
        while(true){
            if (socket.isConnected()){
                try {
                    socket.getOutputStream().write(0);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
