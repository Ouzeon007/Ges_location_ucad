package ism.ouzeon.Views;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

import ism.ouzeon.Core.iViews;

public abstract class Views<T> implements iViews<T>{
    protected Scanner scanner;

    public Views(Scanner scanner) {
        this.scanner = scanner;
    }
    @Override
        public void affiche(List<T> datas) {
            for (T data : datas) {
                System.out.println(data);
            }
        }
        
        public LocalDate formatDate(String date){
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            return LocalDate.parse(date,formatter);
        }
        @Override
        public T update() {
            // TODO Auto-generated method stub
            return null;
        }
        @Override
        public void AffecterChambre() {
            // TODO Auto-generated method stub
            
        }
        @Override
        public void afficheChambre() {
            // TODO Auto-generated method stub
            
        }
        @Override
        public void afficheEtudiant() {
            // TODO Auto-generated method stub
            
        }

}
