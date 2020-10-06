package com.mayab.calidad.doubles.tareaUnitTesting;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.HashMap;

import static org.hamcrest.Matchers.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;


@RunWith(Parameterized.class)
public class TestCuentaParametrizada {

	@Parameters
	public static Iterable data() {
		return Arrays.asList(new Object[][]
				{
			{"ho",20,2},{"bu",30,1},{"mem",50,3}
				});
			
	}
	HashMap<String, Account> hashMapCuentas = new HashMap<String, Account>();
	   
	  int initialBalance;
	  String holder;
	  int zona;
	   
	
	public TestCuentaParametrizada(String holder, int initialBalance, int zona)
	{
		this.holder = holder;
        this.initialBalance = initialBalance;
        this.zona=zona;
		
	}
	
	@Test
	public void testCreateAccountsHashMap()
	{
		Account account = new Account(holder, initialBalance, zona);
		hashMapCuentas.put(holder, account);               
		assertThat(hashMapCuentas.toString(),is("{"+holder+"=Account [balance="+initialBalance+", holder="+holder+", zona="+zona+"]}"));
	}

	@Test
	public void testCreateAccounts()
	{
		Account account = new Account(holder, initialBalance, zona);
		assertThat(account.toString(),is("Account [balance=" + initialBalance + ", holder=" + holder + ", zona=" + zona + "]"));
	}

}
