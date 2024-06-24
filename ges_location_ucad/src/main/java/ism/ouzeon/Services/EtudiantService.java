package ism.ouzeon.Services;

import java.util.List;

import ism.ouzeon.Core.iRepositorie;
import ism.ouzeon.Core.iService;
import ism.ouzeon.Entities.Etudiant;

public class EtudiantService implements iService<Etudiant> {

    private iRepositorie<Etudiant> etudiantRepositorie;

    public EtudiantService(iRepositorie<Etudiant> etudiantRepositorie) {
        this.etudiantRepositorie = etudiantRepositorie;
    }

    @Override
    public int generateId() {
        return etudiantRepositorie.generateId();
    }

    @Override
    public boolean save(Etudiant object) {
        return etudiantRepositorie.save(object);
    }

    @Override
    public List<Etudiant> show() {
        return etudiantRepositorie.selectAll();
    }

    public Etudiant findBy(String mat) {
        return etudiantRepositorie.retrouver(mat);
    }

    @Override
    public void modifier(Etudiant object) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'Modifier'");
    }

    @Override
    public boolean archiver(Etudiant object) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'archiver'");
    }

    @Override
    public List<Etudiant> getChambresPavillon(int id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getChambresPavillon'");
    }

    @Override
    public List<Etudiant> getEtudiantChambres(int id) {
        return etudiantRepositorie.getEtudiantChambres(id );
    }
    
    
    
}
