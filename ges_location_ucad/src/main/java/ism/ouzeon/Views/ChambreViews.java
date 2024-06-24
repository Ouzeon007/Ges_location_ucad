package ism.ouzeon.Views;

import java.util.Scanner;

import ism.ouzeon.Core.iService;
import ism.ouzeon.Entities.Chambre;
import ism.ouzeon.Entities.Etudiant;
import ism.ouzeon.Entities.Pavillon;
import ism.ouzeon.Enums.Etat;
import ism.ouzeon.Enums.TypeChambre;

public class ChambreViews extends Views<Chambre> {

  private iService<Chambre> chambreService;
  private iService<Pavillon> pavillonService;

  public ChambreViews(Scanner scanner, iService<Chambre> chambreService, iService<Pavillon> pavillonService) {
    super(scanner);
    this.chambreService = chambreService;
    this.pavillonService = pavillonService;
  }

  public Chambre give() {
    String num;
    do {
      System.out.println("Entrer le numero de la chambre");
      num = scanner.nextLine();
    } while (chambreService.findBy(num) == null);
    return chambreService.findBy(num);
  }

  public Pavillon givePavillon() {
    String num;
    do {
      System.out.println("Entrer le numero du pavillon");
      num = scanner.nextLine();
    } while (pavillonService.findBy(num) == null);

    return pavillonService.findBy(num);
  }

  @Override
  public Chambre saisie() {
    Chambre ch = new Chambre();
    ch.setEtat(Etat.Activer);
    ch.setType(saisiTypeChambre());

    Pavillon pavillon;
    pavillon = givePavillon();
    ch.setPavillon(pavillon);
    pavillon.add(ch);

    do {
      System.out.println("Entrer le numero de l'etage de la chambre");
      ch.setNumEtage(scanner.nextInt());
    } while (ch.getNumEtage() > pavillon.getNbrEtage() || ch.getNumEtage() < 0);
    return ch;
  }

  public TypeChambre saisiTypeChambre() {
    int pos;

    do {
      for (TypeChambre type : TypeChambre.values()) {
        System.out.println((type.ordinal() + 1) + "-" + type.name());
      }
      System.out.println("Entrer le type de la chambre");
      pos = scanner.nextInt();
    } while (pos <= 0 || pos > TypeChambre.values().length);
    return TypeChambre.values()[pos - 1];
  }

  @Override
  public Chambre update() {
    Chambre chambre;
    chambre = give();
    chambre.setType(saisiTypeChambre());
    // chambre.getPavillon().getTabChambre().remove(chambre);
    for (Etudiant etudiant : chambre.getTabEtudiants()) {
      chambre.getTabEtudiants().remove(etudiant);
    }
    Pavillon pavillon = givePavillon();
    chambre.setPavillon(pavillon);
    pavillon.add(chambre);
    do {
      System.out.println("Entrer le numero de l'etage de la chambre");
      chambre.setNumEtage(scanner.nextInt());
    } while (chambre.getNumEtage() > pavillon.getNbrEtage() || chambre.getNumEtage() < 0);
    return chambre;
  }

  @Override
  public void afficheEtudiant() {
    String num;
    Chambre ch;

    do {
      do {
        System.out.println("Entrer le numero de la chambre");
        num = scanner.nextLine();
      } while (num.length() == 0);
      ch = chambreService.findBy(num);
      if (ch == null) {
        System.out.println("cette chambre n'existe pas");
      }
    } while (ch == null);
    for (Etudiant etudiant : ch.getTabEtudiants()) {
      System.out.println(etudiant);
    }
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

}
