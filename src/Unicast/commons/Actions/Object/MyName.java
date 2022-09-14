/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Unicast.commons.Actions.Object;

import Unicast.commons.Actions.Object.ObjectPackage;
import Unicast.commons.Enum.ACTION;
import java.util.Map;

/**
 *
 * @author Administrator
 */
public class MyName extends ObjectPackage<Map> {

    private final String pcName;
    private final String os;
    private final int line;
    
    public MyName(String pcName, String os, int line, Map card) {
        super(ACTION.I_AM, card);
        this.pcName = pcName;
        this.os = os;
        this.line = line;
    }

    public int getLine() {
        return line;
    }

    public String getOS() {
        return os;
    }

    public String getPcName() {
        return pcName;
    }
}
