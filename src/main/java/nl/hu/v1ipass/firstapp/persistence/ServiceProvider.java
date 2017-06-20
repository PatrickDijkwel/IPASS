package nl.hu.v1ipass.firstapp.persistence;

public class ServiceProvider {
	private static ApplicationService applicationService = new ApplicationService();
	
	public static ApplicationService getApplicationService() {
		return applicationService;
	}

}
