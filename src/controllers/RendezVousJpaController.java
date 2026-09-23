package controllers;

import entities.RendezVous;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RendezVousJpaController {

    public boolean inserer(RendezVous r) {
        String sql = "INSERT INTO rendezvous (\"IdRendezVous\", \"IdPatient\", \"DateSouhaitee\", \"Motif\", \"Statut\") "
                + "VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, r.getIdRendezVous());
            ps.setString(2, r.getIdPatient());
            ps.setDate(3, r.getDateSouhaitee());
            ps.setString(4, r.getMotif());
            ps.setString(5, "En attente");
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<RendezVous> listerTous() {
        List<RendezVous> liste = new ArrayList<>();
        String sql = "SELECT * FROM rendezvous ORDER BY \"DateCreation\" DESC";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                liste.add(mapper(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return liste;
    }

    public List<RendezVous> listerParStatut(String statut) {
        List<RendezVous> liste = new ArrayList<>();
        String sql = "SELECT * FROM rendezvous WHERE \"Statut\" = ? ORDER BY \"DateCreation\" DESC";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, statut);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    liste.add(mapper(rs));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return liste;
    }

    public List<RendezVous> listerParPatient(String idPatient) {
        List<RendezVous> liste = new ArrayList<>();
        String sql = "SELECT * FROM rendezvous WHERE \"IdPatient\" = ? ORDER BY \"DateCreation\" DESC";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, idPatient);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    liste.add(mapper(rs));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return liste;
    }

    public boolean confirmer(String idRendezVous, java.sql.Timestamp dateConfirmee, String idMedecin) {
        String sql = "UPDATE rendezvous SET \"Statut\" = 'Confirmé', \"DateConfirmee\" = ?, \"IdMedecin\" = ? "
                + "WHERE \"IdRendezVous\" = ?";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setTimestamp(1, dateConfirmee);
            ps.setString(2, idMedecin);
            ps.setString(3, idRendezVous);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean refuser(String idRendezVous) {
        String sql = "UPDATE rendezvous SET \"Statut\" = 'Refusé' WHERE \"IdRendezVous\" = ?";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, idRendezVous);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    private RendezVous mapper(ResultSet rs) throws SQLException {
        RendezVous r = new RendezVous();
        r.setIdRendezVous(rs.getString("IdRendezVous"));
        r.setIdPatient(rs.getString("IdPatient"));
        r.setDateSouhaitee(rs.getDate("DateSouhaitee"));
        r.setMotif(rs.getString("Motif"));
        r.setStatut(rs.getString("Statut"));
        r.setDateConfirmee(rs.getTimestamp("DateConfirmee"));
        r.setIdMedecin(rs.getString("IdMedecin"));
        r.setDateCreation(rs.getTimestamp("DateCreation"));
        return r;
    }
}