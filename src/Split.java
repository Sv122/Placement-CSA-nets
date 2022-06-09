import java.util.Scanner;

class seg {
    int seg[][] = new int[4][4];
}

class Split
{

   public static void main(String args[])
   {
      int m, n, i, j = 0;

      Scanner in = new Scanner(System.in);
      System.out.println("Enter the number of rows and columns of matrix");
      m = in.nextInt();
      n = in.nextInt();

      int matrix[][] = new int[m][n];

      System.out.println("Enter the elements of matrix\n");

      for ( i = 0 ; i < m ; i++ )
         for ( j = 0 ; j < n ; j++ )
            matrix[i][j] = in.nextInt();

      System.out.println("entered matrix is:\n");
      for ( i = 0 ; i < m ; i++ )
      {
         for ( j = 0 ; j < n ; j++ )
      System.out.print(matrix[i][j]+"\t");

            System.out.print("\n");
      }


      seg s[]=new seg[10];

      int q, k=0,c=0,d=0;

     if(c%(m+i)!=0 || d%(n+j)!=0)
      {
      for(i=c;i<c+4;i++)
      {
          for(j=d;j<d+4;j++)
             if(i>=m || j>=n)
                  s[k++].seg[i][j]=0;//zero padding
              else
                  s[k++].seg[i][j]=matrix[i][j];


       }
      c=c+4;
      d=d+4;
      }
 
      System.out.println("segments are\n");
     
     q=k;
     do{
      for ( i = 0 ; i < 4 ; i++ )
      {
         for ( j = 0 ; j < 4 ; j++ )
               System.out.print(s[q--].seg[i][j]+"\t");
        System.out.print("\n");
      }
     }while(q!=0);


    }
    }