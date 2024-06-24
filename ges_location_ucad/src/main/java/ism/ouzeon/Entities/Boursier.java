package ism.ouzeon.Entities;

import java.time.LocalDate;

import ism.ouzeon.Enums.TypeBourse;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Getter
@Setter
@NoArgsConstructor

public class Boursier extends Etudiant {
  protected TypeBourse typeBourse;

  public Boursier(String matricule,String nom, String prenom,String email,String telephone,TypeBourse typeBourse, LocalDate dateNaiss) {
    super.matricule = matricule;
    super.nom = nom;
    super.prenom = prenom;
    super.email = email;
    super.telephone = telephone;
    this.typeBourse = typeBourse;
    super.dateNaiss = dateNaiss;
  }

  @Override
  public String toString() {
    return "Boursier [matricule=" + matricule + ", nom=" + nom + ", prenom=" + prenom
        + ", email=" + email + ", telephone=" + telephone + ", dateNaiss=" + dateNaiss + "typeBourse="
        + typeBourse + "]";
  }

}
