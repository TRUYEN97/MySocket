/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Unicast.commons.Interface;

/**
 *
 * @author Administrator
 * @param <T>
 */
public interface IObjectReceiver<T> {

    void setHandler(ISend<T> data);

    void receiver(T object);
}
