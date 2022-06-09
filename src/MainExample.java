
import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

public class MainExample {
	public static Workbook wb = new HSSFWorkbook();
 	public static CreationHelper createHelper = wb.getCreationHelper();
 	public static Sheet sheet = wb.createSheet("new sheet");
 	public static int rowCount = -1;
 	
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
    
	public static void printArray(int arr[]) throws IOException
    {
        int n = arr.length;
        for (int i=0; i<n; ++i) {
        	System.out.print(arr[i] + " ");
        }
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
        

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		int n = 4;
		int maxRun = 1;
		int [][]c = {{0,0,3,0},{0,0,1,3},{0,0,0,1},{0,0,1,0}};
		int d[][] = null;
		int reducedCrossingBubble = 0;
		int reducedCrossingInsertion = 0;
		int reducedCrossingSelection = 0;
		int reducedCrossingSelectionNoDist = 0;
		int reducedCrossingSelectionSplit = 0;
		int reducedCrossingNewIdea = 0;
		int inputForBubble = 0;
		int initialCrossing = 0;
		long bubbleTime = 0;
		long insertionTime = 0;
		long selectionTime = 0;
		long selectionTimeSplit = 0;
		long selectionDistTime = 0;
		long selectionNewIdeaTime = 0;
		long selectionBubbleTime = 0;
		long startTime = 0;
		long endTime = 0;
		BubbleSortAlgo b;
		InsertionSortAlgo i;
		SelectionSortAlgo s;
		SelectionSortAlgoNoDist ss;
		int countRun = 0;
		System.out.print("Initial		");
		System.out.print("Bubble		");
		System.out.print("Insertion		");
		System.out.print("Selection		");
		System.out.print("SelectionNoDist		");
		System.out.print("SelectionNoDistSplit        		");
		System.out.print("SelectionNewIdea		     ");
		System.out.print("inputForBubble 		");
		System.out.println("Number of run 		");
		rowCount++;
        Row row = sheet.createRow((short)rowCount);
        Cell cell = row.createCell(0);
  	  	cell.setCellValue("Initial crossing ");
  	  
  	  	cell = row.createCell(1);
  	  	cell.setCellValue("Bubble crossing");
  	  
  	  	cell = row.createCell(2);
  	  	cell.setCellValue("Insertion crossing");
  	  	
  	  	cell = row.createCell(3);
  	  	cell.setCellValue("Selection crossing");
  	  	
  	    cell = row.createCell(4);
	  	cell.setCellValue("SelectionNoDist crossing");
	  	
	  	cell = row.createCell(5);
	  	cell.setCellValue("SelectionSplit crossing");
	  	
	  	cell = row.createCell(6);
	  	cell.setCellValue("SelectionNewIdea");
	  	
	  	cell = row.createCell(7);
	  	cell.setCellValue("inputForBubble");
	  	
	  	
	  	
		while(countRun<maxRun) {
			reducedCrossingBubble = 0;
			reducedCrossingInsertion = 0;
			reducedCrossingSelection = 0;
			reducedCrossingSelectionSplit = 0;
			initialCrossing = 0;
		//	c = randomMatrix.createRandomMatrix(n);
			//d = c;
		//	c = ClusterMat.ClusterMat(ClusterMat.sortMat(c,n), n);
		//	c = sortMatrix(randomMatrix.createRandomArray(n),c);
			//System.out.println("The matrix");
			printMat(c);
			//System.out.println(" ");
			initialCrossing = computeCrossing(c);
			startTime = System.nanoTime();
			b = new BubbleSortAlgo();
			//b.bubbleSortFnDoWhile(c);
			reducedCrossingBubble = computeCrossing(sortMatrix(b.bubbleSortFnDoWhile(c),c));
			endTime = System.nanoTime();
			bubbleTime+=endTime - startTime;
			//============================================================
			startTime = System.nanoTime(); 
			i = new InsertionSortAlgo();
			//i.insertionSortFNn(c);
			reducedCrossingInsertion = computeCrossing(sortMatrix(i.insertionSortFNn(c),c));
			endTime = System.nanoTime();
			insertionTime+=endTime - startTime;
			//============================================================
			startTime = System.nanoTime(); 
			s = new SelectionSortAlgo();
			//s.SelectionSort(c);
			reducedCrossingSelection = computeCrossing(sortMatrix(s.SelectionSort(c),c));
			endTime = System.nanoTime();
			selectionTime+=endTime - startTime;
			//===========================================================
			startTime = System.nanoTime();
			ss = new SelectionSortAlgoNoDist();
			//ss.SelectionSortNoDist(c);
			reducedCrossingSelectionNoDist = computeCrossing(sortMatrix(ss.SelectionSortNoDist(c),c));
			endTime = System.nanoTime();
			selectionDistTime+=endTime - startTime;
			s = new SelectionSortAlgo();
			int a[] = null;
			int c2[][] = null;
			a = s.SelectionSortMax(c);
			//printArray(a);
			startTime = System.nanoTime();
			reducedCrossingNewIdea = computeCrossing(sortMatrix(a,c));
			endTime = System.nanoTime();
			selectionNewIdeaTime +=endTime - startTime;
			startTime = System.nanoTime();
			c2 = sortMatrix(a,c);
			inputForBubble = computeCrossing(sortMatrix(b.bubbleSortFnDoWhile(c2),c2));
			endTime = System.nanoTime();
			selectionBubbleTime +=endTime - startTime;
			//===========================================================
			int nEven[][] = null;
			int nOdd[][] = null;
			if(n%2 == 0) {
				nEven = new int[n/2][n/2];
				nOdd = new int[n/2][n/2];
			}else {
				nEven = new int[n/2][n/2];
				nOdd = new int[n-nEven.length][n-nEven.length];
				//System.out.println("here" + (nOdd.length));
			}
			for(int l = 0;l<nEven.length;l++)
				for(int k = 0;k<nEven.length;k++)
					nEven[l][k] = c[l][k];
			if(n%2==0) {
				for(int l = nOdd.length;l<n;l++)
					for(int k = nOdd.length;k<n;k++)
						nOdd[l-nOdd.length][k-nOdd.length] = c[l-1][k-1];
			}else {
				for(int l = nOdd.length;l<=n;l++)
					for(int k = nOdd.length;k<=n;k++)
						nOdd[l-nOdd.length][k-nOdd.length] = c[l-1][k-1];
			}
			startTime = System.nanoTime(); 
			s = new SelectionSortAlgo();
			reducedCrossingSelectionSplit = reducedCrossingSelectionSplit+ computeCrossing(sortMatrix(s.SelectionSort(nEven),nEven));
			s = new SelectionSortAlgo();
			reducedCrossingSelectionSplit = reducedCrossingSelectionSplit+ computeCrossing(sortMatrix(s.SelectionSort(nOdd),nOdd));
			endTime = System.nanoTime();
			selectionTimeSplit+=endTime - startTime;
			
			
			//if(initialCrossing>= reducedCrossingBubble && initialCrossing>= reducedCrossingInsertion && initialCrossing>= reducedCrossingSelection && initialCrossing>= reducedCrossingSelectionNoDist) {
			//	if(reducedCrossingInsertion>reducedCrossingSelection && reducedCrossingBubble>reducedCrossingSelection && inputForBubble<=reducedCrossingNewIdea) {
					System.out.print( initialCrossing+ " 	");
					System.out.print("	  "  + reducedCrossingBubble );
					System.out.print("		  " + reducedCrossingInsertion );
					System.out.print("			  " + reducedCrossingSelection );
					System.out.print("		        	  " + reducedCrossingSelectionNoDist );
					System.out.print("	          		  " + reducedCrossingSelectionSplit);
					System.out.print("		              	  " + reducedCrossingNewIdea);
					System.out.print("		             	  " + inputForBubble);
					System.out.println("			  " + countRun );
					countRun++;
					System.out.println("The selection new Idea H; order");
					printArray(a);
					System.out.println("			  " );
					System.out.println("=======The Initial Matrix=====" );
					printMat(c);
					System.out.println("=======The input matrix for Bubble after selection=====" );
					printMat(c2);
					rowCount++;
	            	row = sheet.createRow((short)rowCount);
	            	cell = row.createCell(0);
	      	  		cell.setCellValue(initialCrossing);
	      	  
	      	  		cell = row.createCell(1);
	      	  		cell.setCellValue(reducedCrossingBubble);
	      	  
	      	  		cell = row.createCell(2);
	      	  		cell.setCellValue(reducedCrossingInsertion);
	      	  	
	      	  		cell = row.createCell(3);
	      	  		cell.setCellValue(reducedCrossingSelection);
	      	  		
	      	  		cell = row.createCell(4);
	      	  		cell.setCellValue(reducedCrossingSelectionNoDist);
	      	  		
	      	  		cell = row.createCell(5);
	      	  		cell.setCellValue(reducedCrossingSelectionSplit);
	      	  		
	      	  		cell = row.createCell(6);
	      	  		cell.setCellValue(reducedCrossingNewIdea);
	      	  		
	      	  		cell = row.createCell(7);
	      	  		cell.setCellValue(inputForBubble);
	      	  		
	      	  	
	      	  		
				
		
			
			
			
			if(countRun == maxRun) {
				//System.out.println("Initial Matrix");
				//printMat(c);
				//System.out.println(" ");
				
				System.out.println("AN order using selection sort");
				printArray(s.SelectionSort(c));
				System.out.println(" ");
				System.out.println("Selection Matrix");
				printMat(sortMatrix(s.SelectionSort(c),c));
				
				System.out.println("=============================================");
				System.out.println("AN order using selection sort for Even matrix");
				printArray(s.SelectionSort(nEven));
				System.out.println(" ");
				System.out.println("Selection Matrix for Even matrix");
				printMat(sortMatrix(s.SelectionSort(nEven),nEven));
				
				System.out.println("=============================================");
				System.out.println("AN order using selection sort for Odd matrix");
				int hOdd[] = s.SelectionSort(nOdd);
				for(int z = 0;z<nOdd.length;z++)
					hOdd[z]+=nEven.length;
				printArray(hOdd);
				System.out.println(" ");
				System.out.println("Selection Matrix for Odd matrix");
				printMat(sortMatrix(s.SelectionSort(nOdd),nOdd));
								
				//printMat(nEven);
				//printMat(nOdd);
			}
			
			
		}
		rowCount++;
    	row = sheet.createRow((short)rowCount);
    	cell = row.createCell(0);
	  		cell.setCellValue("--");
	  
	  		cell = row.createCell(1);
	  		cell.setCellValue(bubbleTime/maxRun);
	  
	  		cell = row.createCell(2);
	  		cell.setCellValue(insertionTime/maxRun);
	  	
	  		cell = row.createCell(3);
	  		cell.setCellValue(selectionTime/maxRun);
	  		
	  		cell = row.createCell(4);
	  		cell.setCellValue(selectionDistTime/maxRun);
	  		
	  		cell = row.createCell(5);
	  		cell.setCellValue(selectionTimeSplit/maxRun);
	  		
	  		cell = row.createCell(6);
	  		cell.setCellValue(selectionNewIdeaTime/maxRun);
	  		
	  		cell = row.createCell(7);
	  		cell.setCellValue(selectionBubbleTime/maxRun);
		
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
		System.out.println("File written successfully");
		System.out.println("The clustered matrix");
		printMat(ClusterMat.ClusterMat(ClusterMat.sortMat(c,n), n));
		System.out.println("========");
		System.out.println("The random order is");
		printArray(randomMatrix.createRandomArray(n));
		System.out.println("============");
      }

}
