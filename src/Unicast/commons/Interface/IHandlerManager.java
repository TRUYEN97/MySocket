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
public interface IHandlerManager<K, D> extends IListNumble<K, D>{
    
    IListNumble<K, D> getOnlineNumble();
    
    void add(ClientHandler<D> handler);
    
    void shutdown();
}
