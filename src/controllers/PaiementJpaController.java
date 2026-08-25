
package controllers;

import entities.Paiement;
import entities.Consultation;
import entities.Patient;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PaiementJpaController {

    private Connection getConn() {
        try {
            return DatabaseConfig.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    // — INSERER —
    public boolean inserer(Paiement p) {
        String sql = "INSERT INTO paiement(\"IdPaie\", \"MontPaie\", \"DatePaie\", \"ModePaie\", idconsult, \"IdPatient\") VALUES (?,?,?,?,?,?)";
        try (Connection conn = getConn();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, p.getIdPaie());
            ps.setDouble(2, p.getMontPaie());
            ps.setDate(3, new java.sql.Date(p.getDatePaie().getTime()));
            ps.setString(4, p.getModePaie());
            ps.setString(5, p.getIdconsult());
            ps.setString(6, p.getIdPatient());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // — LISTER TOUS —
    public List<Paiement> listerTous() {
        List<Paiement> liste = new ArrayList<>();
        String sql = "SELECT * FROM paiement ORDER BY \"IdPaie\"";
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
    public boolean modifier(Paiement p) {
        String sql = "UPDATE paiement SET \"MontPaie\"=?, \"DatePaie\"=?, \"ModePaie\"=?, idconsult=?, \"IdPatient\"=? WHERE \"IdPaie\"=?";
        try (Connection conn = getConn();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setDouble(1, p.getMontPaie());
            ps.setDate(2, new java.sql.Date(p.getDatePaie().getTime()));
            ps.setString(3, p.getModePaie());
            ps.setString(4, p.getIdconsult());
            ps.setString(5, p.getIdPatient());
            ps.setString(6, p.getIdPaie());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // — SUPPRIMER —
    public boolean supprimer(String id) {
        String sql = "DELETE FROM paiement WHERE \"IdPaie\"=?";
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
    public Paiement trouverParId(String id) {
        String sql = "SELECT * FROM paiement WHERE \"IdPaie\"=?";
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
    private Paiement mapResultSet(ResultSet rs) throws SQLException {
        Paiement p = new Paiement();
        p.setIdPaie(rs.getString("IdPaie"));
        p.setMontPaie(rs.getDouble("MontPaie"));
        p.setDatePaie(rs.getDate("DatePaie"));
        p.setModePaie(rs.getString("ModePaie"));
        p.setIdconsult(rs.getString("idconsult"));
        p.setIdPatient(rs.getString("IdPatient"));
        return p;
    }
}