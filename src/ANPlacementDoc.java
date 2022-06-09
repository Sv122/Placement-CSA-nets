

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
public class ANPlacementDoc {
        public static int n = 4;
        public static int [] H_bubble = new int [n];
        public static int [] H_CCP = new int [n];
        public static int [] H_SelectionNoDist = new int [n];
        public static int [] H_insertion = new int [n];
        public static int [] H_SelectionDist = new int [n];
      //==============================Supplementary FUNCTIONS=================================================  
      //Finding the index of the max value in the row
        
        public static int max(int[] t) {
            int maximum = t[0];   
            int index = 0;
            for (int i=1; i<t.length; i++) {
                if (t[i] > maximum) {
                    maximum = t[i];    
                    index = i;
                }
            }
            return index;
        }
        
        public static int computeCrossing(int c [][]) {
        int k = 0;
        //Computation the crossing number k between ANs
        for (int i = 0; i < c.length; i++) {
            for (int j = 0; j < c.length; j++) {
                if(i == j) {
                    k+=0;    
                }
                else {
                    k+=(Math.abs(i - j) - 1)*c[i][j];
                }
            }
    }
        return k;
    }
        
        public static int[][] copyMat(int[][] state)
        {
            int[][] returnArray = new int[state.length][state.length];
            for (int i = 0, j = 0; i*j < state.length; i++, j++)
            {
                // deep copy of row:
                returnArray[i] = Arrays.copyOf(state[i], state[i].length);
            }
            return returnArray;
        }
    
        public static int[][] sortMatrix(int H[], int c[][]) {
        int [][] test = new int [c.length] [c.length];
        int x = 0;
        for (int i = 0;i<c.length;i++) {
            x = H[i];
            for (int j = 0;j<c.length;j++) {
                test[i][j] = c[x - 1][H[j] - 1];
                test[j][i] = c[H[j] - 1][x - 1];
                
            }
        }
        return test;
    }
    
        public static void printMat(int arr[][]) throws IOException
    {
        int n = arr.length;
        
        for (int i=0; i<n ; i++) {
            for (int j=0; j<n;  j++) {
            	System.out.print(arr[i][j] + " "); 
            }
            System.out.println(" "); 
        }

    }
    //==============================SORTING FUNCTIONS=================================================
        public static void SelectionSort(int [][] M, ArrayList<Integer> placed) throws IOException {
        int []k = new int[M.length];
        
            for (int i = 0; i < M.length; i++) {
        	k[i] = 0;
            for (int j = 0; j < M.length; j++) {
                if(i == j) {
                    k[i]+=0;    
                }
                else {
                    k[i]+=(Math.abs(i - j) - 1)*M[i][j];
                }
            }
        }
        for(int i = 0; i<n;i++) {
        	if(!placed.contains(max(k) + 1)) {
        		placed.add(max(k) + 1);           
        		
        		 
     
            	k[max(k)] = -1;
        	}
        	 
        }
    }
    
        public static void SelectionSortNoDist(int [][] M, ArrayList<Integer> placed) throws IOException {
    	
        int []k = new int[M.length];
        //Computation the crossing number k between ANs
        for (int i = 0; i < M.length; i++) {
        	k[i] = 0;
            for (int j = 0; j < M.length; j++) {
                if(i == j) {
                    k[i]+=0;    
                }
                else {
                    k[i]+=M[i][j] + M[j][i];
                }
            }
            
        }
        for(int i = 0; i<n;i++) {
        	if(!placed.contains(max(k) + 1)) {
        		
        		placed.add(max(k) + 1);
            	k[max(k)] = -1;
        	} 
        }
    }
      
      	public static int [] bubbleSortFnDoWhile(int n, int H [], int c[][]) throws IOException {
    	int diffcrossing = 0;
        boolean stop = false;
        do{
        	stop = true;
        for (int i = 0; i < n-2; i++)
        {
            diffcrossing = 0;
        for (int j = 0; j < i; j++) {
            	if(i == 0 )
            		System.out.println("Step omitted in first summation");
            	else
            		diffcrossing = diffcrossing + (c[H[i]][H[j]] - c[H[i + 1]][H[j]]);
            }
            for (int j = i + 2; j < n; j++) {
            	if (i == n-2)
            		System.out.println("Step omitted in second summation");
            	else
            		diffcrossing = diffcrossing + (c[H[i + 1]][H[j]] - c[H[i]][H[j]]);
            }
            if (diffcrossing<0)
            {
                int temp = H[i];
                H[i] = H[i+1];
                H[i+1] = temp;
                stop = false;
            }
            
        }
        }while(stop == false);
        return H;
    }
  
      	public static int [] insertionSortFNn(int H [], int c[][]) throws IOException {    
        int bestcost;
        int []H2 = new int[H.length];
        for(int i = 0;i<H.length;i++) {
            H2[i] = H[i];
        }
        int cost = 0;
        int position = 1;
        

        for (int i = 1; i<=n-1; i++) {
        	bestcost = 1000;
            
            for (int j = 0;j<=i;j++) {
            	cost = 0;
            	
                //first summation
                if(j!=0 && j!=i) {
                	for(int k  = 0; k<=j-1; k++) {
                        for(int m = j; m<=i-1;m++) {
                        	
                            cost = cost + c[H[k]][H[m]];
                        }
                    }
                }
                //second summation
                if(j!=0) {
                	for(int k = 0; k<=j-1; k++) {
                        cost = cost + (Math.abs(j-k)-1)*c[H[k]][H[i]];
                    }
                }
                //Third summation
                if
                (j!=i) {
                	for(int m = j; m<=i-1;m++) {
                        cost = cost + (Math.abs(j-m))*c[H[i]][H[m]];
                    }
                }
                if(cost<bestcost) {                    
                    bestcost = cost;
                    position = j;
                    if(position!=i) {
                    	for(int jj = i-1; jj>=position;jj--) {
                            H2[jj+1] = H[jj];
                        }
                    }
                    H2[position] = i;
                    }
            }
           
        }
        
        return H2;
    } 
    
        //==============================MAIN FUNCTION=================================================

      	public static void main(String[] args) throws IOException {
        ArrayList<Integer> placed2 = new ArrayList<Integer>();//
        int [][]c;
	  	int [][]c_CCP;
	  	int [][]c_CCPMax;
     
      	    for(int i = 0;i<n;i++) {
      	    	H_bubble[i] = i;
                H_insertion[i] = i;
            }
            c = randomMatrix.createRandomMatrix(n);
            printMat(c);
            c_CCP = copyMat(c);
            //Calling of PlacementMaxFor and preparing H_SelectionDist
            SelectionSort(c,placed2);
            Iterator<Integer> iter = placed2.iterator();
            int count = -1;
            while (iter.hasNext()) {
                ++count;
                H_SelectionDist[count] = iter.next();
            }
            //Calling of PlacementMaxForNoDist and preparing H_SelectionNoDist
            placed2.clear();
            count = -1;
            SelectionSortNoDist(c,placed2);
            iter = placed2.iterator();
            while (iter.hasNext()) {
                ++count;
                H_SelectionNoDist[count] = iter.next();
            }
            //Calling of bubbleSortFnDoWhile and preparing H_bubble
            H_bubble = bubbleSortFnDoWhile(n,H_bubble,c);
            for(int i = 0; i<n;i++)
            	H_bubble[i] = H_bubble[i] + 1;
            
            //Calling of insertionSortFNn and preparing H_insertion
            H_insertion = insertionSortFNn(H_insertion,c);
            for(int i = 0; i<n;i++)
            	H_insertion[i] = H_insertion[i] + 1;
            
            if((computeCrossing(c) >= computeCrossing(sortMatrix(H_bubble,c)) && (computeCrossing(c) >= computeCrossing(sortMatrix(H_SelectionDist,c_CCP))) && computeCrossing(c) >= computeCrossing(sortMatrix(H_insertion,c)))) {
            System.out.println("The initial number of crossing is: " + computeCrossing(c));
            System.out.println(" ");
            
            count = -1;
            c_CCPMax = copyMat(c);
            

            
            System.out.println("The Number of crossing of selection Algo is: " + computeCrossing(sortMatrix(H_SelectionDist,c)));
            System.out.println(" ");
            System.out.println(" ");
            
            System.out.println("The Number of crossing of selection Algo no Distance is: " + computeCrossing(sortMatrix(H_SelectionNoDist,c)));
            System.out.println(" ");
            System.out.println(" ");
            
            System.out.println("The Number of crossing of bubble Algo is: " + computeCrossing(sortMatrix(H_bubble,c)));
            System.out.println(" ");
            System.out.println(" ");
            
            System.out.println("The Number of crossing of insertion Algo is: " + computeCrossing(sortMatrix(H_insertion,c)));
            }else {
            	System.out.println("Cannot be placed further");
            }
        
      }
}
