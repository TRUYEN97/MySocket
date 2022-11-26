/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Unicast.Server.Handlermanage.Online;

import Unicast.Server.ClientHandler;
import Unicast.commons.AbstractClass.AbsServerReceiver;
import Unicast.commons.Interface.IListNumble;
import java.util.HashMap;
import java.util.Map;
import Unicast.commons.Interface.IAddOnlineNumble;
import Unicast.commons.Interface.IOnlineNumble;

/**
 *
 * @author Administrator
 * @param <K>
 * @param <D>
 */
public class OnlineNumble<K, D> implements IOnlineNumble<K, D>{
    
    private final Map<K, ClientHandler<D>> idHanhdler;
    private final Map< ClientHandler<D>, K> hanhdlerName;
    private final AbsServerReceiver receiver;

    public OnlineNumble(AbsServerReceiver<D> receiver) {
        this.receiver = receiver;
        this.idHanhdler = new HashMap<>();
        this.hanhdlerName = new HashMap<>();
    }

    @Override
    public boolean hasOnline(K id) {
        return this.idHanhdler.containsKey(id);
    }

    @Override
    public void disConnect(K id) {
        if (id == null) {
            return;
        }
        ClientHandler handler = this.idHanhdler.remove(id);
        if (handler != null) {
            handler.disConnect();
            this.hanhdlerName.remove(handler);
        }
    }

    @Override
    public void disConnect(ClientHandler<D> handlerName) {
        disConnect(this.hanhdlerName.remove(handlerName));
    }

    @Override
    public void disConnectAll() {
        for (ClientHandler handler : hanhdlerName.keySet()) {
            handler.disConnect();
        }
        this.idHanhdler.clear();
        this.hanhdlerName.clear();
    }

    public int size() {
        return this.idHanhdler.size();
    }

    @Override
    public K getID(ClientHandler<D> handler) {
        return this.hanhdlerName.get(handler);
    }

    @Override
    public ClientHandler<D> getClientHandler(K id) {
        return idHanhdler.get(id);
    }

    @Override
    public void addNumble(K id, ClientHandler<D> handler) {
        handler.setReceiver(receiver);
        this.idHanhdler.put(id, handler);
        this.hanhdlerName.put(handler, id);
    }
}