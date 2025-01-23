package EquipePeda;
import java.util.Calendar;

public class Personne {
	protected int id;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	protected String nom;
	protected String prenom;
	protected Calendar dateNaissance=Calendar.getInstance();
	protected String telephone;
	protected String email;
	protected String CIN;
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
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCIN() {
		return CIN;
	}
	public void setCIN(String cIN) {
		CIN = cIN;
	}
	public Calendar getDateNaissance() {
		return dateNaissance;
	}
	public void setDateNaissance(Calendar dateNaissance) {
		this.dateNaissance = dateNaissance;
	}


	public Personne() {

	}
	public Personne(String nom, String prenom,String tele,String email,String CIN,int day,int mounth,int year,int id) {
		this.nom = nom;
		this.prenom = prenom;
		dateNaissance.set(year,mounth,day);
		telephone=tele;
		this.email=email;
		this.CIN=CIN;
		this.id=id;
	}
	public Personne(String nom, String prenom) {
		this.nom = nom;
		this.prenom = prenom;

	}

	public String toString() {

		return this.nom + " "
		+ this.prenom;

	}
}






