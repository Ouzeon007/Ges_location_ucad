package ism.ouzeon.RepositorieDB;

import java.util.List;

import ism.ouzeon.Core.iRepositorie;
import ism.ouzeon.Entities.Chambre;
import ism.ouzeon.Entities.Etudiant;

public abstract class Repositorie<T> implements iRepositorie<T>{
    @Override
    public String generateNumero(final int nbre, final String format) {
    final int size = String.valueOf(nbre).length();
    return format + "0".repeat((4 - size) < 0 ? 0 : 4 - size) + nbre;
  }

    @Override
    public boolean archiver(final T object) {
      // TODO Auto-generated method stub
      return true;
    }

    @Override
    public boolean update(final T object) {
      // TODO Auto-generated method stub
      return false;
    }

    @Override
    public List<Chambre> getChambresPavillon(final int id) {
      // TODO Auto-generated method stub
      return null;
    }

    @Override
    public boolean affecter(final Etudiant etudiant, final Chambre chambre) {
      // TODO Auto-generated method stub
      return false;
    }

    @Override
    public int generateId() {
      // TODO Auto-generated method stub
      return 0;
    }

    @Override
    public T retrouver(final String mat) {
      // TODO Auto-generated method stub
      return null;
    }

    @Override
    public boolean save(final T object) {
      // TODO Auto-generated method stub
      return false;
    }

    @Override
    public List<T> selectAll() {
      // TODO Auto-generated method stub
      return null;
    }

    @Override
    public List<Etudiant> getEtudiantChambres(int id) {
      // TODO Auto-generated method stub
      return null;
    }
}
