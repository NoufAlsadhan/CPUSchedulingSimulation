/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package osproject;

/**
 *
 * @author noufy
 */
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
public class OSProject {

    /**
     * @param args the command line arguments
     
     */
    
    static boolean exist=false;
    static int n=0;
     private static ArrayList<PCB> PCB=new ArrayList<PCB>();
   static ArrayList<PCB> atP=new ArrayList<PCB>();//Sorted with FCFS
     private static  String gantt[]=new String[1000];// to keep track of the processes for the gantt chart
    public static void main(String[] args) throws IOException {
     Scanner input=new Scanner(System.in);

        do{
            
            System.out.println("Welcome! Select a service:");
            System.out.println("1.Enter process’ information");
            System.out.println("2.Report detailed information about each process and different scheduling criteria");
            System.out.println("3.Exit the program");
            n=input.nextInt();
            
            switch(n){
                case 1:{
                    
                    System.out.println("To add a new process prompt the following");
                    System.out.print("Please enter number of processes: ");
                    int number =input.nextInt();
                    PCB=new ArrayList<PCB>(number);
                    for (int i=0;i<number ;i++){
                        System.out.println("Please enter process"+(i+1)+" priority(from 1 to 5)");
                        int priority=input.nextInt();
                        while(priority<1||priority>5){
                            System.out.println("Wrong input,enter the right priority:");
                            priority=input.nextInt();
                        }
                        System.out.println("Please enter process "+(i+1)+" arrival time");
                        int arrivalTime=input.nextInt();
                        while(arrivalTime<0){
                            System.out.println("Wrong input,enter positive arrival time:");
                            arrivalTime=input.nextInt();
                        }
                        System.out.println("Please enter process "+(i+1)+" cpu burst");
                        int cpuBurst=input.nextInt();
                        while(cpuBurst<=0){
                            System.out.println("Wrong input,enter the right cpu burst:");
                            cpuBurst=input.nextInt();
                        }
                        
                        PCB process=new PCB(priority,arrivalTime,cpuBurst);
                        process.remainingTime=cpuBurst;
                       PCB.add(process);
                       exist=true;
                    }

                    Scheduling();
                    break;
                }
                case 2:{
                    Report();
                   break;
                }
                case 3:{
                  System.out.println("Thank you for using our system!");  
                }
            }
            
            
        }while(n!=3);
        
       
    }
    
    public static void Scheduling(){
        int currenttime=0;//We will use for calculating the time
        atP=atsorting(PCB);//To do FCFS
        int finishedP=0;//Finished processes will be inserted in this arrayList
        int count=0;//To keep track of the gantt[] array
        //boolean CpuBusy = false;
        String LastId="";
   
        boolean first=true;
        int pIndex=-1;
        while(finishedP!=atP.size()){//Still process has not finished
            
                 double MIN=9999;
          for (int i=0;i<atP.size();i++){
              
              if(atP.get(i).arrivalTime<=currenttime && atP.get(i).priority<MIN && atP.get(i).remainingTime>0 ){
                  MIN=atP.get(i).priority;
              pIndex=i;
              
              
              }
              
          }
          
          if(pIndex!=-1){//There is a process found to schedule
              if(atP.get(pIndex).startTime==-1)//its the first time for the process to execute
              if(first){
                  atP.get(pIndex).startTime=currenttime;//Since no process before it(do not consider CS)
             
              }
              else{ 
                  atP.get(pIndex).startTime=currenttime+1;//Because of the CS

              }
             
              if(LastId!=atP.get(pIndex).id && !first){
                  currenttime++;
              }
              
              LastId=atP.get(pIndex).id;
              gantt[count]=atP.get(pIndex).id;
              
              
             
              count++;
              atP.get(pIndex).remainingTime=atP.get(pIndex).remainingTime-1;
              currenttime++; 
              if(atP.get(pIndex).remainingTime==0){
                  finishedP++;
                 atP.get(pIndex).terminationTime=currenttime; 
                 

              }
              
              if(first){
             first=false;
                 }
          }
          else{
           currenttime++;
          }
        }
        
       
        for (int i=0;i<atP.size();i++){
            
           atP.get(i).turnAroundTime=atP.get(i).terminationTime-atP.get(i).arrivalTime; 
           atP.get(i).waitingTime=atP.get(i).turnAroundTime-atP.get(i).cpuBurst;
           atP.get(i).responseTime=atP.get(i).startTime-atP.get(i).arrivalTime;
            
            
        }
        
        
       }
    public static ArrayList<PCB> atsorting(ArrayList<PCB> Q){//FCFS
        
        for(int i=0;i<Q.size();i++){
            for(int j=0;j<Q.size()-(i+1);j++){
                if(Q.get(j) != null && (Q.get(j+1)!=null))
                    if(Q.get(j).arrivalTime>Q.get(j+1).arrivalTime){
                        PCB temp = Q.get(j);
			Q.set(j, Q.get(j + 1));
			Q.set(j + 1, temp);
                        
                    }
            }
            
        }
        
      return Q;  
}
    
   public static void Report()throws IOException{
       if(exist==false){
           System.out.println("No processes yet.");
       }
       else{
           
       System.out.println("The detalied information about each process:");
       for (int i=0;i<atP.size();i++){

           System.out.println(atP.get(i).toString());
       }
       
       System.out.print("[");
       String g="[";
       for(int i=0;i<gantt.length;i++){
         if(gantt[i]==null)  
             break;
         System.out.print(gantt[i]);
         g=g+gantt[i];
         if(gantt[i]!=gantt[i+1]&&gantt[i+1]!=null){
             System.out.print(" | CS ");
             g=g+" | CS ";}
         if(gantt[i+1]!=null){
         System.out.print(" | ");
         g=g+" | ";}
       }
       System.out.println("]");
        g=g+"]";

       
       try {
        	FileWriter FW = new FileWriter("Report1.txt",false);

			processInfo(atP,FW);
			FW.write("\n"+g);
			FW.close();
      	}catch (Exception e) {
         	System.out.println("An error occurred.");
    	   	e.printStackTrace();
      	} 
       
       
       
       
       
       }
   } 
    
    public static void processInfo(ArrayList<PCB> Q,FileWriter FW)throws IOException{
            FW.write("The detalied information about each process:\n");
            double Tat=0;
            double Wt=0;
            double Rt=0;
            for (int i=0;i<Q.size();i++){
                    FW.write("\n"+(i+1)+"."+atP.get(i).toString()+"\n");
Tat=Tat+atP.get(i).turnAroundTime;
Wt=Wt+atP.get(i).waitingTime;
Rt=Rt+atP.get(i).responseTime;
                }
            
            double avgTat=Tat/Q.size();
            double avgWt=Wt/Q.size();
            double avgRt=Rt/Q.size();
            
            
            System.out.println("Average turn around time="+avgTat);
            System.out.println("Average waiting time="+avgWt);
            System.out.println("Average response time="+avgRt);
            
            FW.write("\nAverage turn around time="+avgTat+"\n");
            FW.write("Average waiting time="+avgWt+"\n");
            FW.write("Average response time="+avgRt+"\n");
            
            
            
            }
        }
    


