/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Unicast.Server;

import CommonsClass.IOServeice;
import Unicast.commons.AbstractClass.AbsSender;
import Unicast.commons.Interface.IDisConnect;
import Unicast.commons.Interface.IHandlerManager;
import Unicast.commons.Interface.ISend;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import Unicast.commons.Interface.IIsConnect;
import Unicast.commons.Interface.IObjectServerReceiver;

/**
 *
 * @author Administrator
 * @param <D>
 */
public class ClientHandler<D> implements Runnable, IIsConnect, IDisConnect, ISend<D> {

    private final Socket socket;
    private final ObjectOutputStream outputStream;
    protected final ObjectInputStream inputStream;
    protected AbsSender<D> iSend;
    private IObjectServerReceiver objectAnalysis;
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

    @Override
    public boolean isConnect() {
        return this.socket != null && this.socket.isConnected() && connect;
    }

    public void setReceiver(IObjectServerReceiver objectAnalysis) {
        this.objectAnalysis = objectAnalysis;
    }

    public AbsSender<D> getSender() {
        return iSend;
    }

    public void setSender(AbsSender<D> iSend) {
        if (iSend == null) {
            return;
        }
        iSend.setConnect(this);
        iSend.setDisConnect(this);
        iSend.setOutputStream(outputStream);
        this.iSend = iSend;
    }

    @Override
    public void run() {
        try {
            while (isConnect()) {
                this.objectAnalysis.receiver(this, (D) inputStream.readObject());
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            this.handlerManager.disConnect(this);
        }
    }

    @Override
    public boolean disConnect() {
        try {
            this.connect = false;
            IOServeice.closeStream(socket);
            IOServeice.closeStream(outputStream);
            IOServeice.closeStream(inputStream);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean send(D object) {
        return this.iSend.send(object);
    }
}
