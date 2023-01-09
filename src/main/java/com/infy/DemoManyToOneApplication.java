package com.infy;

import com.infy.dto.CustomerDTO;
import com.infy.dto.LoanDTO;
import com.infy.entity.Loan;
import com.infy.exception.InfyBankException;
import com.infy.repository.LoanRepository;
import com.infy.service.CustomerLoanService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;

import java.time.LocalDate;
import java.util.Optional;

@SpringBootApplication
public class DemoManyToOneApplication implements CommandLineRunner {
	public static final Log LOGGER = LogFactory.getLog(DemoManyToOneApplication.class);

	@Autowired
	CustomerLoanService customerLoanService;

	@Autowired
	Environment environment;
	@Autowired
	private LoanRepository loanRepository;

	public static void main(String[] args) {
		SpringApplication.run(DemoManyToOneApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		// implementing getLoanDetails() method
		// getLoanDetails();

		// implementing addLoanAndCustomer() method
		// addLoandAndCustomer();

		// implementing sanctionLoanToExistingCustomer() method
		// sanctionLoanToExistingCustomer();

		// implementing closeLoan() method
		//closeLoan();

		// implementing deleteLoan() method
		deleteLoan();
	}

	// implementing getLoanDetails() method
	public void getLoanDetails(){
		try {
			LoanDTO loanDTO = customerLoanService.getLoanDetails(2001);
		} catch (Exception e){
			String message = environment.getProperty(e.getMessage(), "Some exception occured. Please check log file for more details!!!");
			LOGGER.info(message);
		}
	}

	// implementing addLoanAndCustomer() method
	public void addLoandAndCustomer(){
		try{
			LoanDTO loanDTO = new LoanDTO();
			loanDTO.setAmount(556278.0);
			loanDTO.setLoanIssueDate(LocalDate.of(2023, 9, 01));
			loanDTO.setStatus("Open");

			CustomerDTO customerDTO = new CustomerDTO();
			customerDTO.setCustomerId(1006);
			customerDTO.setName("Peter");
			customerDTO.setEmailId("peter@infy.com");
			customerDTO.setDateOfBirth(LocalDate.of(1992, 1, 10));

			loanDTO.setCustomer(customerDTO);

			Integer loanId = customerLoanService.addLoanAndCustomer(loanDTO);
			LOGGER.info(environment.getProperty("UserInterface.NEW_LOAN_CUSTOMER_SUCCESS") + loanId);

		} catch (Exception e){
			String message = environment.getProperty(e.getMessage(), "Some exception occured. Please check the log file for more details!!!");
			LOGGER.info(message);
		}
	}

	// implementing sanctionLoanToExistingCustomer() method
	public void sanctionLoanToExistingCustomer(){
		try{
			LoanDTO loanDTO = new LoanDTO();
			loanDTO.setAmount(573279.0);
			loanDTO.setLoanIssueDate(LocalDate.of(2015, 11, 1));
			loanDTO.setStatus("Open");

			Integer customerId = 1001;
			customerLoanService.sanctionLoanToExistingCustomer(customerId, loanDTO);
			LOGGER.info(environment.getProperty("UserInterface.LOAN_SANCTION_SUCCESS"));

		} catch (Exception e){
			String message = environment.getProperty(e.getMessage(), "Some exception occured. Please check log file for more details!!!");
			LOGGER.info(message);
		}
	}

	// implementing closeLoan() method
	public void closeLoan() {
		try {
			Integer loanId = 2003;
			customerLoanService.closeLoan(loanId);
			LOGGER.info(environment.getProperty("UserInterface.LOAN_CLOSE_SUCCESS"));
		} catch (Exception e) {
			String message = environment.getProperty(e.getMessage(), "Some exception occured, Please check log file for more details!!!");
			LOGGER.info(message);
		}
	}

	// implementing deleteLoan() method
	public void deleteLoan(){
		try{
			Integer loanId = 2003;
			customerLoanService.deleteLoan(loanId);
			LOGGER.info(environment.getProperty("UserInterface.LOAN_DELETE_SUCCESS"));
		} catch (Exception e){
			String message = environment.getProperty(e.getMessage(),"Some exception occured. Please check log file for more details!!!");
			LOGGER.info(message);
		}
	}
}
