import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Application {

    public static void main(String[] args) {
        // Créer la fenêtre principale
        JFrame frame = new JFrame("EURAMED App");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 400);
        frame.setLayout(new BorderLayout());

        // Ajouter un titre
        JLabel titleLabel = new JLabel("Bienvenue dans l'application EURAMED APP", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        frame.add(titleLabel, BorderLayout.NORTH);

        // Panel central pour afficher les boutons
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(5, 1, 10, 10));
        frame.add(mainPanel, BorderLayout.CENTER);

        // Boutons pour les choix
        JButton btnGestionInscription = new JButton("1) Gestion des inscriptions");
        JButton btnGestionEtudiants = new JButton("2) Gestion des étudiants");
        JButton btnGestionScolarite = new JButton("3) Gestion de la scolarité");
        JButton btnQuit = new JButton("4) Quitter");

        // Ajouter les boutons au panel
        mainPanel.add(btnGestionInscription);
        mainPanel.add(btnGestionEtudiants);
        mainPanel.add(btnGestionScolarite);
        mainPanel.add(btnQuit);

        // Ajouter des actions aux boutons
        btnGestionInscription.addActionListener(e -> showInscriptionForm());
        btnGestionEtudiants.addActionListener(e -> showEtudiantsForm());
        btnGestionScolarite.addActionListener(e -> showSubMenu("Gestion de la scolarité"));
        btnQuit.addActionListener(e -> {
            JOptionPane.showMessageDialog(frame, "Merci d'avoir utilisé EURAMED App. Au revoir !");
            System.exit(0);
        });

        // Rendre la fenêtre visible
        frame.setVisible(true);
    }

    // Méthode pour afficher le formulaire d'inscription
    private static void showInscriptionForm() {
        // Crée une nouvelle fenêtre pour le formulaire d'inscription
        new InscriptionAdministrativeForm();
    }
    private static void showEtudiantsForm() {
        new InterfaceEtudiant();
    }

    // Méthode pour afficher un sous-menu selon le choix
    private static void showSubMenu(String menuName) {
        JFrame subFrame = new JFrame(menuName);
        subFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        subFrame.setSize(400, 300);
        subFrame.setLayout(new BorderLayout());

        JLabel label = new JLabel("Vous êtes dans " + menuName, SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 16));
        subFrame.add(label, BorderLayout.CENTER);

        JButton backButton = new JButton("Retour au menu principal");
        backButton.addActionListener(e -> subFrame.dispose());
        subFrame.add(backButton, BorderLayout.SOUTH);

        subFrame.setVisible(true);
    }
}
