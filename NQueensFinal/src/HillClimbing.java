import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class HillClimbing {
	public static int numberofqueens=0;
	public static int typeofhillclimbing=0;
	public static int optiontoproceed=0;
	public static int noofsteps=0;
	public static int noofsuccesses=0;
	public static int nooffailures=0;
	public static int noofattempts=0;
	public static int noofsuccessteps=0;
	public static int nooffailuresteps=0;
	public static int noofrestarts=0;
	public static int noofrandomrestarts=0;
	public static int limit=0;
	public static Queen[] initialboard = new Queen[numberofqueens];
	
		  public static Queen[] generateBoard() {
		        Queen[] startBoard = new Queen[numberofqueens];
		        Random rndm = new Random();
		        for(int i=0; i<numberofqueens; i++){
		            startBoard[i] = new Queen(rndm.nextInt(numberofqueens), i);
		            
		        }
		        
		        return startBoard;
		    }
		  public static void printBoard(Queen[] state) {
			  int[][] tempBoard = new int[numberofqueens][numberofqueens];
		        for (int i=0; i<numberofqueens; i++) {
		            //Get the positions of Queen from the Present board and set those positions as 1 in temp board
		            tempBoard[state[i].getRow()][state[i].getColumn()]=1;
		        }
		        System.out.println();
		        for (int i=0; i<numberofqueens; i++) {
		            for (int j= 0; j < numberofqueens; j++) {
		                System.out.print(tempBoard[i][j] + " ");
		            }
		            System.out.println();
		        }  
		  }
		  public static int getheuristic(Queen[] state) {
			  int heuristic = 0;
		        for (int i = 0; i< state.length; i++) {
		            for (int j=i+1; j<state.length; j++ ) {
		                if (state[i].ifConflict(state[j])) {
		                    heuristic++;
		                }
		            }
		        }
		        return heuristic;
		  }
		
		  public static void main(String[] args) {
			  System.out.println("Enter number of Queens");
			  Scanner sc= new Scanner(System.in);
			  numberofqueens=sc.nextInt();
			  initialboard=generateBoard();
			  noofattempts=100;
			  System.out.print("Enter the type of hill climbing");
			  System.out.print("\n 1. Simple Hill climbing \n 2. Hill climbing with sideways \n 3. Random restart hill climbing\n 4. Random restart hill climbing with sidewyas");
			  typeofhillclimbing=sc.nextInt();
			  System.out.print("\n1.Report Generation \n 2.Printing of 4 iterations");
			  optiontoproceed=sc.nextInt();
			  if(optiontoproceed==2)
				  noofattempts=4;
			  
				  if(typeofhillclimbing==1) {
				  
				  for(int i=0;i<noofattempts;i++) {
					 simplehillclimbing();	 
				  }
				  if(optiontoproceed==1) {
				  float percentage=(float)noofsuccesses/noofattempts;
				  System.out.print("Success percentage="+percentage*100);
				  System.out.print("\nFailure percentage="+(1-percentage)*100);
				  
				  double successaverage=(double)noofsteps/noofattempts;
				  double failureaverage=(double)(noofsteps-noofsuccesses)/noofattempts;
				  System.out.print("\nAverage number of steps in success="+successaverage);
				  System.out.print("\nAverage number of steps in failure="+failureaverage);
				  }
			  }
			  if(typeofhillclimbing==2) {
				  for(int i=0;i<noofattempts;i++) {
					 simplehillclimbingwithsideways();	 
				  }
				  if(optiontoproceed==1) {
				  limit=0;
				  float percentage=(float)noofsuccesses/noofattempts;
				  System.out.print("Success percentage="+percentage*100);
				  System.out.print("\nFailure percentage="+(1-percentage)*100);
				  double successaverage=(double)noofsteps/noofattempts;
				  double failureaverage=(double)((nooffailuresteps+nooffailures)-noofsuccesses)/noofattempts;
				  System.out.print("\nAverage number of steps in success="+successaverage);
				  System.out.print("\nAverage number of steps in failure="+failureaverage);
				  }
			  }
			  if(typeofhillclimbing==3) {
				  for(int i=0;i<noofattempts;i++) {
						 randomrestarthillclimbing(); 
					  }
				  if(optiontoproceed==1) {
				  float percentage=(float)noofsuccesses/noofattempts;
				  System.out.print("Success percentage="+percentage*100);
				  System.out.print("\nFailure percentage="+(1-percentage)*100);
				  double successaverage=(double)noofsteps/noofattempts;
				  //double failureaverage=(double)nooffailuresteps/noofattempts;
				  double restartsaverage=(double)noofrestarts/noofattempts;
				  System.out.print("\nAverage number of steps in success="+successaverage);
				  //System.out.print("\nAverage number of steps in failure="+failureaverage);
				  System.out.print("\nAverage Number of restarts="+ restartsaverage);
				  }
			  }
			  if(typeofhillclimbing==4) {
				  for(int i=0;i<noofattempts;i++) {
						 randomrestarthillclimbingwithsideways(); 
					  }
				  if(optiontoproceed==1) {
				  limit=0;
				  float percentage=(float)noofsuccesses/noofattempts;
				  System.out.print("Success percentage="+percentage*100);
				  System.out.print("\nFailure percentage="+(1-percentage)*100);
				  double successaverage=(double)noofsteps/noofattempts;
				  //double failureaverage=(double)nooffailuresteps/noofattempts;
				  double restartsaverage=(double)(noofrandomrestarts)/noofattempts;
				  System.out.print("\nAverage number of steps in success="+successaverage);
				  //System.out.print("\nAverage number of steps in failure="+failureaverage);
				  System.out.print("\nAverage Number of restarts="+ restartsaverage);
				  }
			  }
			  }
			  
			  
					  
		  
		  
		private static void randomrestarthillclimbingwithsideways() {
			// TODO Auto-generated method stub
			Queen[] presentboard = generateBoard();
			//int limit=0;
			while(true) {
				Queen[] nextboard=getBestBoard(presentboard);
				int nextheuristic=getheuristic(nextboard);
				int presentheuristic=getheuristic(presentboard);
				
				if(nextheuristic==0) {
					System.out.print("##########Solution Found##########");
					printBoard(nextboard);
					noofsuccesses++;
					noofsuccessteps+=noofsteps;
					break;
				}
				else if(nextheuristic<presentheuristic || (nextheuristic==presentheuristic && limit<100)) {
					if(nextheuristic<presentheuristic)
						limit=0;
					else
						limit++;
					for(int i=0;i<presentboard.length;i++)
						presentboard[i]=nextboard[i];
					if(optiontoproceed==2) {
						System.out.print("\n\n");
						printBoard(presentboard);
					}
					noofsteps++;
					
				}
					else {
						presentboard=generateBoard();
						if(optiontoproceed==2) {
							System.out.print("\n\n");
							printBoard(presentboard);
						}
						noofrestarts++;
						
					}
				}
			if(noofrestarts>0)
				noofrandomrestarts++;
		}
		private static void randomrestarthillclimbing() {
			// TODO Auto-generated method stub
			Queen[] presentboard = generateBoard();
			
			while(true) {
				Queen[] nextboard=getBestBoard(presentboard);
				int nextheuristic=getheuristic(nextboard);
				int presentheuristic=getheuristic(presentboard);
				
				if(nextheuristic==0) {
					System.out.print("##########Solution Found##########");
					printBoard(nextboard);
					noofsuccesses++;
					noofsuccessteps+=noofsteps;
					break;
				}
				else if(nextheuristic<presentheuristic) {
					presentheuristic=nextheuristic;
					noofsteps++;
					for(int i=0;i<presentboard.length;i++)
						presentboard[i]=nextboard[i];
					if(optiontoproceed==2) {
						System.out.print("\n\n");
						printBoard(presentboard);
					}
				}
				else {
					presentboard=generateBoard();
					if(optiontoproceed==2) {
						printBoard(presentboard);
						System.out.print("\n\n");
					}
					noofrestarts++;
					
				}
			}
			if(noofrestarts>0)
				noofrandomrestarts++;
			 	
		}
		private static void simplehillclimbingwithsideways() {
			// TODO Auto-generated method stub
			Queen[] presentboard = generateBoard();
			//int limit=0;
			while(true) {
				Queen[] nextboard=getBestBoard(presentboard);
				int nextheuristic=getheuristic(nextboard);
				int presentheuristic=getheuristic(presentboard);
				
				if(nextheuristic==0) {
					System.out.print("##########Solution Found##########");
					printBoard(nextboard);
					noofsuccesses++;
					noofsuccessteps+=noofsteps;
					break;
				}
				else if(nextheuristic<presentheuristic || (nextheuristic==presentheuristic && limit<100)) {
					if(nextheuristic<presentheuristic)
						limit=0;
					else
						limit++;
					for(int i=0;i<presentboard.length;i++)
						presentboard[i]=nextboard[i];
					if(optiontoproceed==2) {
						printBoard(presentboard);
						System.out.print("\n\n");
					}
					noofsteps++;
					
				}
					else {
					System.out.print("###########Reached Plateaux##########");
					printBoard(nextboard);
					nooffailures++;
					nooffailuresteps+=noofsteps;
					break;
					}
				}
			}
			
			
		
		private static void simplehillclimbing() {
			// TODO Auto-generated method stub
			Queen[] presentboard = generateBoard();
			
			while(true) {
				Queen[] nextboard=getBestBoard(presentboard);
				int nextheuristic=getheuristic(nextboard);
				int presentheuristic=getheuristic(presentboard);
				
				if(nextheuristic==0) {
					
					System.out.print("##########Solution Found##########");
					printBoard(nextboard);
					noofsuccesses++;
					
					noofsuccessteps+=noofsteps;
					break;
				}
				else if(nextheuristic<presentheuristic) {
					
					presentheuristic=nextheuristic;
					noofsteps++;
					for(int i=0;i<presentboard.length;i++)
						presentboard[i]=nextboard[i];
					if(optiontoproceed==2) {
						System.out.print("\n\n");
						printBoard(presentboard);
					}
				}
				else {
					System.out.print("###########Reached Plateaux##########");
					printBoard(nextboard);
					nooffailures++;
					nooffailuresteps+=noofsteps;
					break;
				}
			}
			
			
		}
	
		private static Queen[] getBestBoard(Queen[] presentboard) {
			// TODO Auto-generated method stub
			ArrayList<Queen[]> nextSuccesors=getNextSuccesors(presentboard);
			ArrayList<Queen[]> bestSuccesors=new ArrayList<Queen[]>();
			Random r = new Random();
			int minimum = numberofqueens * numberofqueens;
			int current;
			for(int i=0;i<nextSuccesors.size();i++) {
				current=getheuristic(nextSuccesors.get(i));
				if(current<minimum) {
					minimum=current;
					bestSuccesors.clear();
					bestSuccesors.add(nextSuccesors.get(i));
				}
				else if(current==minimum)
					bestSuccesors.add(nextSuccesors.get(i));
					
			}
			return bestSuccesors.get(r.nextInt(bestSuccesors.size()));
		}
		private static ArrayList<Queen[]> getNextSuccesors(Queen[] presentboard) {
			// TODO Auto-generated method stub
			 ArrayList<Queen[]> successors = new ArrayList<>();
		        int prev_row;
		        int prev_column;

		        for(int i=0; i < numberofqueens; i++){

		            prev_row = presentboard[i].getRow();
		            prev_column = presentboard[i].getColumn();

		            while(presentboard[i].walk_up(numberofqueens)){
		                ArrayList<Queen> newqueenlist = new ArrayList<>();
		                Queen[] b = new Queen[numberofqueens];
		                for(int j=0;j<presentboard.length;j++){
		                    newqueenlist.add(new Queen(presentboard[j].getRow(), presentboard[j].getColumn()));
		                }
		                for(int k=0;k<newqueenlist.size();k++) {
		                	b[k]=newqueenlist.get(k);
		                }
		                successors.add(b);
		            }
		            presentboard[i].setRow(prev_row);
		            presentboard[i].setColumn(prev_column);
		            while(presentboard[i].walk_down(numberofqueens)){
		                ArrayList<Queen> newqueenlist = new ArrayList<>();
		                Queen[] b = new Queen[numberofqueens];
		                for(int j=0;j<presentboard.length;j++){
		                    newqueenlist.add(new Queen(presentboard[j].getRow(), presentboard[j].getColumn()));
		                }
		                for(int k=0;k<newqueenlist.size();k++) {
		                	b[k]=newqueenlist.get(k);
		                }
		                successors.add(b);
		            }
		            presentboard[i].setRow(prev_row);
		            presentboard[i].setColumn(prev_column);

		          
		        }
		        return successors;
		        
		}
	}


