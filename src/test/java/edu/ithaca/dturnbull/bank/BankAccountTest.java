package edu.ithaca.dturnbull.bank;

import org.junit.jupiter.api.Test;

import edu.ithaca.dturnbull.bank.BankAccount.InvalidAmountException;

import static org.junit.jupiter.api.Assertions.*;


class BankAccountTest {

    @Test
    void getBalanceTest() {
        BankAccount bankAccount = new BankAccount("a@b.com", 200);

        assertEquals(200, bankAccount.getBalance(), 0.001);

        BankAccount bankAccount2 = new BankAccount("abc@mail.com",100);
        assertEquals(100, bankAccount2.getBalance(), 0.001); //New getBalance Test
    }

    @Test
    void withdrawTest() throws InsufficientFundsException, InvalidAmountException{
        BankAccount bankAccount = new BankAccount("a@b.com", 200);
        bankAccount.withdraw(100);

        assertEquals(100, bankAccount.getBalance(), 0.001);
        assertThrows(InsufficientFundsException.class, () -> bankAccount.withdraw(300));

        bankAccount.withdraw(50); //withdraw test: withdraws 50 to the new getBalance 

        assertEquals(50, bankAccount.getBalance(), 0.001); 
        assertThrows(InsufficientFundsException.class, () -> bankAccount.withdraw(51)); //throws exception when amount is greater than new current balance

    }

    @Test
    void isEmailValidTest(){
        assertTrue(BankAccount.isEmailValid( "a@b.com"));   // valid email address
        assertFalse( BankAccount.isEmailValid(""));         // empty string
        assertFalse(BankAccount.isEmailValid("abc-@mail.com")); //symbol before @ symbol is invalid
        assertFalse(BankAccount.isEmailValid("abcd@mail.b")); //not enough letters after the  "."
        assertFalse(BankAccount.isEmailValid("abc#def@mail.com")); //invalid symbol in email
        assertFalse(BankAccount.isEmailValid("abcdef@mail..com")); // Equivalent class for two dots in a row
        assertFalse(BankAccount.isEmailValid("abcdef@mail")); // no domain
        assertFalse(BankAccount.isEmailValid(".abcdef@mail.com")); // Invald opening character


        
    }

    @Test
    void constructorTest() {
        BankAccount bankAccount = new BankAccount("a@b.com", 200);

        assertEquals("a@b.com", bankAccount.getEmail());
        assertEquals(200, bankAccount.getBalance(), 0.001);
        //check for exception thrown correctly
        assertThrows(IllegalArgumentException.class, ()-> new BankAccount("", 100));
    }

}