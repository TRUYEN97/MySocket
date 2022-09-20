/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Unicast.Client;

import CommonsClass.IOServeice;
import Unicast.commons.Interface.IConnect;
import Unicast.commons.Interface.ISend;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import Unicast.commons.Interface.IObjectReceiver;

/**
 *
 * @author Administrator
 * @param <T>
 */
public class Client<T> implements Runnable, IConnect, ISend<T> {

    private Socket socket;
    private ObjectOutputStream outputStream;
    private ObjectInputStream inputStream;
    private boolean connect;
    private final IObjectReceiver<T> objectAnalysis;

    public Client(IObjectReceiver<T> objectAnalysis) {
        this.objectAnalysis = objectAnalysis;
        this.connect = false;
    }

    @Override
    public boolean connect(String host, int port) {
        try {
            disConnect();
            this.socket = new Socket(host, port);
            this.outputStream = new ObjectOutputStream(socket.getOutputStream());
            this.inputStream = new ObjectInputStream(socket.getInputStream());
            connect = true;
            return true;
        } catch (IOException ex) {
            connect = false;
            return false;
        }
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
            System.out.println(ex.getLocalizedMessage());
            return false;
        }
    }

    @Override
    public void run() {
        try {
            while (true) {
                this.objectAnalysis.receiver((T) this.inputStream.readObject());
            }
        } catch (IOException | ClassNotFoundException ex) {
            System.out.println(ex.getLocalizedMessage());
        } finally {
            disConnect();
        }
    }

    @Override
    public boolean isConnect() {
        return this.socket != null && this.socket.isConnected() && connect;
    }

    @Override
    public boolean disConnect() {
        connect = false;
        try {
            if (this.socket != null && this.socket.isConnected()) {
                IOServeice.closeStream(socket);
                IOServeice.closeStream(inputStream);
                IOServeice.closeStream(outputStream);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}
