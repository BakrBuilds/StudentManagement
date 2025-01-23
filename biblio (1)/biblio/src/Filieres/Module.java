package Filieres;
import java.util.LinkedHashMap;
import java.util.Map;



public class Module {
    protected String nomModule;
    protected LinkedHashMap<String,String[][]> matieres =new LinkedHashMap<>();
    protected int  nombreElement;



    public Module(String nom,int nombreElement) {
        nomModule=nom;

        this.nombreElement=nombreElement;
    }
    public Module() {

    }

    public String getNomModule() {
        return nomModule;
    }
    public int getNombreElement() {
        return nombreElement;
    }

    public void setNomModule(String nomModule) {
        this.nomModule = nomModule;
    }

    public LinkedHashMap<String, String[][]> getMatieres() {
        return matieres;
    }

    public void addMatiere(String matiere,int a,int b,int c,int d) {
        String [][] plan=new String[4][2];
        plan[0][0]="CM";
        plan[0][1]=String.valueOf(a);
        plan[1][0]="TD";
        plan[1][1]=String.valueOf(b);
        plan[2][0]="TP";
        plan[2][1]=String.valueOf(c);
        plan[3][0]="AP";
        plan[3][1]=String.valueOf(d);
        matieres.put(matiere,plan);
    }
    public void removeMatiere(String matiere) {
        matieres.remove(matiere);
    }
    public String toString() {
        return ""+nomModule;

    }
    public void afficherMatieres() {
        for (Map.Entry<String,String[][]> k : matieres.entrySet()) {
            String[][] value=(String[][]) k.getValue();
            System.out.println("La matiere "+k.getKey()+" a le volume Horaire suivant pour CM "+value[0][1]);
            System.out.println("La matiere "+k.getKey()+" a le volume Horaire suivant pour TD "+value[1][1]);
            System.out.println("La matiere "+k.getKey()+" a le volume Horaire suivant pour TP "+value[2][1]);
            System.out.println("La matiere "+k.getKey()+" a le volume Horaire suivant pour AP "+value[3][1]);


        }
    }

    public void setNombreElement(int nombreElement) {
        this.nombreElement = nombreElement;
    }


}

