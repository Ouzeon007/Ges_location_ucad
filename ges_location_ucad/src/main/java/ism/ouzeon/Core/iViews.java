package ism.ouzeon.Core;

import java.util.List;

public interface iViews<T> {

  T saisie();

  void affiche(List<T> object);

  T give();

  T update();

  void  AffecterChambre();

  void afficheChambre();

  void afficheEtudiant();
}
