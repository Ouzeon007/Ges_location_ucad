package ism.ouzeon.Services;

import java.util.List;

import ism.ouzeon.Core.iRepositorie;
import ism.ouzeon.Core.iService;
import ism.ouzeon.Entities.Chambre;
import ism.ouzeon.Entities.Etudiant;

public class ChambreService implements iService<Chambre>{

    private iRepositorie<Chambre> chambreRepositorie;

    public ChambreService(iRepositorie<Chambre> chambreRepositorie) {
        this.chambreRepositorie = chambreRepositorie;
    }

    @Override
    public int generateId() {
        return chambreRepositorie.generateId();
    }

    @Override
    public boolean save(Chambre object) {
        return chambreRepositorie.save(object);
    }

    @Override
    public List<Chambre> show() {
        return chambreRepositorie.selectAll();
    }

    @Override
    public Chambre findBy(String mat) {
        return chambreRepositorie.retrouver(mat);
    }

    @Override
    public void modifier(Chambre object) {
        chambreRepositorie.update(object);
    }

    @Override
    public boolean archiver(Chambre object) {
        chambreRepositorie.archiver(object);
        return true;
    }

    public List<Chambre> getChambresPavillon(int id ) {
        return chambreRepositorie.getChambresPavillon(id);
    }

    @Override
    public List<Etudiant> getEtudiantChambres(int id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getEtudiantChambres'");
    }

    
}
