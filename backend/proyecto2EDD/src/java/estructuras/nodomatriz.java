
package estructuras;

import objetos.*;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author marianasic
 */
public class nodomatriz {
    
    private nodomatriz arriba;
    private nodomatriz abajo;
    private nodomatriz derecha;
    private nodomatriz izquierda;
    
    private objeto valor1;

    public nodomatriz() {
        this.valor1 = null;
        this.izquierda = null;
        this.derecha = null;
        this.abajo = null;
        this.arriba = null;
    }

    public nodomatriz(objeto valor1) {
        this.valor1 = valor1;
    }

    @Override
    public String toString() {
        if(valor1 != null){
            return valor1.toString();
        }else{
            return Integer.MAX_VALUE + "\n" + Integer.MAX_VALUE;
        }
    }
    
    

    /**
     * @return the arriba
     */
    public nodomatriz getArriba() {
        return arriba;
    }

    /**
     * @param arriba the arriba to set
     */
    public void setArriba(nodomatriz arriba) {
        this.arriba = arriba;
    }

    /**
     * @return the abajo
     */
    public nodomatriz getAbajo() {
        return abajo;
    }

    /**
     * @param abajo the abajo to set
     */
    public void setAbajo(nodomatriz abajo) {
        this.abajo = abajo;
    }

    /**
     * @return the derecha
     */
    public nodomatriz getDerecha() {
        return derecha;
    }

    /**
     * @param derecha the derecha to set
     */
    public void setDerecha(nodomatriz derecha) {
        this.derecha = derecha;
    }

    /**
     * @return the izquierda
     */
    public nodomatriz getIzquierda() {
        return izquierda;
    }

    /**
     * @param izquierda the izquierda to set
     */
    public void setIzquierda(nodomatriz izquierda) {
        this.izquierda = izquierda;
    }

    /**
     * @return the valor1
     */
    public objeto getValor1() {
        return valor1;
    }

    /**
     * @param valor1 the valor1 to set
     */
    public void setValor1(objeto valor1) {
        this.valor1 = valor1;
    }

    
}
