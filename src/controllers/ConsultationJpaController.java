/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import entities.Consultation;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ConsultationJpaController {

    private Connection getConn() {
        try {
            return DatabaseConfig.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean inserer(Consultation c) {
        String sql = "INSERT INTO consultation(idconsult, dateconsult, motif, diagnostic, montconsult, \"IdPatient\", \"IdMedecin\") VALUES(?,?,?,?,?,?,?)";
        try {
            Connection conn = getConn();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, c.getIdconsult());
            ps.setDate(2, new java.sql.Date(c.getDateconsult().getTime()));
            ps.setString(3, c.getMotif());
            ps.setString(4, c.getDiagnostic());
            ps.setDouble(5, c.getMontconsult());
            ps.setString(6, c.getIdPatient());
            ps.setString(7, c.getIdMedecin());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean modifier(Consultation c) {
        String sql = "UPDATE consultation SET dateconsult=?, motif=?, diagnostic=?, montconsult=?, \"IdPatient\"=?, \"IdMedecin\"=? WHERE idconsult=?";
        try {
            Connection conn = getConn();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setDate(1, new java.sql.Date(c.getDateconsult().getTime()));
            ps.setString(2, c.getMotif());
            ps.setString(3, c.getDiagnostic());
            ps.setDouble(4, c.getMontconsult());
            ps.setString(5, c.getIdPatient());
            ps.setString(6, c.getIdMedecin());
            ps.setString(7, c.getIdconsult());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean supprimer(String id) {
        String sql = "DELETE FROM consultation WHERE idconsult=?";
        try {
            Connection conn = getConn();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Consultation trouverParId(String id) {
        String sql = "SELECT * FROM consultation WHERE idconsult=?";
        try {
            Connection conn = getConn();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return mapResultSet(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Consultation> listerTous() {
        List<Consultation> liste = new ArrayList<>();
        String sql = "SELECT * FROM consultation ORDER BY idconsult";
        try {
            Connection conn = getConn();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) liste.add(mapResultSet(rs));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return liste;
    }
    public List<Consultation> listerParPatient(String idPatient) {
    List<Consultation> liste = new ArrayList<>();
    String sql = "SELECT * FROM consultation WHERE \"IdPatient\"=? ORDER BY dateconsult DESC";
    try {
        Connection conn = getConn();
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, idPatient);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) liste.add(mapResultSet(rs));
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return liste;
}

    private Consultation mapResultSet(ResultSet rs) throws SQLException {
        Consultation c = new Consultation();
        c.setIdconsult(rs.getString("idconsult"));
       c.setDateconsult(rs.getDate("dateconsult"));
        c.setMotif(rs.getString("motif"));
        c.setDiagnostic(rs.getString("diagnostic"));
        c.setMontconsult(rs.getDouble("montconsult"));
        c.setIdPatient(rs.getString("IdPatient"));
        c.setIdMedecin(rs.getString("IdMedecin"));
        return c;
    }
}

    