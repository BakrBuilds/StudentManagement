package Inscriptions;
import EquipePeda.Personne;



public class InscriptionAdministrative implements Inscription<Float > {
	protected static float montantApayer;
	protected Boolean reussirBac;
	protected float montantPaye;
	protected Personne personne=new Personne();
	protected int age;
	protected Boolean validation;

	public InscriptionAdministrative() {

	}


	public Boolean inscriptionValidee(Float montantApayer) {
		if(age<22 && reussirBac==true && montantPaye==montantApayer) {
			//age<22
			validation=true;
			return true;

		}
		validation=false;
		return false;
	}

	public Boolean getReussirBac() {
		return reussirBac;
	}


	public void setReussirBac(Boolean reussirCnc) {
		this.reussirBac= reussirCnc;
	}

	public float getMontantPaye() {
		return montantPaye;
	}


	public void setMontantPaye(float montantPaye) {
		this.montantPaye = montantPaye;
	}


	public int getAge() {
		return age;
	}


	public void setAge(int age) {
		this.age = age;
	}


	public Boolean getValidation() {
		return validation;
	}


	public void setValidation(Boolean validation) {
		this.validation = validation;
	}


	public static float getMontantApayer() {
		return montantApayer;
	}
	public static void setMontantApayer(float montantApayer) {
		InscriptionAdministrative.montantApayer = montantApayer;
	}

	public Personne getPersonne() {
		return personne;
	}
	public void setPersonne(Personne personne) {
		this.personne = personne;
	}

}
