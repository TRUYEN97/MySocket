/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Unicast.Server.Handlermanage;

import Unicast.Server.Handlermanage.WaitLine.DefaultWaitAccept;
import Unicast.Server.Handlermanage.WaitLine.AbsConfirmAccept;
import Unicast.Server.ClientHandler;
import Unicast.Server.Handlermanage.Online.OnlineNumble;
import Unicast.commons.AbstractClass.AbsSender;
import Unicast.commons.AbstractClass.AbsServerReceiver;
import Unicast.commons.Interface.IHandlerManager;
import Unicast.commons.Interface.IListNumble;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 * @author Administrator
 * @param <K>
 * @param <D>
 */
public class DefaultServerHandleManagement<K, D> implements IHandlerManager<K, D> {

    protected final ExecutorService pool;
    protected final OnlineNumble<K, D> onlineNumble;
    protected final DefaultWaitAccept<K, D> waitAccept;
    protected final AbsSender<D> sender;
    protected final int max;

    public DefaultServerHandleManagement(OnlineNumble<K, D> onlineNumble, AbsSender<D> sender, AbsConfirmAccept<K, D> absConfirmAccept,
            int handlerMax, int waitMax, int timeWait) {
        this.max = handlerMax;
        this.sender = sender;
        this.pool = Executors.newFixedThreadPool(handlerMax);
        this.onlineNumble = onlineNumble;
        absConfirmAccept.setOnlineNumble(onlineNumble);
        this.waitAccept = new DefaultWaitAccept<>(absConfirmAccept, waitMax, timeWait);
    }
    
    public DefaultServerHandleManagement( AbsServerReceiver<D> serverReceiver, AbsSender<D> sender, AbsConfirmAccept<K, D> absConfirmAccept,
            int handlerMax, int waitMax) {
        this.max = handlerMax;
        this.sender = sender;
        this.pool = Executors.newFixedThreadPool(handlerMax);
        this.onlineNumble = new OnlineNumble<>(serverReceiver);
        this.waitAccept = new DefaultWaitAccept<>(absConfirmAccept, waitMax, 1000);
    }

    @Override
    public IListNumble<K, D> getOnlineNumble() {
        return onlineNumble;
    }

    @Override
    public boolean hasOnline(K id) {
        return this.onlineNumble.hasOnline(id);
    }

    @Override
    public void add(ClientHandler<D> handler) {
        if (handler == null) {
            return;
        }
        handler.setSender(sender);
        this.waitAccept.add(handler);
        this.pool.execute(handler);
    }

    @Override
    public void disConnectAll() {
        this.onlineNumble.disConnectAll();
    }

    @Override
    public void disConnect(K ID) {
        this.onlineNumble.disConnect(ID);
    }

    @Override
    public void shutdown() {
        this.pool.shutdown();
    }

    @Override
    public void disConnect(ClientHandler<D> handlerName) {
        this.onlineNumble.disConnect(handlerName);
    }

    @Override
    public ClientHandler<D> getClientHandler(K id) {
        return this.onlineNumble.getClientHandler(id);
    }

    @Override
    public K getID(ClientHandler<D> handler) {
        return this.onlineNumble.getID(handler);
    }
}
