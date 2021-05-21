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
public class avl {
    private nodoavl raiz;
    public static List<Usuarios> listau = new ArrayList<>();
    public avl(){
        raiz = null;
    }
    
    //metodo que retorna la raiz
    public nodoavl getraiz(){
        return raiz;
    }
    
    public void clearall(){
        listau.clear();
    }
    //metodo que sirve para buscar un nodo del arbol avl y lo retorna
    public nodoavl buscarnodo(String cod, nodoavl raiztmp){
        if(raiz == null){
            return null;
        }else if(raiztmp.id.equals(cod)){
            return raiztmp;
        }else if(raiztmp.id.compareTo(cod) < 0){ //equivale a raiztmp.id < cod
            return buscarnodo(cod, raiztmp.subderecho);
        }else{
            return buscarnodo(cod, raiztmp.subizquierdo);
        }
    }
    
    //metodo que retorna el valor del factor de equilibrio
    public int fe(nodoavl n){
        if(n == null){
            return -1;
        }else{
            return n.factore;
        }
    }
    
    //rotacion simple a la izquierda
    public nodoavl rizq(nodoavl n){
        nodoavl aux = n.subizquierdo;
        n.subizquierdo = aux.subderecho;
        aux.subderecho = n;
        n.factore = Math.max(fe(n.subizquierdo), fe(n.subderecho))+1;
        aux.factore = Math.max(fe(aux.subizquierdo), fe(aux.subderecho))+1;
        return aux;
    }
    
    //rotacion simple a la derecha
    public nodoavl rder(nodoavl n){
        nodoavl aux = n.subderecho;
        n.subderecho = aux.subizquierdo;
        aux.subizquierdo = n;
        n.factore = Math.max(fe(n.subizquierdo), fe(n.subderecho))+1;
        aux.factore = Math.max(fe(aux.subizquierdo), fe(aux.subderecho))+1;
        return aux;
    }
    
    //rotacion izquierda izquierda
    public nodoavl rizqizq(nodoavl n){
        nodoavl tmp;
        n.subizquierdo = rder(n.subizquierdo);
        tmp = rizq(n);
        return tmp;
    }
    
    //rotacion derecha derecha
    public nodoavl rderder(nodoavl n){
        nodoavl tmp;
        n.subderecho = rizq(n.subderecho);
        tmp = rder(n);
        return tmp;
    }
    
    //metodo para insertar 
    public nodoavl insertar(nodoavl nuevo, nodoavl sub){
        nodoavl nuevopadre = sub;
        if(nuevo.id.compareTo(sub.id) < 0){ //equivale a nuevo.id < sub.id
            if(sub.subizquierdo == null){
                sub.subizquierdo = nuevo;
            }else{
                sub.subizquierdo = insertar(nuevo, sub.subizquierdo);
                if((fe(sub.subizquierdo) - fe(sub.subderecho)) == 2){
                    if(nuevo.id.compareTo(sub.subizquierdo.id) < 0){
                        nuevopadre = rizq(sub);
                    }else{
                        nuevopadre = rizqizq(sub);
                    }
                }
            }
        }else if(nuevo.id.compareTo(sub.id) > 0){ //equivale a nuevo.id > sub.id
            if(sub.subderecho == null){
                sub.subderecho = nuevo;
            }else{
                sub.subderecho = insertar(nuevo, sub.subderecho);
                if((fe(sub.subderecho) - fe(sub.subizquierdo)) == 2){
                    if(nuevo.id.compareTo(sub.subderecho.id) > 0){
                        nuevopadre = rder(sub);
                    }else{
                        nuevopadre = rderder(sub);
                    }
                }
            }
        }else{
            System.out.println("Nodo Duplicado");
        }
        //Se actualiza la altura
        if((sub.subizquierdo == null) && (sub.subderecho != null)){
            sub.factore = sub.subderecho.factore + 1;
        }else if((sub.subderecho == null) && (sub.subizquierdo != null)){
            sub.factore = sub.subizquierdo.factore + 1;
        }else{
            sub.factore = Math.max(fe(sub.subizquierdo), fe(sub.subderecho)) + 1;
        }
        
        return nuevopadre;
    }
    
    //metodo que inserta como tal
    public void insertarnuevo(String i, String n, String c){
        nodoavl nuevo = new nodoavl(i, n, c);
        if(raiz == null){
            raiz = nuevo;
        }else{
            raiz = insertar(nuevo, raiz);
        }
        
    }
    
    //recorrido inorden
    public void inorden(nodoavl n){
        if(n != null){
            inorden(n.subizquierdo);
            System.out.println(n.id + ", " + n.nombre + "; ");
            inorden(n.subderecho);
        }
    }
    
    //recorrido preorden
    public void preorden(nodoavl n){
       if(n != null){
            System.out.println(n.id + ", " + n.nombre + "; ");
            inorden(n.subizquierdo);
            inorden(n.subderecho);
        } 
    }
    
    public List<Usuarios> preordenn(nodoavl n){
        Usuarios u = new Usuarios();
        if(n != null){
            preordenn(n.subizquierdo);
            u.id = n.id;
            u.nombre = n.nombre;
            u.contrasena = n.contrasena;
            //System.out.println(u.id + ", "+u.contrasena+", "+u.nombre);
            listau.add(u);
            preordenn(n.subderecho);
        }
        
        for(int i = 0; i < listau.size(); i++){
            System.out.println(listau.get(i).id);
            System.out.println(listau.get(i).nombre);
            System.out.println(listau.get(i).contrasena);
        }
        System.out.println("------------------------------------------");
        
        return listau;
    }
    
    //recorrido postorden
    public void postorden(nodoavl n){
        if(n != null){
            System.out.println(n.id + ", " + n.nombre + "; ");
            postorden(n.subizquierdo);
            postorden(n.subderecho);
        }
    }
    
    //metodos para graficar en graphviz
    //metodo string que retorna el 'nIDUSUARIO[label="<izq> |<f> info del usuario | <der>"];'
    public String node(nodoavl n){
        String respuesta = "";
        respuesta+="n"+n.id+"[label=\"<izq> | <f>"+n.id+"\\n"+n.nombre+" |<der>\"];\n";
        return respuesta;
    }
    
    //metodo string que retorna el '"nIDUSUARIO":DER|IZQ -> "nIDUSUARIO":f;'
    public String apuntadores(nodoavl n){
        String respuesta = "";
        respuesta+=node(n);
        
        if(n.subizquierdo != null){
            respuesta+=apuntadores(n.subizquierdo);
            respuesta+="\"n"+n.id+"\":izq -> \"n"+n.subizquierdo.id+"\":f;\n";
        }
        
        if(n.subderecho != null){
            respuesta+=apuntadores(n.subderecho);
            respuesta+="\"n"+n.id+"\":der -> \"n"+n.subderecho.id+"\":f;\n";
        }
        
        return respuesta;
    }
    
    //metodo que retorna todo un string de lo que va dentro del documento.dot
    public String retorno(){
        String respuesta = "";
        respuesta+="digraph usuarios{\n" +
                    "edge [color = blue, splines = ortho];\n" +
                    "node [shape = record, style = filled, fillcolor=lightblue, height = .1];\n";
        respuesta+=apuntadores(this.raiz)+"\n";
        respuesta+="}\n";
        
        return respuesta;
    }
    
    public void dibujar(){
        System.out.println(retorno());
        FileWriter fichero = null;
        PrintWriter pw = null;
        try{
            fichero = new FileWriter("./Graphviz/usuarios.dot");
            pw = new PrintWriter(fichero);
            
            pw.println(retorno());
            
            System.out.println("Esta imprimiendo wiii ");
        }catch(Exception e){
            System.out.println("Error: "+e);
        }finally{
            try{
                if(null != fichero){
                    fichero.close();
                    System.out.println("Se cerro el fichero :) ");
                }else{
                    System.out.println("No se ha cerrado el fichero :( ");
                }
            }catch(Exception ee){
                System.out.println("Error: "+ee);
            }
        }
        
        //ejecutar comandos en consola
        
        try{
            Process p = Runtime.getRuntime().exec("dot -Tpng ./Graphviz/usuarios.dot -o ./Graphviz/usuarios.png");
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
                System.out.println("No se ha ejecutado con exito el comando en consola :( ");
            }
        }catch(IOException | InterruptedException io){
            System.out.println("Error: "+io);
        }
    }
    
    //modificar el elemento en el nodo indicado
    public void modificar(String cod, String nom, String contra){
        nodoavl modificar = buscarnodo(cod, this.raiz);
        String n = modificar.nombre;
        String c = modificar.contrasena;
        String rn = modificar.nombre.replaceAll(n, nom);
        String rc = modificar.contrasena.replaceAll(c, contra);
        modificar.nombre = rn;
        modificar.contrasena = rc;
    }
    
    //eliminar el nodo deseado
    public boolean eliminar(String cod){
        nodoavl aux = this.raiz;
        nodoavl padre = this.raiz;
        boolean eshijoizq = true;
        
        //ve si es un hijo izquierdo o no, busca el nodo a eliminar
        while(!(aux.id.equals(cod))){
            padre = aux;
            if(cod.compareTo(aux.id) < 0){ //equivale a cod < aux.id
                eshijoizq = true;
                aux = aux.subizquierdo;
            }else{
                eshijoizq = false;
                aux = aux.subderecho;
            }
            
            if(aux == null){
                return false;
            }
        }
        
        //el primer caso es si el nodo ya no tiene ningun hijo
        if(aux.subizquierdo == null && aux.subderecho == null){
            if(aux == this.raiz){
                raiz = null;
            }else if(eshijoizq){
                padre.subizquierdo = null;
            }else{
                padre.subderecho = null;
            }
        }
        
        //el otro caso es por si no tiene ningun hijo del lado derecho
        else if(aux.subderecho == null){
            if(aux == this.raiz){
                raiz = aux.subizquierdo;
            }else if(eshijoizq){
                padre.subizquierdo = aux.subizquierdo;
            }else{
                padre.subderecho = aux.subizquierdo;
            }
        }
        
        //mira si el nodo no tiene ningun hijo izquierdo
        else if(aux.subizquierdo == null){
            if(aux == this.raiz){
                raiz = aux.subderecho;
            }else if(eshijoizq){
                padre.subizquierdo = aux.subderecho;
            }else{
                padre.subderecho = aux.subderecho;
            }
        }
        
        //en este ultimo caso, entra es este else si hay mas hijos de cualquiera de los dos lados.
        else{
            //en este nodo se va a almacenar el nodo que se va reemplazar
            nodoavl reemplazo = reemplazar(aux);
            
            if(aux == this.raiz){
                raiz = reemplazo;
            }else if(eshijoizq){
                padre.subizquierdo = reemplazo;
            }else{
                padre.subderecho = reemplazo;
            }
            reemplazo.subizquierdo = aux.subizquierdo;
        }
        
        //Se actualiza la altura
        if((aux.subizquierdo == null) && (aux.subderecho != null)){
            aux.factore = aux.subderecho.factore + 1;
        }else if((aux.subderecho == null) && (aux.subizquierdo != null)){
            aux.factore = aux.subizquierdo.factore + 1;
        }else{
            aux.factore = Math.max(fe(aux.subizquierdo), fe(aux.subderecho)) + 1;
        }
        
        return true;
    }
    
    //metodo que se encarga de devolver el nodo a reemplazar
    public nodoavl reemplazar(nodoavl r){
        nodoavl rpadre = r;
        nodoavl reemplazo = r;
        nodoavl aux = r.subderecho;
        
        //recorrer para encontrar el nodo que va a reemplazar
        while(aux != null){
            rpadre = reemplazo;
            reemplazo = aux;
            aux = aux.subizquierdo;
        }
        
        if(reemplazo != r.subderecho){
            rpadre.subizquierdo = reemplazo.subderecho;
            reemplazo.subderecho = r.subderecho;
        }
        
        return reemplazo;
    }
}