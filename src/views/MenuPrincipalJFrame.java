/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package views;


import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.RoundRectangle2D;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class MenuPrincipalJFrame extends JFrame {

    private static final Color COLOR_PRIMARY       = new Color(24, 95, 165);
    private static final Color COLOR_PRIMARY_LIGHT = new Color(55, 138, 221);
    private static final Color COLOR_BG            = new Color(245, 247, 250);
    private static final Color COLOR_CARD          = Color.WHITE;
    private static final Color COLOR_TEXT          = new Color(30, 30, 40);
    private static final Color COLOR_MUTED         = new Color(120, 130, 150);
    private static final Color COLOR_SUCCESS       = new Color(29, 158, 117);
    private static final Color COLOR_WARNING       = new Color(186, 117, 23);
    private static final Color COLOR_DANGER        = new Color(162, 45, 45);
    private static final Color COLOR_PURPLE        = new Color(127, 119, 221);

    
    
    private String roleConnecte;
    private String idConnecte;

    public MenuPrincipalJFrame(String role, String idConnecte) {
        this.roleConnecte = role;
        this.idConnecte = idConnecte;
        setTitle("Système de Gestion Médicale");
        setSize(960, 620);
        setMinimumSize(new Dimension(800, 550));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        initComponents();
    }
    private void initComponents() {
        JPanel root = new JPanel(new BorderLayout());
        root.setBackground(COLOR_BG);
        root.add(buildHeader(), BorderLayout.NORTH);

        JPanel center = new JPanel(new BorderLayout(12, 12));
        center.setOpaque(false);
        center.setBorder(new EmptyBorder(12, 16, 16, 16));
        center.add(buildStatsRow(), BorderLayout.NORTH);
        center.add(buildMainArea(), BorderLayout.CENTER);

        root.add(center, BorderLayout.CENTER);
        setContentPane(root);
    }

    private JPanel buildHeader() {
        JPanel header = new JPanel(new BorderLayout()) {
            @Override protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                GradientPaint gp = new GradientPaint(0, 0, COLOR_PRIMARY, getWidth(), 0, COLOR_PRIMARY_LIGHT);
                g2.setPaint(gp);
                g2.fillRect(0, 0, getWidth(), getHeight());
                g2.dispose();
            }
        };
        header.setPreferredSize(new Dimension(0, 64));
        header.setBorder(new EmptyBorder(0, 20, 0, 20));

        JLabel title = new JLabel("Système de Gestion Médicale");
        title.setFont(new Font("Segoe UI", Font.BOLD, 18));
        title.setForeground(Color.WHITE);

        String date = LocalDate.now().format(
            DateTimeFormatter.ofPattern("EEEE d MMMM yyyy", Locale.FRENCH));
        date = Character.toUpperCase(date.charAt(0)) + date.substring(1);
        JLabel dateLabel = new JLabel(date);
        dateLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        dateLabel.setForeground(new Color(181, 212, 244));

        JPanel left = new JPanel(new GridLayout(2, 1));
        left.setOpaque(false);
        left.add(title);
        left.add(dateLabel);

        JPanel onlinePanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 6, 0));
        onlinePanel.setOpaque(false);
        JLabel dot = new JLabel("●");
        dot.setForeground(new Color(74, 222, 128));
        dot.setFont(new Font("Segoe UI", Font.PLAIN, 10));
        JLabel onlineLbl = new JLabel("En ligne");
        onlineLbl.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        onlineLbl.setForeground(new Color(181, 212, 244));
        onlinePanel.add(dot);
        onlinePanel.add(onlineLbl);

        header.add(left, BorderLayout.WEST);
        header.add(onlinePanel, BorderLayout.EAST);
        return header;
    }

    private JPanel buildStatsRow() {
        JPanel row = new JPanel(new GridLayout(1, 4, 12, 0));
        row.setOpaque(false);
        row.add(buildStatCard("Médecins actifs",      "12",   "+2 ce mois",        COLOR_PRIMARY_LIGHT));
        row.add(buildStatCard("Patients enregistrés", "348",  "+24 cette semaine", COLOR_SUCCESS));
        row.add(buildStatCard("Consultations/jour",   "57",   "+8 vs hier",        COLOR_WARNING));
        row.add(buildStatCard("Paiements (FCFA)",     "1.2M", "-5% cette semaine", COLOR_DANGER));
        return row;
    }

    private JPanel buildStatCard(String label, String value, String delta, Color accent) {
        JPanel card = createRoundedCard(10);
        card.setLayout(new BorderLayout(0, 4));
        card.setBorder(new EmptyBorder(14, 16, 14, 16));

        JLabel val = new JLabel(value);
        val.setFont(new Font("Segoe UI", Font.BOLD, 26));
        val.setForeground(COLOR_TEXT);

        JLabel lbl = new JLabel(label);
        lbl.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        lbl.setForeground(COLOR_MUTED);

        JLabel dlt = new JLabel(delta);
        dlt.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        dlt.setForeground(delta.startsWith("+") ? COLOR_SUCCESS : COLOR_DANGER);

        JPanel bottom = new JPanel(new GridLayout(3, 1, 0, 2));
        bottom.setOpaque(false);
        bottom.add(val);
        bottom.add(lbl);
        bottom.add(dlt);

        JPanel bar = new JPanel() {
            @Override protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setColor(accent);
                g2.fillRoundRect(0, 0, getWidth(), 4, 4, 4);
                g2.dispose();
            }
        };
        bar.setPreferredSize(new Dimension(0, 4));
        bar.setOpaque(false);

        card.add(bottom, BorderLayout.CENTER);
        card.add(bar, BorderLayout.SOUTH);
        return card;
    }

    private JPanel buildMainArea() {
        JPanel area = new JPanel(new GridLayout(1, 2, 12, 0));
        area.setOpaque(false);
        area.add(buildNavCard());
        area.add(buildPatientsCard());
        return area;
    }

    private JPanel buildNavCard() {
    JPanel card = createRoundedCard(12);
    card.setLayout(new BorderLayout());
    card.setBorder(new EmptyBorder(16, 16, 16, 16));

    JLabel title = new JLabel("Navigation");
    title.setFont(new Font("Segoe UI", Font.BOLD, 13));
    title.setForeground(COLOR_MUTED);
    title.setBorder(new EmptyBorder(0, 0, 12, 0));
    card.add(title, BorderLayout.NORTH);

    java.util.List<JPanel> boutonsAutorises = new java.util.ArrayList<>();

    boolean estAdmin = "Administrateur".equals(roleConnecte);

    if (estAdmin) {
        boutonsAutorises.add(buildNavButton("Médecins", "Gérer les profils médecins", COLOR_PRIMARY_LIGHT));
    }
    if (estAdmin || "Secrétaire".equals(roleConnecte)) {
        boutonsAutorises.add(buildNavButton("Patients", "Dossiers et historiques", COLOR_SUCCESS));
    }
    if (estAdmin || "Secrétaire".equals(roleConnecte) || "Médecin".equals(roleConnecte) || "Patient".equals(roleConnecte)) {
        boutonsAutorises.add(buildNavButton("Consultations", "Planifier et suivre", COLOR_WARNING));
    }
    if (estAdmin || "Caissier".equals(roleConnecte) || "Médecin".equals(roleConnecte) || "Patient".equals(roleConnecte)) {
        boutonsAutorises.add(buildNavButton("Factures", "Générer et exporter", COLOR_PURPLE));
    }
    if (estAdmin || "Caissier".equals(roleConnecte) || "Médecin".equals(roleConnecte) || "Patient".equals(roleConnecte)) {
        boutonsAutorises.add(buildNavButton("Paiements", "Transactions et soldes", new Color(99, 179, 237)));
    }
    if (estAdmin) {
    boutonsAutorises.add(buildNavButton("Utilisateurs", "Gérer comptes et rôles", COLOR_DANGER));
    }

    JPanel btnPanel = new JPanel(new GridLayout(boutonsAutorises.size(), 1, 0, 8));
    btnPanel.setOpaque(false);
    for (JPanel b : boutonsAutorises) {
        btnPanel.add(b);
    }
    card.add(btnPanel, BorderLayout.CENTER);

    JButton quit = new JButton("Quitter");
    quit.setFont(new Font("Segoe UI", Font.PLAIN, 11));
    quit.setForeground(COLOR_DANGER);
    quit.setBorderPainted(false);
    quit.setContentAreaFilled(false);
    quit.setCursor(new Cursor(Cursor.HAND_CURSOR));
    quit.addActionListener(e -> System.exit(0));
    JPanel quitWrap = new JPanel(new FlowLayout(FlowLayout.RIGHT, 0, 4));
    quitWrap.setOpaque(false);
    quitWrap.add(quit);
    card.add(quitWrap, BorderLayout.SOUTH);

    return card;
}

    private JPanel buildNavButton(String label, String sub, Color accent) {
        JPanel btn = new JPanel(new BorderLayout(10, 0)) {
            boolean hover = false;
            {
                setOpaque(false);
                setCursor(new Cursor(Cursor.HAND_CURSOR));
                addMouseListener(new MouseAdapter() {
                    public void mouseEntered(MouseEvent e) { hover = true;  repaint(); }
                    public void mouseExited(MouseEvent e)  { hover = false; repaint(); }
                    public void mouseClicked(MouseEvent e) { ouvrirModule(label); }
                });
            }
            @Override protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(hover ? new Color(245, 248, 255) : COLOR_CARD);
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 10, 10);
                g2.setColor(new Color(220, 228, 240));
                g2.drawRoundRect(0, 0, getWidth()-1, getHeight()-1, 10, 10);
                g2.dispose();
            }
        };
        btn.setBorder(new EmptyBorder(8, 10, 8, 10));
        btn.setPreferredSize(new Dimension(0, 52));

        JPanel iconBox = new JPanel(new BorderLayout()) {
            @Override protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(new Color(accent.getRed(), accent.getGreen(), accent.getBlue(), 30));
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 8, 8);
                g2.dispose();
            }
        };
        iconBox.setPreferredSize(new Dimension(36, 36));
        iconBox.setOpaque(false);
        JLabel ico = new JLabel(label.substring(0, 1), SwingConstants.CENTER);
        ico.setFont(new Font("Segoe UI", Font.BOLD, 14));
        ico.setForeground(accent.darker());
        iconBox.add(ico, BorderLayout.CENTER);

        JPanel text = new JPanel(new GridLayout(2, 1));
        text.setOpaque(false);
        JLabel lbl = new JLabel(label);
        lbl.setFont(new Font("Segoe UI", Font.BOLD, 13));
        lbl.setForeground(COLOR_TEXT);
        JLabel sublbl = new JLabel(sub);
        sublbl.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        sublbl.setForeground(COLOR_MUTED);
        text.add(lbl);
        text.add(sublbl);

        JLabel arrow = new JLabel("›");
        arrow.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        arrow.setForeground(COLOR_MUTED);

        btn.add(iconBox, BorderLayout.WEST);
        btn.add(text, BorderLayout.CENTER);
        btn.add(arrow, BorderLayout.EAST);
        return btn;
    }

    private JPanel buildPatientsCard() {
        JPanel card = createRoundedCard(12);
        card.setLayout(new BorderLayout());
        card.setBorder(new EmptyBorder(16, 16, 16, 16));

        JLabel title = new JLabel("Patients récents");
        title.setFont(new Font("Segoe UI", Font.BOLD, 13));
        title.setForeground(COLOR_MUTED);
        title.setBorder(new EmptyBorder(0, 0, 12, 0));
        card.add(title, BorderLayout.NORTH);

        JPanel list = new JPanel(new GridLayout(5, 1, 0, 6));
        list.setOpaque(false);
        list.add(buildPatientRow("SANKARE Oumou",   "SO", COLOR_PRIMARY_LIGHT, "09h15", "Terminé",    COLOR_SUCCESS));
        list.add(buildPatientRow("KEÏTA Fatou",    "KF", COLOR_SUCCESS,       "10h30", "En attente", COLOR_WARNING));
        list.add(buildPatientRow("TRAORE Naîsha",   "TN", COLOR_DANGER,        "11h00", "Urgent",     COLOR_DANGER));
        list.add(buildPatientRow("SAGBO Fifamè Glawdys", "SG", COLOR_PURPLE,        "13h15", "Terminé",    COLOR_SUCCESS));
        list.add(buildPatientRow("DOSSOU Rébécca",     "DR", COLOR_WARNING,       "14h00", "En attente", COLOR_WARNING));
        card.add(list, BorderLayout.CENTER);

        JPanel barSection = new JPanel(new BorderLayout());
        barSection.setOpaque(false);
        barSection.setBorder(new EmptyBorder(12, 0, 0, 0));
        JLabel barTitle = new JLabel("Répartition consultations");
        barTitle.setFont(new Font("Segoe UI", Font.BOLD, 11));
        barTitle.setForeground(COLOR_MUTED);
        barSection.add(barTitle, BorderLayout.NORTH);

        JPanel bars = new JPanel(new GridLayout(3, 1, 0, 6));
        bars.setOpaque(false);
        bars.setBorder(new EmptyBorder(8, 0, 0, 0));
        bars.add(buildBar("Médecine générale", 0.45f, COLOR_PRIMARY_LIGHT, "45%"));
        bars.add(buildBar("Pédiatrie",         0.28f, COLOR_SUCCESS,       "28%"));
        bars.add(buildBar("Gynécologie",       0.17f, COLOR_PURPLE,        "17%"));
        barSection.add(bars, BorderLayout.CENTER);
        card.add(barSection, BorderLayout.SOUTH);

        return card;
    }

    private JPanel buildPatientRow(String name, String initials, Color avatarColor,
                                   String time, String status, Color statusColor) {
        JPanel row = new JPanel(new BorderLayout(10, 0));
        row.setOpaque(false);
        row.setPreferredSize(new Dimension(0, 44));

        JPanel avatar = new JPanel(new BorderLayout()) {
            @Override protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(new Color(avatarColor.getRed(), avatarColor.getGreen(), avatarColor.getBlue(), 40));
                g2.fillOval(0, 0, getWidth(), getHeight());
                g2.dispose();
            }
        };
        avatar.setPreferredSize(new Dimension(36, 36));
        avatar.setOpaque(false);
        JLabel ini = new JLabel(initials, SwingConstants.CENTER);
        ini.setFont(new Font("Segoe UI", Font.BOLD, 10));
        ini.setForeground(avatarColor.darker());
        avatar.add(ini, BorderLayout.CENTER);

        JPanel info = new JPanel(new GridLayout(2, 1));
        info.setOpaque(false);
        JLabel nameLbl = new JLabel(name);
        nameLbl.setFont(new Font("Segoe UI", Font.BOLD, 12));
        nameLbl.setForeground(COLOR_TEXT);
        JLabel timeLbl = new JLabel("Consultation — " + time);
        timeLbl.setFont(new Font("Segoe UI", Font.PLAIN, 10));
        timeLbl.setForeground(COLOR_MUTED);
        info.add(nameLbl);
        info.add(timeLbl);

        JLabel badge = new JLabel(status, SwingConstants.CENTER) {
            @Override protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(new Color(statusColor.getRed(), statusColor.getGreen(), statusColor.getBlue(), 30));
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), getHeight(), getHeight());
                g2.dispose();
                super.paintComponent(g);
            }
        };
        badge.setFont(new Font("Segoe UI", Font.BOLD, 10));
        badge.setForeground(statusColor.darker());
        badge.setPreferredSize(new Dimension(80, 22));
        badge.setOpaque(false);

        row.add(avatar, BorderLayout.WEST);
        row.add(info, BorderLayout.CENTER);
        row.add(badge, BorderLayout.EAST);
        return row;
    }

    private JPanel buildBar(String label, float ratio, Color color, String pct) {
        JPanel wrap = new JPanel(new BorderLayout(6, 2));
        wrap.setOpaque(false);

        JPanel top = new JPanel(new BorderLayout());
        top.setOpaque(false);
        JLabel lbl = new JLabel(label);
        lbl.setFont(new Font("Segoe UI", Font.PLAIN, 10));
        lbl.setForeground(COLOR_MUTED);
        JLabel pctLbl = new JLabel(pct);
        pctLbl.setFont(new Font("Segoe UI", Font.BOLD, 10));
        pctLbl.setForeground(COLOR_TEXT);
        top.add(lbl, BorderLayout.WEST);
        top.add(pctLbl, BorderLayout.EAST);

        JPanel track = new JPanel() {
            @Override protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(new Color(230, 234, 240));
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 6, 6);
                g2.setColor(color);
                g2.fillRoundRect(0, 0, (int)(getWidth() * ratio), getHeight(), 6, 6);
                g2.dispose();
            }
        };
        track.setPreferredSize(new Dimension(0, 6));
        track.setOpaque(false);

        wrap.add(top, BorderLayout.NORTH);
        wrap.add(track, BorderLayout.CENTER);
        return wrap;
    }

    private JPanel createRoundedCard(int radius) {
        return new JPanel() {
            @Override protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(COLOR_CARD);
                g2.fill(new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), radius, radius));
                g2.setColor(new Color(220, 228, 240));
                g2.draw(new RoundRectangle2D.Float(0, 0, getWidth()-1, getHeight()-1, radius, radius));
                g2.dispose();
            }
            @Override public boolean isOpaque() { return false; }
        };
    }

    private void ouvrirModule(String module) {
        switch (module) {
            case "Médecins"      -> new GESTIONDESMEDECINSJFrame().setVisible(true);
            case "Patients"      -> new GESTIONDESPATIENTSJFrame().setVisible(true);
            case "Consultations" -> {
                if ("Médecin".equals(roleConnecte)) {
                new GESTIONDESCONSULTATIONSJFrame(idConnecte).setVisible(true);
                } else {
                     new GESTIONDESCONSULTATIONSJFrame().setVisible(true);
                }
                }
            case "Factures"      -> new GESTIONDELAFACTUREJFrame().setVisible(true);
            case "Paiements"     -> new GESTIONDUPAIEMENTJFrame().setVisible(true);
        }
    }

    public static void main(String[] args) {
    SwingUtilities.invokeLater(() -> {
        try { UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); }
        catch (Exception ignored) {}
       new MenuPrincipalJFrame("Administrateur", "E001").setVisible(true);
    });
}
}

/**
 *
 * @author Idayath
 */

