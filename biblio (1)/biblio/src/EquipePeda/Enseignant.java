package EquipePeda;
import java.util.HashSet;
import java.util.LinkedHashMap;

import Filieres.Module;

public class Enseignant extends Employe {
    public String getActivite() {
        return activite;
    }
    public void setActivite(String activite) {
        this.activite = activite;
    }
    protected HashSet<String> matiere=new HashSet<>();
    protected String activite;

    public Enseignant(String nom,String prenom,String tele,String mail,String CIN,double salaire,String activite,int year,int month,int day,int id) {
        super(nom,prenom,tele,mail,CIN,salaire,year,month,day,id);
        this.activite=activite;
    }
    public Enseignant(String nom,String prenom) {
        super();
        this.nom=nom;
        this.prenom=prenom;

    }
    public Enseignant() {
        // TODO Auto-generated constructor stub
    }
    public int volumeHoraire(Module module) {
        int s=0;
        for(String n : matiere) {
            LinkedHashMap<String,String[][]> matieresModule= (LinkedHashMap<String, String[][]>) module.getMatieres();
            String[][]  m=(String[][]) matieresModule.get(n);
            if (m[0][0]==this.activite) {
                s=s+Integer.parseInt(m[0][1]);
            }
            else if (m[1][0]==this.activite) {
                s=s+Integer.parseInt(m[1][1]);
            }
            else if (m[2][0]==this.activite) {
                s=s+Integer.parseInt(m[2][1]);
            }
            else if (m[3][0]==this.activite) {
                s=s+Integer.parseInt(m[3][1]);
            }
        }
        return s;
    }
    public void afficherMat() {
        for(String n : matiere) {
            System.out.println(n);
        }
    }
    public HashSet<String> getMatiere() {
        return matiere;
    }
    public void addMatiere(String mat) {
        matiere.add(mat);
    }
    public String getNom() {
        return nom;
    }
    public String getPrenom() {
        return prenom;
    }
    public String toString() {
        String s=nom+" "+prenom;
        return s;
    }
}
