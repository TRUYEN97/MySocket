/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Unicast.commons.Actions;

import Unicast.commons.Enum.ACTION;


/**
 *
 * @author Administrator
 */
public class UpdateProject extends simplePackage{
    
    private final String project;
    
    public UpdateProject(String project) {
        super(ACTION.UPDATE_PROGRAM);
        this.project = project;
    }
    
    public String getProjectName(){
        return project;
    }
    
}
