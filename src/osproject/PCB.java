/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package osproject;

/**
 *
 * @author nouf
 */
public class PCB {
    
    public String id;
    public double priority;
    public double arrivalTime;
    public double cpuBurst;
    public double startTime;
    public double terminationTime;
    public double turnAroundTime;
    public double waitingTime;
    public double responseTime;
    public double remainingTime;
    public boolean visited;
    static public int counter=0;
    public double cs;
    
    public PCB(double p,double a, double c){
counter++;
id="P"+counter;
priority=p;
arrivalTime=a;
cpuBurst=c;
startTime=-1;
terminationTime=-1;
turnAroundTime=0;
waitingTime=0;
responseTime=0;
visited=false;
}

     public String getID() {
 return id;
 }

 public double getcpuburst() {
 return cpuBurst;
 }
 public void setcpuburst( double b) {
cpuBurst = b;
 }
 public double getArrivalTime() {
 return arrivalTime;
 }
 public void setArrivalTime (double a) {
 arrivalTime = a;
 }

 public double getPriority() {
 return priority;
 }
 public void setPriority( int p) {
 priority = p;
 }

 public double getStartTime() {
 return startTime;
 }
 public void setStartTime(double st) {
 startTime = st;
 }
 public double getTermTime() {
 return terminationTime;
 }
 public void setEN_time( double tt) {
 terminationTime = tt;
 }
 public double getTurnAroundTime() {
 return turnAroundTime;
 }
 public void setTurnAroudTime( double tat) {
 turnAroundTime = tat;
 }
 public double getWaitingTime() {
 return waitingTime;
 }
 public void setWaitingTime( int wt) {
waitingTime = wt;
 }
 public double getResponseTime() {
 return responseTime;
 }
 public void setResponseTime( int rt) {
 responseTime = rt;
 }
 public double getRemTime() {
 return remainingTime;
 }
 public void setRemTime( double rmt) {
 remainingTime = rmt;
 }
 
    /**
     *
     * @return
     */
    @Override
    public String toString(){
    return "Process id:"+id+
            "| Priority:"+priority+
            "| Cpu burst:"+cpuBurst+
            "| Arrival time:"+arrivalTime+
            "| Start time:"+startTime+
            "| Termination time:"+terminationTime+
            "| Turn around time:"+turnAroundTime+
            "| Waiting time:"+waitingTime+
            "| Response time:"+responseTime
    +"| CS:"+cs;
}
}
    


