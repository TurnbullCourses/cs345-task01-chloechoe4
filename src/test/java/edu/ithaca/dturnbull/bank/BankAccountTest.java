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
    void isAmountValidTest(){
        //Valid Cases
        assertTrue(BankAccount.isAmountValid(100));
        assertTrue(BankAccount.isAmountValid(365.8));
        //Invalid Cases
        assertThrows(IllegalArgumentException.class, () -> BankAccount.isAmountValid(-52)); // throws exception with negative
        assertFalse(BankAccount.isAmountValid(852.3654)); //too many decimals

    }

    @Test
    void depositTest() throws IllegalArgumentException{
        BankAccount bankAccount = new BankAccount("bts@mail.com", 5000);
        
        bankAccount.deposit(654);
        assertEquals(5654, bankAccount.getBalance(), 0.001); // valid deposit
        bankAccount.deposit(20.3);
        assertEquals(5674.3, bankAccount.getBalance(), 0.001); // valid deposit
        assertThrows(IllegalArgumentException.class, () -> bankAccount.deposit(-10)); // negative input 

    }

    @Test 
    void transferTest() throws IllegalArgumentException{
        BankAccount bankAccount1 = new BankAccount("abc@mail.com", 1000);
        BankAccount bankAccount2 = new BankAccount("abc2@mail.com", 2000);
        assertEquals(1000, bankAccount1.getBalance(), 0.001);
        assertEquals(2000, bankAccount2.getBalance(), 0.001);
        assertThrows(IllegalArgumentException.class, () -> bankAccount1.transfer(bankAccount2, -50.0)); // negative int amount
        assertThrows(IllegalArgumentException.class, () -> bankAccount1.transfer(bankAccount2, -95.32)); //negative amount with decimals
        assertThrows(IllegalArgumentException.class, () -> bankAccount1.transfer(bankAccount2, -4000.35698)); //too many decimals + negative amount


    }

    @Test
    void constructorTest() {
        BankAccount bankAccount = new BankAccount("a@b.com", 200);

        assertEquals("a@b.com", bankAccount.getEmail());
        assertEquals(200, bankAccount.getBalance(), 0.001);
        //check for exception thrown correctly
        assertThrows(IllegalArgumentException.class, ()-> new BankAccount("", 100));
        assertThrows(IllegalArgumentException.class, () -> new BankAccount("abc@mail.com", -400));//invalid amount because negative
        assertThrows(IllegalArgumentException.class, () -> new BankAccount("abc@mail.com.com", -20.3285));//invalid amount because negative with too many decimals
    }

}