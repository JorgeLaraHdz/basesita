/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.basesita;
import java.sql.Connection;
import java.sql.SQLException;
/**
 *
 * @author Mento
 */
public class Basesita {

    public static void main(String[] args) {
        System.out.println("Holis");
        
        try (Connection conexion = Conexion.conectar()) {

            if (conexion != null) {
                System.out.println("La base de datos está disponible");
                // Ejecuta un ejemplo de consulta sobre la base sakila
                ExampleQuery.ejecutarEjemplo();
                //System.out.println("\n--- Prueba de inserción ---\n");
                //ExampleQuery.insertarEjemplo();
            }
            
        } catch (SQLException e) {
            System.out.println("Error al cerrar la conexión");
            System.out.println(e.getMessage());
        }
    }
}
