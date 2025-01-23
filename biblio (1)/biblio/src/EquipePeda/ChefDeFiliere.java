package EquipePeda;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import Filieres.Etudiant;
import Filieres.Module;

public class ChefDeFiliere extends Employe implements Serializable{

    private static final long serialVersionUID = 1L;
    protected int id = 1;
    protected HashMap<String,Integer> nbreSeance=new HashMap<>();



    public String Mention(Etudiant e, int annee) {
        Float moyenne = e.moyenneAnnee(annee);

        if (moyenne >= 16.f) {
            return "Tres bien";
        }
        if (moyenne >= 14.f && moyenne < 16.f) {
            return "Bien";
        }
        if (moyenne >= 12.f && moyenne < 14.f) {
            return "Assez bien";
        }
        if (moyenne >= 10 && moyenne < 12) {
            return "Passable";
        }
        if (moyenne < 10 ) {
            return "Insuffisant";
        }
        return "";
    }

    public HashMap<String, Integer> getNbreSeance() {
        return nbreSeance;
    }

    public void addnbreSeance(String matiere,int nbreseance) {
        nbreSeance.put(matiere,nbreseance);
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    protected ArrayList<Etudiant> etudiants = new ArrayList<>();
    protected HashMap<String, Calendar> examPlan = new HashMap<>();
    protected float sanctionAbsence = 0.25f;

    public ChefDeFiliere(int salaire, String nom, String prenom, String tele, String email, String CIN, int day,
                         double salaire1, int mounth, int year,int id) {
        super(nom, prenom, tele, email, CIN, salaire1, day, mounth, year,id);

    }

    public ChefDeFiliere(String nom,String prenom) {
        this.nom = nom;
        this.prenom=prenom;

    }

    public ChefDeFiliere() {
        // TODO Auto-generated constructor stub
    }
    public void addEtudiant(Etudiant e) {
        etudiants.add(e);
    }

    public void addExam(String matiere, int jour, int mois, int annee, int heure) {
        Calendar time = Calendar.getInstance();
        time.set(annee, mois + 1, jour, heure, 0);
        examPlan.put(matiere, time);

    }

    public void afficherExam() {

        for (Map.Entry<String, Calendar> plan : examPlan.entrySet()) {
            System.out.println("examen en " + plan.getKey() + " le " + plan.getValue().getTime());

        }

    }

    public ArrayList<Etudiant> getEtudiants() {
        return etudiants;
    }

    public void setEtudiants(ArrayList<Etudiant> etudiants) {
        this.etudiants = etudiants;
    }

    public HashMap<String, Calendar> getExamPlan() {
        return examPlan;
    }

    public void setExamPlan(HashMap<String, Calendar> examPlan) {
        this.examPlan = examPlan;
    }

    public void setExam(String matiere, int jour, int mois, int annee, int heure) {
        Calendar time = Calendar.getInstance();
        time.set(jour, mois + 1, annee, heure, 0);
        examPlan.replace(matiere, time);

    }

    public Boolean noteEliminatoire(Etudiant e, Module m) {

        HashMap<String, Float> notesMat = e.getNotesMatieres();
        LinkedHashMap<String, String[][]> matieres = new LinkedHashMap<>();
        matieres = (LinkedHashMap<String, String[][]>) m.getMatieres();

        for (Map.Entry<String, Float> k : notesMat.entrySet()) {

            if (matieres.containsKey(k.getKey())) {
                if ((Float) k.getValue() < 6) {
                    return true;
                }
            }
        }
        return false;
    }

    // module valide
    public Boolean moduleValide(Etudiant e, Module m) {
        HashMap<Integer, HashMap<Module, Float>> mod = new HashMap<>();
        float noteModule = 0.f;
        mod = e.getNoteModules();

        for (Map.Entry<Integer, HashMap<Module, Float>> k : mod.entrySet()) {

            HashMap<Module, Float> val = new HashMap<>();
            val = (HashMap<Module, Float>) k.getValue();
            for (Map.Entry<Module, Float> j : val.entrySet()) {
                if (j.getKey() == m) {

                    noteModule = val.get(m);

                    if (noteModule >= 11 && noteEliminatoire(e, m) == false) {

                        return true;

                    }

                }

            }
        }

        return false;

    }

    public Boolean validationAnneeModules(int annee, Etudiant e) {
        try {
            HashMap<Integer, HashMap<Module, Float>> modules = e.getNoteModules();
            int j = 0;

            Float moyenne = e.moyenneAnnee(annee);

            if (annee == 1) {

                HashMap<Module, Float> notesSemestre1 = modules.get(1);
                HashMap<Module, Float> notesSemestre2 = modules.get(2);
                for (Map.Entry<Module, Float> k : notesSemestre1.entrySet()) {
                    if (moduleValide(e, (Module) k.getKey()) == false) {
                        j++;

                    }
                }
                for (Map.Entry<Module, Float> m : notesSemestre2.entrySet()) {
                    if (moduleValide(e, (Module) m.getKey()) == false) {
                        j++;

                    }
                }

                if (moyenne >= 11 && j < 2) {
                    return true;
                }

            }

            if (annee == 2) {
                HashMap<Module, Float> notesSemestre3 = modules.get(3);
                HashMap<Module, Float> notesSemestre4 = modules.get(4);

                for (Map.Entry<Module, Float> k : notesSemestre3.entrySet()) {
                    if (moduleValide(e, (Module) k.getKey()) == false) {
                        j++;

                    }
                    for (Map.Entry<Module, Float> m : notesSemestre4.entrySet()) {

                        if (moduleValide(e, (Module) m.getKey()) == false) {
                            j++;

                        }

                        if (moyenne >= 11 && j < 2) {
                            return true;
                        }
                        return false;
                    }

                }
            }
            if (annee == 3) {
                HashMap<Module, Float> notesSemestre5 = modules.get(5);
                HashMap<Module, Float> notesSemestre6 = modules.get(6);
                for (Map.Entry<Module, Float> k : notesSemestre5.entrySet()) {
                    if (moduleValide(e, (Module) k.getKey()) == false) {
                        j++;

                    }
                }
                for (Map.Entry<Module, Float> m : notesSemestre6.entrySet()) {
                    if (moduleValide(e, (Module) m.getKey()) == false) {
                        j++;

                    }
                }

                if (moyenne >= 11 && j < 2) {
                    return true;
                }
            }
            return false;
        } catch (Exception err) {
            System.out.println("vous n avez pas saisi les notes des modules");
        }
        return null;

    }




    public void ratt(Etudiant e, int annee) {
        try {
            HashMap<Integer, HashMap<Module, Float>> modules = e.getNoteModules();

            if (annee == 1) {
                HashMap<Module, Float> notesSemestre1 = modules.get(1);
                HashMap<Module, Float> notesSemestre2 = modules.get(2);
                for (Map.Entry<Module, Float> k : notesSemestre1.entrySet()) {

                    if (moduleValide(e, (Module) k.getKey()) == false) {
                        System.out.println(
                                "l'etudiant " + e + " n'a pas valide le module : " + k.getKey() + " du semestre 1");
                    }
                }
                for (Map.Entry<Module, Float> m : notesSemestre2.entrySet()) {

                    if (moduleValide(e, (Module) m.getKey()) == false) {
                        System.out.println(
                                "l'etudiant " + e + " n'a pas valide le module : " + m.getKey() + " du semestre 2");

                    }

                }

            }
            if (annee == 3) {
                HashMap<Module, Float> notesSemestre5 = modules.get(5);
                HashMap<Module, Float> notesSemestre6 = modules.get(6);
                for (Map.Entry<Module, Float> k : notesSemestre5.entrySet()) {

                    if (moduleValide(e, (Module) k.getKey()) == false) {
                        System.out.println(
                                "l'etudiant " + e + " n'a pas valide le module : " + k.getKey() + " du semestre 1");
                    }
                }
                for (Map.Entry<Module, Float> m : notesSemestre6.entrySet()) {

                    if (moduleValide(e, (Module) m.getKey()) == false) {
                        System.out.println(
                                "l'etudiant " + e + " n'a pas valide le module : " + m.getKey() + " du semestre 2");

                    }
                }

            }
            if (annee == 2) {
                HashMap<Module, Float> notesSemestre3 = modules.get(3);
                HashMap<Module, Float> notesSemestre4 = modules.get(4);
                for (Map.Entry<Module, Float> k : notesSemestre3.entrySet()) {

                    if (moduleValide(e, (Module) k.getKey()) == false) {
                        System.out.println(
                                "l'etudiant " + e + "n'a pas valide le module :" + k.getKey() + "du semestre 1");
                    }
                }
                for (Map.Entry<Module, Float> m : notesSemestre4.entrySet()) {

                    if (moduleValide(e, (Module) m.getKey()) == false) {
                        System.out.println(
                                "l'etudiant " + e + " n'a pas valide le module : " + m.getKey() + " du semestre 2");

                    }
                }
            }
        } catch (Exception err) {
            System.out.println("vous n'avez pas saisi les notes des modules");
        }
    }

    public String toString() {
        return nom + " "+prenom;
    }

    public float gestionAbsence(int i, float note) {

        return note - i * sanctionAbsence;

    }

}
