package ism.ouzeon.Repositories;

import java.util.ArrayList;
import java.util.List;

import ism.ouzeon.Entities.Etudiant;

public class EtudiantRepositorie extends Repositorie<Etudiant> {

  private List<Etudiant> Etudiants = new ArrayList<>();

  public int generateId() {
    return Etudiant.getNbrEtudiant();
  }

  @Override
  public boolean save(Etudiant etudiant) {
    boolean ok = true;
    if (ok) {
      int id = generateId();
      etudiant.setId(id);
      etudiant.setMatricule(generateNumero(id, "ETU"));
      Etudiants.add(etudiant);
      return true;
    } else {
      return false;
    }
  }

  

  @Override
  public List<Etudiant> selectAll() {
    return Etudiants;
  }

  public Etudiant retrouver(String mat) {
    for (Etudiant etudiant : Etudiants) {
      if (etudiant != null) {
        if (etudiant.getMatricule().compareTo(mat) == 0) {
          return etudiant;
        }
      }
    }
    return null;
  }

  @Override
  public boolean update(Etudiant object) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'update'");
  }
}
