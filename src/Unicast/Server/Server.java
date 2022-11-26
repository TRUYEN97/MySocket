/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Unicast.Server;

import Unicast.commons.Interface.IHandlerManager;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author Administrator
 * @param <K>
 * @param <D>
 */
public class Server<K, D> extends Thread {
    
    private final ServerSocket serverSocket;
    private final IHandlerManager<K, D> handlerManager;
    
    public Server(int port, IHandlerManager<K, D> handlerManager) throws Exception {
        this.serverSocket = new ServerSocket(port);
        this.handlerManager = handlerManager;
    }
    
    @Override
    public void run() {
        try (this.serverSocket) {
            ClientHandler<D> handler;
            while (true) {
                handler = createHanhdler(this.serverSocket.accept());
                if (handler == null) {
                    continue;
                }
                this.handlerManager.add(handler);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    private ClientHandler<D> createHanhdler(Socket socket) {
        try {
            return new ClientHandler<>(socket, handlerManager);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public IHandlerManager getIHandlerManager() {
        return handlerManager;
    }
    
}
