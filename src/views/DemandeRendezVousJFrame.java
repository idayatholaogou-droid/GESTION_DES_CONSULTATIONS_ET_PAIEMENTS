package views;

import controllers.RendezVousJpaController;
import entities.RendezVous;
import javax.swing.*;
import java.awt.*;
import java.sql.Date;

public class DemandeRendezVousJFrame extends JFrame {

    private String idPatientConnecte;
    private JTextField txtDate;
    private JTextArea txtMotif;

    public DemandeRendezVousJFrame(String idPatientConnecte) {
        this.idPatientConnecte = idPatientConnecte;
        setTitle("Demander un Rendez-vous");
        setSize(450, 380);
        setLocationRelativeTo(null);
        setResizable(false);
        setIconImage(new ImageIcon(getClass().getResource("/views/images/logo_icone_32.png")).getImage());

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel lblDate = new JLabel("Date souhaitée (AAAA-MM-JJ) :");
        txtDate = new JTextField();
        txtDate.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        txtDate.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel lblMotif = new JLabel("Motif de la consultation :");
        txtMotif = new JTextArea(6, 20);
        txtMotif.setLineWrap(true);
        txtMotif.setWrapStyleWord(true);
        JScrollPane scrollMotif = new JScrollPane(txtMotif);
        scrollMotif.setAlignmentX(Component.LEFT_ALIGNMENT);

        JButton btnEnvoyer = new JButton("Envoyer la demande");
        btnEnvoyer.setIcon(new ImageIcon(getClass().getResource("/views/images/Enregistrer_24.png")));
        btnEnvoyer.setAlignmentX(Component.LEFT_ALIGNMENT);
        btnEnvoyer.addActionListener(e -> envoyerDemande());

        lblDate.setAlignmentX(Component.LEFT_ALIGNMENT);
        lblMotif.setAlignmentX(Component.LEFT_ALIGNMENT);

        panel.add(lblDate);
        panel.add(txtDate);
        panel.add(Box.createVerticalStrut(15));
        panel.add(lblMotif);
        panel.add(scrollMotif);
        panel.add(Box.createVerticalStrut(15));
        panel.add(btnEnvoyer);

        setContentPane(panel);
    }

    private void envoyerDemande() {
        String dateTexte = txtDate.getText().trim();
        String motif = txtMotif.getText().trim();

        if (dateTexte.isEmpty() || motif.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Veuillez remplir tous les champs.", "Erreur", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Date dateSouhaitee;
        try {
            dateSouhaitee = Date.valueOf(dateTexte);
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, "Format de date invalide. Utilisez AAAA-MM-JJ.", "Erreur", JOptionPane.ERROR_MESSAGE);
            return;
        }

        RendezVousJpaController ctrl = new RendezVousJpaController();
        RendezVous r = new RendezVous();
        r.setIdRendezVous(genererProchainId(ctrl.listerTous().size()));
        r.setIdPatient(idPatientConnecte);
        r.setDateSouhaitee(dateSouhaitee);
        r.setMotif(motif);

        if (ctrl.inserer(r)) {
            JOptionPane.showMessageDialog(this, "Votre demande a été envoyée avec succès !");
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Erreur lors de l'envoi de la demande.", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }

    private String genererProchainId(int nombreExistant) {
        int prochain = nombreExistant + 1;
        return String.format("R%03d", prochain);
    }
}