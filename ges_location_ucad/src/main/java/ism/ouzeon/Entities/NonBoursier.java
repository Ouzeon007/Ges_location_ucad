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
public class NonBoursier extends Etudiant {
  private String addresse;

    public NonBoursier(String matricule,String nom, String prenom,String email,String telephone, LocalDate dateNaiss,String addresse) {
    super.matricule = matricule;
    super.nom = nom;
    super.prenom = prenom;
    super.email = email;
    super.telephone = telephone;
    super.dateNaiss = dateNaiss;
    this.addresse = addresse;
  }
  @Override
  public String toString() {
    return "NonBoursier [matricule=" + matricule + ", nom=" + nom + ", prenom=" + prenom
        + ", email=" + email + ", telephone=" + telephone + ", dateNaiss=" + dateNaiss + " , adresse="+addresse+"]";
  }

}
