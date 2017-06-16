package nl.hu.v1ipass.firstapp.model;


public class Team {
	private String teamNaam;
	private String leeftijdsCategorie;
	private Trainer trainer;
	
	public Team(String teamNaam, String lcat) {
		this.teamNaam = teamNaam;
		this.leeftijdsCategorie = lcat;
	}

	public String getTeamNaam() {
		return teamNaam;
	}

	public void setTeamNaam(String teamNaam) {
		this.teamNaam = teamNaam;
	}
	
	public String getLeeftijdsCategorie() {
		return leeftijdsCategorie;
	}
	
	public void setLeeftijdsCategorie(String leeftijdsCategorie) {
		this.leeftijdsCategorie = leeftijdsCategorie;
	}

	public Trainer getTrainer() {
		return trainer;
	}

	public void setTrainer(Trainer trainer) {
		this.trainer = trainer;
	}
	

}
