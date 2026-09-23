package views;

import controllers.RendezVousJpaController;
import controllers.MedecinJpaController;
import entities.RendezVous;
import entities.Medecin;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Timestamp;
import java.util.List;

public class GestionRendezVousJFrame extends JFrame {

    private JTable table;
    private DefaultTableModel model;
    private RendezVousJpaController ctrl = new RendezVousJpaController();

    public GestionRendezVousJFrame() {
        setTitle("Gestion des Rendez-vous");
        setSize(800, 500);
        setLocationRelativeTo(null);
        setIconImage(new ImageIcon(getClass().getResource("/views/images/logo_icone_32.png")).getImage());

        model = new DefaultTableModel(new String[]{"ID", "Patient", "Date souhaitée", "Motif", "Statut"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);

        JButton btnConfirmer = new JButton("Confirmer");
        btnConfirmer.setIcon(new ImageIcon(getClass().getResource("/views/images/Voir_24.png")));
        btnConfirmer.addActionListener(e -> confirmerSelection());

        JButton btnRefuser = new JButton("Refuser");
        btnRefuser.setIcon(new ImageIcon(getClass().getResource("/views/images/Supprimer_24.png")));
        btnRefuser.addActionListener(e -> refuserSelection());

        JButton btnFermer = new JButton("Fermer");
        btnFermer.setIcon(new ImageIcon(getClass().getResource("/views/images/Fermer_24.png")));
        btnFermer.addActionListener(e -> dispose());

        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));
        btnPanel.add(btnConfirmer);
        btnPanel.add(btnRefuser);
        btnPanel.add(btnFermer);

        setLayout(new BorderLayout());
        add(scrollPane, BorderLayout.CENTER);
        add(btnPanel, BorderLayout.SOUTH);

        chargerDemandes();
    }

    private void chargerDemandes() {
        model.setRowCount(0);
        List<RendezVous> liste = ctrl.listerParStatut("En attente");
        for (RendezVous r : liste) {
            model.addRow(new Object[]{
                r.getIdRendezVous(),
                r.getIdPatient(),
                r.getDateSouhaitee(),
                r.getMotif(),
                r.getStatut()
            });
        }
    }

    private void confirmerSelection() {
        int ligne = table.getSelectedRow();
        if (ligne == -1) {
            JOptionPane.showMessageDialog(this, "Veuillez sélectionner une demande.", "Erreur", JOptionPane.ERROR_MESSAGE);
            return;
        }
        String idRendezVous = (String) model.getValueAt(ligne, 0);

        List<Medecin> medecins = new MedecinJpaController().listerTous();
        if (medecins.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Aucun médecin disponible.", "Erreur", JOptionPane.ERROR_MESSAGE);
            return;
        }
        String[] idsMedecins = medecins.stream().map(Medecin::getIdMedecin).toArray(String[]::new);
        String idMedecin = (String) JOptionPane.showInputDialog(this, "Choisir un médecin :", "Confirmer le rendez-vous",
                JOptionPane.QUESTION_MESSAGE, null, idsMedecins, idsMedecins[0]);
        if (idMedecin == null) return;

        String dateTexte = JOptionPane.showInputDialog(this, "Date et heure confirmées (AAAA-MM-JJ HH:MM) :");
        if (dateTexte == null || dateTexte.trim().isEmpty()) return;

        Timestamp dateConfirmee;
        try {
            dateConfirmee = Timestamp.valueOf(dateTexte.trim() + ":00");
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, "Format invalide. Utilisez AAAA-MM-JJ HH:MM.", "Erreur", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (ctrl.confirmer(idRendezVous, dateConfirmee, idMedecin)) {
            JOptionPane.showMessageDialog(this, "Rendez-vous confirmé.");
            chargerDemandes();
        } else {
            JOptionPane.showMessageDialog(this, "Erreur lors de la confirmation.", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void refuserSelection() {
        int ligne = table.getSelectedRow();
        if (ligne == -1) {
            JOptionPane.showMessageDialog(this, "Veuillez sélectionner une demande.", "Erreur", JOptionPane.ERROR_MESSAGE);
            return;
        }
        String idRendezVous = (String) model.getValueAt(ligne, 0);
        if (ctrl.refuser(idRendezVous)) {
            JOptionPane.showMessageDialog(this, "Demande refusée.");
            chargerDemandes();
        } else {
            JOptionPane.showMessageDialog(this, "Erreur lors du refus.", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }
}