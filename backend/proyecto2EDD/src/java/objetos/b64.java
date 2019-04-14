/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objetos;

import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author marianasic
 */

@XmlRootElement
public class b64 {
    
    public String url;
    
    public b64(){
        this.url="";
    }
    
    public b64(String url){
        this.url = url;
    }
}
