package Filieres;

import java.util.HashSet;

import Filieres.Module;

public class Specialites {
    private static final long serialVersionUID = 1L;
    protected String domaine;
    protected String nomSpecialite;
    protected HashSet<Module> modules=new HashSet<>();



    public Specialites(String domaine) {
        this.domaine=domaine;

    }

    public Specialites() {
        // TODO Auto-generated constructor stub
    }

    public String getNomSpecialite() {
        return nomSpecialite;
    }
    public void setNomSpecialite(String nomSpecialite) {
        this.nomSpecialite = nomSpecialite;
    }
    public void addModule(Module m1) {
        modules.add(m1);
    }
    public void removeModule(Module m1) {
        modules.remove(m1);
    }


    public void setDomaine(String domaine) {
        this.domaine = domaine;
    }
    public String getDomaine() {
        return domaine;
    }

    public HashSet<Module> getModules() {
        return modules;
    }


}
