import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;


public class InscriptionPedaForm extends JFrame {
    private JComboBox<String> comboFiliere;
    private JButton btnSubmit;
    private boolean validationAdmin;
    private Connection connection;
    private static String URL;
    private static String USER;
    private static String PASSWORD;



    public InscriptionPedaForm(boolean validationAdmin) {
        this.validationAdmin = validationAdmin;
        setTitle("Inscription Pédagogique");
        setSize(400, 200);
        setLayout(new GridLayout(3, 1));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Vérifier si l'inscription administrative est validée
        if (!validationAdmin) {
            JOptionPane.showMessageDialog(this, "L'inscription administrative n'a pas été validée. Impossible de continuer.", "Erreur", JOptionPane.ERROR_MESSAGE);
            dispose();
            return;
        }

        // Initialiser la connexion à la base de données
        try {
            Properties props = new Properties();
            URL = props.getProperty("DB_URL");
            USER = props.getProperty("DB_USER");
            PASSWORD = props.getProperty("DB_PASSWORD");
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Erreur de connexion à la base de données : " + e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            dispose();
            return;
        }

        // Ajouter les champs et labels
        add(new JLabel("Choisir une filière :"));
        comboFiliere = new JComboBox<>(new String[]{"AI", "Data", "Cloud"}); // Les filières fixes
        add(comboFiliere);

        btnSubmit = new JButton("Valider l'inscription pédagogique");
        add(btnSubmit);

        // Gestion du clic sur le bouton
        btnSubmit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                validerInscriptionPeda();
            }
        });

        setVisible(true);
    }

    private void validerInscriptionPeda() {
        String filiereChoisie = comboFiliere.getSelectedItem().toString();

        // Logique de validation (exemple simplifié)
        boolean validation = true; // Vous pouvez remplacer par une logique réelle

        if (validation) {
            try {
                // Insérer la filière et l'étudiant dans la base de données
                int filiereId = obtenirIdFiliere(filiereChoisie);

                if (filiereId != -1) {
                    PreparedStatement stmtEtudiant = connection.prepareStatement(
                            "INSERT INTO Etudiant (id, filiereId) VALUES (?, ?)");
                    stmtEtudiant.setInt(1, genererIdEtudiant()); // Générer un ID étudiant
                    stmtEtudiant.setInt(2, filiereId);
                    stmtEtudiant.executeUpdate();

                    JOptionPane.showMessageDialog(this, "Inscription pédagogique validée pour la filière : " + filiereChoisie, "Succès", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(this, "Filière introuvable.", "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, "Erreur lors de l'enregistrement : " + e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "L'inscription pédagogique a échoué.", "Erreur", JOptionPane.ERROR_MESSAGE);
        }

        dispose();
    }

    private int obtenirIdFiliere(String nomFiliere) throws SQLException {
        // Simuler un mapping des filières vers leurs IDs
        switch (nomFiliere) {
            case "AI":
                return 1; // ID de la filière AI
            case "Data":
                return 2; // ID de la filière Data
            case "Cloud":
                return 3; // ID de la filière Cloud
            default:
                return -1; // Filière non trouvée
        }
    }

    private int genererIdEtudiant() throws SQLException {
        // Générer un nouvel ID unique pour un étudiant
        PreparedStatement stmt = connection.prepareStatement("SELECT MAX(id) AS max_id FROM Etudiant");
        var rs = stmt.executeQuery();
        if (rs.next()) {
            return rs.getInt("max_id") + 1;
        }
        return 1; // Premier ID si la table est vide
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new InscriptionPedaForm(true)); // Simule une validation administrative réussie
    }
}
