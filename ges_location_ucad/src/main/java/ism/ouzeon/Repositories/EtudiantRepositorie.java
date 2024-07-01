package ism.ouzeon.Repositories;

import java.util.ArrayList;
import java.util.List;

import ism.ouzeon.Entities.Chambre;
import ism.ouzeon.Entities.Etudiant;
import ism.ouzeon.Entities.Loge;
import ism.ouzeon.Enums.Etat;
import ism.ouzeon.Enums.TypeChambre;

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

  @Override
  public boolean affecter( Etudiant etudiant,  Chambre chambre) {

    if (etudiant instanceof Loge) {
      if (chambre.getEtat() == Etat.Archiver) {
        System.out.println("la chambre est archiver");
        return false;
      } if (chambre.getTabEtudiants().size() < tailleChambre(chambre)) {
        chambre.add(etudiant);
        ((Loge) etudiant).setChambre(chambre);
        return true;
      } else {
        System.out.println("la chambre est pleine");
        return false;
      }
    } else {
      System.out.println("cet etudiant n'est pas loge");
      return false;
    }
  }

  public int tailleChambre(Chambre chambre) {
    if (chambre.getType().compareTo(TypeChambre.Individuel) == 0) {
      return 1;
    } else {
      return 2;
    }
  }



}
