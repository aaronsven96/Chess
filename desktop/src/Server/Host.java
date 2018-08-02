package Server;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net;
import com.badlogic.gdx.net.SocketHints;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class Host {

    public void ConnectToSocket() throws UnknownHostException {
        SocketHints hints=new SocketHints();
        com.badlogic.gdx.net.Socket socket = Gdx.net.newClientSocket(Net.Protocol.TCP , InetAddress.getLocalHost().toString(), 100, hints);
    }
}
