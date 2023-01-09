package com.infy.dto;

import java.time.LocalDate;

public class LoanDTO {
    private Integer loanId;
    private Double amount;
    private LocalDate loanIssueDate;
    private String status;
    private CustomerDTO customer;

    public Integer getLoanId() {
        return this.loanId;
    }

    public void setLoanId(Integer loanId) {
        this.loanId = loanId;
    }

    public Double getAmount() {
        return this.amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public LocalDate getLoanIssueDate() {
        return this.loanIssueDate;
    }

    public void setLoanIssueDate(LocalDate loanIssueDate) {
        this.loanIssueDate = loanIssueDate;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public CustomerDTO getCustomer() {
        return this.customer;
    }

    public void setCustomer(CustomerDTO customer) {
        this.customer = customer;
    }

    @Override
    public String toString() {
        return "LoanDTO{" +
                "loanId=" + loanId +
                ", amount=" + amount +
                ", loanIssueDate=" + loanIssueDate +
                ", status='" + status + '\'' +
                ", customer=" + customer +
                '}';
    }

}
