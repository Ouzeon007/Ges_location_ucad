package ism.ouzeon.Views;

import java.util.Scanner;

import ism.ouzeon.Core.iService;
import ism.ouzeon.Entities.Boursier;
import ism.ouzeon.Entities.Chambre;
import ism.ouzeon.Entities.Etudiant;
import ism.ouzeon.Entities.Loge;
import ism.ouzeon.Entities.NonBoursier;
import ism.ouzeon.Enums.TypeBourse;
import ism.ouzeon.Enums.TypeChambre;

public class EtudiantViews extends Views<Etudiant> {

  private iService<Etudiant> etudiantService;
  private iService<Chambre> chambreService;

  public EtudiantViews(Scanner scanner, iService<Etudiant> etudiantService, iService<Chambre> chambreService) {
    super(scanner);
    this.etudiantService = etudiantService;
    this.chambreService = chambreService;
  }

  @Override
  public Etudiant saisie() {
    int choix = ChoixEtudiant();
    switch (choix) {
      case 1:
        return saisieBoursier();
      case 2:
        return saisieLoge();
      case 3:
        return saisieNonBoursier();
      default:
        return null;
    }
  }

  public Boursier saisieBoursier() {
    Boursier etudiant = new Boursier();
    do {
      System.out.println("Entrez le nom de l'etudiant");
      etudiant.setNom(scanner.nextLine());
    } while (etudiant.getNom().length() == 0);

    do {
      System.out.println("Entrez le prenom de l'etudiant");
      etudiant.setPrenom(scanner.nextLine());
    } while (etudiant.getPrenom().length() == 0);

    do {
      System.out.println("Entrez l'email de l'etudiant");
      etudiant.setEmail(scanner.nextLine());
    } while (etudiant.getEmail().length() == 0);

    do {
      System.out.println("Entrez le telephone de l'etudiant");
      etudiant.setTelephone(scanner.nextLine());
    } while (etudiant.getTelephone().length() == 0);

    System.out.println("entrer la date de naissance de l'etudiant");
    etudiant.setDateNaiss(formatDate(scanner.nextLine()));

    etudiant.setTypeBourse(saisiTypeBoursse());

    return etudiant;

  }

  public Boursier saisieLoge() {
    Loge etudiant = new Loge();
    do {
      System.out.println("Entrez le nom de l'etudiant");
      etudiant.setNom(scanner.nextLine());
    } while (etudiant.getNom().length() == 0);

    do {
      System.out.println("Entrez le prenom de l'etudiant");
      etudiant.setPrenom(scanner.nextLine());
    } while (etudiant.getPrenom().length() == 0);

    do {
      System.out.println("Entrez l'email de l'etudiant");
      etudiant.setEmail(scanner.nextLine());
    } while (etudiant.getEmail().length() == 0);

    do {
      System.out.println("Entrez le telephone de l'etudiant");
      etudiant.setTelephone(scanner.nextLine());
    } while (etudiant.getTelephone().length() == 0);

    System.out.println("entrer la date de naissance de l'etudiant");
    etudiant.setDateNaiss(formatDate(scanner.nextLine()));

    etudiant.setTypeBourse(saisiTypeBoursse());

    return etudiant;

  }

  public NonBoursier saisieNonBoursier() {
    NonBoursier etudiant = new NonBoursier();
    do {
      System.out.println("Entrez le nom de l'etudiant");
      etudiant.setNom(scanner.nextLine());
    } while (etudiant.getNom().length() == 0);

    do {
      System.out.println("Entrez le prenom de l'etudiant");
      etudiant.setPrenom(scanner.nextLine());
    } while (etudiant.getPrenom().length() == 0);

    do {
      System.out.println("Entrez l'email de l'etudiant");
      etudiant.setEmail(scanner.nextLine());
    } while (etudiant.getEmail().length() == 0);

    do {
      System.out.println("Entrez le telephone de l'etudiant");
      etudiant.setTelephone(scanner.nextLine());
    } while (etudiant.getTelephone().length() == 0);

    System.out.println("entrer la date de naissance de l'etudiant");
    etudiant.setDateNaiss(formatDate(scanner.nextLine()));

    do {
      System.out.println("Entrez l'adresse de l'etudiant");
      etudiant.setAddresse(scanner.nextLine());
    } while (etudiant.getAddresse().length() == 0);

    return etudiant;

  }

  public TypeBourse saisiTypeBoursse() {
    int pos;

    do {
      for (TypeBourse type : TypeBourse.values()) {
        System.out.println((type.ordinal() + 1) + "-" + type.name());
      }
      System.out.println("Entrer le type de bourse");
      pos = scanner.nextInt();
    } while (pos <= 0 || pos > TypeBourse.values().length);
    return TypeBourse.values()[pos - 1];
  }

  public int ChoixEtudiant() {
    int choix;
    do {
      System.out.println("1-Etudiant boursier");
      System.out.println("2-Etudiant boursier Loge");
      System.out.println("3-Etudiant non boursier");
      choix = scanner.nextInt();
    } while (choix < 1 || choix > 3);
    return choix;
  }

  public Etudiant give() {
    scanner.nextLine();
    String mat;
    do {
      // scanner.nextLine();
      System.out.println("Entrer la matricule de l'etudiant");
      mat = scanner.nextLine();
    } while (etudiantService.findBy(mat) == null || mat.isEmpty());

    return etudiantService.findBy(mat);
  }

  public Chambre giveChambre() {
    System.out.println("Entrer le numero de la chambre");
    String num = scanner.nextLine();
    return chambreService.findBy(num);
  }

  // public void AffecterChambre() {
  //   Etudiant etudiant;
  //     etudiant = give();
  //   if (etudiant instanceof Loge) {
  //     Chambre chambre;
  //     do {
  //       chambre = giveChambre();
  //       if (chambre.getEtat() == Etat.Archiver) {
  //         System.out.println("la chambre est archiver");
  //       }
  //     } while (chambre == null || chambre.getEtat() == Etat.Archiver);
  //     if (chambre.getTabEtudiants().size() < tailleChambre(chambre)) {
  //       chambre.add(etudiant);
  //       ((Loge) etudiant).setChambre(chambre);
  //     } else {
  //       System.out.println("la chambre est pleine");
  //     }
  //     if (chambre.getTabEtudiants().size() < tailleChambre(chambre)) {
  //       chambre.add(etudiant);
  //       ((Loge) etudiant).setChambre(chambre);
  //     }
  //   } else {
  //     System.out.println("cet etudiant n'est pas loge");
  //   }

  // }

  public int tailleChambre(Chambre chambre) {
    if (chambre.getType().compareTo(TypeChambre.Individuel) == 0) {
      return 1;
    } else {
      return 2;
    }
  }


  
  //   @Override
  // public void affiche(List<Etudiant> etudiants) {
  //   for (Etudiant etudiant : etudiants) {
  //     if (etudiant instanceof Boursier) {
  //       System.out.println(etudiant);
  //     }
  //     if (etudiant instanceof Boursier) {
  //       System.out.println(etudiant);
  //     }
  //     if (etudiant instanceof Boursier) {
  //       System.out.println(etudiant);
  //     }
      
  //   }
  // }

}
