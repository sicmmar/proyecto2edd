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
public class lista {
    
    private int tamano;
    private nodolista raiz;
    
    public lista(){
        this.tamano = 0;
        this.raiz = null;
    }

    /**
     * @return the tamano
     */
    public int getTamano() {
        return tamano;
    }

    /**
     * @param tamano the tamano to set
     */
    public void setTamano(int tamano) {
        this.tamano = tamano;
    }

    /**
     * @return the raiz
     */
    public nodolista getRaiz() {
        return raiz;
    }

    /**
     * @param raiz the raiz to set
     */
    public void setRaiz(nodolista raiz) {
        this.raiz = raiz;
    }

    public void RemoveAt(int index)
    {
        if(index == 0)
        {
            raiz = raiz.getSiguiente();
        }
        else
        {
                int contador = 0;
                nodolista temporal = raiz;
                while(contador < index -1)
                {
                    temporal = temporal.getSiguiente();
                    contador ++;
                }
                temporal.setSiguiente(temporal.getSiguiente().getSiguiente());  
                temporal.getSiguiente().setAnterior(temporal);
        }
        tamano --;
    }
    
    public int getIndexOf(Object obj)
    {
        int index = 0;
        nodolista temporal = raiz;
        
        for (int i = 0; i <tamano; i++) {
            if(temporal.getValor1().equals(obj))
            {
                break;
            }
            else{
                temporal = temporal.getSiguiente();
                index++;
            }
        }
        return index;
    }
    
    public boolean Contains(Object obj)
    {
        boolean contiene = false;
        nodolista temporal = raiz;
        
        for (int i = 0; i <tamano; i++) {
            if(temporal.getValor1().equals(obj))
            {
                contiene = true;
                break;
            }
            else{
                temporal = temporal.getSiguiente();
            }
        }
        return contiene;
    }
    
    //este metodo agrega un nodo al principio de la lista
    public void addPrimero(Object obj1)
    {
        if(raiz == null)
        {
            raiz = new nodolista(obj1);
        }
        else
        {
            nodolista temp = raiz;
            nodolista nuevo = new nodolista(obj1);
            nuevo.setSiguiente(temp);
            raiz = nuevo;
        }
        tamano++;
    }
      
    //da el valor que halla en el nodo que se le especifique en el parametro index
    public nodolista getnodolista(int index)
    {
        int contador = 0;
        nodolista temporal = raiz;
        while(contador < index)
        {
            temporal = temporal.getSiguiente();
            contador ++;
        }
        return temporal;
    }
    
    public Object getValor1(int index)
    {
        int contador = 0;
        nodolista temporal = raiz;
        while(contador < index)
        {
            temporal = temporal.getSiguiente();
            contador ++;
        }
        return temporal.getValor1();
    }
   
    
    //elimina solo el primer nodo
    public void eliminarPrimero()
    {
        raiz = raiz.getSiguiente();
        tamano--;
    }
    
    //corta todos los nodos que hallan desde el numero escogido en adelante
    //ejemplo 1 -> 2 -> 3 -> 4 -> 5 -> 6
    // si se escoge el 3 cortaría el 4 5 y 6 no prro >:v que mamon jajaja NADAAAAAAAA, NADAAAAAAAAAAAAAAAAAAAAAA no hay excusa >:V
    /*
    public void cortar(int index)
    {
        int contador = 0;
        nodolista temporal = raiz;
        while(contador < index-1)
        {
            temporal = temporal.getSiguiente();
            contador ++;
        }
        temporal.setSiguiente(null);
        tamano = index;
    }
    */
    
    public void vaciar()
    {
        raiz = null;
        tamano = 0;
    }
    
    //devuelve el tamaño de la lista
    public int tamano()
    {
         return tamano;
    }
    
    //dice si la lista esta vacia o no
    public boolean estaVacia()
    {
        return(raiz == null); 
    }  
    
}
