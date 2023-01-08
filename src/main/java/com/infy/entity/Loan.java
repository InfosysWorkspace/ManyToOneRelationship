package com.infy.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Objects;

@Entity
public class Loan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer loanId;
    private Double amount;
    private LocalDate issueDate;
    private String status;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cust_id")
    private Customer customer;

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

    public LocalDate getIssueDate() {
        return this.issueDate;
    }

    public void setIssueDate(LocalDate issueDate) {
        this.issueDate = issueDate;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Customer getCustomer() {
        return this.customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public String toString() {
        return "Loan{" +
                "loanId=" + loanId +
                ", amount=" + amount +
                ", issueDate=" + issueDate +
                ", status='" + status + '\'' +
                ", customer=" + customer +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (null == o || this.getClass() != o.getClass()) return false;
        Loan loan = (Loan) o;
        return this.loanId.equals(loan.loanId) && this.amount.equals(loan.amount) && this.issueDate.equals(loan.issueDate) && this.status.equals(loan.status) && this.customer.equals(loan.customer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.loanId, this.amount, this.issueDate, this.status, this.customer);
    }
}
