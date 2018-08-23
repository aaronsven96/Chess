package server;

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

import java.nio.charset.Charset;
import java.util.concurrent.ConcurrentLinkedQueue;
import server.ThreadServer;

import static com.badlogic.gdx.Net.Protocol.TCP;

public class TCPCommunication {

    //Makes Socket
    public ThreadServer makeSocket(final ConcurrentLinkedQueue<String> receive, final ConcurrentLinkedQueue<String> send, final int port) {
        final ThreadServer returnData = new ThreadServer(null, null);
        final Thread thread=new Thread(new Runnable() {
            @Override
            public void run() {
                //Make Hints
                Thread messenger = null;

                ServerSocketHints hints=new ServerSocketHints();
                hints.acceptTimeout=0; //waits forever
                ServerSocket server = null;
                try {
                    server= Gdx.net.newServerSocket(TCP, port,  hints);
                }
                catch (com.badlogic.gdx.utils.GdxRuntimeException e){
                    return;
                }
                SocketHints hint=new SocketHints();
                System.out.println("Server: "+server);
                Socket socket =server.accept(hint);
                returnData.socket=server;

                InputStream inStream =socket.getInputStream();
                OutputStream outStream=socket.getOutputStream();

                try {
                    messenger=readBytes(inStream,receive,socket);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                while(socket.isConnected()){
                    if (Thread.currentThread().isInterrupted()){
                        messenger.interrupt();
                        System.out.println("interrupted");
                        return;
                    }
                    else if(!send.isEmpty()){
                        try {
                            outStream.write(send.remove().getBytes(Charset.forName("UTF-8")));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
                System.out.println("working");
            }
        });
        thread.start();
        returnData.thread=thread;
        System.out.println(thread);
        return returnData;
    }


    //Connects to Socket
    public Thread ConnectToSocket(final ConcurrentLinkedQueue<String> receive, final ConcurrentLinkedQueue<String> send, final String ipAddress,final int port) {
        Thread thread=new Thread(new Runnable() {
            @Override
            public void run() {
                Socket socket = null;
                Thread messenger=null;
                SocketHints hints=new SocketHints();
                socket = Gdx.net.newClientSocket(Net.Protocol.TCP ,ipAddress , port, hints);
                //InetAddress.getLocalHost().toString().split("/")[1]
                InputStream inStream =socket.getInputStream();
                OutputStream outStream=socket.getOutputStream();
                try {
                    messenger=readBytes(inStream,receive,socket);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                while(socket.isConnected()){
                    if (Thread.currentThread().isInterrupted()){
                        messenger.interrupt();
                        return;
                    }
                    if(!send.isEmpty()){
                        try {
                            outStream.write(send.remove().getBytes(Charset.forName("UTF-8")));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });
        thread.start();
        return thread;
    }
    private Thread  readBytes(final InputStream stream, final ConcurrentLinkedQueue queue, final Socket socket) throws IOException {
        Thread thread =new Thread(new Runnable() {
            @Override
            public void run() {
                while (socket.isConnected()){
                    if (Thread.currentThread().isInterrupted()){
                        return;
                    }
                    try {
                        queue.add(readMessage(stream));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            }
        });
        thread.start();
        return thread;
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