import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.lang.Math;
/* Current Subroutines
	Main 
	Greencsv takes the csv file and enters the data into the results array
	Slopecsv takes the csv file and enters the data into the results array
	Print will print the current Results array
	Pin will look for all possible pins based on the slope at the point on the green
	FiveFootSearch takes the value given to it within Pin and looks at the surrounding 5 feet 
	ThreeFootSearch takes the value given to it within Pin and looks at the surrounding 3 feet 
	GreenArea find the area of the csv file that is a green
	Hardness will work out which of the sourding areas to look at
	Calculator works out the hardness of the pin
*/
public class Golf
{
	public static int[][][] results = new int[75][100][5]; //1 is slope 0 is green 2 will be the scratch Hardness rating 3 is a possible pin location 4 is  how hard each pin is compared to the hardess pin on the green
	public static String hole = "";
	public static void main(String[] args){		
		///System.out.println("Input Hole");
		///Scanner in = new Scanner(System.in);
		///String Hole = in.nextLine(); 
		Slopecsv();
		Greencsv();
		Pin();
		Print();
		
		//GreenArea();
		

	}//\Main
	public static void Greencsv(){
		Scanner scanIn = null;
		int Rowc = 0;
		String Inputln = "";
		double xnum = 0;
		String xfilelacation;

		///xfilelacation = "/home/werdnasemloh/Documents/cs310_Project/Project/GreenHole" + hole + ".csv";
		xfilelacation = "/home/werdnasemloh/Documents/cs310_Project/Project/GreenHole1.csv";


		try{ //brings in the slope information
			scanIn = new Scanner(new BufferedReader(new FileReader(xfilelacation)));
			while (scanIn.hasNextLine()){
				Inputln = scanIn.nextLine();
				String[] InArray = Inputln.split(",");
				for (int x = 0; x < InArray.length; x++){
					results[x][Rowc][0] = Integer.parseInt(InArray[x]);
				}
				Rowc++;
			}
		} catch (Exception e){
			System.out.println(e);
		}
	}//\Greencsv
	public static void Slopecsv(){
		Scanner scanIn = null;
		int Rowc = 0;
		String Inputln = "";
		double xnum = 0;
		String xfilelacation;
		///xfilelacation = "/home/werdnasemloh/Documents/cs310_Project/Project/SlopeHole" + hole + ".csv";
		xfilelacation = "/home/werdnasemloh/Documents/cs310_Project/Project/SlopeHole1.csv";
		
		try{ //brings in the slope information
			scanIn = new Scanner(new BufferedReader(new FileReader(xfilelacation)));
			while (scanIn.hasNextLine()){
				Inputln = scanIn.nextLine();
				String[] InArray = Inputln.split(",");
				for (int x = 0; x < InArray.length; x++){
					results[x][Rowc][1] = Integer.parseInt(InArray[x]);
				}
				Rowc++;
			}
		} catch (Exception e){
			System.out.println(e);
		}
	}//\Slopecsv
	public static void Print(){
		for (int x = 0; x < 100; x++){
			for (int y = 0; y < 75; y++){		
					System.out.print(results[y][x][4] + ",");
			}
			System.out.println(":");
		}
	}//\print
	public static void Pin(){
		int counter = 0		//counts the number of possible pins
		int Max = 0;		//Keeps track of the hardest pin
		for (int x = 0; x < 100; x++){
			for (int y = 0; y < 75; y++){
				if (results[y][x][0] == 1 && results[y][x][1] < 2){ // if on the green and have a slope less than 2 I.E 1
					int Found = FiveFootSearch(x,y);
					if (Found == 1){
						results[y][x][3] = 1;
						results[y][x][2] = (int) (Hardness(x,y));
						if (results[y][x][2] > Max){
							Max = results[y][x][2];
						}
						counter++;
						//System.out.println("Added");
					}
				}
			}
		}
		for (int x = 0; x < 100; x++){
			for (int y = 0; y < 75; y++){
				results[y][x][4] = (results[y][x][2]*100)/Max;
			}
		}
		System.out.println(counter);
	}//\Pin
	public static int FiveFootSearch(int x, int y){
		int Found = 1;
		//System.out.println(x + " , " + y);
		for (int I = x-5; I < x+6; I++){
			for (int J = y-5; J < y+6; J++){
				//System.out.println(I + " , " + J + " , " + results[J][I][0]);
				if (results[J][I][0] == 0 && results[J][I][1] > 3){
					Found = 0;				
				}
				else{
					Found = ThreeFootSearch(x,y);				
				}		
			}		
		}
		return Found;
	}//\FiveFootSearch
	public static int ThreeFootSearch(int x, int y){
		int Found = 1;
		//System.out.println(x + " , " + y);
		for (int I = x-3; I < x+4; I++){
			for (int J = y-3; J < y+3; J++){
				//System.out.println(I + " , " + J + " , " + results[J][I][0]);
				if (results[J][I][0] == 0){
					Found = 0;				
				}
				if (results[J][I][1] > 2){
					Found = 0;				
				}		
			}		
		}
		return Found;
	}
	public static void GreenArea(){
		int Area = 0;
		for (int x = 0; x < 100; x++){
			for (int y = 0; y < 75; y++){
				if (results[y][x][0] == 1){
					Area++;				
				}
			}
		}
		System.out.println(Area + " Square feet of green area");
	}
	public static double Hardness(int x, int y){
		double Hard = 0;
		if (x<25){
			if (y<25){
				for (int I = 0; I < x+26; I++){
					for (int J = 0; J < y+26; J++){
						Hard = Hard + Calculator(x,y,I,J);
					}
				}
			}else if (y>49){
				for (int I = 0; I < x+26; I++){
					for (int J = y-25; J < 75; J++){
						Hard = Hard + Calculator(x,y,I,J);
					}
				}
			}else{
				for (int I = 0; I < x+26; I++){
					for (int J = y-25; J < y+26; J++){
						Hard = Hard + Calculator(x,y,I,J);
					}
				}
			}
		}else if (x>74){
			if (y<25){
				for (int I = x-25; I < 100; I++){
					for (int J = 0; J < y+26; J++){
						Hard = Hard + Calculator(x,y,I,J);
					}
				}
			}else if (y>49){
				for (int I = x-25; I < 100; I++){
					for (int J = y-25; J < 75; J++){
						Hard = Hard + Calculator(x,y,I,J);
					}
				}
			}else{
				for (int I = x-25; I < 100; I++){
					for (int J = y-25; J < y+26; J++){
						Hard = Hard + Calculator(x,y,I,J);
					}
				}
			}
		}else{
			if (y<25){
				for (int I = x-25; I < x+26; I++){
					for (int J = 0; J < y+26; J++){
						Hard = Hard + Calculator(x,y,I,J);
					}
				}
			}else if (y>49){
				for (int I = x-25; I < x+26; I++){
					for (int J = y-25; J < 75; J++){
						Hard = Hard + Calculator(x,y,I,J);
					}
				}
			}else{
				for (int I = x-25; I < x+26; I++){
					for (int J = y-25; J < y+26; J++){
						Hard = Hard + Calculator(x,y,I,J);
					}
				}
			}
		}
		return Hard;
	}//\Hardness
	public static double Calculator(int x, int y, int I, int J){
		double Hard = 0;
		double Distance = 0;
		Distance = ((x-I)*(x-I))+((y-J)*(y-J));
		Distance = Math.sqrt(Distance);
		if (results[J][I][0] == 1){
			Distance = 100-(3*Distance);
		}else{
			Distance = 125-(3*Distance);
		}
		Distance = Distance/100;
		Hard = results[J][I][1]*Distance;
		return Hard;
	}
}
