import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Properties;

import Inscriptions.InscriptionAdministrative;

public class InscriptionAdministrativeForm extends JFrame {
    private JTextField txtNom, txtPrenom, txtDateNaissance, txtMontantPaye;
    private JComboBox<String> comboBac;
    private JButton btnSubmit;
    private static String URL;
    private static String USER;
    private static String PASSWORD;


    public InscriptionAdministrativeForm() {
        setTitle("Inscription Administrative");
        setSize(400, 300);
        setLayout(new GridLayout(6, 2));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Ajouter les champs et labels
        add(new JLabel("Nom :"));
        txtNom = new JTextField();
        add(txtNom);

        add(new JLabel("Prénom :"));
        txtPrenom = new JTextField();
        add(txtPrenom);

        add(new JLabel("Réussir BAC (oui/non) :"));
        comboBac = new JComboBox<>(new String[]{"oui", "non"});
        add(comboBac);

        add(new JLabel("Date de Naissance (année) :"));
        txtDateNaissance = new JTextField();
        add(txtDateNaissance);

        add(new JLabel("Montant Payé :"));
        txtMontantPaye = new JTextField();
        add(txtMontantPaye);

        btnSubmit = new JButton("Soumettre");
        add(btnSubmit);

        // Gestion du clic sur le bouton
        btnSubmit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                soumettreInscription();
            }
        });

        setVisible(true);
    }

    private void soumettreInscription() {
        // Récupérer les données des champs
        String nom = txtNom.getText();
        String prenom = txtPrenom.getText();
        boolean reussirBac = comboBac.getSelectedItem().toString().equals("oui");
        int anneeNaissance;
        float montantPaye;

        try {
            anneeNaissance = Integer.parseInt(txtDateNaissance.getText());
            montantPaye = Float.parseFloat(txtMontantPaye.getText());
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Veuillez entrer des valeurs valides pour la date de naissance et le montant payé.", "Erreur", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Calculer l'âge
        Calendar time = Calendar.getInstance();
        int anneeActuelle = time.get(Calendar.YEAR);
        int age = anneeActuelle - anneeNaissance;

        // Création de l'objet InscriptionAdministrative
        InscriptionAdministrative inscription = new InscriptionAdministrative();
        inscription.setAge(age);
        inscription.setReussirBac(reussirBac);
        inscription.setMontantPaye(montantPaye);

        // Définir le montant à payer
        InscriptionAdministrative.setMontantApayer(1000f); // Exemple : 1000 comme montant fixe

        // Validation de l'inscription
        if (inscription.inscriptionValidee(InscriptionAdministrative.getMontantApayer())) {
            // Enregistrement dans la base de données
            Properties props = new Properties();
            URL = props.getProperty("DB_URL");
            USER = props.getProperty("DB_USER");
            PASSWORD = props.getProperty("DB_PASSWORD");
            try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
                String insertPersonne = "INSERT INTO Personne (nom, prenom, CIN, telephone, email) VALUES (?, ?, '', '', '')";

                try (PreparedStatement psPersonne = conn.prepareStatement(insertPersonne, PreparedStatement.RETURN_GENERATED_KEYS)) {
                    psPersonne.setString(1, nom);
                    psPersonne.setString(2, prenom);
                    psPersonne.executeUpdate();

                    // Récupérer l'ID généré
                    var rs = psPersonne.getGeneratedKeys();
                    if (rs.next()) {
                        int personneId = rs.getInt(1);

                        // Insérer les données d'inscription administrative
                        String insertInscription = "INSERT INTO InscriptionAdministrative (personneId, age, reussirBac, montantPaye) VALUES (?, ?, ?, ?)";
                        try (PreparedStatement psInscription = conn.prepareStatement(insertInscription)) {
                            psInscription.setInt(1, personneId);
                            psInscription.setInt(2, age);
                            psInscription.setBoolean(3, reussirBac);
                            psInscription.setFloat(4, montantPaye);
                            psInscription.executeUpdate();

                            // Afficher le message de succès
                            JOptionPane.showMessageDialog(this, "Inscription administrative validée et enregistrée avec succès !");

                            // Réinitialiser le formulaire

                            new InscriptionPedaForm(true);
                            reinitialiserFormulaire();
                        }
                    }
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Erreur lors de l'enregistrement : " + ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Inscription non validée : critères non remplis.", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void reinitialiserFormulaire() {
        // Réinitialiser les champs du formulaire
        txtNom.setText("");
        txtPrenom.setText("");
        txtDateNaissance.setText("");
        txtMontantPaye.setText("");
        comboBac.setSelectedIndex(0);
    }

    public static void main(String[] args) {
        new InscriptionAdministrativeForm();
    }
}
