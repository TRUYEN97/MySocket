/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Unicast.commons.Actions.Object;

import Unicast.commons.Actions.simplePackage;
import Unicast.commons.Enum.ACTION;

/**
 *
 * @author Administrator
 * @param <T>
 */
public class ObjectPackage<T> extends simplePackage {

    protected final T data;

    public ObjectPackage(ACTION action, T data) {
        super(action);
        this.data = data;
    }
    
    public T getdata(){
        return data;
    }

}
