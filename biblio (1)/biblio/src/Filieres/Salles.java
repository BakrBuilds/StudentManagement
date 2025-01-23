package Filieres;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;



public class Salles {
    protected static HashMap<Integer,Boolean> num= new HashMap<Integer, Boolean>();
    protected static String [] activite= {"CM","TD","TP","AP"};
    protected static HashMap<String,ArrayList<Integer>> salle=new HashMap<String, ArrayList<Integer>>();

    public Salles() {
        for ( Map.Entry <Integer,Boolean> n : num.entrySet()) {
            num.replace((Integer) n.getKey(),true);
        }
    }


    public int NombreSalle() {
        int s = 0;
        for(Map.Entry<String,ArrayList<Integer>> entry : salle.entrySet()) {

            s=s+entry.getValue().size();
        }

        return s;
    }
    public void addNumSalles() {
        for(Map.Entry<String,ArrayList<Integer>> entry : salle.entrySet()) {
            ArrayList<Integer> sa=entry.getValue();
            for(Integer n : sa) {
                num.put(n,true);
            }
        }
    }

    public static void setAllTrue() {
        for ( Map.Entry <Integer,Boolean> n : num.entrySet()) {
            num.replace((Integer) n.getKey(),true);
        }


    }
    public HashMap<Integer, Boolean> getNum() {
        return num;
    }
    public void setNum(HashMap<Integer, Boolean> num) {
        Salles.num = num;
    }



    public void afficherEtat(int i) {
        System.out.println(num.get(i));
    }
    public static void SetEtatSalleOccupee(int i) {
        num.replace(i, false);
    }



    public static int affecterSalle(String act) {
        try {
            ArrayList<Integer> salles=salle.get(act);
            for(Integer n : salles) {
                if(num.get(n) == true) {
                    SetEtatSalleOccupee(n);
                    System.out.println(n);
                    return n;
                }
            }
        }
        catch(Exception e){
            System.out.println("L'activité "+act +" choisie ne figure pas dans la liste des activités disponibles");
        }
        return 0;
    }

    public String[] getActivite() {
        return activite;
    }
    public void setActivite(String[] activite) {
        Salles.activite = activite;
    }

    public static HashMap<String, ArrayList<Integer>> getSalle() {
        return salle;
    }
    public static void setSalle(HashMap<String, ArrayList<Integer>> salle) {
        Salles.salle = salle;
    }



    public void sallesAct(ArrayList<Integer> cm,ArrayList<Integer>tp,ArrayList<Integer> td,ArrayList<Integer>ap) {
        salle.put("CM",cm);
        salle.put("TP",tp);
        salle.put("TD",td);
        salle.put("AP",ap);
    }
    public void afficher() {
        for (Map.Entry<String, ArrayList<Integer>> entry : salle.entrySet()) {
            ArrayList<Integer> value = entry.getValue();
            System.out.println("Pour: "+entry.getKey()+" on a");
            for(Integer n : value) {
                System.out.println( n);
            }
        }
    }

}
