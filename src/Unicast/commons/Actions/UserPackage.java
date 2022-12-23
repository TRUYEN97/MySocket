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
public class UserPackage extends SimplePackage{
    private final String userName;
    private final String token;
    private final String level;
   
    
    public UserPackage(String userName,String level, String token) {
        super(ACTION.LOGIN);
        this.userName = userName;
        this.token = token;
        this.level = level;
    }

    public String getLevel() {
        return level;
    }

    public String getUserName() {
        return userName;
    }

    public String getToken() {
        return token;
    }
    
    
    
}
