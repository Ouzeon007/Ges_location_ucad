package ism.ouzeon.Entities;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Etudiant {

  protected int id;
  protected String matricule;
  protected String nom;
  protected String prenom;
  protected String email;
  protected String telephone;
  protected LocalDate dateNaiss;
  protected static int nbrEtudiant;

  public static int getNbrEtudiant() {
    return ++nbrEtudiant;
  }

  @Override
  public String toString() {
    return "Etudiant [matricule=" + matricule + ", nom=" + nom + ", prenom=" + prenom + ", email=" + email
        + ", telephone=" + telephone + ", dateNaiss=" + dateNaiss + "]";
  }

}
