package com.mycompany.basesita;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.ResultSetMetaData;
import javax.swing.table.DefaultTableModel;

public class ExampleQuery {

    // Método para obtener los datos y prepararlos para el JTable
    public static DefaultTableModel obtenerModeloAlumnos() {
        // Creamos el modelo de la tabla que la interfaz gráfica va a consumir
        DefaultTableModel modelo = new DefaultTableModel();

        try (Connection conexion = Conexion.conectar()) {
            if (conexion == null) {
                System.out.println("No se pudo establecer la conexión para la consulta");
                return modelo;
            }

            // Usamos tu consulta SQL para traer Alumnos y Grupos
            String sql = "SELECT a.matricula AS 'Matrícula', "
                       + "CONCAT(a.nombre, ' ', a.apellido_paterno, ' ', IFNULL(a.apellido_materno, '')) AS 'Alumno', "
                       + "g.nombre AS 'Grupo', "
                       + "g.turno AS 'Turno', "
                       + "i.ciclo_escolar AS 'Ciclo' "
                       + "FROM inscripciones i "
                       + "INNER JOIN alumnos a ON i.id_alumno = a.id_alumno "
                       + "INNER JOIN grupos g ON i.id_grupo = g.id_grupo;";

            try (PreparedStatement ps = conexion.prepareStatement(sql);
                 ResultSet rs = ps.executeQuery()) {

                // Extraemos los metadatos para saber cuántas y cuáles columnas hay
                ResultSetMetaData rsMd = rs.getMetaData();
                int cantidadColumnas = rsMd.getColumnCount();

                // 1. Llenamos los encabezados de la tabla
                for (int i = 1; i <= cantidadColumnas; i++) {
                    modelo.addColumn(rsMd.getColumnLabel(i));
                }

                // 2. Llenamos las filas con los datos
                while (rs.next()) {
                    Object[] fila = new Object[cantidadColumnas];
                    for (int i = 0; i < cantidadColumnas; i++) {
                        fila[i] = rs.getObject(i + 1);
                    }
                    modelo.addRow(fila);
                }

            } catch (SQLException e) {
                System.out.println("Error al ejecutar la consulta: " + e.getMessage());
            }

        } catch (SQLException e) {
            System.out.println("Error al cerrar la conexión: " + e.getMessage());
        }

        return modelo;
    }
}