

import java.io.*;
import java.util.*;
public class ClusterMat {
    
    public static void printArray(int arr[]) throws IOException
    {
        int n = arr.length;
        for (int i=0; i<n; ++i) {
            System.out.print(arr[i] + " "); 
        }
        System.out.println(" ");
    }
    
    public static void printMat(int arr[][]) throws IOException
    {
        int n = arr.length;
        for (int i=0; i<n ; i++) {
            for (int j=0; j<n;  j++) 
                System.out.print(arr[i][j] + " "); 
            System.out.println();
        }   
    }
    static int [] sortMat(int mat[][], int n)
    {
        // temporary matrix of size n^2
        int temp[] = new int[n * n];
        int k = 0;
     
        // copy the elements of matrix
        // one by one into temp[]
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                temp[k++] = mat[i][j];
     
        // sort temp[]
        Arrays.sort(temp);
         
        // copy the elements of temp[]
        // one by one in mat[][]
        k = 0;
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                mat[i][j] = temp[k++];
        return temp;
    }
    public static int[][] ClusterMat(int []arr, int n){
        int ClusteredMat[][] = new int[n][n];
        int noFClusters = (int) Math.sqrt(n);
        int ind = n*n;
        int startInd = n;
        Random r=new Random();
        int ind1;
        int ind2;
        int maxUpper = 2;
        int minUpper = 2;
        int difference = 0;
        int maxLower = 2;
        int minLower = 1;
        int temp;
        if(noFClusters*noFClusters<n)
        	difference = n - noFClusters*noFClusters;
        for(int j = 0;j<100;j++) {
        	for(int i = 0;i<=(n*n) - noFClusters*noFClusters*noFClusters;i++) {
            	ind1 = r.nextInt((n*n) - noFClusters*noFClusters*noFClusters);
            	ind2 = r.nextInt((n*n) - noFClusters*noFClusters*noFClusters);
            	temp = arr[ind1];
            	arr[ind1] = arr[ind2];
            	arr[ind2] = temp;
            }
        }
        for(int k = 0; k <n;k = k + noFClusters) {
        	maxUpper = maxUpper * 2;
        	maxLower = maxLower * 2;
        	
        	//minUpper = minUpper + minUpper;
        	//minLower = minLower + minLower;
        	/*if(ind<(n*n)/2)
        		ind = n*n;
        	if(startInd>((n*n)/4 + n))
        		startInd = n;*/
                if(noFClusters >0) {
                    for(int i = k;i<n;i++) {
                        if(i-k<noFClusters) {
                            for(int m = k;m<n;m++)
                                if(i == m)
                                    ClusteredMat[i][m] = 0;
                                else {
                                    //System.out.println(ind);
                                    ClusteredMat[i][m] = r.nextInt(maxUpper-minUpper)+minUpper;
                                    //ind --;
                                }  
                            
                            for(int m = k + noFClusters;m<n;m++)
                                if(i == m)
                                    ClusteredMat[i][m] = 0;
                                else
                                    ClusteredMat[i][m] = r.nextInt(maxLower-minLower)+minLower;
                            if(i>=noFClusters) {
                                for(int m = 0;m<k;m++)
                                    if(i == m)
                                        ClusteredMat[i][m] = 0;
                                    else {
                                        ClusteredMat[i][m] = r.nextInt(maxLower-minLower)+minLower;
                                    }
                            }
                        }
                        
                    }
                }
               
        }
        if(difference>0) {
        	for(int i = noFClusters*noFClusters;i<n;i++) {

                if(i-noFClusters*noFClusters<noFClusters) {
                    for(int m = noFClusters*noFClusters;m<n;m++)
                        if(i == m) {
                            ClusteredMat[i][m] = 0;
                        	//System.out.println("Here" + (i));

                        }
                        else {
                            //System.out.println(ind);
                            ClusteredMat[i][m] = r.nextInt(maxUpper-minUpper)+minUpper;
                            //ind --;
                        }  
                   /* 
                    for(int m = k + noFClusters;m<n;m++)
                        if(i == m)
                            ClusteredMat[i][m] = 0;
                        else
                            ClusteredMat[i][m] = r.nextInt(maxLower-minLower)+minLower;
                    if(i>=noFClusters) {
                        for(int m = 0;m<k;m++)
                            if(i == m)
                                ClusteredMat[i][m] = 0;
                            else {
                                ClusteredMat[i][m] = r.nextInt(10-1)+1;
                            }
                    }*/
                }
                
            }
        }
        return ClusteredMat;
                
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
    public static void main(String[] args) throws IOException {
        // TODO Auto-generated method stub
        int n = 100;
        int b[] = new int[n];
        int c[][] = new int [n][n];
       // c = randomMatrix.createRandomMatrix(n);
        //System.out.println("The initial matrix is");
        //printMat(c);
        //System.out.println();
        //printArray(sortMat(c,n));
        System.out.println("Clustered matrix");
        c = ClusterMat(sortMat(c,n), n);
        printMat(c);
        System.out.println(" ");
        System.out.println("The order is: ");
        b = createRandomArray(n);
        printArray(b);
        System.out.println("=================== ");
        System.out.println("After the order ");
        printMat(Main.sortMatrix(b, c));
    }

}
