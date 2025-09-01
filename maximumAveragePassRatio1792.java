class Solution {
    class Pair{
       int pass;
       int total;
       public Pair(int pass, int total){
        this.pass=pass;
        this.total=total;
       }
       private double gain(){
         return ((double)(pass+1)/(total+1))-((double)pass/total);
       }
       private double ratio(){
        return (double)pass/total;
       }
       
    }
    public double maxAverageRatio(int[][] classes, int extraStudents) {

        PriorityQueue<Pair> pq=new PriorityQueue<>((a,b)->Double.compare(b.gain(),a.gain()));
        for(int[] curClass: classes){
            pq.add(new Pair(curClass[0],curClass[1]));
        }
        while(extraStudents>0){
            Pair p=pq.poll();
            p.pass++;
            p.total++;
            pq.add(new Pair(p.pass,p.total));
            extraStudents--;
        }
        double totalPassRatio=0.0;
        while(!pq.isEmpty()){
          totalPassRatio+=pq.poll().ratio();
        }
        return totalPassRatio/classes.length;
    }
}


/*
 mistakes i made:
   1>understood the problem incorrectly-initially thought of adding all the extra students to one class.
   2>then i added students one by one to the classes which has less passratio - but this will lead me to incorrect solution 
*/

/*
 Approach for the problem :
    

     idea is to add extraStudent to the class in which adding a student to that class will give me the max gain among adding student to all other classes
     
     i.e
     the class which give me max Gain =(pass+1/gain+1)-(pass/gain);-> adding one student to it 
                                --gain
                +1 -> new PassRatio
     --- passRatio



 */