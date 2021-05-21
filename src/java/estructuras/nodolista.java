
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
public class nodolista {
    
    private nodolista siguiente;
    private nodolista anterior;
    
    private Object valor1;
    
    public nodolista(){
        this.siguiente = null;
        this.anterior = null;
        this.valor1 = "";
    }

    public nodolista(Object valor1) {
        this.valor1 = valor1;
    }

    @Override
    public String toString() {
        return valor1.toString();
    }
    
    

    /**
     * @return the siguiente
     */
    public nodolista getSiguiente() {
        return siguiente;
    }

    /**
     * @param siguiente the siguiente to set
     */
    public void setSiguiente(nodolista siguiente) {
        this.siguiente = siguiente;
    }

    /**
     * @return the anterior
     */
    public nodolista getAnterior() {
        return anterior;
    }

    /**
     * @param anterior the anterior to set
     */
    public void setAnterior(nodolista anterior) {
        this.anterior = anterior;
    }

    /**
     * @return the valor1
     */
    public Object getValor1() {
        return valor1;
    }

    /**
     * @param valor1 the valor1 to set
     */
    public void setValor1(Object valor1) {
        this.valor1 = valor1;
    }


}
