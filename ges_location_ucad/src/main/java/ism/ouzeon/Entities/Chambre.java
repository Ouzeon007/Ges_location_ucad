package ism.ouzeon.Entities;

import java.util.ArrayList;
import java.util.List;

import ism.ouzeon.Enums.Etat;
import ism.ouzeon.Enums.TypeChambre;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Chambre {

  private int id;
  private String numero;
  private int numEtage;
  private TypeChambre type;
  private Etat etat;
  private Pavillon pavillon;
  private static int nbrChambre;
  private List<Etudiant> tabEtudiants = new ArrayList<>();

  public static int getNbrChambre() {
    return ++nbrChambre;
  }


  public void add(Etudiant etudiant) {
    tabEtudiants.add(etudiant);
  }


  @Override
  public String toString() {
    return "Chambre [numero=" + numero + ", numEtage=" + numEtage + ", type=" + type + ", etat=" + etat + "]";
  }
}
