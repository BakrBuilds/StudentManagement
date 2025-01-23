import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        // Exécuter l'interface graphique sur le thread de l'Event Dispatch Thread (EDT)
        SwingUtilities.invokeLater(() -> {
            new InscriptionAdministrativeForm(); // Afficher l'interface d'inscription administrative
        });
    }
}
