/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab042si4;
import java.util.Random;

/**
 *
 * @author Owner
 */
public class LAB042SI4 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Random rando = new Random();
        //test constructor 
        HashTableLin s1 = new HashTableLin(5,0.4);
        HashTableLin s2 = new HashTableLin(10,0.5);
        
        //Test Linear probing /INSERT
        System.out.println("Linear probing Demonstrated - Number of Same mod should be at the next Index");
        System.out.println("Maxnum = 10, Load = 0.5, MaxNum/Load = 20, Thus Size should = 23");
        s2.insert(7);
        s2.insert(30);
        s2.insert(53); 
        s2.printKeys();
        System.out.print("\n");
        s2.printKeysAndIndexes();
        System.out.println("\nSize = " + s2.getSize() + ", Number of Keys = " + s2.getKeys());
        
        //test Rehash and Insert 
        //maxLoad = 0.4, MaxNum of keys = 5 
        System.out.println("\nRehashing needed, Size of table should be 29");
        for(int i =0; i<10;i++)    //since trying to input 10 values and thats greater then maxNum of keys, rehash will start
        {
           s1.insert(rando.nextInt(100));
        }
        s1.printKeys();
        System.out.print("\n");
        s1.printKeysAndIndexes();
        System.out.println("\nSize = " + s1.getSize() + ", Number of Keys = " + s1.getKeys());
        
        int n = 100000;//max number of elements in table is 100 000 
//        
        //lambda = 0.1 SIMULATION--------------------------------------------
        HashTableLin x = new HashTableLin(n,0.3); //maxNum = 100 000 and load factor is 0.9 to prevent rehashing
        int p = 0;
        double s = 0.0;
        for(int i = 0; i<100;i++){
        while(x.getKeys() != n)//keys get incremented everytime you insert so while loop stops when 100 000 reached
        {
            p+= x.insert2(rando.nextInt(2147483647)); //2147483647 //return number of probes //call to isIn method prevent duplicates
        }
        s += (double)p/n; //sum of probes 
        }
        //double s = (double)p/(100*n); 
        System.out.println("Average probes = " + s/100); //divide by 100
    }
}