package EquipePeda;


public class Employe extends Personne {
    protected double salaire;
    protected int id;


    public Employe(String nom,String prenom,String tele,String email,String CIN,double salaire,int day,int year,int mounth,int id) {
        super(nom,prenom,tele,email,CIN,day,mounth,year,id);
        this.salaire=salaire;

    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public Employe() {
        super();
    }
    public double getSalaire() {
        return salaire;
    }
    public void setSalaire(double salaire) {
        this.salaire = salaire;
    }







}



