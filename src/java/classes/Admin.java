/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author marianasic
 */

@XmlRootElement
public class Admin {
    public String user;
    public String pass;
    
    public Admin(){
        this.user="";
        this.pass="";
    }
    
    public Admin(String user, String pass){
        this.user = user;
        this.pass = pass;
    }
}
