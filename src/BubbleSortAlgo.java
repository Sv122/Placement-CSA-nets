
import java.io.IOException;

public class BubbleSortAlgo {
	public static int [] bubbleSortFnDoWhile(int c[][]) throws IOException {
		int n = c.length;
		int H[] = new int [n];
		for(int i = 0; i<n;i++)
			H[i] = i;
    	int diffcrossing = 0;
        boolean stop = false;
        do{
        	stop = true;
            //System.out.println("Inside do while " + stop);

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
                //System.out.println("Diff of " + i + " " + diffcrossing);

                int temp = H[i];
                H[i] = H[i+1];
                H[i+1] = temp;
                stop = false;
            }
            
        }
        //System.out.println(stop);

        }while(stop = false);
        for(int i = 0;i<n;i++)
        	H[i]+=1;
        return H;
    }
  
}
