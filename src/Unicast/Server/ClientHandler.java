/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Unicast.Server;

import CommonsClass.IOServeice;
import Unicast.commons.Interface.IHandlerManager;
import Unicast.commons.Interface.ISend;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import Unicast.commons.Interface.IObjectReceiver;

/**
 *
 * @author Administrator
 * @param <T>
 */
public class ClientHandler<T> implements Runnable, ISend<T> {

    private final Socket socket;
    private final ObjectOutputStream outputStream;
    protected final ObjectInputStream inputStream;
    private IObjectReceiver<T> objectAnalysis;
    private final IHandlerManager handlerManager;
    private boolean connect;

    public ClientHandler(Socket socket, IHandlerManager handlerManager) throws IOException {
        this.socket = socket;
        this.outputStream = new ObjectOutputStream(socket.getOutputStream());
        this.inputStream = new ObjectInputStream(socket.getInputStream());
        this.connect = true;
        this.handlerManager = handlerManager;
    }

    public String getHostAddress() {
        InetAddress address;
        address = socket.getInetAddress();
        if (address == null) {
            return null;
        }
        return address.getHostAddress();
    }

    public int getPort() {
        return this.socket.getPort();
    }

    public boolean isConnect() {
        return this.socket != null && this.socket.isConnected() && connect;
    }

    public void setObjectAnalysis(IObjectReceiver<T> objectAnalysis) {
        this.objectAnalysis = objectAnalysis;
    }

    @Override
    public void run() {
        try {
            while (isConnect()) {
                this.objectAnalysis.receiver((T) inputStream.readObject());
            }
        } catch (IOException | ClassNotFoundException e) {
            this.handlerManager.disConnect(this);
        } finally {
            disConnect();
        }
    }

    public void disConnect() {
        this.connect = false;
        IOServeice.closeStream(socket);
        IOServeice.closeStream(outputStream);
        IOServeice.closeStream(inputStream);
    }

    @Override
    public boolean send(T object) {
        try {
            if (!isConnect()) {
                return false;
            }
            this.outputStream.writeObject(object);
            return true;
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }
}
