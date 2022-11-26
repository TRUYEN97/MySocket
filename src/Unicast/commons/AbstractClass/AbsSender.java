/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Unicast.commons.AbstractClass;

import Unicast.commons.Interface.IDisConnect;
import Unicast.commons.Interface.ISend;
import java.io.ObjectOutputStream;
import Unicast.commons.Interface.IIsConnect;
import java.io.IOException;

/**
 *
 * @author Administrator
 * @param <T>
 */
public abstract class AbsSender<T> implements ISend<T>{
    
    protected ObjectOutputStream outputStream;
    private IIsConnect connect;
    private IDisConnect disConnect;

    protected AbsSender() {
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
            ex.printStackTrace();
            disConnect();
            return false;
        }
    }

    public void setOutputStream(ObjectOutputStream outputStream) {
        this.outputStream = outputStream;
    }

    public void setConnect(IIsConnect connect) {
        this.connect = connect;
    }
    
    public void setDisConnect(IDisConnect disConnect) {
        this.disConnect = disConnect;
    }
    
    public boolean disConnect(){
        return disConnect.disConnect();
    }
    
    protected boolean isConnect(){
        return connect.isConnect();
    }

}
