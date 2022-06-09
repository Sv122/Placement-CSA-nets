

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
public class bubbleSort {
        public static int n = 4;
        public static int [] H = new int [n];
        public static int [] H_CCP = new int [n];
        public static int [] H2 = new int [n];
        public static Workbook wb = new HSSFWorkbook();
    	public static CreationHelper createHelper = wb.getCreationHelper();
    	public static Sheet sheet = wb.createSheet("new sheet");
    	public static int rowCount = -1;
    	public static boolean stop = false;

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
    public static void printMat(int arr[][]) throws IOException
    {
        int n = arr.length;
        
    	Row row;
    	Cell cell;
        for (int i=0; i<n ; i++) {
        	rowCount++;
        	row = sheet.createRow((short)rowCount);
            for (int j=0; j<n;  j++) {
            	cell = row.createCell(j);
                cell.setCellValue(arr[i][j]); 
            }
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
    //==============================SORTING FUNCTIONS=================================================
    public static int Placement(ArrayList<Integer> ANs, int [][] M, ArrayList<Integer> visited, ArrayList<Integer> placed, int prevDiffCross, int cAN) {
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
                    int diffcrossing = 0;
                    if(maxValue!=0 && M[AN][maxValue]>prevDiffCross) {
                        x[maxValue] = 0;
                        temp.add(maxValue);
                        if(cAN == -1) {
                            cAN = AN;
                        }
                        for (int j = 0; j <cAN ; j++) {
                            diffcrossing = diffcrossing + (M[cAN][j] - M[AN][j]);
                        }
                        for (int j = cAN + 2; j < M.length; j++) {
                            diffcrossing = diffcrossing + (M[cAN][j] - M[AN][j]);
                        }
                        
                        Placement(temp,M,visited,placed,diffcrossing,AN);//recursive calling
                        temp.clear();
                    }
                }
                placed.add(AN+1);
            }
            else {
                ANs.remove(ANs.indexOf(AN));
            }
            
        }
        return Placement(ANs,M,visited,placed,0,-1);
                
    }
    
    public static int [] bubbleSortFn(int n, int H [], int c[][]) {
        int diffcrossing = 0;
        for (int k = 0; k < n; k++) {
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
            }
        }
        }
        return H;
    }
    public static int [] bubbleSortFnDoWhile(int n, int H [], int c[][]) {
        int diffcrossing = 0;
        
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
        }while(stop == true);
        return H;
    }
    public static void main(String[] args) throws IOException {
        ArrayList<Integer> ANs = new ArrayList<Integer>();//
        ArrayList<Integer> visited = new ArrayList<Integer>();//
        ArrayList<Integer> placed = new ArrayList<Integer>();//
        

        for(int l = 0; l<15;l++) {
      	    rowCount++;
        	Row row = sheet.createRow((short)rowCount);
      	    for(int i = 0;i<n;i++) {
                H[i] = i;
                H2[i] = i;
                ANs.add(i);
            }
            int [][]c = randomMatrix.createRandomMatrix(n);
            int [][]c_CCP = copyBoard(c);
            int [][]c_doWhile = copyBoard(c);
            int [][]c_doWhile2 = copyBoard(c);
            //printMat(c_CCP);
            int count = -1;
            printMat(c);
            System.out.println(" ");
            Placement(ANs,c,visited,placed,-1,-1);
        	  //Create a cell and put a value in it.
        	  Cell cell = row.createCell(0);
        	  cell.setCellValue("The initial number of crossing is: ");
        	  cell = row.createCell(1);
        	  cell.setCellValue(computeCrossing(c));
        	  rowCount++;
        	  row = sheet.createRow((short)rowCount);
        	  cell = row.createCell(0);
        	  cell.setCellValue("=========================================== ");  	
            Iterator<Integer> iter = placed.iterator();
            while (iter.hasNext()) {
                ++count;
                H_CCP[count] = iter.next();
            }
            H = bubbleSortFn(n,H,c);
            for(int i = 0; i<n;i++)
            	H[i] = H[i] + 1;
            if((computeCrossing(c) <= computeCrossing(sortMatrix(H,c)) || (computeCrossing(c) <= computeCrossing(sortMatrix(H_CCP,c_CCP))))) {
              rowCount++;
          	  row = sheet.createRow((short)rowCount);
          	  cell = row.createCell(0);
          	  cell.setCellValue("The matrix is in its best case and cannot be placed further");  	
            }else {
            	rowCount++;
            	  row = sheet.createRow((short)rowCount);
            	  cell = row.createCell(0);
            	  cell.setCellValue("Sorted array by traditional bubble");
                
            	  cell = row.createCell(1);
            	  cell.setCellValue(computeCrossing(sortMatrix(H,c)));
            	  
            	  System.out.println();
                printArray(H);
               
              rowCount++;
          	  row = sheet.createRow((short)rowCount);
          	  cell = row.createCell(0);
          	  cell.setCellValue("=========================================== ");              
                rowCount++;
          	  row = sheet.createRow((short)rowCount);
          	  cell = row.createCell(0);
          	  cell.setCellValue("The sorted array by CCP and crossdiff");
          	  printArray(H_CCP);
                rowCount++;
          	  row = sheet.createRow((short)rowCount);
          	  cell = row.createCell(0);
          	  cell.setCellValue("Our reduced number of crossing is: ");
          	  
          	cell = row.createCell(1);
        	  cell.setCellValue(computeCrossing(sortMatrix(H_CCP,c_CCP)));
        	  
        	  rowCount++;
          	  row = sheet.createRow((short)rowCount);
          	  cell = row.createCell(0);
          	  cell.setCellValue("=========================================== ");
          	H2 = bubbleSortFnDoWhile(n,H2,c);
          	printMat(c);
          	for(int i = 0; i<n;i++)
            	H2[i] = H2[i] + 1;
                rowCount++;
          	  row = sheet.createRow((short)rowCount);
          	  cell = row.createCell(0);
          	  cell.setCellValue("The sorted array by bubbleSortDoWhile");
          	  printArray(H2);
                rowCount++;
          	  row = sheet.createRow((short)rowCount);
          	  cell = row.createCell(0);
          	  cell.setCellValue("The reduced number of crossing by bubbleSortDoWhile is: ");
          	  
          	cell = row.createCell(1);
        	  cell.setCellValue(computeCrossing(sortMatrix(H2,c)));
        	  
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
        	  ANs.clear();
              visited.clear();
              placed.clear();
            }
        }
        
    }
}
