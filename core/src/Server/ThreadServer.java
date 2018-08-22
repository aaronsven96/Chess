package server;

import com.badlogic.gdx.net.ServerSocket;
import com.badlogic.gdx.net.Socket;

public class ThreadServer {
    public Thread thread;
    public ServerSocket socket;
    ThreadServer(Thread thread,ServerSocket socket){
        this.thread = thread;
        this.socket = socket;
    }
}
