/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import java.util.*;
import javax.ws.rs.core.Application;
import objetos.*;

/**
 *
 * @author marianasic
 */
@javax.ws.rs.ApplicationPath("webresources")
public class ApplicationConfig extends Application {
    //instanciar las estructuras de datos estaticas
    //public static avl arbol = new avl();
    static List<Usuarios> UsuarioSerializado = new ArrayList<>();
    static List<objeto> ObjetoAuxiliar = new ArrayList<>();

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

    /**
     * Do not modify addRestResourceClasses() method.
     * It is automatically populated with
     * all resources defined in the project.
     * If required, comment out calling this method in getClasses().
     */
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(classes.UsuariosResource.class);
    }
    
}
