
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class SelectionSortAlgo {
	
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
	
	public static int [] SelectionSort(int [][] M) throws IOException {
		int count = -1;
		ArrayList<Integer> placed = new ArrayList<Integer>();
		int []H = new int [M.length];
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
        for(int i = 0; i<M.length;i++) {
        	if(!placed.contains(max(k) + 1)) {
        		placed.add(max(k) + 1);
            	k[max(k)] = -1;
        	}
        	 
        }
        Iterator<Integer> iter = placed.iterator();
        
        while (iter.hasNext()) {
            ++count;
            H[count] = iter.next();
        }
        return H;
    }
    public static int maxCross(int [][]M, int []arr) {
        int []k = new int[M.length];
        int flag = -1;
        for (int i = 0; i < M.length; i++) {
        	k[i] = 0;
        	flag = -1;
        	for(int l = 0;l<arr.length;l++)
        		if(i == arr[l])
        			flag = 0;
        	if(flag == 0)
        		continue;
        	for(int j = 0;j<arr.length;j++) {
            	//k[i] = 0;
        		if(arr[j] < 0)
        			continue;
            	if(i == arr[j]) {
                    k[i]+=0;    
                }
                else {
                    k[i]+=M[i][arr[j]] + M[arr[j]][i];
                }
            }
        }
    	
    	return max(k);
    }
	public static int [] SelectionSortMax(int [][] M) throws IOException {
		int count = -1;
		ArrayList<Integer> placed = new ArrayList<Integer>();
		int []H = new int [M.length];
        int []k = new int[M.length];
        int arr[] = new int[M.length];
        for (int i = 0; i < M.length; i++) {
        	k[i] = 0;
        	H[i] = -1;
            for (int j = 0; j < M.length; j++) {
                if(i == j) {
                    k[i]+=0;    
                }
                else {
                    k[i]+=(Math.abs(i - j) - 1)*M[i][j];
                }
            }
        }
               
        H[0] = max(k);
        //System.out.println(H[0]);
        for (int i = 1; i < M.length; i++) {
        	H[i] = maxCross(M,H);
        }
        
        
        for(int i = 0;i<H.length;i++)
        	H[i]+=1;

        return H;
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
    
	
	public static void main(String[] args) throws IOException {
		int [][]c = null;
		int n = 5;
		c = randomMatrix.createRandomMatrix(n);
		printMat(c);
		int []arr = new int [c.length];
		int []arr2 = new int [c.length];
		System.out.println("=======================");
		arr = SelectionSort(c);
		for(int i = 0;i<c.length;i++)
			System.out.print((arr[i]) + " ");
		System.out.println(" ");
		System.out.println("==========New One===========");
		//arr = null;
		arr2 = SelectionSortMax(c);
		for(int i = 0;i<c.length;i++)
			System.out.print((arr2[i] ) + " ");
	}
}
