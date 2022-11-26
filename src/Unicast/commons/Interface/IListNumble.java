/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Unicast.commons.Interface;

import Unicast.Server.ClientHandler;

/**
 *
 * @author Administrator
 * @param <K>
 * @param <D>
 */
public interface IListNumble<K, D> {
    
    
    void disConnectAll();
    
    void disConnect(K id);
    
    void disConnect(ClientHandler<D> handlerName);
    
    boolean hasOnline(K id);
    
    ClientHandler<D> getClientHandler(K id);
    
    K getID(ClientHandler<D> handler);
}
