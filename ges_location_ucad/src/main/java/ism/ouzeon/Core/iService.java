package ism.ouzeon.Core;

import java.util.List;

import ism.ouzeon.Entities.Etudiant;

public interface iService<T> {
  int generateId();

  boolean save(T object);

  List<T> show();

  T findBy(String mat);

  void modifier(T object);
  boolean archiver(T object);

  List<T> getChambresPavillon(int id );
  List<Etudiant> getEtudiantChambres(int id );
}
