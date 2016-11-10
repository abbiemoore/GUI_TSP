import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.util.Arrays;
import javax.swing.JFileChooser;

public class TSP_BB
{
int[][] costMatrix;
double min = Double.POSITIVE_INFINITY;
int[] bestPath;
int count;
int[] newArray;
   public TSP_BB()
   {
      try
      {
         JFileChooser myChooser = new JFileChooser(); //create a JFileChooser so user can pick which file to look in
         int returnVal = myChooser.showOpenDialog(null); 
         File myFile_Name = myChooser.getSelectedFile(); //points to the file that the user wants to read
         Scanner in = new Scanner(myFile_Name); //creates a new scanner in the file         
         int n=in.nextInt(); //create a while loop so that scanner reads entire txt file
         int count=0;
         bestPath = new int[n-1];
         costMatrix = new int[n][n];
         for(int row=0; row<n;row++)
         {
            for(int col=0; col<n;col++)
            {
               costMatrix[row][col]=in.nextInt();
               //System.out.println(Integer.toString(costMatrix[row][col]));
            }
         }
         newArray= new int[n-1];
         int j=1;
         for (int i=0; i<newArray.length; i++)
         {
            newArray[i]=j;
            j++;
         }

      }
      catch(FileNotFoundException e)
      {
         System.out.println("File Not Found");
        
      }
   }
   
   public int[] getMyArray()
   {
      return newArray;   
   }
   
   public void perm(int [] input, int p)
   {
      
      if (p==input.length-1)//if p is at the end of the word
      {
      
         System.out.println("perm " +Arrays.toString(input));
                  
         int cost = costEvaluate(input);
         if(cost<min)
            {
               min=cost;
               for (int i =0; i<input.length; i++)
               {
                  bestPath[i]=input[i];
                  
               }
               System.out.println("best path" + Arrays.toString(bestPath));
            }
         }

      else 
      {
         int toDateSum = upToDateSum(input, p);
         if(toDateSum<min)
         {
            for(int i = p; i<input.length; i++) 
            {
               //System.out.println("p " + Integer.toString(p));
            
               swap(input, i, p);
               perm(input, p+1); //call the perm method again and raise p increase indicies 
               swap(input, i, p);
            }
         }
         else
         {
         System.out.println("pruned");
         }
      }
   }
   
   public int[] getPath()
   {
      return bestPath;
   }
   
   public double getMin()
   {
      return min;
   }
   
   public int costEvaluate(int[] cities)
   {
   int sum=0;
   int cost1 = costMatrix[0][cities[0]];
   sum=sum+cost1;
   int cost2 = costMatrix[cities[cities.length-1]][0];
   sum=sum+cost2;
   
      for(int i=0; i<cities.length-1; i++)
      { 
         int cost = costMatrix[cities[i]][cities[i+1]];
         sum= sum+cost;
      }         
      
      return sum;
         
   }
   
   public int upToDateSum(int[] cities, int p)
   {
      int upToDateSum=0;
      int cost1 = costMatrix[0][cities[0]];
      upToDateSum=upToDateSum+cost1;      
      for(int i=0; i<p; i++)
      { 
         int cost = costMatrix[cities[i]][cities[i+1]];
         upToDateSum= upToDateSum+cost;
      }
      return upToDateSum;         
   
   }
   
   public double estimate(int[] cities, int j)
   {
      double rowMin = Double.POSITIVE_INFINITY;
      int row = cities[j];
      for(int i=row; i<cities.length; i++)
      {
         if (costMatrix[row][i]< rowMin)
         {
            rowMin = costMatrix[row][i];
         }
      }
      return rowMin;

   }
   
   public void swap(int[] a, int i, int j)
   {
      int temp = a[i];
      a[i]=a[j];
      a[j]=temp;
   }
 
}
