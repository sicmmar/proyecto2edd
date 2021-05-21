
package objetos;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import javax.xml.bind.annotation.XmlRootElement;
/**
 *
 * @author marianasic
 */

@XmlRootElement
public class objeto {
    public int codigo;
    public String nombre;
    public String costo;
    public String tiempo;

    public objeto(){
        this.codigo = 0;
        this.nombre = "";
        this.costo = "";
        this.tiempo = "";
    }
    public objeto(int codigo, String nombre, String costo, String tiempo) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.costo = costo;
        this.tiempo = tiempo;
    }

    @Override
    public String toString() {
        return costo + "\n"+tiempo;
    }
    
}
