/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Unicast.commons.Actions;

import Unicast.commons.Enum.ACTION;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author Administrator
 */
public class MapRowsParameter extends SimplePackage {

    private final List<HashMap<String, String>> rows;

    public MapRowsParameter(ACTION action) {
        super(action);
        this.rows = new ArrayList<>();
    }

    public MapRowsParameter() {
        super(null);
        this.rows = new ArrayList<>();
    }

    public void addRow(HashMap<String, String> row) {
        this.rows.add(row);
    }
    
    public List<HashMap<String, String>> getAll(){
        return new ArrayList<>(this.rows);
    }

    public void resetRow() {
        this.rows.clear();
    }
}
