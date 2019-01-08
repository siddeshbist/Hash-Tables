/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab042si4;

/**
 *
 * @author Siddesh
 */
public class HashTableLin {
    private Integer[] table;
    private int size; //size of table
    private int keys; //number of elements stored, incremented everytime
    private int maxKeys;//max number of elements stored 
    private double maxLoad;//max load factor allowed can be a decimal value
    
    public HashTableLin(int maxNum, double load)
    {
       maxLoad=load; //maximum load factor equal to load
       keys=0; //no elements in hash table
       maxKeys = maxNum; //max number of keys equals max Num value, how many you can insert before you have to call rehash
       double s1= maxNum/(load); //determines size of table
       int s = (int)s1+1; //integer round down so add 1  
       if(isPrime(s)){
            size = s;
        }
        else{
            size = nextPrime(s); 
        }
        table = new Integer[size]; 
    }
    
    public void insert(int n)
    {
      if(isIn(n)){
            return;
        } 
      ++keys; //inserting elements so increase keys by one
      if(keys>maxKeys)
      {
          rehash(); //make a bigger hash table
      }
      int k = n%size; //hash function
      
      if(table[k]==null) //Check to see if spot it empty or not
      {
          table[k]=n; //if spot empty then insert element in that spot
      }
      else 
            while( table[k]!= null) //spot not empty so must keep on doing linear probing until find an empty spot
            {k=(k+1)%size;
            }
      table[k]=n; //insert into empty spot found from doing linear probing
      
    }
    
    
    
    
    
    
    private void rehash()
    {
     size=size*2; //bigger table twice larger then old size
     Integer [] newHashTable;
     if(isPrime(size))
     {
         newHashTable = new Integer[size];
     }
     else
     {
         size=nextPrime(size); //find next prime number after size
         newHashTable= new Integer[size];
     }
     
     for(int i=0;i<table.length;i++)
     {
         if(table[i]!=null)
         {
             newHashTable[table[i]%size]=table[i];//find the index in the newHashTable and then you set that equal to the element from the old table
         }
     }
     double s1 = maxLoad*size;
     int s = (int)s1; //typecast conversion
     maxKeys = s; //update field
     table = newHashTable; //update field
     
     
     }
    
    
    public boolean isIn(int n)
    {
        int k = n%size; // hash function 
        while(table[k]!=null)
        {
        if(table[k]==n)
        {
       return true;
        }
else
{
k=(k+1)/size; //increase the index and runs loop again
}
}
return false; //element not in table
}
    
    
    public void printKeys()
    {
       for(int i=0; i<table.length;i++) 
       {
           if(table[i]!=null)
           {
               System.out.println(table[i]+ ",");
           }
       }
    }
    
    public void printKeysAndIndexes(){
        for(int i =0; i<table.length;i++){
            if(table[i] != null){
                System.out.print(table[i] + "["+ i+ "], " );
            }
        }
    }
    
      public boolean isPrime(int n){
        if(n<2)
        {
            return false; //1 is not a prime number
        }
        else if (n==2)
        {
           return true; //2 is a prime number  
        }
        else if(n%2==0&&n!=2) //even number but 2 is a prime number so make an exception
        {
            return false; 
        }
        
       for(int i =3; i<n; i=i+2){
            if(n%i==0){
                return false; //not prime as divisible by a number other then itself
            }
        }
        return true;
    }
      
      public int nextPrime(int n){
        
        boolean x = true;
        while(x){
            if(isPrime(++n)){
                x = false; //once a prime number is found, x becomes false and while loop stops running
            }
        }
        //System.out.println(n);
        return n;
    }//end nextPrime
    
      
      public int getKeys()
      {
          return keys; 
      }
      
      public int getSize()
      {
          return size;
      }
      
       //for simulation, NO REHASHING 
    public int insert2(int n){
        int probes = 0;
        if(isIn(n)){
            return probes;
        }
        ++keys;
        int k = n%size;
        if(table[k] == null){
            table[k] = n;
            return probes++;
        }
        else{
            while(table[k] !=null){
                k = (k+1)%size;
                probes++;
            }
            table[k] =n;
            probes++; //probe at value
        }
        return probes;
    }
    
}
