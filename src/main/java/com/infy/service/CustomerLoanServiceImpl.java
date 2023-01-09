package com.infy.service;

import com.infy.dto.CustomerDTO;
import com.infy.dto.LoanDTO;
import com.infy.entity.Customer;
import com.infy.entity.Loan;
import com.infy.exception.InfyBankException;
import com.infy.repository.CustomerRepository;
import com.infy.repository.LoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service(value = "customerLoanService")
@Transactional
public class CustomerLoanServiceImpl implements CustomerLoanService{

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private LoanRepository loanRepository;

    @Override
    public LoanDTO getLoanDetails(Integer loanId) throws InfyBankException{
        Optional<Loan> optional = loanRepository.findById(loanId);
        Loan loan = optional.orElseThrow(() -> new InfyBankException("Service.LOAN_UNAVAILABLE"));
        LoanDTO loanDTO = new LoanDTO();
        loanDTO.setLoanId(loan.getLoanId());
        loanDTO.setAmount(loan.getAmount());
        loanDTO.setLoanIssueDate(loan.getIssueDate());
        loanDTO.setStatus(loan.getStatus());

        Customer customer = loan.getCustomer();
        if(customer != null){
            CustomerDTO customerDTO = new CustomerDTO();
            customerDTO.setCustomerId(customer.getCustomerId());
            customerDTO.setName(customer.getName());
            customerDTO.setEmailId(customer.getEmailId());
            customerDTO.setDateOfBirth(customer.getDateOfBirth());

            loanDTO.setCustomer(customerDTO);
        }

        return loanDTO;
    }

    // implementing addLoanAndCustomer() method
    @Override
    public Integer addLoanAndCustomer(LoanDTO loanDTO) throws InfyBankException{
        Loan loan = new Loan();
        loan.setAmount(loanDTO.getAmount());
        loan.setIssueDate(loanDTO.getLoanIssueDate());
        loan.setStatus("Open");

        CustomerDTO customerDTO = loanDTO.getCustomer();
        Customer customer = new Customer();

        customer.setCustomerId(customerDTO.getCustomerId());
        customer.setName(customerDTO.getName());
        customer.setEmailId(customerDTO.getEmailId());
        customer.setDateOfBirth(customerDTO.getDateOfBirth());

        loan.setCustomer(customer);
        loanRepository.save(loan);

        return loan.getLoanId();
    }

    // implementing sanctionLoanToExistingCustomer() method
    @Override
    public Integer sanctionLoanToExistingCustomer(Integer customerId, LoanDTO loanDTO) throws InfyBankException{
        Loan loan = new Loan();
        loan.setAmount(loanDTO.getAmount());
        loan.setIssueDate(loanDTO.getLoanIssueDate());
        loan.setStatus(loanDTO.getStatus());

        Optional<Customer> optional = customerRepository.findById(customerId);
        Customer customer = optional.orElseThrow(() -> new InfyBankException("Service.CUSTOMER_UNAVAILABLE"));
        loan.setCustomer(customer);
        loanRepository.save(loan);

        return loan.getLoanId();
    }

    // implementing closeLoan() method
    @Override
    public void closeLoan(Integer loanId) throws InfyBankException{
        Optional<Loan> optional = loanRepository.findById(loanId);
        Loan loan = optional.orElseThrow(() -> new InfyBankException("Service.LOAN_UNAVAILABLE"));
        loan.setStatus("Closed");
    }

    // implementing deleteLoan() method
    @Override
    public void deleteLoan(Integer loanId) throws InfyBankException {
        Optional<Loan> optional = loanRepository.findById(loanId);
        Loan loan = optional.orElseThrow(() -> new InfyBankException("Service.LOAN_UNAVAILABLE"));
        loan.setCustomer(null);
        loanRepository.delete(loan);
    }
}
