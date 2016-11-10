import javax.swing.*;
import java.awt.Dimension;
import java.awt.event.*;
import java.util.Arrays;
import javax.swing.JFileChooser;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.PrintWriter;
import java.io.File;
import java.io.FileNotFoundException;
import javax.swing.JScrollPane;
public class GUIBox_TSP_BB extends JFrame implements ActionListener 
{

//this program adds on to the GUI already created previously, to be able to search a name in the file 

   //instantiate variables
   JPanel myPanel;
	JButton myButton_perm;
   JLabel myLabel;
   JTextArea myTextArea_perm;
   JTextArea myTextArea_cost;
   JTextArea myTextArea_time;
   JScrollPane scroll_cost;
   JScrollPane scroll_time;
   JScrollPane scroll_perm;
   JFileChooser myChooser;

   JLabel myLabel_cost;
   JLabel myLabel_perm;
   JLabel myLabel_time;
   TSP_BB myTSP;
   JButton myButton_file;
   JLabel myLabel_text;


  
    

	public GUIBox_TSP_BB()
	{
      //creates the myPerm object from Scramble class and initializes the count as 0
		myTSP = new TSP_BB();
      
      setSize(600,500);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		myPanel = new JPanel();
      
      //Created new buttons for the user to click
		myButton_perm = new JButton("Find Permutations");
      myButton_perm.addActionListener(this);
		myButton_perm.setBounds(150,250,150,40); 
      
      myButton_file = new JButton("File");
      myButton_file.addActionListener(this);
		myButton_file.setBounds(320,250,150,40); 
      
      myTextArea_perm = new JTextArea();
      //myTextArea_perm.setBounds(50,100,300,100);
      scroll_perm = new JScrollPane(myTextArea_perm);
      scroll_perm.setBounds(50,100,300,100);
      
      myTextArea_cost = new JTextArea();
      //myTextArea_cost.setBounds(400,100,50,50);
      scroll_cost = new JScrollPane(myTextArea_cost);
      scroll_cost.setBounds(400,100,75,50);
      
      myTextArea_time = new JTextArea();
      //myTextArea_time.setBounds(500,100,50,50);
      scroll_time = new JScrollPane(myTextArea_time);
      scroll_time.setBounds(500,100,75,50);
      
      //created a label for the text area
      myLabel_perm = new JLabel("Permutations");
      myLabel_perm.setBounds(50,50,100,50);
      
      //created new label for counter
      myLabel_cost = new JLabel("Cost");
      myLabel_cost.setBounds(400,50,100,50);
      
      //created blank label to show the count of each word that is permutated
      myLabel_time = new JLabel("Time");
      myLabel_time.setBounds(500,50,100,50);
      		
      //added all the characteristics to the panel
		myPanel.setLayout(null);
		myPanel.add(myButton_perm);
      myPanel.add(scroll_perm);
      myPanel.add(scroll_cost);
      myPanel.add(scroll_time);
      myPanel.add(myLabel_cost);
      myPanel.add(myLabel_perm);
      myPanel.add(myLabel_time);
      myPanel.add(myButton_file);

		add(myPanel);
		setVisible(true);					
	}

	public void actionPerformed(ActionEvent evt)
   {
   
      if (evt.getSource()==myButton_file)
      {   
         try
         {
            JFileChooser myChooser = new JFileChooser(); //create a JFileChooser so user can pick which file to look in
            int returnVal = myChooser.showOpenDialog(null); 
            File myFile_Name = myChooser.getSelectedFile(); //points to the file that the user wants to read
            Scanner in = new Scanner(myFile_Name); //creates a new scanner in the file
            int n=in.nextInt(); //create a while loop so that scanner reads entire txt file
            
            System.out.print(Integer.toString(n));
            int[][]matrix = new int[n][n];
            for(int row=0; row<n;row++)
            {
               for(int col=0; col<n;col++)
               {
                  matrix[row][col]=in.nextInt();
                  //System.out.println(Integer.toString(matrix[row][col]));
               }
            }
        
   
         }
 
      catch(FileNotFoundException e)
      {
         System.out.println("File Not Found");
        
      }
      }

      if (evt.getSource()==myButton_perm) //if the button perm gets clicked
      {
      
         int[] num_array = myTSP.getMyArray(); //turns the string into a charater
         long startTime = System.nanoTime();
         myTSP.perm(num_array, 0);//performs the method of perm from Scramble class
         long duration = System.nanoTime() - startTime;
         double seconds = ((double)duration / 1000000000);
         //System.out.println(Double.toString(seconds));
         myTextArea_time.setText(Double.toString(seconds));
         myTextArea_perm.setText(Arrays.toString(myTSP.getPath()));
         myTextArea_cost.setText(Double.toString(myTSP.getMin()));
                  
      }
      
   }
}