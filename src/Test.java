/*Java Program to put the even and odd elements in two separate arrays*/

import java.util.Scanner;

public class Test
{
    //Method to print the even and odd array
    static void printArray(int even[], int m, int odd[],int n1)
    {
        System.out.println("The array with even elements...");
        for(int i=0;i<m;i++)
        {
            System.out.print(even[i]+" ");   //Print Even Array
        }
        System.out.println("");
        System.out.println("The array with odd elements...");
        for(int i=0;i<n1;i++)
        {
            System.out.print(odd[i]+" ");    //Print Odd Array
        }
        
    }
    
    //Method to put even and odd elements in different arrays
    static void separate(int arr[],int n)    
    {
         int m=0,n1=0;    //Declare the size of the array for even and odd elements
         for(int i=0;i<n;i++)   
         {
            if(arr[i]%2==0)
             m++;    //Increment even array size
             else 
             n1++;   //Increment odd array size
         }
        int even[]=new int[m];    //Declare an even array
        int odd[]=new int[n1];   //Declare an odd array
        
        int k=0,t=0;
        for(int i=0;i<n;i++)
        {
            if(arr[i]%2==0)
            {
              even[k]=arr[i];   //Initialize elements of even array
              k++;
            }
            else
            {
                odd[t]=arr[i];  //Initialize elements of odd array
                t++;
            }
        }
        printArray(even,m,odd,n1);
    }

     //Driver Method
     public static void main(String []args)
     {
         Scanner sc=new Scanner(System.in);
         int n;     //Declare array size
         System.out.println("Enter the size of the array");
         n=sc.nextInt();   //Initialize array size
         
         int arr[]=new int[n];   //Declare array 
        System.out.println("Enter the array");  
        for(int i=0;i<n;i++)     //Initialize array
        {
            arr[i]=sc.nextInt();
        }
        
        separate(arr,n);
        
    }
}