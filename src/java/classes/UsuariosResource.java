/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import java.util.*;
import estructuras.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import javax.ws.rs.core.*;
import javax.ws.rs.*;
import javax.xml.bind.DatatypeConverter;
import objetos.*;
        
/**
 * REST Web Service
 *
 * @author marianasic
 */

//path que define como es que se va a acceder al recurso:
@Path("/usuarios")
public class UsuariosResource {
    
    public static avl arbol = new avl();
    public static grafomatriz matriz = new grafomatriz();
    
    @Context
    private UriInfo context;

    /**
     * Creates a new instance of UsuariosResource
     */
    public UsuariosResource() {
    }

    
    /**
     * 
     * @param a
     * @return 
     */
    
    @Path("/login")
    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public Admin postJson(Admin a){
        Admin respuesta = new Admin();
        
        if(a.user.equals("admin") && a.pass.equals("201504051")){
            respuesta.user = "true";
            respuesta.pass = "true";
        }else{
            respuesta.user = "false";
            respuesta.pass = "false";
        }
        
        return respuesta;
    }
    
    /**
     * 
     * @param u
     * @return 
     */
    @Path("/newusuario")
    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public Usuarios postJson(Usuarios u){
        arbol.insertarnuevo(u.id, u.nombre, u.contrasena);
        arbol.dibujar();
        return u;
    }
    
    /**
     * 
     * @param o
     * @return 
     */
    @Path("/newdestino")
    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public objeto postDestino(objeto o){
        objeto raiz = new objeto(0, "0", "", "");
        matriz.setValor(0, 0, raiz);
        matriz.addEncabezados(o, o);
        matriz.dibujar();
        return o;
    }
    
    /**
     * 
     * @param ru
     * @return 
     */
    @Path("/newruta")
    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public ruta postRuta(ruta ru){
        objeto tmp = new objeto(ru.pto1, String.valueOf(ru.pto2), String.valueOf(ru.costo), String.valueOf(ru.tiempo));
        int punto1 = matriz.getfila(ru.pto1);
        int punto2 = matriz.getfila(ru.pto2);
        
        if(punto1 == Integer.MAX_VALUE || punto2 == Integer.MAX_VALUE){
            ruta r = new ruta(0, 0, 0, 0);
            return r;
        }else{
            System.out.println("Punto 1: "+punto1+". Punto 2: "+punto2);
            matriz.setValor(punto1, punto2, tmp);
            matriz.setValor(punto2, punto1, tmp);
        }
        matriz.dibujar();
        return ru;
    } 
    
    @Path("/eliminarruta")
    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public ruta deleteRuta(ruta ru){
        objeto ob = new objeto(0, "", String.valueOf(Integer.MAX_VALUE), String.valueOf(Integer.MAX_VALUE));
        int punto1 = matriz.getfila(ru.pto1);
        int punto2 = matriz.getfila(ru.pto2);
        
        if(punto1 == Integer.MAX_VALUE || punto2 == Integer.MAX_VALUE){
            ruta r = new ruta(0, 0, 0, 0);
            return r;
        }else{
            System.out.println("Punto 1: "+punto1+". Punto 2: "+punto2);
            matriz.setValor(punto1, punto2, ob);
            matriz.setValor(punto2, punto1, ob);
        }
        matriz.dibujar();
        return ru;
    }
    
    /**
     * 
     * @return 
     */
    @Path("/getusuarios")
    @GET
    @Produces("application/json")
    public List<Usuarios> getJson(){
        arbol.clearall();
        List tmp = arbol.preordenn(arbol.getraiz());
        return tmp;
    }
    
    /**
     * 
     * @return 
     */
    @Path("/getrutas")
    @GET
    @Produces("application/json")
    public List<objeto> getRutas(){
        return matriz.getdestinos();
    }
    
    @Path("/getdestinos")
    @GET
    @Produces("application/json")
    public List<objeto> getDestinos(){
        return matriz.getpaises();
    }
    
    /**
     * 
     * @param u
     * @return 
     */
    @Path("/editarusuario")
    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public Usuarios editJson(Usuarios u){
        arbol.modificar(u.id, u.nombre, u.contrasena);
        arbol.dibujar();
        return u;
    }
    
    /**
     * 
     * @param u
     * @return 
     */
    @Path("/eliminarusuario")
    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public Usuarios deleteJson(Usuarios u){
        arbol.eliminar(u.id);
        arbol.dibujar();
        return u;
    }
    
    @Path("/imgusuarios")
    @GET
    @Produces("application/json")
    public b64 getB64(){
        b64 b = new b64();
        try{
            String imgDS = DatatypeConverter.printBase64Binary(Files.readAllBytes(Paths.get("/home/marianasic/apache-tomcat-8.0.15/bin/Graphviz/usuarios.png")));
            b.url = imgDS;
            return b;
        }catch(IOException ioe){
            System.out.println("Error: "+ ioe);
            b.url = "Error: "+ioe;
            return b;
        }
    }
    
    @Path("/imgmatriz")
    @GET
    @Produces("application/json")
    public b64 b64Matriz(){
        b64 b = new b64();
        try{
            String img = DatatypeConverter.printBase64Binary(Files.readAllBytes(Paths.get("/home/marianasic/apache-tomcat-8.0.15/bin/Graphviz/matriz.png")));
            b.url = img;
            return b;
        }catch(IOException ioe){
            System.out.println("Error: "+ioe);
            b.url = "Error: "+ioe;
            return b;
        }
    }
    
}