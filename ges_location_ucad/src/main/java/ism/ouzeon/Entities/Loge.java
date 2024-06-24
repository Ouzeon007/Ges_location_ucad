package ism.ouzeon.Entities;


import java.time.LocalDate;

import ism.ouzeon.Enums.TypeBourse;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

public class Loge extends Boursier {
  private Chambre chambre;

  public Loge(String matricule,String nom, String prenom,String email,String telephone,TypeBourse typeBourse, LocalDate dateNaiss) {
    super.matricule = matricule;
    super.nom = nom;
    super.prenom = prenom;
    super.email = email;
    super.telephone = telephone;
    super.typeBourse = typeBourse;
    super.dateNaiss = dateNaiss;

  }

  @Override
  public String toString() {
    return "Loge [matricule=" + matricule + ", nom=" + nom + ", prenom=" + prenom + ", email=" + email
        + ", telephone=" + telephone + ", dateNaiss=" + dateNaiss + "]";
  }
}
