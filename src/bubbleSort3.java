

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
public class bubbleSort3 {
        public static int n = 4;
        public static int [] H = new int [n];
        public static int [] H_CCP = new int [n];
        public static int [] H2 = new int [n];
        public static int [] H3 = new int [n];
        public static int [] H5 = new int [n];
        public static int [] H6 = new int [n];
        public static Workbook wb = new HSSFWorkbook();
        public static int [] H4 = new int [n];
    	public static CreationHelper createHelper = wb.getCreationHelper();
    	public static Sheet sheet = wb.createSheet("new sheet");
    	public static int rowCount = -1;

      //==============================Supplementary FUNCTIONS=================================================  
      //Finding the index of the max value in the row
        public static int max(int[] t, int [][]M) {
            int maximum = t[0];   
            int index = 0;
            for (int i=1; i<M.length; i++) {
                if (t[i] > maximum) {
                    maximum = t[i];    
                    index = i;
                }
            }

            int countindex = 0;
            int count;

            for (int j=0; j<M.length; j++) {
                countindex += M[j][index];
            } 

            for (int i=0; i<M.length; i++) {
                if (t[i] == maximum) {
                    count = 0;
                    for(int j=0; j<M.length; j++) {
                        count += M[j][i];
                    } 
                    if(count>countindex) {
                        index = i;
                        countindex = count;
                    } 
                }
            }
            return index;
        }
        
      //Finding the index of the max value in the row
        public static int max2(int[] t) {
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
        
        private static int[][] copyBoard(int[][] state)
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
    
    public static void printArray(int arr[]) throws IOException
    {
        int n = arr.length;
        rowCount++;
    	Row row = sheet.createRow((short)rowCount);
    	Cell cell;
        for (int i=0; i<n; ++i) {
        	cell = row.createCell(i);
            cell.setCellValue(arr[i]); 
        }
        rowCount++;
    	row = sheet.createRow((short)rowCount);
    	cell = row.createCell(0);
        cell.setCellValue(" "); 
        FileOutputStream fileOut = new FileOutputStream("workbook.xls");
	  	try {
		wb.write(fileOut);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	fileOut.close();
    }
    
    public static void printArray2(int arr[]) throws IOException
    {
        int n = arr.length;
        
        for (int i=0; i<n; ++i) {
        	System.out.print(arr[i] + " "); 
        }
        
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
    public static int PlacementMax(ArrayList<Integer> ANs, int [][] M, ArrayList<Integer> visited, ArrayList<Integer> placed, int prevDiffCross) {
        int AN;//A variable to an AN at a time
        int [] x = new int [M.length];//An array used to extract the AN row from the matrix M
        
        int maxValue = 0;
        ArrayList<Integer> temp = new ArrayList<Integer>();//
        if(ANs.isEmpty())
            return 0;//Recursion base condition; i.e. if there is no more ANs to be placed
        else {
            AN = ANs.get(0);//Set AN to the first element in the array list ANs
            if(!visited.contains(AN)) {//if it is in visited, this means that it is already placed and this step
                //is skipped
                visited.add(AN);
                //extracting the AN row
                for (int i = 0; i < M.length; i++) {
                    x[i] = M[AN][i];
                }
                for (int i = 0; i < M.length; i++) {
                    maxValue = max(x,M);
                    int tempMax = 0;
                    if(maxValue!=0 && M[AN][maxValue]>prevDiffCross) {
                        tempMax = x[maxValue];
                        x[maxValue] = 0;
                        temp.add(maxValue);
                        PlacementMax(temp,M,visited,placed,tempMax);//recursive calling
                        temp.clear();
                    }
                }
                placed.add(AN+1);
            }
            else {
                ANs.remove(ANs.indexOf(AN));
            }
            
        }
        return PlacementMax(ANs,M,visited,placed,0);
                
    }
    
    public static void PlacementMaxFor(int [][] M, ArrayList<Integer> placed) throws IOException {
    	System.out.println("Tracing of our algorithm");
        int []k = new int[M.length];
        //Computation the crossing number k between ANs
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
            System.out.println("The crossing of AN " + (i + 1) + " is: " + k[i]);
        }
       // System.out.println("K" + k[0] +" "+ k[1]+" "+ k[2]+" "+ k[3]);
        //printArray(k);
        for(int i = 0; i<n;i++) {
        	if(!placed.contains(max2(k) + 1)) {
        		System.out.println("Place the AN " + (max2(k) + 1) + " as it has the max crossing at iteration: " + (i + 1));
        		placed.add(max2(k) + 1);
            	k[max2(k)] = -1;
        	}
        	 
        }
                 
        
        
        System.out.println("===================End Of tracing our Algorithm============================");
        System.out.println(" ");
    }
    
    public static void PlacementMaxForTesting(int [][] M, ArrayList<Integer> placed) throws IOException {
    	
        int []k = new int[M.length];
        //Computation the crossing number k between ANs
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
       // System.out.println("K" + k[0] +" "+ k[1]+" "+ k[2]+" "+ k[3]);
        //printArray(k);
        for(int i = 0; i<n;i++) {
        	if(!placed.contains(max2(k) + 1)) {
        		
        		placed.add(max2(k) + 1);
            	k[max2(k)] = -1;
        	}
        	 
        }
    }
    
    
    public static int [] bubbleSortFnDoWhile(int n, int H [], int c[][]) throws IOException {
    	System.out.println("Tracing of bubble sort using do while");
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
            System.out.println("The difference crossing of AN " + (i + 1) + " is: " + diffcrossing);
            if (diffcrossing<0)
            {
            	System.out.println("As the diff crossing is less than 0, then swap");
                int temp = H[i];
                H[i] = H[i+1];
                H[i+1] = temp;
                stop = false;
                System.out.print("Accordingly H becomes ");
                printArray2(H);
                System.out.println(" ");
            }
            else {
            	System.out.println("As the diff crossing is greater than or equal to 0, then NO swap");
            	System.out.print("Accordingly H Stills ");
                printArray2(H);
                System.out.println(" ");
            }
        }
        }while(stop == false);
        System.out.println("===================End Of tracing bubble Sort Do While============================");
        System.out.println(" ");
        return H;
    }
    public static int [] bubbleSortFnDoWhileTesting(int n, int H [], int c[][]) throws IOException {
    	
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
            	//System.out.println("As the diff crossing is less than 0, then swap");
                int temp = H[i];
                H[i] = H[i+1];
                H[i+1] = temp;
                stop = false;
                //System.out.print("Accordingly H becomes ");
                //printArray2(H);
                //System.out.println(" ");
            }
            else {
            	//System.out.println("As the diff crossing is greater than or equal to 0, then NO swap");
            	//System.out.print("Accordingly H Stills ");
                //printArray2(H);
                //System.out.println(" ");
            }
        }
        }while(stop == false);
        //System.out.println("===================End Of tracing bubble Sort Do While============================");
        //System.out.println(" ");
        return H;
    }
    //
    public static int [] insertionSortFNn(int H [], int c[][]) throws IOException {  
    	System.out.println("Tracing of insertion sort");

        int bestcost;
        int cost;
        int position;
        bestcost = 1000;

        for (int i = 1; i<=n-1; i++) {
        	
            cost = 0;
            System.out.println("cost is: " + cost);
            for (int j = 0;j<=i;j++) {
                //first summation
                for(int k  = 0; k<=j-1; k++) {
                    for(int m = j; m<=i-1;m++) {
                        cost = cost + c[H[k]][H[m]];
                    }
                }
                //second summation
                for(int k = 0; k<=j-1; k++) {
                    cost = cost + (Math.abs(j-k)-1)*c[H[k]][H[i]];
                }
                //Third summation
                for(int m = j; m<=i-1;m++) {
                    cost = cost + (Math.abs(j-m))*c[H[i-1]][H[m]];
                }
                if(cost<bestcost && cost != 0) {                    
                    bestcost = cost;
                    position = j;
                    for(int jj = i-1; jj>=position;jj--) {
                        H[jj+1] = H[jj];                        
                    }
                    H[position] = i;
                 }
            }
           
        }
        
        return H;
    } 
    
    
    public static int [] insertionSortFNn2(int H [], int c[][]) throws IOException {    
    	System.out.println("Tracing of insertion sort");

        int bestcost;
        int []H2 = new int[H.length];
        for(int i = 0;i<H.length;i++) {
            H2[i] = H[i];
        }
        int cost = 0;
        int position = 1;
        bestcost = 1000;

        for (int i = 1; i<=n-1; i++) {
            System.out.println("At iteration " + i + " assume the best cost = " + bestcost);
            cost = 0;
            for (int j = 0;j<=i;j++) {
                
                //first summation
                for(int k  = 0; k<=j-1; k++) {
                    for(int m = j; m<=i-1;m++) {
                        cost = cost + c[H[k]][H[m]];
                    }
                //    System.out.println("cost is: " + cost);
                }
                //second summation
                for(int k = 0; k<=j-1; k++) {
                    cost = cost + (Math.abs(j-k)-1)*c[H[k]][H[i]];
                }
                //Third summation
                for(int m = j; m<=i-1;m++) {
                    cost = cost + (Math.abs(j-m))*c[H[i-1]][H[m]];
                }
                //System.out.println("cost is: " + bestcost);
                System.out.println("The cost of iteration " + i + " is " + cost);
                if(cost<bestcost && cost != 0) {                    
                	System.out.print("Since cost less than best cost, then ");
                    bestcost = cost;
                    position = j;
                    System.out.print(" the best cost = " + cost + " and the position is set to " + j);
                    System.out.println(" ");
                    for(int jj = i-1; jj>=position;jj--) {
                    	System.out.println("Accordingly the element is inserted in its position using j steps as follows");
                        H2[jj+1] = H[jj];
                        System.out.print("H is ");
                        printArray2(H2);
                        System.out.println(" ");
                        //H2[j] = H[j+1];
                        
                    }
                    H2[position] = i;
                    System.out.println(" ");
                    System.out.print("H is ");
                    printArray2(H2);                }
            }
           
        }
        
        return H2;
    } 
    
    public static int [] insertionSortFNnTesting(int H [], int c[][]) throws IOException {    
    	//System.out.println("Tracing of insertion sort");

        int bestcost = 0;
        int []H2 = new int[H.length];
        for(int i = 0;i<H.length;i++) {
            H2[i] = H[i];
        }
        int cost = 0;
        int flag;
        int position = 1;
        bestcost = computeCrossing(c);

        for (int i = 1; i<n-1; i++) {
            ///System.out.println("At iteration " + i + " assume the best cost = " + bestcost);
            cost = 0;
            flag = 0;
            for (int j = 0;j<i;j++) {
                
                //first summation
                for(int k  = 0; k<j-1; k++) {
                    for(int m = j; m<i-1;m++) {
                        cost = cost + c[H[k]][H[m]];
                    }
                //    System.out.println("cost is: " + cost);
                }
                //second summation
                for(int k = 0; k<j-1; k++) {
                    cost = cost + (Math.abs(j-k)-1)*c[H[k]][H[i]];
                }
                //Third summation
                for(int m = j; m<i-1;m++) {
                    cost = cost + (Math.abs(j-m))*c[H[i-1]][H[m]];
                }
                //System.out.println("cost is: " + bestcost);
              //  System.out.println("The cost of AN " + (j+1) + " is " + cost);
                if(cost<bestcost) {                    
                	//System.out.print("Since cost less than best cost, then ");
                    bestcost = cost;
                    position = j;
                //    System.out.print(" the best cost = " + cost + " and the position is set to " + j);
                    //System.out.println(" ");
            
                    flag = 1;
                }
            }
            if(flag == 1) {
                for(int j = i-1; j>position;j--) {
                	//System.out.println("Accordingly the element is inserted in its position using j steps as follows");
                    H2[j+1] = H[j];
                    //System.out.print("H is ");
                    //printArray2(H2);
                    //System.out.println(" ");
                    //H2[j] = H[j+1];
                    
                }
            }
            
            /*System.out.println(" ");
            System.out.println(position);
            System.out.println(" ");
            System.out.println(i);*/
            if (flag == 1) {
            	//System.out.print("Finally ");
                H2[position] = i-1;
            	//System.out.print("H is ");
                //printArray2(H2);
                //System.out.println(" ");
            }
        }
        
        return H2;
    } 
    public static void main(String[] args) throws IOException {
        ArrayList<Integer> ANs = new ArrayList<Integer>();//
        ArrayList<Integer> visited = new ArrayList<Integer>();//
        ArrayList<Integer> placed = new ArrayList<Integer>();//
        ArrayList<Integer> ANs2 = new ArrayList<Integer>();//
        ArrayList<Integer> visited2 = new ArrayList<Integer>();//
        ArrayList<Integer> placed2 = new ArrayList<Integer>();//
        int [][]c;
	  	int [][]c_CCP;
	  	int [][]c_CCPMax;
     
      	    for(int i = 0;i<n;i++) {
                H[i] = i;
                H2[i] = i;
                H4[i] = i;
                H5[i] = i;
                H6[i] = i;
                ANs.add(i);
                ANs2.add(i);
            }
            c = randomMatrix.createRandomMatrix(n);
            printMat(c);
            c_CCP = copyBoard(c);
            H = bubbleSortFnDoWhileTesting(n,H,c);
            for(int i = 0; i<n;i++)
            	H[i] = H[i] + 1;
            
            PlacementMaxForTesting(c,placed2);
            
            Iterator<Integer> iter = placed2.iterator();
           // System.out.println("Placed");
            //System.out.print(placed2);
            int count = -1;
            while (iter.hasNext()) {
                ++count;
                H3[count] = iter.next();
            }
            
            H4 = insertionSortFNnTesting(H4,c);
            for(int i = 0; i<n;i++)
            	H4[i] = H4[i] + 1;
            
            if((computeCrossing(c) >= computeCrossing(sortMatrix(H,c)) && (computeCrossing(c) >= computeCrossing(sortMatrix(H3,c_CCP))) && computeCrossing(c) >= computeCrossing(sortMatrix(H4,c)))) {
            System.out.println("The initial number of crossing is: " + computeCrossing(c));
            System.out.println(" ");
            
             count = -1;
            c_CCPMax = copyBoard(c);
            //Placement(ANs,c,visited,placed,-1,-1);
            
            iter = placed.iterator();
           // while (iter.hasNext()) {
            //    ++count;
             //   H_CCP[count] = iter.next();
           // }
            placed2.clear();
            PlacementMaxFor(c,placed2);
            
            iter = placed2.iterator();
           // System.out.println("Placed");
            //System.out.print(placed2);
            //count = -1;
            
            System.out.println("The reduced number of crossing of our Algo. is: " + computeCrossing(sortMatrix(H3,c_CCPMax)));
            System.out.println(" ");
            System.out.println(" ");
            //H = bubbleSortFn(n,H,c);
            //printArray(H);
            H5 = bubbleSortFnDoWhile(n,H5,c);
            
            for(int i = 0; i<n;i++)
            	H5[i] = H5[i] + 1;
            System.out.println("The reduced number of crossing of bubble Algo. is: " + computeCrossing(sortMatrix(H5,c)));
            System.out.println(" ");
            System.out.println(" ");
            H6 = insertionSortFNn(H6,c);
            //printArray(H4);
            for(int i = 0; i<n;i++)
            	H6[i] = H6[i] + 1;
            System.out.println("The reduced number of crossing of insertion Algo. is: " + computeCrossing(sortMatrix(H6,c)));
            
            
            	//continue;
              //rowCount++;
          	  //row = sheet.createRow((short)rowCount);
          	  //cell = row.createCell(0);
          	  //cell.setCellValue("The matrix is in its best case and cannot be placed further");
            	//l--;
          	   // System.out.println("Continued");
            	  
            	
            }else {
            	System.out.println("Cannot be placed further");
            }
        
            }
}
