/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.autolavadosystem.controller;

import com.mycompany.autolavadosystem.conexion.ConexionDB;
import com.mycompany.autolavadosystem.model.VehiculoModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Rodo
 */
public class VehiculoController {
    public static void insertarVehiculo(VehiculoModel vehiculo) {
        try (Connection conn = ConexionDB.obtenerConexion()) {
            String sql = "INSERT INTO tbb_vehiculos (matricula, marca, modelo, color, anio, tipo, cliente) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, vehiculo.getMatricula());
            stmt.setString(2, vehiculo.getMarca());
            stmt.setString(3, vehiculo.getModelo());
            stmt.setString(4, vehiculo.getColor());
            stmt.setInt(5, vehiculo.getAnio());
            stmt.setString(6, vehiculo.getTipo());
            stmt.setString(7, vehiculo.getCliente());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static List<VehiculoModel> obtenerTodos() {
        List<VehiculoModel> lista = new ArrayList<>();
        try (Connection conn = ConexionDB.obtenerConexion()) {
            String sql = "SELECT * FROM tbb_vehiculos";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                VehiculoModel v = new VehiculoModel(
                    rs.getInt("idVehiculo"),
                    rs.getString("matricula"),
                    rs.getString("marca"),
                    rs.getString("modelo"),
                    rs.getString("color"),
                    rs.getInt("anio"),
                    rs.getString("tipo"),
                    rs.getString("cliente")
                );
                lista.add(v);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return lista;
    }

    public static void eliminarVehiculo(int id) {
        try (Connection conn = ConexionDB.obtenerConexion()) {
            String sql = "DELETE FROM tbb_vehiculos WHERE idVehiculo = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static void modificarVehiculo(VehiculoModel vehiculo) {
        try (Connection conn = ConexionDB.obtenerConexion()) {
            String sql = "UPDATE tbb_vehiculos SET matricula = ?, marca = ?, modelo = ?, color = ?, anio = ?, tipo = ?, cliente = ? WHERE idVehiculo = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, vehiculo.getMatricula());
            stmt.setString(2, vehiculo.getMarca());
            stmt.setString(3, vehiculo.getModelo());
            stmt.setString(4, vehiculo.getColor());
            stmt.setInt(5, vehiculo.getAnio());
            stmt.setString(6, vehiculo.getTipo());
            stmt.setString(7, vehiculo.getCliente());
            stmt.setInt(8, vehiculo.getIdVehiculo());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}

