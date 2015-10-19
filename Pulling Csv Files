import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
public class Golf
{
  

  public static void main(String[] args)
  {
    int[][] results = new int[100][75];
    Scanner scanIn = null;
    int Rowc = 0;
    int Row = 0;
    int colc = 0;
    int col = 0;
    String Inputln = "";
    double xnum = 0;
    String xfilelacation;

    xfilelacation = "/dcs/13/csunbw/Project/Oak - Hole 1.csv";
    
    try{
      scanIn = new Scanner(new BufferedReader(new FileReader(xfilelacation)));
      while (scanIn.hasNextLine()){
	      Inputln = scanIn.nextLine();
	      String[] InArray = Inputln.split(",");
      	for (int x = 0; x < InArray.length; x++){
      	  System.out.println(Integer.parseInt(InArray[x]));
      	}
      	Rowc++;
      }
    } catch (Exception e){
      System.out.println(e);
    }
  }
}


