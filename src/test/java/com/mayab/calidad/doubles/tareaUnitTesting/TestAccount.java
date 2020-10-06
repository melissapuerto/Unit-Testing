package com.mayab.calidad.doubles.tareaUnitTesting;

import static org.junit.Assert.*;
import org.junit.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.junit.Before;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.*;
import static org.hamcrest.MatcherAssert.assertThat; 
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.when;

import java.util.HashMap;

public class TestAccount {
	
	public Account account2;
	public Account account;
	private AlertListener alerts;
	HashMap<Integer, Integer> hashMapMovimiento = new HashMap<Integer, Integer>();
	HashMap<Integer, Integer> hashMapComision = new HashMap<Integer, Integer>();

	
	@Before
	public void setUpMocks()
	{
		account2= mock(Account.class); 
		alerts= mock(AlertListener.class);
		HashMap<Integer, Integer> hashMapComision = new HashMap<Integer, Integer>();


	}
	


	@Test
	public void testAbonos()
	{
		when (account2.credit(anyInt())).thenAnswer(new Answer <Integer> () {
			
			public Integer answer(InvocationOnMock invocation) throws Throwable
			{
				account2.numComisiones+=1;
				account2.zona=3;
				int arg = (Integer) invocation.getArguments() [0]; //lo que le estas depositando
				float comision = (float) (arg * (0.01 * 3));//comision
			    float ncom = arg - comision; // abono con comision
			    account2.balance += ncom;
				account2.suma=0;
				hashMapMovimiento.put(account2.numComisiones, (int) ncom);
				hashMapComision.put(account2.numComisiones, (int) comision);
			for (float f : hashMapComision.values()) {
				    account2.suma += f;
				}
				
		        return (int) account2.balance;
			      
			}
			});
		when (account2.calcularComision()).thenAnswer(new Answer <Integer> () {
			
			public Integer answer(InvocationOnMock invocation) throws Throwable
			{
				
			for (float f : hashMapComision.values()) {
				    account2.suma += f;
				}
				
		        return (int) account2.suma;
			      
			}
			});


		account2.credit(100); //Deposita 100 pero le quitan 3 pesos de comision
		account2.credit(100);
		account2.credit(100);
		assertThat(account2.balance, is(291)); //300 - 9 de comision es 291
		assertThat(account2.numComisiones,is(3)); //Se hicieron 3 transacciones
		assertThat(account2.suma, is(9)); //El total de comisiones es 9

	}
	

	@Test
	public void testAlert()
	{
		this.account=new Account("00346131", 100, 2, alerts);
		account.debit(24);	
		verify(alerts).sendAlert("00346131, your account balance is below 100");

		
	}
	


	

	
	
}

	

