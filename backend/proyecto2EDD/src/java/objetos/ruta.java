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
public class ruta {
    public int pto1;
    public int pto2;
    public int costo;
    public int tiempo;
    
    public ruta(){
        this.pto1 = 0;
        this.pto2 = 0;
        this.costo = 0;
        this.tiempo = 0;
    }
    
    public ruta(int pto1, int pto2, int costo, int tiempo){
        this.pto1 = pto1;
        this.pto2 = pto2;
        this.costo = costo;
        this.tiempo = tiempo;
    }

    @Override
    public String toString() {
        return "$" + costo +"/n" + tiempo+ " min";
    }
    
}
