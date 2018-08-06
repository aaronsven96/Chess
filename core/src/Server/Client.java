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

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.concurrent.ConcurrentLinkedQueue;

import static com.badlogic.gdx.Net.Protocol.TCP;


public class Client {
    ConcurrentLinkedQueue<String> data;
    public void ConnectToSocket(final ConcurrentLinkedQueue<String> data) {
        this.data=data;
        new Runnable() {
            @Override
            public void run() {
                Socket socket = null;
                SocketHints hints=new SocketHints();
                try {
                     socket = Gdx.net.newClientSocket(Net.Protocol.TCP , InetAddress.getLocalHost().toString().split("/")[1], 100, hints);
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                }
                InputStream stream =socket.getInputStream();
                while(true){
                    try {
                        data.add(readBytes(stream));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        };

    }
    private String  readBytes(InputStream stream) throws IOException {
        ByteArrayOutputStream result = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int length;
        while ((length = stream.read(buffer)) != -1) {
            result.write(buffer, 0, length);
        }
// StandardCharsets.UTF_8.name() > JDK 7
        return result.toString("UTF-8");
    }
}
