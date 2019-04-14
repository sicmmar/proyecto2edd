
package estructuras;
import java.io.*;
import java.util.*;
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
public class grafomatriz {
    //esta bonita prro >:v
    private int dimension;
    private nodomatriz raiz;
    private nodomatriz cabeza;
    private nodomatriz cabeza2;
    
    public grafomatriz(){
        this.dimension = 1;
        raiz = new nodomatriz(null);
    }
    
    public void addEncabezados(objeto fila, objeto columna){
        
        nodomatriz nodoColumna = new nodomatriz(columna);
        nodomatriz nodoFila = new nodomatriz(fila);
        
        //Agrego el nodoCabecera Columna
        nodomatriz tmpColumna = raiz;
        while(tmpColumna.getDerecha()!= null){
            tmpColumna = tmpColumna.getDerecha();
        }
        tmpColumna.setDerecha(nodoColumna);
        nodoColumna.setIzquierda(tmpColumna);
        
        //Agrego el nodoCabecera Fila
        nodomatriz tmpFila = raiz;
        while(tmpFila.getAbajo() != null){
            tmpFila = tmpFila.getAbajo();
        }
        tmpFila.setAbajo(nodoFila);
        nodoFila.setArriba(tmpFila);
        
        //Agrego los nodos Contenido
        tmpFila = tmpFila.getAbajo();
        tmpColumna = tmpColumna.getDerecha();
        for (int i = 0; i < this.dimension; i++) {
            objeto ob = new objeto(0, "", String.valueOf(Integer.MAX_VALUE), String.valueOf(Integer.MAX_VALUE));
            nodomatriz nodoValor = new nodomatriz(ob);
            
            if(i==this.dimension-1){
                tmpFila.setDerecha(nodoValor);
                nodoValor.setIzquierda(tmpFila);
                
                tmpColumna.setAbajo(nodoValor);
                nodoValor.setArriba(tmpColumna);
                
                tmpFila = tmpFila.getDerecha();
                tmpColumna = tmpColumna.getAbajo();
            }else{
                tmpFila.setDerecha(nodoValor);
                nodoValor.setIzquierda(tmpFila);
                
                nodoValor = new nodomatriz(ob);
                tmpColumna.setAbajo(nodoValor);
                nodoValor.setArriba(tmpColumna);
                
                tmpFila = tmpFila.getDerecha();
                tmpColumna = tmpColumna.getAbajo();
                
                nodomatriz tmp1=tmpFila;
                nodomatriz tmp2=tmpColumna;
                int cont=i;
                while(cont>=0){
                    tmp1 = tmp1.getIzquierda();
                    tmp2 = tmp2.getArriba();
                    cont--;
                }
                tmp1 = tmp1.getArriba();
                tmp2 = tmp2.getIzquierda();
                cont=i;
                while(cont>=0){
                    tmp1 = tmp1.getDerecha();
                    tmp2 = tmp2.getAbajo();
                    cont--;
                }
                
                tmpFila.setArriba(tmp1);
                tmp1.setAbajo(tmpFila);
                
                tmpColumna.setIzquierda(tmp2);
                tmp2.setDerecha(tmpColumna);
            }
        }
        //aumento en uno la dimensión
        this.dimension++;
    }
                
    public grafomatriz(int dimension){
        this.dimension = dimension;
        raiz = null;
        CrearMatriz(dimension);
    }   
    
    private void CrearMatriz(int dimension) {
        
        lista list = new lista();
        lista list2 = new lista();
        boolean par = true;
        int contador = 1;
        
        int numero = dimension; 
        while (numero>0){numero -= 2;} 
        if (numero == 0){
            //System.out.println("es par"); 
            par = true;
        }
        else {
            //System.out.println("es impar"); 
            par = false;
        }
        
        int iteracion;
        if (par) {
            iteracion = (dimension/2);
        }
        else
        {
            iteracion = dimension;
            iteracion ++;
            iteracion /=2;
        }
        
        
        for(int ii = 0; ii< iteracion; ii++){
            
              list.vaciar();
              cabeza = new nodomatriz(null);
              list.addPrimero(cabeza);
              contador = 1;
            
              //PRIMERA FILA EN CREARSE
            for (int i = 0; i < dimension - 1 ; i++) {
                
                //CREO EL NODO Y LO VOY ENLAZANDO, AL FINAL QUEDA: cabeza <-> nuevo <-> otros
                nodomatriz temp = cabeza;
                nodomatriz n = new nodomatriz(null);
                n.setDerecha(temp);
                temp.setIzquierda(n);
               
                //SI HAY ELEMENTOS EN LA LISTA2 ES PORQUE HAY OTRA ITERACION, EMPIEZO A ENLAZAR ARRIBA Y ABAJO
                 if (list2.tamano()!=0) {
                    nodomatriz NodoQueEstaArriba = (nodomatriz)list2.getValor1(dimension-contador);
                    temp.setArriba(NodoQueEstaArriba);
                    NodoQueEstaArriba.setAbajo(temp);
                    contador ++;
                    
                    //SI ESTOY EN MI ULTIMA ITERACION ENLAZO ARRIBA Y ABAJO EL ULTIMIO NODO CREADO
                          if(i==dimension-2)
                            {
                                nodomatriz NodoArriba = (nodomatriz)list2.getValor1(dimension-(contador+1));
                                n.setArriba(NodoArriba);
                                NodoArriba.setAbajo(n);
                            }
                }
                 //GUARDO LA RAIZ DE LA MATRIZ LA CERO CERO (0,0)
                 if(ii==0 && i==dimension - 2)
                 {
                     raiz = n;
                 }
                 
                  cabeza = n;
                
                list.addPrimero(n);
                
            }
            
              list2.vaciar();
              cabeza2 = new nodomatriz(null);
              list2.addPrimero(cabeza2);
              contador = 1;
             
              //capich :D
              //hace las conexiones entre las listas de ayuda solo si la matriz es par
              //si no es par, lo va a hacer hasta la penultima iteracion y por eso es: iteracion-1
              if (par || (par==false && ii!=iteracion-1)) {
                   for (int i = 0; i < dimension - 1; i++) {
                
                    nodomatriz NodoQueEstaArriba = (nodomatriz)list.getValor1(dimension-contador);
                    nodomatriz temp = cabeza2;
                    nodomatriz n = new nodomatriz(null);

                    n.setDerecha(temp);
                    temp.setIzquierda(n);
                    temp.setArriba(NodoQueEstaArriba);
                    NodoQueEstaArriba.setAbajo(temp);

                    //SI ESTA EN LA ULTIMA ITERACION 
                    if(i==dimension-2)
                    {
                        nodomatriz NodoArriba = (nodomatriz)list.getValor1(dimension-(contador+1));
                        n.setArriba(NodoArriba);
                        NodoArriba.setAbajo(n);
                    }

                    cabeza2 = n;

                    list2.addPrimero(n);

                    contador ++;
                }
            }
        }
        
    }
    
    public objeto getValornodomatriz(int fila, int columna)
    {
         //BAJO LA CANTIDAD DE FILAS INDICADA
        nodomatriz temp = raiz;
        for (int i = 0; i < fila; i++) {
            temp = temp.getAbajo();
        }
        //ME VOY A LA DERECHA LA CANTIDAD DE COLUMNAS INDICADA
        for (int j = 0; j < columna; j++) {
            temp = temp.getDerecha();
        }
        return temp.getValor1();
    }
    
    public int getfila(int codigo){
        nodomatriz temp = raiz;
        
        for(int fila = 0; fila < this.dimension; fila++){
            if(temp.getValor1().codigo == codigo){
                return fila;
            }else{
                temp = temp.getAbajo();
            }
        }
        return Integer.MAX_VALUE;
    }
    
    public String getvalor(int codigo){
        nodomatriz temp = raiz;
        
        for(int fila = 0; fila < this.dimension; fila++){
            if(temp.getValor1().codigo == codigo){
                return temp.getValor1().nombre;
            }else{
                temp = temp.getAbajo();
            }
        }
        
        return "false";
    }
    
    public nodomatriz getNodo(int fila, int columna)
    {
         //BAJO LA CANTIDAD DE FILAS INDICADA
        nodomatriz temp = raiz;
        for (int i = 0; i < fila; i++) {
            temp = temp.getAbajo();
        }
        //ME VOY A LA DERECHA LA CANTIDAD DE COLUMNAS INDICADA
        for (int j = 0; j < columna; j++) {
            temp = temp.getDerecha();
        }
        return temp;
    }
    
    public void setValor(int fila, int columna, objeto data1)
    {
        //BAJO LA CANTIDAD DE FILAS INDICADA
        nodomatriz temp = raiz;
        for (int i = 0; i < fila; i++) {
            temp = temp.getAbajo();
        }
        //ME VOY A LA DERECHA LA CANTIDAD DE COLUMNAS INDICADA
        for (int j = 0; j < columna; j++) {
            temp = temp.getDerecha();
        }
        temp.setValor1(data1);
    }

    public nodomatriz getRaiz() {
        return raiz;
    }
    
    public void dibujar() {
        FileWriter fichero = null;
        PrintWriter pw = null;
        try{
            fichero = new FileWriter("./Graphviz/matriz.dot");
            pw = new PrintWriter(fichero);
            
            pw.println(recorrido());
            
        }catch(Exception e){
        }finally{
            try{
                if(null != fichero){
                    fichero.close();
                }
            }catch(Exception ee){
                System.out.println("Error: "+ee);
            }
        }
        
        //ejecutar comandos en consola
        
        try{
            Process p = Runtime.getRuntime().exec("dot -Tpng ./Graphviz/matriz.dot -o ./Graphviz/matriz.png");
            StringBuilder output = new StringBuilder();
            BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
            
            String line;
            while((line = reader.readLine()) != null){
                output.append(line).append("\n");
            }
            
            int exitval = p.waitFor();
            if(exitval == 0){
                System.out.println("Ejecutado con exito ");
                System.out.println(output);
            }else{
                
            }
        }catch(IOException | InterruptedException io){
            System.out.println("Error: "+ io);
        }
    }
    
    private String recorrido(){
        String recorrido = "digraph g{\n" +
                "    node [shape=box, style=filled, color=lightyellow];\n edge[color=gold]; \n";
        nodomatriz tmp = this.raiz;
        
        recorrido += "{\n";
        for (int fila = 0; fila < this.dimension; fila++) {
            for (int columna = 0; columna < this.dimension; columna++) {
                recorrido += "\n    "+fila+""+columna+" [label = \""+this.getNodo(fila, columna).getValor1()+"\"]";
            }
        }
        recorrido += "\n}\n";
        
        for (int fila = 0; fila < this.dimension; fila++) {
            recorrido += "{rank=same\n";
            for (int columna = 0; columna < this.dimension-1; columna++) {
                recorrido += "\n  "+fila+""+columna+" -> "+fila+""+(columna+1);
                recorrido += "\n  "+fila+""+(columna+1)+" -> "+fila+""+columna;
            }
            recorrido += "\n}";
        }
        
        for (int fila = 0; fila < this.dimension-1; fila++) {
            recorrido +="{";
            for (int columna = 0; columna < this.dimension; columna++) {
                recorrido += "\n  "+fila+""+columna+" -> "+(fila+1)+""+columna;
                recorrido += "\n  "+(fila+1)+""+columna+" -> "+fila+""+columna;
            }
            recorrido += "\n}";
        }
        recorrido += "\n}";
        return recorrido;
    }
    
    public int dim(){
        return dimension;
    }
    
    public void eliminar(){
        
    }
    
    public List<objeto> getdestinos(){
    List<objeto> listatmp = new ArrayList<>();
        
        for(int fila = 1; fila < this.dimension; fila++){
            for(int columna = 1; columna < this.dimension; columna++){
                try{
                    if(this.getNodo(fila, columna).getValor1().codigo == 0){
//                        objeto o = new objeto(0, "", "", "");
//                        listatmp.add(o);
                    }else{
                        listatmp.add((objeto) this.getNodo(fila, columna).getValor1());
                    }
                }catch(Exception e){
                    System.out.println("Error: "+e);
                }
                
            }
        }
        
        try{
            for(int i = 0; i < listatmp.size(); i++){
                System.out.println(listatmp.get(i).toString());
            }
        }catch(Exception e){
            System.out.println("Error: "+e);
        }
        
        return listatmp;
    }
    
    public List<objeto> getpaises(){
        List<objeto> lista = new ArrayList<>();
        
        for(int fila = 1; fila < this.dimension; fila++){
            lista.add((objeto) this.getNodo(0, fila).getValor1());
        }
        
        return lista;
    }
    
}
