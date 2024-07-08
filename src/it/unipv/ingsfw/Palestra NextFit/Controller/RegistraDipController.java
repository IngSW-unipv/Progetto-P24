package Controller;

import NextFit.Dipendente;
import NextFit.Palestra;
import View.RegistraDipView;

public class RegistraDipController {
    private Palestra palestra;
    private RegistraDipView view;

    public RegistraDipController(Palestra palestra, RegistraDipView view) {
        this.palestra = palestra;
        this.view = view;
        this.view.setController(this);
    }

    public void registraDipendente(String nome, String cognome, String mail, String password, int eta, double stipendio, String tipo) {
        Dipendente d = palestra.creaDipendente(nome, cognome, mail, password, eta, stipendio, tipo);
        
        if (palestra.registraDipendente(d)) {
            view.mostraSuccesso();
        } else {
            view.mostraErrore();
        }
    }
}
