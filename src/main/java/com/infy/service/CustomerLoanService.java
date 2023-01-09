package com.infy.service;

import com.infy.dto.LoanDTO;
import com.infy.exception.InfyBankException;

public interface CustomerLoanService {
    public LoanDTO getLoanDetails(Integer loanId) throws InfyBankException;

    // add addLoanAndCustomer() method
    public Integer addLoanAndCustomer(LoanDTO loanDTO) throws InfyBankException;

    // adding sanctionLoanToExistingCustomer() method
    public Integer sanctionLoanToExistingCustomer(Integer customerId, LoanDTO loanDTO) throws InfyBankException;

    // adding closeLoan() method
    public void closeLoan(Integer loanId) throws InfyBankException;

    // adding deleteLoan() method
    public void deleteLoan(Integer loanId) throws InfyBankException;



}
