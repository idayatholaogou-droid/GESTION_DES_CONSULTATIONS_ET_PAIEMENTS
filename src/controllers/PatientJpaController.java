/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

 
import entities.Patient;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;
 
public class PatientJpaController {
 
    private Connection getConn() {
        try {
            return DatabaseConfig.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
 
    public boolean inserer(Patient p) {
        String sql = "INSERT INTO patient(\"IdPatient\", \"NomPatient\", \"PrenomPatient\", \"Age\", \"Telephone\", \"EmailPatient\") VALUES(?,?,?,?,?,?)";
        try {
            Connection conn = getConn();
            if (conn == null) {
            System.out.println("ERREUR : connexion impossible !");
            return false;
        }
        System.out.println("Connexion OK");
        System.out.println("ID : " + p.getIdPatient());
        System.out.println("Nom : " + p.getNomPatient());
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, p.getIdPatient());
            ps.setString(2, p.getNomPatient());
            ps.setString(3, p.getPrenomPatient());
            ps.setInt(4, p.getAge());
            ps.setString(5, p.getTelephone());
            ps.setString(6, p.getEmailPatient());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean modifier(Patient p) {
        String sql = "UPDATE patient SET \"NomPatient\"=?, \"PrenomPatient\"=?, \"Age\"=?, \"Telephone\"=?, \"EmailPatient\"=? WHERE \"IdPatient\"=?";
        try {
            Connection conn = getConn();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, p.getNomPatient());
            ps.setString(2, p.getPrenomPatient());
            ps.setInt(3, p.getAge());
            ps.setString(4, p.getTelephone());
            ps.setString(5, p.getEmailPatient());
            ps.setString(6, p.getIdPatient());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
 
    // ── SUPPRIMER ────────────────────────────────────────────
    public boolean supprimer(String id) {
        String sql = "DELETE FROM patient WHERE \"IdPatient\"=?";
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
 
    // ── TROUVER PAR ID ───────────────────────────────────────
    public Patient trouverParId(String id) {
        String sql = "SELECT * FROM patient WHERE \"IdPatient\"=?";
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
 
    public List<Patient> listerTous() {
        List<Patient> liste = new ArrayList<>();
        String sql = "SELECT * FROM patient ORDER BY \"NomPatient\"";
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
 
    public Patient trouverParEmail(String email) {
        String sql = "SELECT * FROM patient WHERE \"EmailPatient\"=?";
        try {
            Connection conn = getConn();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return mapResultSet(rs);
         } catch (SQLException e) {
             e.printStackTrace();
    }
    return null;
    }
    private Patient mapResultSet(ResultSet rs) throws SQLException {
    Patient p = new Patient();
    p.setIdPatient(rs.getString("IdPatient"));
    p.setNomPatient(rs.getString("NomPatient"));
    p.setPrenomPatient(rs.getString("PrenomPatient"));
    p.setAge(rs.getInt("Age"));
    p.setTelephone(rs.getString("Telephone"));
    p.setEmailPatient(rs.getString("EmailPatient"));
    p.setMotDePasse(rs.getString("MotDePasse"));
    p.setCodeReset(rs.getString("CodeReset"));
    p.setDateExpirationCode(rs.getTimestamp("DateExpirationCode"));
    return p;
}
}