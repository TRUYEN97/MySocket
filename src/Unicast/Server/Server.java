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
 */
public class Server extends Thread {

    private final ServerSocket serverSocket;
    private final IHandlerManager handlerManager;

    public Server(int port, IHandlerManager handlerManager) throws IOException {
        this.serverSocket = new ServerSocket(port);
        this.handlerManager = handlerManager;
    }

    @Override
    public void run() {
        try (this.serverSocket) {
            ClientHandler handler;
            while (true) {
                handler = createHanhdler(this.serverSocket.accept());
                if (handler == null) {
                    continue;
                }
                this.handlerManager.add(handler);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            this.handlerManager.shutdownNow();
        }
    }

    private ClientHandler createHanhdler(Socket socket) {
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
