package nl.hu.v1ipass.firstapp.persistence;

import java.util.List;

import nl.hu.v1ipass.firstapp.model.Aanvraag;
import nl.hu.v1ipass.firstapp.model.Clublid;
import nl.hu.v1ipass.firstapp.model.Clubmedewerker;
import nl.hu.v1ipass.firstapp.model.Kaarten;
import nl.hu.v1ipass.firstapp.model.Team;
import nl.hu.v1ipass.firstapp.model.Trainer;
import nl.hu.v1ipass.firstapp.model.Training;
import nl.hu.v1ipass.firstapp.model.Trainingsessie;
import nl.hu.v1ipass.firstapp.model.Veld;
import nl.hu.v1ipass.firstapp.model.Wedstrijd;
import nl.hu.v1ipass.firstapp.model.Wedstrijdsessie;

public class ApplicationService {
	private ClubmedewerkerDAO clubmedewerkerDAO = new ClubmedewerkerDAO();
	private ClublidDAO clublidDAO = new ClublidDAO();
	private AanvraagDAO aanvraagDAO = new AanvraagDAO();
	private TrainerDAO trainerDAO = new TrainerDAO();
	private TeamDAO teamDAO = new TeamDAO();
	private TrainingDAO trainingDAO = new TrainingDAO();
	private TrainingSessieDAO trainingSessieDAO = new TrainingSessieDAO();
	private WedstrijdSessieDAO wedstrijdSessieDAO = new WedstrijdSessieDAO();
	private KaartenDAO kaartenDAO = new KaartenDAO();
	private VeldDAO veldDAO = new VeldDAO();
	private WedstrijdDAO wedstrijdDAO = new WedstrijdDAO();
	
	
	//GET ALL METHODES/QUERIES
	public List<Aanvraag> getAllAanvragen() {
		return aanvraagDAO.findAllAanvragen();
	}
	public List<Clublid> getAllClubleden() {
		return clublidDAO.findAllClubleden();
	}
	public List<Clubmedewerker> getAllClubmedewerkers() {
		return clubmedewerkerDAO.findAllMedewerkers();
	}
	public List<Kaarten> getAllKaarten() {
		return kaartenDAO.findAllKaarten();
	}
	public List<Team> getAllTeams() {
		return teamDAO.findAllTeams();
	}
	public List<Trainer> getAllTrainers() {
		return trainerDAO.findAllTrainers();
	}
	public List<Training> getAllTrainingen() {
		return trainingDAO.findAllTrainingen();
	}
	public Training findTrainingByTrainingnummer(int trainingnummer) {
		return trainingDAO.findByTrainingNummer(trainingnummer);
	}
	public Training findLatestTrainingRecord() {
		return trainingDAO.findLatestTrainingRecord();
	}
	public List<Trainingsessie> getAllTrainingSessies() {
		return trainingSessieDAO.findAllTrainingSessies();
	}
	public List<Trainingsessie> findTrainingSessieByLidnummer(int lidnummer) {
		return trainingSessieDAO.findTrainingSessieByLidnummer(lidnummer);
	}
	public Trainingsessie findTrainingSessieByTrainingClublid(int trainingnummer, int lidnummer) {
		return trainingSessieDAO.findTrainingSessieByTrainingClublid(trainingnummer, lidnummer);
	}
	public List<Veld> getAllVelden() {
		return veldDAO.findAllVelden();
	}
	public List<Wedstrijd> getAllWedstrijden() {
		return wedstrijdDAO.findAllWedstrijden();
	}
	public Wedstrijd findWedstrijdByWedstrijdnummer(int wedstrijdnummer) {
		return wedstrijdDAO.findByWedstrijdNummer(wedstrijdnummer);
	}
	public Wedstrijd findLatestWedstrijdRecord() {
		return wedstrijdDAO.findLatestWedstrijdRecord();
	}
	public List<Wedstrijdsessie> getAllWedstrijdSessies() {
		return wedstrijdSessieDAO.findAllWedstrijdSessies();
	}
	public List<Wedstrijdsessie> findWedstrijdSessieByLidnummer(int lidnummer) {
		return wedstrijdSessieDAO.findWedstrijdSessieByLidnummer(lidnummer);
	}
	//Voor het zien van de aanwezigheid van de teamleden per wedstrijd
	public List<Wedstrijdsessie> findWedstrijdSessieByWedstrijdnummer(int wedstrijdnummer) {
		return wedstrijdSessieDAO.findWedstrijdSessieByWedstrijdnummer(wedstrijdnummer);
	}
	//Voor het zien van de aanwezigheid van de teamleden per training
	public List<Trainingsessie> findTrainingSessieByTrainingnummer(int trainingnummer) {
		return trainingSessieDAO.findTrainingSessieByTrainingnummer(trainingnummer);
	}
	
	public List<Aanvraag> getTrainerAanvragen(int trainernummer) {
		return aanvraagDAO.findTrainerAanvragen(trainernummer);
	}
	public Aanvraag findByAanvraagNummer(int aanvraagnummer) {
		return aanvraagDAO.findByAanvraagNummer(aanvraagnummer);
	}
	public void updateAanvraagStatus(int aanvraagnummer, String status) {
		aanvraagDAO.updateAanvraag(aanvraagnummer, status);
	}
	public void createAanvraag(Aanvraag aanvraag) {
		aanvraagDAO.createAanvraag(aanvraag);
	}
	
	
	
	public Trainer findTrainerByTrainernummer(int trainernummer) {
		return trainerDAO.findByTrainerNummer(trainernummer);
	}
	
	public Team getTeamByTrainer(int trainernummer) {
		return teamDAO.findByTrainer(trainernummer);
	}
	public Team findTeamByTeamnaam(String teamnaam) {
		return teamDAO.findByTeamNaam(teamnaam);
	}

	public List<Training> getTrainingByTeam(Team team) {
		return trainingDAO.findTrainingByTeam(team);
	}
	
	public List<Wedstrijd> getWedstrijdByTeam(Team team) {
		return wedstrijdDAO.findWedstrijdByTeam(team);
	}
	
	public Veld findVeldByVeldnummer(int veldnummer) {
		return veldDAO.findByVeldNummer(veldnummer);
	}
	public void createTraining(Training training) {
		trainingDAO.createTraining(training);
	}
	public void createTrainingSessie(Trainingsessie trainingsessie) {
		trainingSessieDAO.createTrainingSessie(trainingsessie);
	}
	public void createWedstrijd(Wedstrijd wedstrijd) {
		wedstrijdDAO.createWedstrijd(wedstrijd);
	}
	
	public void createWedstrijdSessie(Wedstrijdsessie wedstrijdsessie) {
		wedstrijdSessieDAO.createWedstrijdSessie(wedstrijdsessie);
	}
	
	public void updateWedstrijdSessie(Wedstrijdsessie wedstrijdsessie) {
		wedstrijdSessieDAO.updateWedstrijdSessie(wedstrijdsessie);
	}

	public List<Clublid> findClubledenByTeamnaam(String teamnaam) {
		return clublidDAO.findClubledenByTeamnaam(teamnaam);
	}
	public Clublid findClublidByLidnummer(int lidnummer) {
		return clublidDAO.findByLidNummer(lidnummer);
	}
	
	public void updateTrainingSessie(Trainingsessie trainingsessie) {
		trainingSessieDAO.updateTrainingSessie(trainingsessie);
	}
	
	
	
	//Methode voor bij het inloggen, om gegevens te verzamelen over wie ingelogd is
	public Clublid findClublidByGebruikersnaam(String gebruikersnaam) {
		return clublidDAO.findClublidByGebruikersnaam(gebruikersnaam);
	}
	//Methode voor bij het inloggen, om gegevens te verzamelen over wie ingelogd is
	public Trainer findTrainerByGebruikersnaam(String gebruikersnaam) {
		return trainerDAO.findTrainerByGebruikersnaam(gebruikersnaam);
	}
	//Methode voor bij het inloggen, om gegevens te verzamelen over wie ingelogd is
	public Clubmedewerker findClubmedewerkerByGebruikersnaam(String gebruikersnaam) {
		return clubmedewerkerDAO.findClubmedewerkerByGebruikersnaam(gebruikersnaam);
	}
}
