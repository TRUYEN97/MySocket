/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Unicast.Server.Handlermanage.WaitLine;

import Unicast.Server.ClientHandler;
import java.awt.event.ActionEvent;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import javax.swing.Timer;

/**
 *
 * @author Administrator
 * @param <K>
 * @param <D>
 */
public class DefaultWaitAccept<K, D> {

    private final Queue<ClientHandler<D>> queueAccept;
    private final Map<Long, ClientHandler<D>> mapAccept;
    private final Timer timer;
    private final AbsConfirmAccept<K, D> acceptConnect;
    private final int max;

    public DefaultWaitAccept(AbsConfirmAccept<K, D> acceptConnect, int waitMax, int timeWait) {
        this.queueAccept = new ArrayDeque<>(waitMax);
        this.mapAccept = new HashMap<>();
        this.max = waitMax;
        this.acceptConnect = acceptConnect;
        this.timer = new Timer(timeWait, (ActionEvent e) -> {
            ClientHandler<D> handler;
            synchronized (DefaultWaitAccept.this.queueAccept) {
                if (DefaultWaitAccept.this.queueAccept.isEmpty() || (handler = DefaultWaitAccept.this.queueAccept.poll()) == null) {
                    DefaultWaitAccept.this.timer.stop();
                    return;
                }
                handler.disConnect();
            }
        });
    }

    public void add(ClientHandler<D> handler) {
        long id = System.currentTimeMillis();
        this.queueAccept.add(handler);
        this.mapAccept.put(id, handler);
        handler.setReceiver( this.acceptConnect);
    }

    public ClientHandler<D> getClientHandler(long id) {
        return this.mapAccept.get(id);
    }
    
    public int maxSize(){
        return this.max;
    }
    
    public int size(){
        return this.mapAccept.size();
    }

}
