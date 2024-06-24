package ism.ouzeon.Repositories;

import java.util.ArrayList;
import java.util.List;

import ism.ouzeon.Entities.Pavillon;

public class PavillonRepositorie extends Repositorie<Pavillon>{

  private List<Pavillon> Pavillons = new ArrayList<>();

  @Override
  public boolean save(Pavillon pavillon) {
    boolean ok = true;
    if (ok) {
      int id = generateId();
      pavillon.setId(id);
      pavillon.setNumero(generateNumero(id, "PAV"));
      Pavillons.add(pavillon);
      return true;
    } else {
      return false;
    }
  }

  @Override
  public List<Pavillon> selectAll() {
    return Pavillons;
  }

  @Override
  public int generateId() {
    return Pavillon.getNbrPavillon();
  }

  @Override
  public Pavillon retrouver(String mat) {
    for (Pavillon pavillon : Pavillons) {
      if (pavillon != null) {
        if (pavillon.getNumero().compareTo(mat) == 0) {
          return pavillon;
        }
      }
    }
    return null;
  }

  public boolean update(Pavillon pavillon) {
    for (Pavillon p : Pavillons) {
      if (p != null) {
        if (p.getNumero().compareTo(pavillon.getNumero()) == 0) {
          p.setNbrEtage(pavillon.getNbrEtage());
          return true;
        }
      }
    }
    return false;
  }
}
