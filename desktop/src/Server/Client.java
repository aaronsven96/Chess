package Server;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.net.HttpRequestHeader;
import com.badlogic.gdx.net.HttpResponseHeader;
import com.badlogic.gdx.net.HttpStatus;
import com.badlogic.gdx.net.ServerSocket;
import com.badlogic.gdx.net.ServerSocketHints;
import com.badlogic.gdx.net.Socket;
import com.badlogic.gdx.net.SocketHints;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.badlogic.gdx.utils.Pool.Poolable;

import static com.badlogic.gdx.Net.Protocol.TCP;


public class Client {

    public void makeSocket() {
        ServerSocketHints hints=new ServerSocketHints();
        ServerSocket server = Gdx.net.newServerSocket(TCP, 100,  hints);
    }
}
