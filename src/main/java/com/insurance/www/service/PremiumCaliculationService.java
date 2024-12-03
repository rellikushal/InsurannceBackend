package com.insurance.www.service;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.RequestBody;

import com.insurance.www.model.PremiumCaliculation;
import com.insurance.www.repository.PremiumCaliculationRepository;

@Service
public class PremiumCaliculationService 
{

	@Autowired
	PremiumCaliculationRepository premiumCaliculationRepository;
	
	public  PremiumCaliculation CaliculatePremiumAmount(long id)
	{
	 Optional<PremiumCaliculation> OptinalData = premiumCaliculationRepository.findById(id);
	 if(OptinalData.isPresent() && !OptinalData.isEmpty())
	 {
		 PremiumCaliculation OptinalData1 = OptinalData.get();
		 return OptinalData1 ;
	 }
	 return null;
		
	}
	
	public PremiumCaliculation state() {
		return premiumCaliculationRepository.findByStateTrue();
	}
	
	public String AddPremiumDetails(PremiumCaliculation data)
	{
		premiumCaliculationRepository.save(data);
		return "aded";
	}
	long intialId ;
	public double  CaliculatePremiumAmount1(Long id) {
	    // Retrieve the record by ID
	    Optional<PremiumCaliculation> optionalData = premiumCaliculationRepository.findById(id);

	    // Retrieve all records
	    List<PremiumCaliculation> allData = premiumCaliculationRepository.findAll();

	    if (optionalData.isPresent()) {
	    	PremiumCaliculation data = optionalData.get();
	        double p1Value = data.getP1();
	        data.setState(true);
	        premiumCaliculationRepository.save(data);
	        // Set the state of all other records to false
	        for (PremiumCaliculation record : allData) {
	            if (!(record.getId() == id)) {
	                record.setState(false);
	                premiumCaliculationRepository.save(record);
	            }
	        }

	        return p1Value + 1;
	    }

	    // If the record with the specified ID does not exist, set the state of all records to false
	    for (PremiumCaliculation record : allData) {
	        record.setState(false);
	    }

	    return 0;
	}

	
	public void selectedId(@PathVariable long id)
	{
		 Optional<PremiumCaliculation> OptinalData = premiumCaliculationRepository.findById(id);
		 if(OptinalData.isPresent() && !OptinalData.isEmpty())
		 {
			//  PremiumCaliculation OptinalData1 = OptinalData.get();
			 intialId = id;		 
		 }
	}
	
	public int calculatePremiumAmountforAdmin(PremiumCaliculation request) {
	    String marketValue1 = request.getMarKetValue();
	    String buildingAge = request.getBuildingAge();
	    String securityCheck = request.getSecurityCheck();

	    // Retrieve the record with state = true
	    PremiumCaliculation stateTrueData = premiumCaliculationRepository.findByStateTrue();

	    PremiumCaliculation premiumData;

	    if (stateTrueData != null) {
	        premiumData = stateTrueData;
	    } else {
	        Optional<PremiumCaliculation> optionalData = premiumCaliculationRepository.findById(1L);
	        if (optionalData.isPresent()) {
	            premiumData = optionalData.get();
	        } else {
	            // Handle the case where the record with id = 1 is not found (optional)
	            throw new RuntimeException("Default premium calculation data not found.");
	        }
	    }

	    double initialValue = premiumData.getP1();
	    double securityInitialValue = "Yes".equals(securityCheck) ? premiumData.getP7() : premiumData.getP8();
	    double buildingAgeRate = 0;

	    switch (buildingAge) {
	        case "0 to 5 Years":
	            buildingAgeRate = premiumData.getP2();
	            break;
	        case "5 to 10 Years":
	            buildingAgeRate = premiumData.getP3();
	            break;
	        case "10 to 15 Years":
	            buildingAgeRate = premiumData.getP4();
	            break;
	        case "15 to 20 Years":
	            buildingAgeRate = premiumData.getP5();
	            break;
	        case "20 to 25 Years":
	            buildingAgeRate = premiumData.getP6();
	            break;
	    }

	    long marketValueLong = Long.parseLong(marketValue1);
	    double sumOfInitialValues = initialValue + buildingAgeRate + securityInitialValue;

	    System.out.println("Initial Value: " + initialValue+1);
	    System.out.println("Building Age Rate: " + buildingAgeRate+1);
	    System.out.println("Security Initial Value: " + securityInitialValue+1);
	    System.out.println("Sum of Initial Values: " + sumOfInitialValues);
	    System.out.println("Market Value Long: " + marketValueLong);

	    int premiumValue = (int) Math.floor(marketValueLong * sumOfInitialValues);
	    System.out.println("Premium Value: " + premiumValue);

	    return Math.round((int)premiumValue);
	}


	public String changePremiumCaliculationvalues(long id , PremiumCaliculation request)
	{
		Optional<PremiumCaliculation> OptinalData = premiumCaliculationRepository.findById(id);
		 if(OptinalData.isPresent() && !OptinalData.isEmpty())
		 {
			 PremiumCaliculation OptinalData1 = OptinalData.get();
			 OptinalData1.setP1(request.getP1());
			 OptinalData1.setP2(request.getP2());
			 OptinalData1.setP3(request.getP3());
			 OptinalData1.setP4(request.getP4());
			 OptinalData1.setP5(request.getP5());
			 OptinalData1.setP6(request.getP6());
			 OptinalData1.setP7(request.getP7());
			 OptinalData1.setP8(request.getP8());
			 
			 premiumCaliculationRepository.save(OptinalData1);
			 return "UpdatedSuccessfully";
		 }
		 return "NotUpdatedSuccessFully";
	}
	
	public List<PremiumCaliculation> getAllPremiumDetails()
	{
		return premiumCaliculationRepository.findAll();
	}
}
