
import java.util.ArrayList;

public class CCPlacement210924 {

            public static void main(String[] args) {
             // TODO Auto-generated method stub
                ArrayList<Integer> ANs = new ArrayList<Integer>();//
                ArrayList<Integer> visited = new ArrayList<Integer>();//
                int count = -1;
                //Matrix initialization
                
                int [][]M = {
                        {0,1,1,0,0,0,3,0},
                        {0,0,0,0,0,2,0,0},
                        {0,0,0,0,1,0,0,0},
                        {0,0,0,0,0,0,0,1},
                        {0,0,0,0,0,0,0,0},
                        {0,0,0,1,0,0,0,0},
                        {0,0,1,0,0,0,0,0},
                        {0,0,0,0,0,0,0,0}
                };
                /*int [][]M = {
                        {0,1,0,0,2,0},
                        {0,0,0,0,0,1},
                        {0,0,0,2,0,0},
                        {0,0,0,0,1,0},
                        {0,0,0,0,0,2},
                        {0,0,0,0,0,0}
                    };*/
                /*int [][]M = {
                        {0,1,0,1},
                        {0,0,1,4},
                        {3,1,0,0},
                        {0,1,0,0}
                };*/
                int []H = new int [M.length];
                int [] k = new int[M.length];
                //Computation the crossing number k between ANs
                for (int i = 0; i < M.length; i++) {
                    ANs.add(i);//Adding the parsed AN to the array list
                    H[i] = i;
                    for (int j = 0; j < M.length; j++) {
                        if(i == j) {
                            k[i]+=0;    
                        }
                        else {
                            k[i]+=(Math.abs(i - j) - 1)*M[i][j];
                        }
                    }
                 //   System.out.println(k[i]);
                }
                //System.out.println();
                //System.out.println();
                //Calling the recursive placement function
                Placement(ANs,M,visited,0,H,count,-1);
            }
            
            public static int [] Placement(ArrayList<Integer> ANs, int [][] M, ArrayList<Integer> visited, int channelPlaceNumber, int []H, int count, int cAN) {
                int AN;//A variable to an AN at a time
                int [] x = new int [M.length];//An array used to extract the AN row from the matrix M
                
                int maxValue = 0;
                ArrayList<Integer> temp = new ArrayList<Integer>();//
                if(ANs.isEmpty())
                    return null;//Recursion base condition; i.e. if there is no more ANs to be placed
                else {
                    AN = ANs.get(0);//Set AN to the first element in the array list ANs
                    if(!visited.contains(AN)) {//if it is in visited, this means that it is already placed and this step
                        //is skipped
                        visited.add(AN);
                        //ANs.remove(0);
                        //extracting the AN row
                        for (int i = 0; i < M.length; i++) {
                            x[i] = M[AN][i];
                        }
                        for (int i = 0; i < M.length; i++) {
                            maxValue = max(x,M);
                            
                            int diffcrossing = 0;
                            if(maxValue!=0 && M[AN][maxValue]>channelPlaceNumber) {
                                
                                x[maxValue] = 0;
                                temp.add(maxValue);
                                /*if(!visited.contains(AN)) {
                                    System.out.println("Contain: " + AN + " : " + visited.indexOf(AN));
                                    visited.remove(visited.indexOf(AN));
                                }*/
                                if(cAN == -1) {
                                    cAN = AN;
                                }
                                for (int j = 0; j <cAN ; j++) {
                                    //System.out.println(j + " AN " + AN);
                                    diffcrossing = diffcrossing + (M[cAN][j] - M[cAN][j]);
                                }
                                //System.out.println("  ");
                                for (int j = cAN + 2; j < M.length; j++) {
                                    diffcrossing = diffcrossing + (M[cAN][j] - M[cAN][j]);
                                    //System.out.println(j + " AN2 " + AN);
                                }
                                
                                Placement(temp,M,visited,diffcrossing,H,count,AN);//recursive calling
                                temp.clear();
                            }
                        }
                        System.out.print((AN+1) + " ");
                        count = count + 1;
                        H[count] = AN + 1;
                    }
                    else {
                        ANs.remove(ANs.indexOf(AN));
                    }
                    
                }
                return Placement(ANs,M,visited,0,H,count,-1);
                        
            }
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
            }//end method max
        }
