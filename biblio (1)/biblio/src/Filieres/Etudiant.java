package Filieres;

import java.sql.*;
import java.util.*;

import EquipePeda.ChefDeFiliere;
import EquipePeda.Employe;
import EquipePeda.Personne;

public class Etudiant extends Personne {

    protected String sexe;
    protected String filiereBac;
    protected Filiere filiere;
    protected String cne;
    protected HashMap<String, Float> notesMatieres = new HashMap<>();
    protected HashMap<Integer, HashMap<Module, Float>> noteModules = new HashMap<>();
    private static String URL;
    private static String USER;
    private static String PASSWORD;



    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public String getFiliereBac() {
        return filiereBac;
    }

    public void setFilierePrep(String filiereBac) {
        this.filiereBac = filiereBac;
    }

    public Etudiant(String nom, String prenom, String tele, String mail, String CIN, int year, int month, int day, String cne, int id) {
        super(nom, prenom, tele, mail, CIN, year, month, day, id);
        this.cne = cne;
    }

    public Etudiant(String nom, String prenom, int id) {
        this.nom = nom;
        this.prenom = prenom;
        this.id = id;
    }

    public Etudiant() {
        // Constructeur par défaut
    }

    public Filiere getFiliere() {
        return filiere;
    }

    public void setFiliere(Filiere filiere) {
        this.filiere = filiere;
    }

    public HashMap<Integer, HashMap<Module, Float>> getNoteModules() {
        return noteModules;
    }

    public void setNoteModules(HashMap<Integer, HashMap<Module, Float>> noteModules) {
        this.noteModules = noteModules;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getCne() {
        return cne;
    }

    public void setCne(String cne) {
        this.cne = cne;
    }

    public HashMap<String, Float> getNotesMatieres() {
        return notesMatieres;
    }

    public void setNotesMatieres(HashMap<String, Float> notesMatieres) {
        this.notesMatieres = notesMatieres;
    }

    public String toString() {
        return nom + " " + prenom;
    }

    public void addNotes(String matiere, float note) {
        notesMatieres.put(matiere, note);
    }

    public float minNote(int semestre) {
        Float min = -1.f;
        try {
            HashMap<Module, Float> val = noteModules.get(semestre);
            for (Map.Entry<Module, Float> note : val.entrySet()) {
                min = 20.f;
                if (note.getValue() < min) {
                    min = note.getValue();
                }
            }
        } catch (Exception e) {
            System.out.print("La liste est vide : ");
        }
        return min;
    }

    public float maxNote(int semestre) {
        Float max = -1.f;
        try {
            HashMap<Module, Float> val = noteModules.get(semestre);
            for (Map.Entry<Module, Float> note : val.entrySet()) {
                max = 0.f;
                if (note.getValue() > max) {
                    max = note.getValue();
                }
            }
        } catch (Exception e) {
            System.out.print("La liste est vide : ");
        }
        return max;
    }

    public void addNoteModule(int semestre, Module m1) {
        Float noteModule = 0.f;
        HashMap<String, String[][]> matieresModule = m1.getMatieres();
        for (Map.Entry<String, String[][]> matiereModule : matieresModule.entrySet()) {
            for (Map.Entry<String, Float> j : notesMatieres.entrySet()) {
                if (matiereModule.getKey().equals(j.getKey())) {
                    noteModule = noteModule + j.getValue();
                }
            }
        }

        HashMap<Module, Float> notes = new HashMap<>();
        notes.put(m1, noteModule / m1.getNombreElement());

        if (!noteModules.containsKey(semestre)) {
            noteModules.put(semestre, notes);
        } else {
            HashMap<Module, Float> noteMod = noteModules.get(semestre);
            noteMod.put(m1, noteModule / m1.getNombreElement());
        }
    }

    public float moyenneSemestre(int semestre) {
        if (noteModules.containsKey(semestre)) {
            HashMap<Module, Float> notes = noteModules.get(semestre);
            float s = 0;
            for (Map.Entry<Module, Float> k : notes.entrySet()) {
                s = s + k.getValue();
            }
            return s / notes.size();
        } else {
            return -1.f;
        }
    }

    public float moyenneAnnee(int annee) {
        float s = 0;
        if (annee == 1) {
            s = moyenneSemestre(1) + moyenneSemestre(2);
            return s / 2;
        }
        if (annee == 2) {
            s = moyenneSemestre(3) + moyenneSemestre(4);
            return s / 2;
        }
        if (annee == 3) {
            s = moyenneSemestre(5) + moyenneSemestre(6);
            return s / 2;
        }
        return -1.f;
    }

    public ChefDeFiliere chef() {
        ArrayList<Employe> employe = filiere.getEquipe();
        for (Employe e : employe) {
            if (e.getId() == 1) {
                return (ChefDeFiliere) e;
            }
        }
        return null;
    }

    public void AfficherNotesModule() {
        for (Map.Entry<Integer, HashMap<Module, Float>> entry : noteModules.entrySet()) {
            int key = entry.getKey();
            HashMap<Module, Float> value = entry.getValue();
            System.out.println("Le semestre " + key + " : ");
            System.out.println(value);
        }
    }

    // Méthode pour récupérer l'ID de l'étudiant à partir du nom et prénom depuis la base de données
    public static int getIdByNomPrenom(String nom, String prenom) {
        int idEtudiant = -1; // Valeur par défaut si l'étudiant n'est pas trouvé
        String query = "SELECT id FROM Etudiant WHERE id IN (SELECT id FROM Personne WHERE nom = ? AND prenom = ?)";
        Properties props = new Properties();
        URL = props.getProperty("DB_URL");
        USER = props.getProperty("DB_USER");
        PASSWORD = props.getProperty("DB_PASSWORD");
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, nom);
            statement.setString(2, prenom);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                idEtudiant = resultSet.getInt("id");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return idEtudiant;
    }

    // Méthode pour récupérer les notes de l'étudiant par son ID depuis la base de données
    public static List<Float> getNotesById(int idEtudiant) {
        List<Float> notes = new ArrayList<>();
        String query = "SELECT note FROM Notes WHERE etudiantId = ?";

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, idEtudiant);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                notes.add(resultSet.getFloat("note"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return notes;
    }
}
