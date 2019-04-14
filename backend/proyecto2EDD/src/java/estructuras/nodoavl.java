package estructuras;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author marianasic
 */
public class nodoavl {
    String id, nombre, contrasena;
    int factore;
    nodoavl subizquierdo, subderecho;
    
    public nodoavl(String id, String nombre, String contrasena){
        this.id = id;
        this.nombre = nombre;
        this.contrasena = contrasena;
        this.factore = 0;
        this.subizquierdo = null;
        this.subderecho = null;
    }
}
