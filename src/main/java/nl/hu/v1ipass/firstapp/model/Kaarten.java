package nl.hu.v1ipass.firstapp.model;

public class Kaarten {
	private String kaartKleur;
	private int lidNummer;
	private int wedstrijdNummer;
	
	public Kaarten(String kaartKleur, int lidNummer, int wedstrijdNummer) {
		this.lidNummer = lidNummer;
		this.wedstrijdNummer = wedstrijdNummer;
		this.kaartKleur = kaartKleur;
	}

	public String getKaartKleur() {
		return kaartKleur;
	}

	public void setKaartKleur(String kaartKleur) {
		this.kaartKleur = kaartKleur;
	}

	public int getLidNummer() {
		return lidNummer;
	}

	public void setLidNummer(int lidNummer) {
		this.lidNummer = lidNummer;
	}

	public int getWedstrijdNummer() {
		return wedstrijdNummer;
	}

	public void setWedstrijdNummer(int wedstrijdNummer) {
		this.wedstrijdNummer = wedstrijdNummer;
	}
}
