package ism.ouzeon.Services;

import java.util.List;

import ism.ouzeon.Core.iRepositorie;
import ism.ouzeon.Core.iService;
import ism.ouzeon.Entities.Chambre;
import ism.ouzeon.Entities.Etudiant;
import ism.ouzeon.Entities.Pavillon;

public class PavillonService implements iService<Pavillon> {

  private iRepositorie<Pavillon> pavillonRepositorie;

  public PavillonService(iRepositorie<Pavillon> pavillonRepositorie) {
    this.pavillonRepositorie = pavillonRepositorie;
  }

  @Override
  public int generateId() {
    return pavillonRepositorie.generateId();
  }

  @Override
  public boolean save(Pavillon object) {
    return pavillonRepositorie.save(object);
  }

  @Override
  public List<Pavillon> show() {
    return pavillonRepositorie.selectAll();
  }

  @Override
  public Pavillon findBy(String mat) {
    return pavillonRepositorie.retrouver(mat);
  }

  @Override
  public void modifier(Pavillon pavillon){
    pavillonRepositorie.update(pavillon);
  }

  @Override
  public boolean archiver(Pavillon object) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'archiver'");
  }

  @Override
  public List<Chambre> getChambresPavillon(int id) {
    return pavillonRepositorie.getChambresPavillon(id);
  }

  @Override
  public List<Etudiant> getEtudiantChambres(int id) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getEtudiantChambres'");
  }

  @Override
  public boolean affecter(Etudiant etudiant, Chambre chambre) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'affecter'");
  }
  

}
