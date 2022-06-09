

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class SelectionSortAlgoNoDist {
	static ArrayList<Integer> placed = new ArrayList<Integer>();
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
    
	public static int [] SelectionSortNoDist(int [][] M) throws IOException {
    	int count = -1;
		int []H = new int [M.length];
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
      
}
