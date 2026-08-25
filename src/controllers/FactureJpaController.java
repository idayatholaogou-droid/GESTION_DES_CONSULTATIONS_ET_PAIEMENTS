/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;
import entities.Facture;
import entities.Consultation;
import entities.Paiement;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Idayath
 */
public class FactureJpaController {
    
    private Connection getConn() {
        try {
            return DatabaseConfig.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    // — INSERER —
    public boolean inserer(Facture f) {
        String sql = "INSERT INTO facture(\"IdFacture\", \"DateFacture\", \"Statut\", \"MontFacture\", idconsult, \"IdPaie\") VALUES (?,?,?,?,?,?)";
        try (Connection conn = getConn();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, f.getIdFacture());
            ps.setDate(2, new java.sql.Date(f.getDateFacture().getTime()));
            ps.setString(3, f.getStatut());
            ps.setDouble(4, f.getMontFacture());
            ps.setString(5, f.getIdconsult());
            ps.setString(6, f.getIdPaie());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // — LISTER TOUS —
    public List<Facture> listerTous() {
        List<Facture> liste = new ArrayList<>();
        String sql = "SELECT * FROM facture ORDER BY \"IdFacture\"";
        try (Connection conn = getConn();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) liste.add(mapResultSet(rs));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return liste;
    }

    // — MODIFIER —
    public boolean modifier(Facture f) {
        String sql = "UPDATE facture SET \"DateFacture\"=?, \"Statut\"=?, \"MontFacture\"=?, idconsult=?, \"IdPaie\"=? WHERE \"IdFacture\"=?";
        try (Connection conn = getConn();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setDate(1, new java.sql.Date(f.getDateFacture().getTime()));
            ps.setString(2, f.getStatut());
            ps.setDouble(3, f.getMontFacture());
            ps.setString(4, f.getIdconsult());
            ps.setString(5, f.getIdPaie());
            ps.setString(6, f.getIdFacture());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // — SUPPRIMER —
    public boolean supprimer(String id) {
        String sql = "DELETE FROM facture WHERE \"IdFacture\"=?";
        try (Connection conn = getConn();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // — TROUVER PAR ID —
    public Facture trouverParId(String id) {
        String sql = "SELECT * FROM facture WHERE \"IdFacture\"=?";
        try (Connection conn = getConn();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return mapResultSet(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // — MAPPING —
    private Facture mapResultSet(ResultSet rs) throws SQLException {
        Facture f = new Facture();
        f.setIdFacture(rs.getString("IdFacture"));
        f.setDateFacture(rs.getDate("DateFacture"));
        f.setStatut(rs.getString("Statut"));
        f.setMontFacture(rs.getDouble("MontFacture"));
        f.setIdconsult(rs.getString("idconsult"));
        f.setIdPaie(rs.getString("IdPaie"));
        return f;
    }
    
}
