package Inscriptions;
import java.util.ArrayList;

import Filieres.Etudiant;
import Filieres.Filiere;

import EquipePeda.Personne;

public class InscriptionPeda implements Inscription<InscriptionAdministrative> {

	protected Filiere choixFiliere;
	protected Boolean validationAdmin;
	protected Boolean validation;
	protected Personne personne=new Personne();
	protected InscriptionAdministrative ins;



	public InscriptionPeda(Filiere filiere) {

    	   choixFiliere=filiere;


	}





	public Boolean inscriptionValidee(InscriptionAdministrative i) {
		// TODO Auto-generated method stub
		ArrayList<Etudiant> etud=new ArrayList<>();
		etud=choixFiliere.getEtudiants();
		validationAdmin=i.inscriptionValidee(InscriptionAdministrative.getMontantApayer());

		int capacite=choixFiliere.getCapacite();


		if(etud.size()<capacite && validationAdmin) {
			personne=new Etudiant(i.getPersonne().getNom(),i.getPersonne().getPrenom(),i.getPersonne().getId());
			etud.add((Etudiant)personne);
			((Etudiant) personne).setFiliere(choixFiliere);
			validation=true;
		return true;

	}
		validation=false;
		return false;}

	public void setFiliere(Filiere f) {
		choixFiliere.suppEtudiant((Etudiant)personne);
		this.setChoixFiliere(f);


	}

	public Filiere getChoixFiliere() {
		return choixFiliere;
	}





	public void setChoixFiliere(Filiere choixFiliere) {
		this.choixFiliere = choixFiliere;
	}





	public Boolean getValidation() {
		return validation;
	}





	public void setValidation(Boolean validation) {
		this.validation = validation;
	}





	public Personne getPersonne() {
		return personne;
	}





	public void setPersonne(Personne personne) {
		this.personne = personne;
	}





	public void setChoix(Etudiant e,Filiere filiere) {


		e.setFiliere(filiere);


	}







	public Boolean getValidationAdmin() {
		return validationAdmin;
	}





	public void setValidationAdmin(Boolean validationAdmin) {
		this.validationAdmin = validationAdmin;
	}

}
