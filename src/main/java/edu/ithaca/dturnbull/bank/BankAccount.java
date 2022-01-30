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


    class InvalidAmountException extends Exception{
        public InvalidAmountException(String message)
        {
           message = "Please enter a positive amount to withdraw";
        }
    }

    /**
     * @post reduces the balance by amount if amount is non-negative and smaller than balance
     */
    public void withdraw (double amount) throws InsufficientFundsException, InvalidAmountException{
        if (amount <= balance){
            balance -= amount;
        }
        else if (amount >= balance){
            throw new InsufficientFundsException("Not enough money");
        }
        else{
            throw new InvalidAmountException("Please enter a positive amount to withdraw");
        }
    }


    public static boolean isEmailValid(String email){

        if (email.indexOf('@') == -1){
            return false;
        }
        
        int beforeAt = email.indexOf("@") - 1;
        String[] check = email.split("@");
        String prefix = check[0];
        String domain = check[1];

        if(prefix.contains("#")){
            return false;
        }
        else if(email.contains("..")){
            return false;
        }
        else if(email.indexOf(".") == beforeAt | email.indexOf("_") == beforeAt | email.indexOf("-") == beforeAt){
            return false;
        }
        else if(!domain.contains(".")){
            return false;
        }
        else if(email.length() - email.indexOf(".") < 3){
            return false;
        }
        else {
            return true;
        }
    }
}