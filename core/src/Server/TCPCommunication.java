package Server;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net;
import com.badlogic.gdx.net.ServerSocket;
import com.badlogic.gdx.net.ServerSocketHints;
import com.badlogic.gdx.net.Socket;
import com.badlogic.gdx.net.SocketHints;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.concurrent.ConcurrentLinkedQueue;

import static com.badlogic.gdx.Net.Protocol.TCP;

public class TCPCommunication {

    //Makes Socket
    public void makeSocket(final ConcurrentLinkedQueue<String> data) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                ServerSocketHints hints=new ServerSocketHints();
                hints.acceptTimeout=0;
                ServerSocket server = Gdx.net.newServerSocket(TCP, 100,  hints);
                SocketHints hint=new SocketHints();
                Socket socket =server.accept(hint);
                try {
                    readBytes(socket.getInputStream(),data,socket);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.out.println("working");
            }
        }).start();
    }

    //Connects to Socket
    public void ConnectToSocket(final ConcurrentLinkedQueue<String> data) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Socket socket = null;
                SocketHints hints=new SocketHints();
                try {
                    socket = Gdx.net.newClientSocket(Net.Protocol.TCP , InetAddress.getLocalHost().toString().split("/")[1], 100, hints);
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                }
                InputStream inStream =socket.getInputStream();
                OutputStream outStream=socket.getOutputStream();
                try {
                    readBytes(inStream,data,socket);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                while(socket.isConnected()){
                    try {
                        Thread.sleep(4000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    try {
                        System.out.println("write");
                        outStream.write('h');
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

    }
    private void  readBytes(final InputStream stream, final ConcurrentLinkedQueue queue, final Socket socket) throws IOException {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (socket.isConnected()){
                    try {
                        queue.add(readMessage(stream));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            }
        }).start();

    }
    private String readMessage(InputStream stream) throws IOException {
        ByteArrayOutputStream result = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int length;
        while ((length = stream.read(buffer)) != -1) {
            result.write(buffer, 0, length);
            System.out.println("result: "+result.toString("UTF-8"));
        }
        System.out.println("result: "+result.toString("UTF-8"));
        // StandardCharsets.UTF_8.name() > JDK 7
        return result.toString("UTF-8");
    }
}

