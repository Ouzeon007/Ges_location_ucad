package ism.ouzeon.Views;

import java.util.Scanner;

import ism.ouzeon.Core.iService;

import ism.ouzeon.Entities.Chambre;

import ism.ouzeon.Entities.Pavillon;

public class PavillonViews extends Views<Pavillon> {
  private iService<Pavillon> pavillonService;

  public PavillonViews(Scanner scanner, iService<Pavillon> pavillonService) {
    super(scanner);
    this.pavillonService = pavillonService;
  }

  @Override
  public Pavillon saisie() {
    Pavillon pavillon = new Pavillon();
    do {
      System.out.println("Entrer le nombre d'etage du pavillon");
      pavillon.setNbrEtage(scanner.nextInt());
    } while (pavillon.getNbrEtage() < 0);
    return pavillon;
  }

  public Pavillon give() {
    scanner.nextLine();
    String num;
    do {
      System.out.println("Entrer le numero du pavillon");
      num = scanner.nextLine();
    } while (pavillonService.findBy(num)==null);

    return pavillonService.findBy(num);
  }

  @Override
  public void afficheChambre() {
    Pavillon pavillon;

    do {
      
      pavillon = give();
      if (pavillon == null) {
        System.out.println("cet pavillon n'existe pas");
      }
    } while (pavillon == null);
    for (Chambre ch : pavillon.getTabChambre()) {
      System.out.println(ch);
    }
  }

}
