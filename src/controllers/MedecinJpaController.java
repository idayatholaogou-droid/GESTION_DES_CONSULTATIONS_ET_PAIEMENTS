/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;
import entities.Medecin;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;

/**
 *
 * @author Idayath
 */
public class MedecinJpaController {
     private Connection getConn() {
        try {
            return DatabaseConfig.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

   
 public boolean inserer(Medecin m) {
    String sql = "INSERT INTO medecin(\"IdMedecin\", \"NomMedecin\", \"PrenomMedecin\", \"Specialite\", \"EmailMedecin\", \"MotDePasse\") VALUES(?,?,?,?,?,?)";
    try {
        Connection conn = getConn();
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, m.getIdMedecin());
        ps.setString(2, m.getNomMedecin());
        ps.setString(3, m.getPrenomMedecin());
        ps.setString(4, m.getSpecialite());
        ps.setString(5, m.getEmailMedecin());
        ps.setString(6, m.getMotDePasse());
        return ps.executeUpdate() > 0;
    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    }
}

    public boolean modifier(Medecin m) {
        String sql = "UPDATE medecin SET \"NomMedecin\"=?, \"PrenomMedecin\"=?, \"Specialite\"=?, \"EmailMedecin\"=? WHERE \"IdMedecin\"=?";
        try {
            Connection conn = getConn();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, m.getNomMedecin());
            ps.setString(2, m.getPrenomMedecin());
            ps.setString(3, m.getSpecialite());
            ps.setString(4, m.getEmailMedecin());
            ps.setString(5, m.getIdMedecin());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean supprimer(String id) {
        String sql = "DELETE FROM medecin WHERE \"IdMedecin\"=?";
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

    public Medecin trouverParId(String id) {
        String sql = "SELECT * FROM medecin WHERE \"IdMedecin\"=?";
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

    public List<Medecin> listerTous() {
        List<Medecin> liste = new ArrayList<>();
        String sql = "SELECT * FROM medecin ORDER BY \"NomMedecin\"";
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

    public List<Medecin> rechercherParNom(String nom) {
        List<Medecin> liste = new ArrayList<>();
        String sql = "SELECT * FROM medecin WHERE LOWER(\"NomMedecin\") LIKE LOWER(?)";
        try {
            Connection conn = getConn();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, "%" + nom + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) liste.add(mapResultSet(rs));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return liste;
    }
 
    public Medecin trouverParEmail(String email) {
        String sql = "SELECT * FROM medecin WHERE \"EmailMedecin\"=?";
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
    public boolean enregistrerCodeReset(String email, String code, java.util.Date expiration) {
    String sql = "UPDATE medecin SET \"CodeReset\"=?, \"DateExpirationCode\"=? WHERE \"EmailMedecin\"=?";
    try {
        Connection conn = getConn();
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, code);
        ps.setTimestamp(2, new java.sql.Timestamp(expiration.getTime()));
        ps.setString(3, email);
        return ps.executeUpdate() > 0;
    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    }
}

    public boolean majMotDePasse(String email, String nouveauMotDePasse) {
       String sql = "UPDATE medecin SET \"MotDePasse\"=?, \"CodeReset\"=NULL, \"DateExpirationCode\"=NULL WHERE \"EmailMedecin\"=?";
        try {
            Connection conn = getConn();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, nouveauMotDePasse);
            ps.setString(2, email);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
  private Medecin mapResultSet(ResultSet rs) throws SQLException {
    Medecin m = new Medecin();
    m.setIdMedecin(rs.getString("IdMedecin"));
    m.setNomMedecin(rs.getString("NomMedecin"));
    m.setPrenomMedecin(rs.getString("PrenomMedecin"));
    m.setSpecialite(rs.getString("Specialite"));
    m.setEmailMedecin(rs.getString("EmailMedecin"));
    m.setMotDePasse(rs.getString("MotDePasse"));
    m.setCodeReset(rs.getString("CodeReset"));
    m.setDateExpirationCode(rs.getTimestamp("DateExpirationCode"));
    return m;
}
}
    

