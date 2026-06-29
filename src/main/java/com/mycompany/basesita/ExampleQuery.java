package com.mycompany.basesita;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ExampleQuery {

    public static void ejecutarEjemplo() {
        try (Connection conexion = Conexion.conectar()) {

            if (conexion == null) {
                System.out.println("No se pudo establecer la conexión para la consulta");
                return;
            }

            String sql = "SELECT actor_id, first_name, last_name FROM actor LIMIT 10";

            try (PreparedStatement ps = conexion.prepareStatement(sql);
                 ResultSet rs = ps.executeQuery()) {

                System.out.println("Resultados de la consulta de ejemplo (tabla actor):");
                
                while (rs.next()) {
                    int id = rs.getInt("actor_id");
                    String nombre = rs.getString("first_name");
                    String apellido = rs.getString("last_name");
                    System.out.printf("%d: %s %s%n", id, nombre, apellido);
                }
                

            } catch (SQLException e) {
                System.out.println("Error al ejecutar la consulta: " + e.getMessage());
            }

        } catch (SQLException e) {
            System.out.println("Error al cerrar la conexión: " + e.getMessage());
        }
    }
    
    public static void insertarEjemplo() {
        try (Connection conexion = Conexion.conectar()) {

            if (conexion == null) {
                System.out.println("No se pudo establecer la conexión para la inserción");
                return;
            }

            String sql = "INSERT INTO actor (first_name, last_name) VALUES (?, ?)";

            try (PreparedStatement ps = conexion.prepareStatement(sql)) {

                ps.setString(1, "Juan");
                ps.setString(2, "Pérez");

                int filasInsertadas = ps.executeUpdate();

                if (filasInsertadas > 0) {
                    System.out.println("Inserción exitosa: " + filasInsertadas + " fila(s) insertada(s)");
                } else {
                    System.out.println("No se insertó ninguna fila");
                }

            } catch (SQLException e) {
                System.out.println("Error al insertar: " + e.getMessage());
            }

        } catch (SQLException e) {
            System.out.println("Error al cerrar la conexión: " + e.getMessage());
        }
    }
}
