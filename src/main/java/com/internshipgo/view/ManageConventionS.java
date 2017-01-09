package com.internshipgo.view;

import javax.validation.constraints.NotNull;

/**
 * Created by choaib on 03/01/17.
 */
public class ManageConventionS {
    @NotNull
    private String userName;
     public Integer stageType;
   public String  univYear;
    public String nom;
    public String prenom;
    public String mail;
    public String mailPerso;
    public String secsociale;
    public String adresse;
    public String codepostal;
    public String ville;
    public String pays;
    public String telephone;
    public String specialite;
    public String doubleDiplome;
    public  Integer modeEtu;
    public String typeEtab;
    public String nomEtab;
    public String siretEtab;
    public Integer domaineEtab;
    public Integer tailleEtab;
    public String  adresseEtab;
    public String  cpEtab;
    public String  villeEtab;
    public String  paysEtab;
    public String  telephoneEtab;
    public String  siteEtab;
    public String  civiliteRespAdmin;
    public String  nomRespAdmin;
    public String  prenomRespAdmin;
    public String  fonctionRespAdmin;
    public String  telephoneRespAdmin;
    public String  serviceRespAdmin;
    public Integer protection;
    public String  adresseLieu;
    public String  cpLieu;
    public String  villeLieu;
    public String  paysLieu;
    public String  telephoneLieu;
    public String  civiliteRespStage;
    public String  nomRespStage;
    public String  prenomRespStage;
    public String  mailRespStage;
    public Integer fonctionRespStage;
    public String telephoneRespStage;
    public String definitionstage;
    public String descriptionstage;
    public String dateDebut;
    public String dateFin;
    public String horaire;
    public String horaireHebdomadaireH;
    public String horaireHebdomadaireM;
    public String jourDePresence;
    public String preciserNuit;
    public String preciserDimanche;
    public String preciserFerie;
    public String jourDeConges;
    public String gratification;
    public String restauration;
    public String hebergement;
    public String frais;
    public String autreAvantage;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
