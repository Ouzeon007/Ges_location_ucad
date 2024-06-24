package ism.ouzeon.Repositories;

import java.util.ArrayList;
import java.util.List;

import ism.ouzeon.Entities.Chambre;
import ism.ouzeon.Entities.Etudiant;
import ism.ouzeon.Enums.Etat;

public class ChambreRepositorie extends Repositorie<Chambre> {

  private List<Chambre> Chambres = new ArrayList<>();

  public Chambre retrouver(String mat) {
    for (Chambre chambre : Chambres) {
      if (chambre != null) {
        if (chambre.getNumero().compareTo(mat) == 0) {
          return chambre;
        }
      }
    }
    return null;
  }

  @Override
  public boolean save(Chambre chambre) {
    boolean ok = true;
    if (ok) {
      int id = generateId();
      chambre.setId(id);
      chambre.setNumero(generateNumero(id, "CHA"));
      Chambres.add(chambre);
      return true;
    } else {
      return false;
    }
  }

  @Override
  public List<Chambre> selectAll() {
    return Chambres;
  }

  @Override
  public int generateId() {
    return Chambre.getNbrChambre();
  }

  @Override
  public boolean update(Chambre chambre) {
    for (Chambre c : Chambres) {
      if (c != null) {
        if (c.getNumero().compareTo(chambre.getNumero()) == 0) {
          c.getPavillon().getTabChambre().remove(chambre);
          c.setType(chambre.getType());
          c.setPavillon(chambre.getPavillon());
          c.setNumEtage(chambre.getNumEtage());
          return true;
        }
      }
    }
    return false;
  }

  @Override
  public boolean archiver(Chambre chambre) {
    for (Chambre c : Chambres) {
      if (c != null) {
        if (c.getNumero().compareTo(chambre.getNumero()) == 0) {
          c.setEtat(Etat.Archiver);
          for (Etudiant etudiant : c.getTabEtudiants()) {
            c.getTabEtudiants().remove(etudiant);
          }

        }
      }
    }
    return true;
  }
}
