package nl.hu.v1ipass.firstapp.model;

import java.util.Date;

public class Aanvraag {
	private int aanvraagNummer;
	private String aanvraagType;
	private Date aanvraagDatum;
	private Status aanvraagStatus;
	private Date datum;
	private String tijdstip;
	private String tegenstander;
	private Trainer trainer;
	private Veld veld;
	private enum Status {
		NIEUW("nieuw"), GOEDGEKEURD("goedgekeurd"), AFGEKEURD("afgekeurd");
		
		private String key;
		
		private Status(String key) {
			this.key = key;
		}
		
		public String getKey() {
			return key;
		}
	}
	
	public Aanvraag(String aanvraagType, Date aDatum, Date datum, String tijdstip) {
		this.aanvraagType = aanvraagType;
		this.aanvraagDatum = aDatum;
		this.datum = datum;
		this.tijdstip = tijdstip;
	}
	
	public Status getAanvraagStatus() {
		return aanvraagStatus;
	}
	
	public String getAanvraagStatusString() {
		return aanvraagStatus.getKey();
	}
	
	public void setAanvraagStatus(String key) {
		switch(key) {
		case "nieuw":
			aanvraagStatus = Status.NIEUW;
			break;
		case "goedgekeurd":
			aanvraagStatus = Status.GOEDGEKEURD;
			break;
		case "afgekeurd":
			aanvraagStatus = Status.AFGEKEURD;
			break;
		default:
			break;
		}
	}

	public int getAanvraagNummer() {
		return aanvraagNummer;
	}

	public void setAanvraagNummer(int aanvraagNummer) {
		this.aanvraagNummer = aanvraagNummer;
	}

	public String getAanvraagType() {
		return aanvraagType;
	}

	public void setAanvraagType(String aanvraagType) {
		this.aanvraagType = aanvraagType;
	}

	public Date getAanvraagDatum() {
		return aanvraagDatum;
	}

	public void setAanvraagDatum(Date aanvraagDatum) {
		this.aanvraagDatum = aanvraagDatum;
	}

	public Date getDatum() {
		return datum;
	}

	public void setDatum(Date datum) {
		this.datum = datum;
	}

	public String getTijdstip() {
		return tijdstip;
	}

	public void setTijdstip(String tijdstip) {
		this.tijdstip = tijdstip;
	}

	public String getTegenstander() {
		return tegenstander;
	}

	public void setTegenstander(String tegenstander) {
		this.tegenstander = tegenstander;
	}

	public Trainer getTrainer() {
		return trainer;
	}

	public void setTrainer(Trainer trainer) {
		this.trainer= trainer;
	}

	public Veld getVeld() {
		return veld;
	}

	public void setVeld(Veld veld) {
		this.veld = veld;
	}
	
	

}
