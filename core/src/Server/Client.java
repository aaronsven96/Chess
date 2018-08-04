package Server;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.Net;
import com.badlogic.gdx.net.HttpRequestHeader;
import com.badlogic.gdx.net.HttpResponseHeader;
import com.badlogic.gdx.net.HttpStatus;
import com.badlogic.gdx.net.ServerSocket;
import com.badlogic.gdx.net.ServerSocketHints;
import com.badlogic.gdx.net.Socket;
import com.badlogic.gdx.net.SocketHints;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.badlogic.gdx.utils.Pool.Poolable;

import java.net.InetAddress;
import java.net.UnknownHostException;

import static com.badlogic.gdx.Net.Protocol.TCP;


public class Client {
    public void ConnectToSocket() throws UnknownHostException {
        SocketHints hints=new SocketHints();
        com.badlogic.gdx.net.Socket socket = Gdx.net.newClientSocket(Net.Protocol.TCP , InetAddress.getLocalHost().toString().split("/")[1], 100, hints);
    }

}
