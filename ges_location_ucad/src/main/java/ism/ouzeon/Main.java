package ism.ouzeon;

import java.util.Scanner;

import ism.ouzeon.Core.iRepositorie;
import ism.ouzeon.Core.iService;
import ism.ouzeon.Core.iViews;
import ism.ouzeon.Entities.Chambre;
import ism.ouzeon.Entities.Etudiant;
import ism.ouzeon.Entities.Pavillon;
import ism.ouzeon.Repositories.ChambreRepositorie;
import ism.ouzeon.Repositories.EtudiantRepositorie;
import ism.ouzeon.Repositories.PavillonRepositorie;
import ism.ouzeon.Services.ChambreService;
import ism.ouzeon.Services.EtudiantService;
import ism.ouzeon.Services.PavillonService;
import ism.ouzeon.Views.ChambreViews;
import ism.ouzeon.Views.EtudiantViews;
import ism.ouzeon.Views.PavillonViews;

public class Main {
  private static Scanner scanner = new Scanner(System.in);

  public static void main(String[] args) {

    iRepositorie<Etudiant> etudiantRepositorie = new EtudiantRepositorie();
    iRepositorie<Chambre> chambreRepositorie = new ChambreRepositorie();
    iRepositorie<Pavillon> pavillonRepositorie = new PavillonRepositorie();

    iService<Etudiant> etudiantService = new EtudiantService(etudiantRepositorie);
    iService<Chambre> chambreService = new ChambreService(chambreRepositorie);
    iService<Pavillon> pavillonService = new PavillonService(pavillonRepositorie);

    iViews<Etudiant> etudiantVue = new EtudiantViews(scanner, etudiantService, chambreService);
    iViews<Chambre> chambreVue = new ChambreViews(scanner, chambreService, pavillonService);
    iViews<Pavillon> pavillonVue = new PavillonViews(scanner, pavillonService);

    int choix;
    do {
      choix = menu();
      switch (choix) {
        case 1 -> {
          pavillonService.save(pavillonVue.saisie());
        }
        case 2 -> {
          pavillonVue.affiche(pavillonService.show());
        }
        case 3 -> {
          Pavillon pavillon;
          do {
            pavillon = pavillonVue.give();
          } while (pavillon == null);

          System.out.println("Entrer le nombre d'etage du pavillon");
          pavillon.setNbrEtage(scanner.nextInt());

          pavillonService.modifier(pavillon);
        }
        case 4 -> {
          chambreService.save(chambreVue.saisie());
        }
        case 5 -> {
          chambreVue.affiche(chambreService.show());
        }
        case 6 -> {
          Chambre chambre = chambreVue.update();
          chambreService.modifier(chambre);
        }

        case 7 -> {
          etudiantService.save(etudiantVue.saisie());
        }
        case 8 -> {
          etudiantVue.affiche(etudiantService.show());
        }

        case 9 -> {

          Etudiant etudiant=etudiantVue.give();
          Chambre chambre=chambreVue.give();
          etudiantService.affecter(etudiant, chambre);
          // if (etudiantService.affecter(etudiant, chambre)==false) {
          //   System.out.println("l'etudiant n'est pas loge");
          // }

        }

        case 10 -> {
          chambreVue.affiche(pavillonService.getChambresPavillon(pavillonVue.give().getId()));
        }
        case 11 -> {
          etudiantVue.affiche(chambreService.getEtudiantChambres(chambreVue.give().getId()));
        }
        case 12 -> {
          scanner.nextLine();
          Chambre ch = chambreVue.give();
          if (ch != null) {
            chambreService.archiver(ch);
          }
        }
        case 13 -> {
          System.out.println("Au revoir");
        }
      }
    } while (choix != 13);
  }

  public static int menu() {
    System.out.println("-------------------------------------------");
    System.out.println("1- Ajouter un pavillon");
    System.out.println("2- lister les pavillon");
    System.out.println("3- Modifier un pavillon");
    System.out.println("4- Ajouter une chambre");
    System.out.println("5- lister les chambres");
    System.out.println("6- modifier une chambre");
    System.out.println("7- Ajouter un etudiant");
    System.out.println("8- lister les etudiants");
    System.out.println("9- Affecter une chambre a un etudiant");
    System.out.println("10- Lister les chambres d'un pavillon");
    System.out.println("11- Lister les etudiants d'une chambre");
    System.out.println("12- Archiver une chambre");
    System.out.println("13- Quitter");
    System.out.println("-------------------------------------------");

    return scanner.nextInt();
  }
}