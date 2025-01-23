package Filieres;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import EquipePeda.ChefDeFiliere;
import EquipePeda.Employe;



public class Filiere {
	protected String nomFiliere;// GI
	protected ArrayList<Employe> equipe = new ArrayList<>();// HashMap
	protected HashMap<Integer, LinkedHashMap<String, String>> emplois = new HashMap<>();
	protected int anneeOuverture;
	protected int idPromo;// 15
	protected Specialites specialite;
	protected int capacite;
	protected ArrayList<Etudiant> etudiants = new ArrayList<Etudiant>();






	public Filiere(String nom,int annee) {

		Calendar time = Calendar.getInstance();
		int year = time.get(Calendar.YEAR);
		this.anneeOuverture=annee;
		idPromo = year - anneeOuverture;

		this.nomFiliere = nom;
	}






	public void afficherEmplois(int semaine) {
		for (Map.Entry<Integer, LinkedHashMap<String, String>> entry : emplois.entrySet()) {
			if(entry.getKey() == semaine) {

				Salles.setAllTrue();


				LinkedHashMap<String, String> value = entry.getValue();

				for (Map.Entry<String, String> entry2 : value.entrySet()) {

					String key1 = entry2.getKey();
					String key2 = entry2.getValue();
					String act = key2.substring(0,5);
					System.out.println(act);
					if (act.contentEquals("TD")) {

						System.out.println(key1 +":"+key2+"=> salle " + Salles.affecterSalle("TD"));
					}
					else if (act.contentEquals("TP")) {

						System.out.println(key1 + ":" + key2 + "=> salle " + Salles.affecterSalle("TP"));

					}
					else if (act.contentEquals("AP")) {

						System.out.println(key1 + ":" + key2 + "=> salle " + Salles.affecterSalle("AP"));
					}
					else {

						System.out.println("  " + key1 + " : " + key2 + "=> salle " +Salles.affecterSalle("CM"));
					}
				}
			}
		}
	}



	public void suppEtudiant(Etudiant e) {
		etudiants.remove(e);
	}
	public ArrayList<Etudiant> getEtudiants() {
		return etudiants;
	}
	public void addEtudiant(Etudiant e) {
		etudiants.add(e);
	}

	public HashMap<Integer, LinkedHashMap<String, String>> getEmplois() {
		return emplois;
	}
	public ChefDeFiliere getChef() {
		for (Employe e : equipe) {
			if (e.getId() == 1) {
				return (ChefDeFiliere) e;
			}
		}
		return null;
	}
	public void setEmplois(HashMap<Integer, LinkedHashMap<String, String>> emplois) {
		this.emplois = emplois;
	}

	public int getAnneeOuverture() {
		return anneeOuverture;
	}



	public int getCapacite() {
		return capacite;
	}

	public void setCapacite(int capacite) {
		this.capacite = capacite;
	}

	public ArrayList<EquipePeda.Employe> getEquipe() {
		return equipe;
	}

	public void setEquipe(ArrayList<Employe> equipe) {
		this.equipe = equipe;
	}

	public String getNomFiliere() {
		return nomFiliere;
	}

	public void setNomFiliere(String nomFiliere) {
		this.nomFiliere = nomFiliere;
	}
	public Filiere() {
		super();
	}

	public Specialites getSpecialite() {
		return specialite;
	}

	public void setSpecialite(Specialites specialite) {
		this.specialite = specialite;
	}

	public void addEmplois(Integer i, LinkedHashMap<String, String> planning) {
		emplois.put(i, planning);
	}



	public String seanceDuJour(Integer semaine, String jour) {
		HashMap<String, String> Map = emplois.get(semaine);
		if (Map == null) {
			return null;
		}
		return Map.get(jour);
	}

	public void addSeance(Integer semaine, String jour, String seance) {
		if (emplois.containsKey(semaine)==false) {
			LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();
			map.put(jour, seance);
			emplois.put(semaine, map);
		}
		else {
			LinkedHashMap<String, String> map = emplois.get(semaine);

			map.put(jour, seance);}

	}

	public void listEtudiants() {
		try {
			List<String> e = new ArrayList<String>();
			for (int i = 0; i < etudiants.size(); i++) {
				e.add(etudiants.get(i).getNom() + " " + etudiants.get(i).getPrenom());
			}
			Collections.sort(e);
			for (int i = 0; i < etudiants.size(); i++) {
				System.out.println(e.get(i) + "");
			}
		} catch (Exception err) {
			System.out.println("pas d'etudiants");
		}
	}

	public void addEquipe(Employe e) {
		equipe.add(e);
	}

	public String toString() {
		return nomFiliere;
	}


	public ChefDeFiliere chef() {
		ArrayList<Employe> employe=this.getEquipe();
		for(Employe e : employe) {

			if(e.getId()==1) {
				return (ChefDeFiliere) e;

			}
		}
		return null;
	}



	public int getIdPromo() {
		// TODO Auto-generated method stub
		return idPromo;

	}






	public void setAnneeOuverture(int annee) {
		// TODO Auto-generated method stub
		anneeOuverture=annee;

	}

}
