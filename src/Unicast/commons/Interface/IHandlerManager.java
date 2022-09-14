/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Unicast.commons.Interface;

import Unicast.Server.ClientHandler;
import java.util.List;

/**
 *
 * @author Administrator
 * @param <T>
 */
public interface IHandlerManager<T> {

    void add(ClientHandler<T> handler);
    
    void disConnectAll();
    
    boolean disConnect(long ID);
    
    boolean isShutdown();
    
    void shutdown();
    
    List<Runnable> shutdownNow();
    
    int getMaxClint();
    
    int getWaitLine();
    
    int getAmountOfClients();

    void disConnect(ClientHandler<T> handlerName);
}
