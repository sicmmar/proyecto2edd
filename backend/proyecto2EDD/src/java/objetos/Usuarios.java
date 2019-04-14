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
public class Usuarios {
    public String id;
    public String nombre;
    public String contrasena;
    
    public Usuarios(){
        this.id = "";
        this.nombre = "";
        this.contrasena = "";
    }
    
    public Usuarios(String id, String nombre, String contrasena){
        this.id = id;
        this.nombre = nombre;
        this.contrasena = contrasena;
    }
}
