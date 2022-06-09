
import java.awt.List;
import java.util.Collections;
import java.util.Random;
import java.util.*;


public class randomMatrix {
	public static int[][] createRandomMatrix(int n){            
        Random r=new Random();
        int[][] a=new int[n][n];
        
        for(int i=0;i<n;i++)
        {
            for(int j=0;j<n;j++)
            {
                
               a[i][j]=r.nextInt(50);
            }
           
        }
        for(int i=0;i<n;i++)
            a[i][i] = 0;
      return a;
        }
	public static int[] createRandomArray(int n){            
        Random r=new Random();
        int[] a=new int[n];
        
        for(int i=0;i<n;i++)               
               a[i] = i + 1;
        int ind1;
        int ind2;
        int temp;
        for(int i = 0;i<n;i++) {
        	ind1 = r.nextInt(n);
        	ind2 = r.nextInt(n);
        	temp = a[ind1];
        	a[ind1] = a[ind2];
        	a[ind2] = temp;
        }
      return a;
        }
}
