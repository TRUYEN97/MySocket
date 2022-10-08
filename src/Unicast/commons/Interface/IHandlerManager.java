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
    
    void disConnect(String ID);
    
    void shutdown();

    void disConnect(ClientHandler<T> handlerName);
}
