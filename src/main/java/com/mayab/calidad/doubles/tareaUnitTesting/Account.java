package com.mayab.calidad.doubles.tareaUnitTesting;

import java.util.HashMap;

public class Account {


   public int balance;
   public String holder;
   public int zona;
   public int suma=0;
   public int numComisiones;
   public  AlertListener alerts;
   HashMap<Integer, Integer> movimientos = new HashMap<Integer, Integer>();
   HashMap<Integer, Integer> comisiones = new HashMap<Integer, Integer>();

    public Account(String holder, int initialBalance, int zona, AlertListener alerts){
       this.holder = holder;
       this.balance = initialBalance;
       this.alerts = alerts;
       this.zona=zona;
    }
    
    public Account(String holder, int initialBalance, int zona){
        this.holder = holder;
        this.balance = initialBalance;
        this.zona=zona;
    }
    
    public float getBalance() {
        return this.balance;
    }
    
    public String getHolder(){
        return this.holder;
    }

    public float debit(int n) {
	   numComisiones+=1;
       float comision = (float) (n * (0.01 * this.zona));//comision
       float ncom = n + comision; // cargo con comision
       this.balance = (int) (this.balance - ncom) ; 
       this.movimientos.put(numComisiones, (int) (ncom*-1));
       this.comisiones.put(numComisiones, (int) comision);
       if(this.balance < 100){
       this.alerts.sendAlert(this.holder+", your account balance is below 100");
        }
        return this.balance;
    }
   
    public float credit(int n) { 
	   numComisiones+=1;
       float comision = (float) (n * (0.1 * this.zona));
       float ncom = n - comision; 
       this.balance = (int) (this.balance + ncom) ; //actualizamos balance
       //insertamos en el hashmap movimientos del cargo
       this.movimientos.put(numComisiones, (int) ncom);
       //insertamos en el hashmap comisiones
       this.comisiones.put(numComisiones, (int) comision);
       //alerta
        if(this.balance < 100){
         this.alerts.sendAlert(this.holder+", your account balance is below 100");
        }
        return this.balance;
    }
  
    public void setAlertListener(AlertListener listener){
      this.alerts = listener;
  	}
  
    public int calcularComision(){
  	for (float f : comisiones.values()) {
		    suma += f;
		}
      return suma;
  	}
  
    public void historialComisiones(){
      System.out.println("Historial de las comisiones: "+comisiones.toString());
    }
  
    public void historialMovimientos(){
      System.out.println("Historial de los movimientos: "+movimientos.toString());
    }
  
	@Override
	public String toString() {
		return "Account [balance=" + balance + ", holder=" + holder + ", zona=" + zona + "]";
	}
	
  
}
