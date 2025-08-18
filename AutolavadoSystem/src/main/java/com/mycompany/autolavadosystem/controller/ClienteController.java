/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.autolavadosystem.controller;

import com.mycompany.autolavadosystem.conexion.ConexionDB;
import com.mycompany.autolavadosystem.model.ClienteModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Rodo
 */
public class ClienteController {
    public static boolean ingresarCliente(ClienteModel cliente) {
        String sql = "INSERT INTO tbi_cliente (nombre, primerApellido, segundoApellido, direccion, telefono, correo, password) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection con = ConexionDB.obtenerConexion(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, cliente.getNombre());
            ps.setString(2, cliente.getPrimerApellido());
            ps.setString(3, cliente.getSegundoApellido());
            ps.setString(4, cliente.getDireccion());
            ps.setString(5, cliente.getTelefono());
            ps.setString(6, cliente.getCorreo());
            ps.setString(7, cliente.getPassword());

            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public static boolean eliminarCliente(int idCliente) {
        String sql = "DELETE FROM tbi_cliente WHERE idCliente = ?";
        try (Connection con = ConexionDB.obtenerConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idCliente);
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }
    
    public static List<ClienteModel> obtenerTodos() {
        List<ClienteModel> lista = new ArrayList<>();
        String sql = "SELECT * FROM tbi_cliente";

        try (Connection con = ConexionDB.obtenerConexion(); Statement st = con.createStatement(); ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                ClienteModel cliente = new ClienteModel(
                    rs.getInt("idCliente"),
                    rs.getString("nombre"),
                    rs.getString("primerApellido"),
                    rs.getString("segundoApellido"),
                    rs.getString("direccion"),
                    rs.getString("telefono"),
                    rs.getString("correo"),
                    rs.getString("contrasena")
                );
                lista.add(cliente);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return lista;
    }

    public static List<ClienteModel> buscarPorNombre(String nombre) {
        List<ClienteModel> lista = new ArrayList<>();
        String sql = "SELECT * FROM tbi_cliente WHERE LOWER(nombre) LIKE ?";

        try (Connection con = ConexionDB.obtenerConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, "%" + nombre.toLowerCase() + "%");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                ClienteModel cliente = new ClienteModel();
                cliente.setIdCliente(rs.getInt("idCliente"));
                cliente.setNombre(rs.getString("nombre"));
                cliente.setPrimerApellido(rs.getString("primerApellido"));
                cliente.setSegundoApellido(rs.getString("segundoApellido"));
                cliente.setDireccion(rs.getString("direccion"));
                cliente.setTelefono(rs.getString("telefono"));
                cliente.setCorreo(rs.getString("correo"));
                cliente.setPassword(rs.getString("password"));
                lista.add(cliente);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return lista;
    }

    public static boolean actualizarCliente(ClienteModel cliente) {
        String sql = "UPDATE tbi_cliente SET nombre=?, primerApellido=?, segundoApellido=?, direccion=?, telefono=?, correo=?, password=? WHERE idCliente=?";
        try (Connection con = ConexionDB.obtenerConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, cliente.getNombre());
            ps.setString(2, cliente.getPrimerApellido());
            ps.setString(3, cliente.getSegundoApellido());
            ps.setString(4, cliente.getDireccion());
            ps.setString(5, cliente.getTelefono());
            ps.setString(6, cliente.getCorreo());
            ps.setString(7, cliente.getPassword());
            ps.setInt(8, cliente.getIdCliente());

            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

}

