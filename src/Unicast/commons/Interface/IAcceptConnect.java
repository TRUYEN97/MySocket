/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Unicast.commons.Interface;

import Unicast.commons.Interface.IObjectServerReceiver;

/**
 *
 * @author Administrator
 */
interface IAcceptConnect<T> extends IObjectServerReceiver<T>{
    
    String acceptable(T packageData);
}
