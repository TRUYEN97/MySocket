/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Unicast.commons.Actions.Object.List;

import Unicast.commons.Actions.Object.ObjectPackage;
import Unicast.commons.Enum.ACTION;
import java.util.List;

/**
 *
 * @author Administrator
 */
public class ListPackage<T> extends ObjectPackage<List<T>>{

    public ListPackage(ACTION action, List<T> data) {
        super(action, data);
    }
}
