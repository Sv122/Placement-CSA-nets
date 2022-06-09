
import java.io.IOException;

public class InsertionSortAlgo {
	public static int [] insertionSortFNn(int c[][]) throws IOException {
		int H[] = new int [c.length];
		for(int i = 0; i<c.length;i++)
			H[i] = i;
        int bestcost;
        int []H2 = new int[H.length];
        for(int i = 0;i<H.length;i++) {
            H2[i] = H[i];
        }
        int cost = 0;
        int position = 1;
        

        for (int i = 1; i<(H.length)-1; i++) {
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
                if(j!=i) {
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
        for(int i = 0;i<c.length;i++)
        	H2[i]+=1;
        return H2;
    } 
	public static void main(String[] args) throws IOException {
		int c[][] = new int[4][4];
		c[0][0] = 0;
		c[0][1] = 9;
		c[0][2] = 0;
		c[0][3] = 4;
		c[1][0] = 1;
		c[1][1] = 0;
		c[1][2] = 2;
		c[1][3] = 1;
		c[2][0] = 5;
		c[2][1] = 0;
		c[2][2] = 0;
		c[2][3] = 4;
		c[3][0] = 3;
		c[3][1] = 9;
		c[3][2] = 6;
		c[3][3] = 0;
		
		
		
	}
}
