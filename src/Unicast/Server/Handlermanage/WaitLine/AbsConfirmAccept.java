/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Unicast.Server.Handlermanage.WaitLine;

import Unicast.Server.ClientHandler;
import Unicast.commons.Interface.IObjectServerReceiver;
import Unicast.commons.Interface.IOnlineNumble;

/**
 *
 * @author Administrator
 * @param <K> type of packageData
 * @param <D> type of id client
 */
public abstract class AbsConfirmAccept<K, D> implements IObjectServerReceiver<D>{

    protected IOnlineNumble<K, D> onlineNumble;

    public AbsConfirmAccept() {
    }

    public void setOnlineNumble(IOnlineNumble<K, D> onlineNumble) {
        this.onlineNumble = onlineNumble;
    }
    

    @Override
    public void receiver(ClientHandler<D> handler, D data) {
        if (onlineNumble == null) {
            return;
        }
        K id = acceptable(handler, data);
        if (id == null) {
            return;
        }
        onlineNumble.addNumble(id, handler);
    }

    protected abstract K acceptable(ClientHandler<D> handler, D object);

}
