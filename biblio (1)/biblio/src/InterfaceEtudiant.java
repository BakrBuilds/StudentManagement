import Filieres.Etudiant;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class InterfaceEtudiant {

    // Constructeur pour initialiser et afficher l'interface
    public InterfaceEtudiant() {
        // Créer la fenêtre principale
        JFrame frame = new JFrame("Gestion des Notes");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Créer un panneau pour les boutons
        JPanel panel = new JPanel();
        frame.add(panel);
        placeComponents(panel);

        // Afficher la fenêtre
        frame.setVisible(true);
    }

    private static void placeComponents(JPanel panel) {
        panel.setLayout(null);

        // Créer un bouton pour la note minimale
        JButton minNoteButton = new JButton("Note Minimale");
        minNoteButton.setBounds(50, 30, 300, 30);
        panel.add(minNoteButton);

        // Créer un bouton pour la note maximale
        JButton maxNoteButton = new JButton("Note Maximale");
        maxNoteButton.setBounds(50, 70, 300, 30);
        panel.add(maxNoteButton);

        // Créer un bouton pour la moyenne de l'année
        JButton moyenneAnneeButton = new JButton("Moyenne Année");
        moyenneAnneeButton.setBounds(50, 110, 300, 30);
        panel.add(moyenneAnneeButton);

        // Action pour le bouton de la note minimale
        minNoteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Demander le nom et prénom de l'étudiant
                String nom = JOptionPane.showInputDialog("Entrez le nom de l'étudiant:");
                String prenom = JOptionPane.showInputDialog("Entrez le prénom de l'étudiant:");

                // Chercher l'ID de l'étudiant à partir du nom et prénom
                int idEtudiant = Etudiant.getIdByNomPrenom(nom, prenom); // Vous devez implémenter cette méthode dans la classe Etudiant

                if (idEtudiant != -1) { // Si l'étudiant existe
                    // Récupérer les notes de l'étudiant à partir de l'ID
                    List<Float> notes = Etudiant.getNotesById(idEtudiant); // Implémentez cette méthode dans la classe Etudiant

                    // Calculer la note minimale
                    float minNote = getMinNote(notes);
                    JOptionPane.showMessageDialog(null, "La note minimale est: " + minNote);
                } else {
                    JOptionPane.showMessageDialog(null, "Étudiant non trouvé.");
                }
            }
        });

        // Action pour le bouton de la note maximale
        maxNoteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Demander le nom et prénom de l'étudiant
                String nom = JOptionPane.showInputDialog("Entrez le nom de l'étudiant:");
                String prenom = JOptionPane.showInputDialog("Entrez le prénom de l'étudiant:");

                // Chercher l'ID de l'étudiant
                int idEtudiant = Etudiant.getIdByNomPrenom(nom, prenom);

                if (idEtudiant != -1) {
                    // Récupérer les notes de l'étudiant à partir de l'ID
                    List<Float> notes = Etudiant.getNotesById(idEtudiant);

                    // Calculer la note maximale
                    float maxNote = getMaxNote(notes);
                    JOptionPane.showMessageDialog(null, "La note maximale est: " + maxNote);
                } else {
                    JOptionPane.showMessageDialog(null, "Étudiant non trouvé.");
                }
            }
        });

        // Action pour le bouton de la moyenne de l'année
        moyenneAnneeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Demander le nom et prénom de l'étudiant
                String nom = JOptionPane.showInputDialog("Entrez le nom de l'étudiant:");
                String prenom = JOptionPane.showInputDialog("Entrez le prénom de l'étudiant:");

                // Chercher l'ID de l'étudiant
                int idEtudiant = Etudiant.getIdByNomPrenom(nom, prenom);

                if (idEtudiant != -1) {
                    // Récupérer les notes de l'étudiant à partir de l'ID
                    List<Float> notes = Etudiant.getNotesById(idEtudiant);

                    // Calculer la moyenne des notes
                    float moyenne = getMoyenne(notes);
                    JOptionPane.showMessageDialog(null, "La moyenne des notes est: " + moyenne);
                } else {
                    JOptionPane.showMessageDialog(null, "Étudiant non trouvé.");
                }
            }
        });
    }

    // Méthode pour calculer la note minimale
    private static float getMinNote(List<Float> notes) {
        float min = Float.MAX_VALUE;
        for (float note : notes) {
            if (note < min) {
                min = note;
            }
        }
        return min;
    }

    // Méthode pour calculer la note maximale
    private static float getMaxNote(List<Float> notes) {
        float max = Float.MIN_VALUE;
        for (float note : notes) {
            if (note > max) {
                max = note;
            }
        }
        return max;
    }

    // Méthode pour calculer la moyenne des notes
    private static float getMoyenne(List<Float> notes) {
        float sum = 0;
        for (float note : notes) {
            sum += note;
        }
        return sum / notes.size();
    }
}
