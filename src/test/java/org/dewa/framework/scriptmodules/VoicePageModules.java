package org.dewa.framework.scriptmodules;

import static org.dewa.framework.utils.BaseClass.assertContains;
import static org.dewa.framework.utils.BaseClass.assertEquals;

import com.microsoft.playwright.Page;

public class VoicePageModules extends ParentModule{

	public VoicePageModules(Page page) {
		super(page);
		// TODO Auto-generated constructor stub
	}
	
	public void sentimentHappySentimentNoComments(String subsName, String projectName,String voiceId) {
		navigateTillVoicePage(subsName, projectName, voiceId);
		String userSentiment = getVoicePage().getAIorUserTitleAttribute("User");
		System.out.println("User Sentiment is "+userSentiment);
		assertContains("Actual User Sentiment is equal to Expected User Sentiment", "Actual User Sentiment is not equal to Expected User Sentiment",
				userSentiment.trim(), "Happy");
		boolean aiSentimentAvailablity = getVoicePage().checkAISenimentAvailability();
		assertEquals("AI Sentiment is not Available", "AI Sentiment is Available", false, aiSentimentAvailablity);
		getVoicePage().closeDocumentPage();
	}
	
	public void sentimentHappySentimentPositiveComments(String subsName, String projectName,String voiceId) {
		navigateTillVoicePage(subsName, projectName, voiceId);
		String userSentiment = getVoicePage().getAIorUserTitleAttribute("User");
		System.out.println("User Sentiment is "+userSentiment);
		assertContains("Actual User Sentiment is equal to Expected User Sentiment", "Actual User Sentiment is not equal to Expected User Sentiment",
				userSentiment.trim(), "Happy");
		String aiSentiment = getVoicePage().getAIorUserTitleAttribute("AI");
		System.out.println("AI Sentiment is "+aiSentiment);
		assertContains("Actual AI Sentiment is equal to Expected AI Sentiment", "Actual AI Sentiment is not equal to Expected AI Sentiment",
				aiSentiment.trim(), "Happy");
		getVoicePage().closeDocumentPage();
	}
	
	public void sentimentHappySentimentNegativeComments(String subsName, String projectName,String voiceId) {
		navigateTillVoicePage(subsName, projectName, voiceId);
		String userSentiment = getVoicePage().getAIorUserTitleAttribute("User");
		System.out.println("User Sentiment is "+userSentiment);
		assertContains("Actual User Sentiment is equal to Expected User Sentiment", "Actual User Sentiment is not equal to Expected User Sentiment",
				userSentiment.trim(), "Happy");
		String aiSentiment = getVoicePage().getAIorUserTitleAttribute("AI");
		System.out.println("AI Sentiment is "+aiSentiment);
		assertContains("Actual AI Sentiment is equal to Expected AI Sentiment", "Actual AI Sentiment is not equal to Expected AI Sentiment",
				aiSentiment.trim(), "Unhappy");
		getVoicePage().closeDocumentPage();
	}
	
	public void sentimentNeutralSentimentNoComments(String subsName, String projectName,String voiceId) {
		navigateTillVoicePage(subsName, projectName, voiceId);
		String userSentiment = getVoicePage().getAIorUserTitleAttribute("User");
		System.out.println("User Sentiment is "+userSentiment);
		assertContains("Actual User Sentiment is equal to Expected User Sentiment", "Actual User Sentiment is not equal to Expected User Sentiment",
				userSentiment.trim(), "Neutral");
		boolean aiSentimentAvailablity = getVoicePage().checkAISenimentAvailability();
		assertEquals("AI Sentiment is not Available", "AI Sentiment is Available", false, aiSentimentAvailablity);
		getVoicePage().closeDocumentPage();
	}
	
	public void sentimentNeutralSentimentNeutralComments(String subsName, String projectName,String voiceId) {
		navigateTillVoicePage(subsName, projectName, voiceId);
		String userSentiment = getVoicePage().getAIorUserTitleAttribute("User");
		System.out.println("User Sentiment is "+userSentiment);
		assertContains("Actual User Sentiment is equal to Expected User Sentiment", "Actual User Sentiment is not equal to Expected User Sentiment",
				userSentiment.trim(), "Neutral");
		String aiSentiment = getVoicePage().getAIorUserTitleAttribute("AI");
		System.out.println("AI Sentiment is "+aiSentiment);
		assertContains("Actual AI Sentiment is equal to Expected AI Sentiment", "Actual AI Sentiment is not equal to Expected AI Sentiment",
				aiSentiment.trim(), "Neutral");
		getVoicePage().closeDocumentPage();
	}
	
	public void sentimentNeutralSentimentPositiveComments(String subsName, String projectName,String voiceId) {
		navigateTillVoicePage(subsName, projectName, voiceId);
		String userSentiment = getVoicePage().getAIorUserTitleAttribute("User");
		System.out.println("User Sentiment is "+userSentiment);
		assertContains("Actual User Sentiment is equal to Expected User Sentiment", "Actual User Sentiment is not equal to Expected User Sentiment",
				userSentiment.trim(), "Neutral");
		String aiSentiment = getVoicePage().getAIorUserTitleAttribute("AI");
		System.out.println("AI Sentiment is "+aiSentiment);
		assertContains("Actual AI Sentiment is equal to Expected AI Sentiment", "Actual AI Sentiment is not equal to Expected AI Sentiment",
				aiSentiment.trim(), "Happy");
		getVoicePage().closeDocumentPage();
	}
	
	public void sentimentNeutralSentimentNegativeComments(String subsName, String projectName,String voiceId) {
		navigateTillVoicePage(subsName, projectName, voiceId);
		String userSentiment = getVoicePage().getAIorUserTitleAttribute("User");
		System.out.println("User Sentiment is "+userSentiment);
		assertContains("Actual User Sentiment is equal to Expected User Sentiment", "Actual User Sentiment is not equal to Expected User Sentiment",
				userSentiment.trim(), "Neutral");
		String aiSentiment = getVoicePage().getAIorUserTitleAttribute("AI");
		System.out.println("AI Sentiment is "+aiSentiment);
		assertContains("Actual AI Sentiment is equal to Expected AI Sentiment", "Actual AI Sentiment is not equal to Expected AI Sentiment",
				aiSentiment.trim(), "Unhappy");
		getVoicePage().closeDocumentPage();
	}
	
	public void sentimentUnhappySentimentNoComments(String subsName, String projectName,String voiceId) {
		navigateTillVoicePage(subsName, projectName, voiceId);
		String userSentiment = getVoicePage().getAIorUserTitleAttribute("User");
		System.out.println("User Sentiment is "+userSentiment);
		assertContains("Actual User Sentiment is equal to Expected User Sentiment", "Actual User Sentiment is not equal to Expected User Sentiment",
				userSentiment.trim(), "Unhappy");
		boolean aiSentimentAvailablity = getVoicePage().checkAISenimentAvailability();
		assertEquals("AI Sentiment is not Available", "AI Sentiment is Available", false, aiSentimentAvailablity);
		getVoicePage().closeDocumentPage();
	}
	
	public void sentimentUnhappySentimentNeutralComments(String subsName, String projectName,String voiceId) {
		navigateTillVoicePage(subsName, projectName, voiceId);
		String userSentiment = getVoicePage().getAIorUserTitleAttribute("User");
		System.out.println("User Sentiment is "+userSentiment);
		assertContains("Actual User Sentiment is equal to Expected User Sentiment", "Actual User Sentiment is not equal to Expected User Sentiment",
				userSentiment.trim(), "Unhappy");
		String aiSentiment = getVoicePage().getAIorUserTitleAttribute("AI");
		System.out.println("AI Sentiment is "+aiSentiment);
		assertContains("Actual AI Sentiment is equal to Expected AI Sentiment", "Actual AI Sentiment is not equal to Expected AI Sentiment",
				aiSentiment.trim(), "Neutral");
		getVoicePage().closeDocumentPage();
	}
	
	public void sentimentUnhappySentimentNegativeComments(String subsName, String projectName,String voiceId) {
		navigateTillVoicePage(subsName, projectName, voiceId);
		String userSentiment = getVoicePage().getAIorUserTitleAttribute("User");
		System.out.println("User Sentiment is "+userSentiment);
		assertContains("Actual User Sentiment is equal to Expected User Sentiment", "Actual User Sentiment is not equal to Expected User Sentiment",
				userSentiment.trim(), "Unhappy");
		String aiSentiment = getVoicePage().getAIorUserTitleAttribute("AI");
		System.out.println("AI Sentiment is "+aiSentiment);
		assertContains("Actual AI Sentiment is equal to Expected AI Sentiment", "Actual AI Sentiment is not equal to Expected AI Sentiment",
				aiSentiment.trim(), "Unhappy");
		getVoicePage().closeDocumentPage();
	}
	
	public void voiceTypeInquires(String subsName, String projectName,String voiceId) {
		navigateTillVoicePage(subsName, projectName, voiceId);
		String voiceType = getVoicePage().getVoiceSummaryAttributes("Type");
		System.out.println("Voice Type is "+voiceType.trim());
		assertContains("Actual Voice Type is equal to Expected Voice Type", "Actual Voice Type is not equal to Expected Voice Type",
				voiceType.trim(), "Inquiry");
		getVoicePage().closeDocumentPage();
	}
	
	public void voiceTypeSuggestions(String subsName, String projectName,String voiceId) {
		navigateTillVoicePage(subsName, projectName, voiceId);
		String voiceType = getVoicePage().getVoiceSummaryAttributes("Type");
		System.out.println("Voice Type is "+voiceType.trim());
		assertContains("Actual Voice Type is equal to Expected Voice Type", "Actual Voice Type is not equal to Expected Voice Type",
				voiceType.trim(), "Suggestion");
		getVoicePage().closeDocumentPage();
	}
	
	public void voiceTypeComments(String subsName, String projectName,String voiceId) {
		navigateTillVoicePage(subsName, projectName, voiceId);
		String voiceType = getVoicePage().getVoiceSummaryAttributes("Type");
		System.out.println("Voice Type is "+voiceType.trim());
		assertContains("Actual Voice Type is equal to Expected Voice Type", "Actual Voice Type is not equal to Expected Voice Type",
				voiceType.trim(), "Comment/Complaint");
		getVoicePage().closeDocumentPage();
	}
	
	public void voiceTypeCompliments(String subsName, String projectName,String voiceId) {
		navigateTillVoicePage(subsName, projectName, voiceId);
		String voiceType = getVoicePage().getVoiceSummaryAttributes("Type");
		System.out.println("Voice Type is "+voiceType.trim());
		assertContains("Actual Voice Type is equal to Expected Voice Type", "Actual Voice Type is not equal to Expected Voice Type",
				voiceType.trim(), "Appreciation");
		getVoicePage().closeDocumentPage();
	}
	
	public void voiceTypeComplaints(String subsName, String projectName,String voiceId) {
		navigateTillVoicePage(subsName, projectName, voiceId);
		String voiceType = getVoicePage().getVoiceSummaryAttributes("Type");
		System.out.println("Voice Type is "+voiceType.trim());
		assertContains("Actual Voice Type is equal to Expected Voice Type", "Actual Voice Type is not equal to Expected Voice Type",
				voiceType.trim(), "Complaint");
		getVoicePage().closeDocumentPage();
	}
	
	public void voiceTypeNoComments(String subsName, String projectName,String voiceId) {
		navigateTillVoicePage(subsName, projectName, voiceId);
		String voiceType = getVoicePage().getVoiceSummaryAttributes("Type");
		if(voiceType.isBlank()) {
			voiceType="Blank";
		}
		System.out.println("Voice Type is "+voiceType.trim());
		assertContains("Actual Voice Type is equal to Expected Voice Type", "Actual Voice Type is not equal to Expected Voice Type",
				voiceType.trim(), "Blank");
		getVoicePage().closeDocumentPage();
	}
	
	public void voiceTypeComplimentsUrdu(String subsName, String projectName,String voiceId) {
		navigateTillVoicePage(subsName, projectName, voiceId);
		String voiceType = getVoicePage().getVoiceSummaryAttributes("Type");
		System.out.println("Voice Type is "+voiceType.trim());
		assertContains("Actual Voice Type is equal to Expected Voice Type", "Actual Voice Type is not equal to Expected Voice Type",
				voiceType.trim(), "Appreciation");
		getVoicePage().closeDocumentPage();
	}
	
	public void voiceTypeSuggestionsArabic(String subsName, String projectName,String voiceId) {
		navigateTillVoicePage(subsName, projectName, voiceId);
		String voiceType = getVoicePage().getVoiceSummaryAttributes("Type");
		System.out.println("Voice Type is "+voiceType.trim());
		assertContains("Actual Voice Type is equal to Expected Voice Type", "Actual Voice Type is not equal to Expected Voice Type",
				voiceType.trim(), "Suggestion");
		getVoicePage().closeDocumentPage();
	}
	
	public void sqlAccessibilityOfServicesHappyCustomer(String subsName, String projectName,String voiceId) {
		navigateTillVoicePage(subsName, projectName, voiceId);
		String sql = getVoicePage().getVoiceSummaryAttributes("SQL");
		System.out.println("SQL is "+sql.trim());
		assertContains("Actual SQL is equal to Expected SQL", "Actual SQL is not equal to Expected SQL",
				sql.trim(), "Accessibility");
		getVoicePage().closeDocumentPage();
	}
	
	public void sqlAccessibilityOfServicesUnhappyCustomer(String subsName, String projectName,String voiceId) {
		navigateTillVoicePage(subsName, projectName, voiceId);
		String sql= getVoicePage().getVoiceSummaryAttributes("SQL");
		System.out.println("SQL is "+sql.trim());
		assertContains("Actual SQL is equal to Expected SQL", "Actual SQL is not equal to Expected SQL",
				sql.trim(), "Accessibility");
		getVoicePage().closeDocumentPage();
	}
	
	public void sqlSpeedOfServiceDeliveryHappyCustomer(String subsName, String projectName,String voiceId) {
		navigateTillVoicePage(subsName, projectName, voiceId);
		String sql = getVoicePage().getVoiceSummaryAttributes("SQL");
		System.out.println("SQL is "+sql.trim());
		assertContains("Actual SQL is equal to Expected SQL", "Actual SQL is not equal to Expected SQL",
				sql.trim(), "Speed of Service Delivery");
		getVoicePage().closeDocumentPage();
	}
	
	public void sqlSpeedOfServiceDeliveryUnhappyCustomer(String subsName, String projectName,String voiceId) {
		navigateTillVoicePage(subsName, projectName, voiceId);
		String sql = getVoicePage().getVoiceSummaryAttributes("SQL");
		System.out.println("SQL is "+sql.trim());
		assertContains("Actual SQL is equal to Expected SQL", "Actual SQL is not equal to Expected SQL",
				sql.trim(), "Speed of Service Delivery");
		getVoicePage().closeDocumentPage();
	}
	
	public void sqlProfessionalismOfStaffHappyCustomer(String subsName, String projectName,String voiceId) {
		navigateTillVoicePage(subsName, projectName, voiceId);
		String sql = getVoicePage().getVoiceSummaryAttributes("SQL");
		System.out.println("SQL is "+sql.trim());
		assertContains("Actual SQL is equal to Expected SQL", "Actual SQL is not equal to Expected SQL",
				sql.trim(), "Professionalism of Staff");
		getVoicePage().closeDocumentPage();
	}
	
	public void sqlRespectForCustomerPrivacyHappyCustomer(String subsName, String projectName,String voiceId) {
		navigateTillVoicePage(subsName, projectName, voiceId);
		String sql = getVoicePage().getVoiceSummaryAttributes("SQL");
		System.out.println("SQL is "+sql.trim());
		assertContains("Actual SQL is equal to Expected SQL", "Actual SQL is not equal to Expected SQL",
				sql.trim(), "Respect Customer Privacy");
		getVoicePage().closeDocumentPage();
	}
	
	public void sqlEaseOfUseHappyCustomer(String subsName, String projectName,String voiceId) {
		navigateTillVoicePage(subsName, projectName, voiceId);
		String sql = getVoicePage().getVoiceSummaryAttributes("SQL");
		System.out.println("SQL is "+sql.trim());
		assertContains("Actual SQL is equal to Expected SQL", "Actual SQL is not equal to Expected SQL",
				sql.trim(), "Ease of Use");
		getVoicePage().closeDocumentPage();
	}
	
	public void sqlInformationQualityHappyCustomer(String subsName, String projectName,String voiceId) {
		navigateTillVoicePage(subsName, projectName, voiceId);
		String sql = getVoicePage().getVoiceSummaryAttributes("SQL");
		System.out.println("SQL is "+sql.trim());
		assertContains("Actual SQL is equal to Expected SQL", "Actual SQL is not equal to Expected SQL",
				sql.trim(), "Information Quality");
		getVoicePage().closeDocumentPage();
	}
	
	public void sqlAppearanceOfServiceDeliveryHappyCustomer(String subsName, String projectName,String voiceId) {
		navigateTillVoicePage(subsName, projectName, voiceId);
		String sql = getVoicePage().getVoiceSummaryAttributes("SQL");
		System.out.println("SQL is "+sql.trim());
		assertContains("Actual SQL is equal to Expected SQL", "Actual SQL is not equal to Expected SQL",
				sql.trim(), "Appearance");
		getVoicePage().closeDocumentPage();
	}
	
	public void sqlProfessionalismOfStaffNeutralCustomer(String subsName, String projectName,String voiceId) {
		navigateTillVoicePage(subsName, projectName, voiceId);
		String sql = getVoicePage().getVoiceSummaryAttributes("SQL");
		System.out.println("SQL is "+sql.trim());
		assertContains("Actual SQL is equal to Expected SQL", "Actual SQL is not equal to Expected SQL",
				sql.trim(), "Professionalism of Staff");
		getVoicePage().closeDocumentPage();
	}
	
	public void sqlNoComments(String subsName, String projectName,String voiceId) {
		navigateTillVoicePage(subsName, projectName, voiceId);
		String sql = getVoicePage().getVoiceSummaryAttributes("SQL");
		if(sql.isBlank()) {
			sql="Blank";
		}
		System.out.println("SQL is "+sql.trim());
		assertContains("Actual SQL is equal to Expected SQL", "Actual SQL is not equal to Expected SQL",
				sql.trim(), "Blank");
		getVoicePage().closeDocumentPage();
	}
	
	public void verifyCategorizationOfBillingServicesBillPayment(String subsName, String projectName,String voiceId) {
		navigateTillVoicePage(subsName, projectName, voiceId);
		String mainService = getVoicePage().getVoiceSummaryAttributes("Main Service");
		String subService = getVoicePage().getVoiceSummaryAttributes("Sub Service");
		System.out.println("Main Service is "+mainService.trim());
		assertContains("Actual Main Service is equal to Expected Main Service", "Actual Main Service is not equal to Expected Main Service",
				mainService.trim(), "Billing Services");
		System.out.println("Sub Service is "+subService.trim());
		assertContains("Actual Sub Service is equal to Expected Sub Service", "Actual Sub Service is not equal to Expected Sub Service",
				subService.trim(), "Bill Payment");
		getVoicePage().closeDocumentPage();
	}
	
	public void verifyCategorizationOfBillingServicesRequestForRefund(String subsName, String projectName,String voiceId) {
		navigateTillVoicePage(subsName, projectName, voiceId);
		String mainService = getVoicePage().getVoiceSummaryAttributes("Main Service");
		String subService = getVoicePage().getVoiceSummaryAttributes("Sub Service");
		System.out.println("Main Service is "+mainService.trim());
		assertContains("Actual Main Service is equal to Expected Main Service", "Actual Main Service is not equal to Expected Main Service",
				mainService.trim(), "Billing Services");
		System.out.println("Sub Service is "+subService.trim());
		assertContains("Actual Sub Service is equal to Expected Sub Service", "Actual Sub Service is not equal to Expected Sub Service",
				subService.trim(), "Refund");
		getVoicePage().closeDocumentPage();
	}
	
	public void verifyCategorizationOfBillingServicesRequestForClearanceCertificate(String subsName, String projectName,String voiceId) {
		navigateTillVoicePage(subsName, projectName, voiceId);
		String mainService = getVoicePage().getVoiceSummaryAttributes("Main Service");
		String subService = getVoicePage().getVoiceSummaryAttributes("Sub Service");
		System.out.println("Main Service is "+mainService.trim());
		assertContains("Actual Main Service is equal to Expected Main Service", "Actual Main Service is not equal to Expected Main Service",
				mainService.trim(), "Billing Services");
		System.out.println("Sub Service is "+subService.trim());
		assertContains("Actual Sub Service is equal to Expected Sub Service", "Actual Sub Service is not equal to Expected Sub Service",
				subService.trim(), "Request for Clearance Certificate");
		getVoicePage().closeDocumentPage();
	}
	
	public void verifyCategorizationOfElectricityWaterManagementServicesRequestForActivationOfElectricityWater(String subsName, String projectName,String voiceId) {
		navigateTillVoicePage(subsName, projectName, voiceId);
		String mainService = getVoicePage().getVoiceSummaryAttributes("Main Service");
		String subService = getVoicePage().getVoiceSummaryAttributes("Sub Service");
		System.out.println("Main Service is "+mainService.trim());
		assertContains("Actual Main Service is equal to Expected Main Service", "Actual Main Service is not equal to Expected Main Service",
				mainService.trim(), "Electricity & Water Management Services");
		System.out.println("Sub Service is "+subService.trim());
		assertContains("Actual Sub Service is equal to Expected Sub Service", "Actual Sub Service is not equal to Expected Sub Service",
				subService.trim(), "Activation of Electricity/Water (Move-in)");
		getVoicePage().closeDocumentPage();
	}
	
	public void verifyCategorizationOfElectricityWaterManagementServicesRequestForDeactivationOfElectricityWater(String subsName, String projectName,String voiceId) {
		navigateTillVoicePage(subsName, projectName, voiceId);
		String mainService = getVoicePage().getVoiceSummaryAttributes("Main Service");
		String subService = getVoicePage().getVoiceSummaryAttributes("Sub Service");
		System.out.println("Main Service is "+mainService.trim());
		assertContains("Actual Main Service is equal to Expected Main Service", "Actual Main Service is not equal to Expected Main Service",
				mainService.trim(), "Electricity & Water Management Services");
		System.out.println("Sub Service is "+subService.trim());
		assertContains("Actual Sub Service is equal to Expected Sub Service", "Actual Sub Service is not equal to Expected Sub Service",
				subService.trim(), "Request for De-activation of Electricity/Water (Move-out)");
		getVoicePage().closeDocumentPage();
	}
	
	public void verifyCategorizationOfSustainabilityConsumptionManagementServicesEVAccountAndChargingCardManagement(String subsName, String projectName,String voiceId) {
		navigateTillVoicePage(subsName, projectName, voiceId);
		String mainService = getVoicePage().getVoiceSummaryAttributes("Main Service");
		String subService = getVoicePage().getVoiceSummaryAttributes("Sub Service");
		System.out.println("Main Service is "+mainService.trim());
		assertContains("Actual Main Service is equal to Expected Main Service", "Actual Main Service is not equal to Expected Main Service",
				mainService.trim(), "Sustainability & Consumption Management Services");
		System.out.println("Sub Service is "+subService.trim());
		assertContains("Actual Sub Service is equal to Expected Sub Service", "Actual Sub Service is not equal to Expected Sub Service",
				subService.trim(), "EV Account & Charging Card Management");
		getVoicePage().closeDocumentPage();
	}
	
	public void verifyCategorizationOfSustainabilityConsumptionManagementServicesRequestForConsumptionVerificationElectricityWater(String subsName, String projectName,String voiceId) {
		navigateTillVoicePage(subsName, projectName, voiceId);
		String mainService = getVoicePage().getVoiceSummaryAttributes("Main Service");
		String subService = getVoicePage().getVoiceSummaryAttributes("Sub Service");
		System.out.println("Main Service is "+mainService.trim());
		assertContains("Actual Main Service is equal to Expected Main Service", "Actual Main Service is not equal to Expected Main Service",
				mainService.trim(), "Sustainability & Consumption Management Services");
		System.out.println("Sub Service is "+subService.trim());
		assertContains("Actual Sub Service is equal to Expected Sub Service", "Actual Sub Service is not equal to Expected Sub Service",
				subService.trim(), "Request for Consumption Verification - Electricity/Water");
		getVoicePage().closeDocumentPage();
	}
	
	public void verifyCategorizationOfElectricityNetworkServicesGettingElectricityConnection(String subsName, String projectName,String voiceId) {
		navigateTillVoicePage(subsName, projectName, voiceId);
		String mainService = getVoicePage().getVoiceSummaryAttributes("Main Service");
		String subService = getVoicePage().getVoiceSummaryAttributes("Sub Service");
		System.out.println("Main Service is "+mainService.trim());
		assertContains("Actual Main Service is equal to Expected Main Service", "Actual Main Service is not equal to Expected Main Service",
				mainService.trim(), "Electricity Network Services");
		System.out.println("Sub Service is "+subService.trim());
		assertContains("Actual Sub Service is equal to Expected Sub Service", "Actual Sub Service is not equal to Expected Sub Service",
				subService.trim(), "Getting Electricity Connections");
		getVoicePage().closeDocumentPage();
	}
	
	public void verifyCategorizationOfElectricityNetworkServicesRequestForElectricityNetworkAndModification(String subsName, String projectName,String voiceId) {
		navigateTillVoicePage(subsName, projectName, voiceId);
		String mainService = getVoicePage().getVoiceSummaryAttributes("Main Service");
		String subService = getVoicePage().getVoiceSummaryAttributes("Sub Service");
		System.out.println("Main Service is "+mainService.trim());
		assertContains("Actual Main Service is equal to Expected Main Service", "Actual Main Service is not equal to Expected Main Service",
				mainService.trim(), "Electricity Network Services");
		System.out.println("Sub Service is "+subService.trim());
		assertContains("Actual Sub Service is equal to Expected Sub Service", "Actual Sub Service is not equal to Expected Sub Service",
				subService.trim(), "Electricity Network Services - Electricity Network Modification");
		getVoicePage().closeDocumentPage();
	}
	
	public void verifyCategorizationOfNOCServicesRequestForBuildingNoObjectionCertificateWater(String subsName, String projectName,String voiceId) {
		navigateTillVoicePage(subsName, projectName, voiceId);
		String mainService = getVoicePage().getVoiceSummaryAttributes("Main Service");
		String subService = getVoicePage().getVoiceSummaryAttributes("Sub Service");
		System.out.println("Main Service is "+mainService.trim());
		assertContains("Actual Main Service is equal to Expected Main Service", "Actual Main Service is not equal to Expected Main Service",
				mainService.trim(), "NOC Services");
		System.out.println("Sub Service is "+subService.trim());
		assertContains("Actual Sub Service is equal to Expected Sub Service", "Actual Sub Service is not equal to Expected Sub Service",
				subService.trim(), "Request for Building No Objection Certificate - Water");
		getVoicePage().closeDocumentPage();
	}
	
	public void verifyCategorizationOfNOCServicesRequestForBuildingNoObjectionCertificateElectricity(String subsName, String projectName,String voiceId) {
		navigateTillVoicePage(subsName, projectName, voiceId);
		String mainService = getVoicePage().getVoiceSummaryAttributes("Main Service");
		String subService = getVoicePage().getVoiceSummaryAttributes("Sub Service");
		System.out.println("Main Service is "+mainService.trim());
		assertContains("Actual Main Service is equal to Expected Main Service", "Actual Main Service is not equal to Expected Main Service",
				mainService.trim(), "NOC Services");
		System.out.println("Sub Service is "+subService.trim());
		assertContains("Actual Sub Service is equal to Expected Sub Service", "Actual Sub Service is not equal to Expected Sub Service",
				subService.trim(), "Request for Building No Objection Certificate - Electricity");
		getVoicePage().closeDocumentPage();
	}
	
	public void verifyCategorizationOfWaterNetworkServicesGettingWaterConnection(String subsName, String projectName,String voiceId) {
		navigateTillVoicePage(subsName, projectName, voiceId);
		String mainService = getVoicePage().getVoiceSummaryAttributes("Main Service");
		String subService = getVoicePage().getVoiceSummaryAttributes("Sub Service");
		System.out.println("Main Service is "+mainService.trim());
		assertContains("Actual Main Service is equal to Expected Main Service", "Actual Main Service is not equal to Expected Main Service",
				mainService.trim(), "Water Network Services");
		System.out.println("Sub Service is "+subService.trim());
		assertContains("Actual Sub Service is equal to Expected Sub Service", "Actual Sub Service is not equal to Expected Sub Service",
				subService.trim(), "Getting Water Connection");
		getVoicePage().closeDocumentPage();
	}
	
	public void verifyCategorizationOfWaterNetworkServicesRequestForWaterNetworkModification(String subsName, String projectName,String voiceId) {
		navigateTillVoicePage(subsName, projectName, voiceId);
		String mainService = getVoicePage().getVoiceSummaryAttributes("Main Service");
		String subService = getVoicePage().getVoiceSummaryAttributes("Sub Service");
		System.out.println("Main Service is "+mainService.trim());
		assertContains("Actual Main Service is equal to Expected Main Service", "Actual Main Service is not equal to Expected Main Service",
				mainService.trim(), "Water Network Services");
		System.out.println("Sub Service is "+subService.trim());
		assertContains("Actual Sub Service is equal to Expected Sub Service", "Actual Sub Service is not equal to Expected Sub Service",
				subService.trim(), "Request for Water Network Modification");
		getVoicePage().closeDocumentPage();
	}
	
	public void verifyCategorizationOfElectricityNetworkServicesGettingElectricityConnectionUrdu(String subsName, String projectName,String voiceId) {
		navigateTillVoicePage(subsName, projectName, voiceId);
		String mainService = getVoicePage().getVoiceSummaryAttributes("Main Service");
		String subService = getVoicePage().getVoiceSummaryAttributes("Sub Service");
		System.out.println("Main Service is "+mainService.trim());
		assertContains("Actual Main Service is equal to Expected Main Service", "Actual Main Service is not equal to Expected Main Service",
				mainService.trim(), "Electricity Network Services");
		System.out.println("Sub Service is "+subService.trim());
		assertContains("Actual Sub Service is equal to Expected Sub Service", "Actual Sub Service is not equal to Expected Sub Service",
				subService.trim(), "Getting Electricity Connections");
		getVoicePage().closeDocumentPage();
	}
	
	public void verifyCategorizationOfSustainabilityConsumptionManagementServicesEVChargingArabic(String subsName, String projectName,String voiceId) {
		navigateTillVoicePage(subsName, projectName, voiceId);
		String mainService = getVoicePage().getVoiceSummaryAttributes("Main Service");
		String subService = getVoicePage().getVoiceSummaryAttributes("Sub Service");
		System.out.println("Main Service is "+mainService.trim());
		assertContains("Actual Main Service is equal to Expected Main Service", "Actual Main Service is not equal to Expected Main Service",
				mainService.trim(), "Sustainability & Consumption Management Services");
		System.out.println("Sub Service is "+subService.trim());
		assertContains("Actual Sub Service is equal to Expected Sub Service", "Actual Sub Service is not equal to Expected Sub Service",
				subService.trim(), "EV charging");
		getVoicePage().closeDocumentPage();
	}
	

	public void verifyLocationDisplayLocationInVoiceSummaryPage(String subsName, String projectName,String voiceId) {
		navigateTillVoicePage(subsName, projectName, voiceId);
		String location = getVoicePage().getVoiceSummaryAttributes("Location");
		System.out.println("Location is "+location.trim());
		boolean locationAvailability = getVoicePage().checkValueAvailabilityForAttribute("Location");
		assertEquals("Location is Visible : "+location, "Location is not Visible", true, locationAvailability);
		getVoicePage().closeDocumentPage();
	}
	
	public void verifyLocationNoLocationInVoice(String subsName, String projectName,String voiceId) {
		navigateTillVoicePage(subsName, projectName, voiceId);
		String location = getVoicePage().getVoiceSummaryAttributes("Location");
		System.out.println("Location is "+location.trim());
		boolean locationAvailability = getVoicePage().checkValueAvailabilityForAttribute("Location");
		assertEquals("Location is not Visible", "Location is Visible : "+location, false, locationAvailability);
		getVoicePage().closeDocumentPage();
	}
	
	public void verifySubjectDisplaySubjectInVoiceSummaryPage(String subsName, String projectName,String voiceId) {
		navigateTillVoicePage(subsName, projectName, voiceId);
		String subject = getVoicePage().getVoiceSummaryAttributes("Subject");
		System.out.println("Subject is "+subject.trim());
		boolean subjectAvailability = getVoicePage().checkValueAvailabilityForAttribute("Subject");
		assertEquals("Subject is Visible : "+subject, "Subject is not Visible", true, subjectAvailability);
		getVoicePage().closeDocumentPage();
	}
	
	public void verifySourceDisplaySourceInVoiceSummaryPage(String subsName, String projectName,String voiceId) {
		navigateTillVoicePage(subsName, projectName, voiceId);
		String source = getVoicePage().getVoiceSummaryAttributes("Source");
		System.out.println("Source is "+source.trim());
		boolean sourceAvailability = getVoicePage().checkValueAvailabilityForAttribute("Source");
		assertEquals("Source is Visible : "+source, "Source is not Visible", true, sourceAvailability);
		getVoicePage().closeDocumentPage();
	}
	
	public void verifyDateANDTimeDisplayDateInVoiceSummaryPage(String subsName, String projectName,String voiceId) {
		navigateTillVoicePage(subsName, projectName, voiceId);
		String date = getVoicePage().getVoiceSummaryAttributes("Date");
		System.out.println("Date is "+date.trim());
		boolean dateAvailability = getVoicePage().checkValueAvailabilityForAttribute("Date");
		assertEquals("Date is Visible : "+date, "Date is not Visible", true, dateAvailability);
		getVoicePage().closeDocumentPage();
	}

	public void verifyMappingServiceCodesValidateCorrectMappingOfServiceCodesToSubServices771(String subsName, String projectName,String voiceId) {
		navigateTillVoicePage(subsName, projectName, voiceId);
		String subService = getVoicePage().getVoiceSummaryAttributes("Sub Service");
		System.out.println("Sub Service is "+subService.trim());
		assertContains("Actual Sub Service is equal to Expected Sub Service", "Actual Sub Service is not equal to Expected Sub Service",
				subService.trim(), "De-activation of Electricity/Water (Move-out)");
		getVoicePage().closeDocumentPage();
	}
	
	public void verifyMappingServiceCodesValidateCorrectMappingOfServiceCodesToSubServices768(String subsName, String projectName,String voiceId) {
		navigateTillVoicePage(subsName, projectName, voiceId);
		String subService = getVoicePage().getVoiceSummaryAttributes("Sub Service");
		System.out.println("Sub Service is "+subService.trim());
		assertContains("Actual Sub Service is equal to Expected Sub Service", "Actual Sub Service is not equal to Expected Sub Service",
				subService.trim(), "Bill Payment");
		getVoicePage().closeDocumentPage();
	}
	
	public void verifyMappingServiceCodesValidateCorrectMappingOfServiceCodesToSubServices4156(String subsName, String projectName,String voiceId) {
		navigateTillVoicePage(subsName, projectName, voiceId);
		String subService = getVoicePage().getVoiceSummaryAttributes("Sub Service");
		System.out.println("Sub Service is "+subService.trim());
		assertContains("Actual Sub Service is equal to Expected Sub Service", "Actual Sub Service is not equal to Expected Sub Service",
				subService.trim(), "EV Charging");
		getVoicePage().closeDocumentPage();
	}
	
	public void verifyCustomerInformationValidateMobileNumberExtraction(String subsName, String projectName,String voiceId) {
		navigateTillVoicePage(subsName, projectName, voiceId);
		String customerContact = getVoicePage().getVoiceSummaryAttributes("Customer Contact");
		System.out.println("Customer Contact is "+customerContact.trim());
		boolean customerContactAvailability = getVoicePage().checkValueAvailabilityForAttribute("Customer Contact");
		assertEquals("Customer Contact is Visible : "+customerContact, "Customer Contact is not Visible", true, customerContactAvailability);
		getVoicePage().closeDocumentPage();
	}
	
	
	public void afterMethod() {
		getHomePage().clickOnHomeButton();
		getPage().waitForTimeout(2000);
		getPage().reload();
	}
	
	

}
