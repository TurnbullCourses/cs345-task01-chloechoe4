package edu.ithaca.dturnbull.bank;

public class BankAccount {

    private String email;
    private double balance;

    /**
     * @throws IllegalArgumentException if email is invalid
     */
    public BankAccount(String email, double startingBalance){
        if (isEmailValid(email)){
            this.email = email;
            this.balance = startingBalance;
        }
        else {
            throw new IllegalArgumentException("Email address: " + email + " is invalid, cannot create account");
        }
    }

    public double getBalance(){
        return balance;
    }

    public String getEmail(){
        return email;
    }

    /**
     * @post reduces the balance by amount if amount is non-negative and smaller than balance
     */
    public void withdraw (double amount) throws InsufficientFundsException{
        if (amount <= balance){
            balance -= amount;
        }
        else {
            throw new InsufficientFundsException("Not enough money");
        }
    }


    public static boolean isEmailValid(String email){
        
        if (email.indexOf('@') == -1){
            return false;
        }
        int beforeAt = email.indexOf("@") - 1;
        if(email.contains("#")){
            return false;
        }
        else if(email.contains("..")){
            return false;
        }
        else if(email.indexOf(".") == beforeAt | email.indexOf("_") == beforeAt | email.indexOf("-") == beforeAt){
            return false;
        }
        else {
            return true;
        }
    }
}