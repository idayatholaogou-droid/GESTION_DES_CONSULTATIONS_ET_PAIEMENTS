package controllers;

import entities.Personnel;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;

public class PersonnelJpaController {

    private Connection getConn() {
        try {
            return DatabaseConfig.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

 
    public boolean inserer(Personnel p) {
    String sql = "INSERT INTO personnel(\"IdPersonnel\", \"NomPersonnel\", \"EmailPersonnel\", \"MotDePasse\", \"Role\") VALUES(?,?,?,?,?)";
    try {
        Connection conn = getConn();
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, p.getIdPersonnel());
        ps.setString(2, p.getNomPersonnel());
        ps.setString(3, p.getEmailPersonnel());
        ps.setString(4, p.getMotDePasse());
        ps.setString(5, p.getRole());
        return ps.executeUpdate() > 0;
    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    }
}

    public Personnel trouverParEmail(String email) {
        String sql = "SELECT * FROM personnel WHERE \"EmailPersonnel\"=?";
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

    public List<Personnel> listerTous() {
        List<Personnel> liste = new ArrayList<>();
        String sql = "SELECT * FROM personnel ORDER BY \"IdPersonnel\"";
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
    
    public boolean supprimer(String id) {
        String sql = "DELETE FROM personnel WHERE \"IdPersonnel\"=?";
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

    public boolean modifierRole(String id, String nouveauRole) {
        String sql = "UPDATE personnel SET \"Role\"=? WHERE \"IdPersonnel\"=?";
        try {
            Connection conn = getConn();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, nouveauRole);
            ps.setString(2, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
     
     private Personnel mapResultSet(ResultSet rs) throws SQLException {
        Personnel p = new Personnel();
        p.setIdPersonnel(rs.getString("IdPersonnel"));
        p.setNomPersonnel(rs.getString("NomPersonnel"));
        p.setEmailPersonnel(rs.getString("EmailPersonnel"));
        p.setMotDePasse(rs.getString("MotDePasse"));
        p.setRole(rs.getString("Role"));
        return p;
    }
       
}