/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Unicast.commons.Actions;

import Unicast.commons.Enum.ACTION;
import java.io.Serializable;

/**
 *
 * @author Administrator
 */
public class SimplePackage implements Serializable{
    private final ACTION action;

    public SimplePackage(ACTION action) {
        this.action = action;
    }

    public ACTION getAction() {
        return action;
    }
    
}
