
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Timer;

class myHashTable{

   

        static class Student{
            String name;
            static int size=500;
            public Student(String n){
                name=n;
            }
            @Override
            public int hashCode() {
                int hash =name.hashCode();
                if(hash<0){
                    hash*=-1;
                }
                return hash%size;
        }
    }

    public Student[] students = new Student[500];
    int size=500;
    int numUsed=0;
    
    public void resize(){
        Student[] newStudents = new Student[students.length*2];
        size=students.length*2;
        Student.size*=2;
        for(int i =0;i<students.length;i++){
            newStudents[i]=students[i];
        }
        students=newStudents;
    }
    public Student find(String name){
        Student student = new Student(name);
        int index=student.hashCode();
        for(int i=size;i>=500;i/=2){
         if(index-i<0){
            continue;
         }
         if(students[index%i].name.equals(name)){
           return students[index%i];
         }
        }
       for(int i=0;i<students.length;i++) {
        if(students[i]!=null&&students[i].name.equals(name)){
            return students[i];
        }
       }
    
        return student;
    }
    
    public void add(Student s){
        numUsed++;
        if(numUsed==students.length-1){
            resize();
        }
        int hash=s.hashCode();
        if(students[hash]==null){
            students[hash]=s;
            return;
        }
        for(int i=hash;i<students.length;i++){
            if(students[i]==null){
                students[i]=s;
                return;
            }
        }
    }
    public static void main(String[] args) throws FileNotFoundException{
        Scanner sc = new Scanner(new File("StudentsNames.txt"));
        myHashTable table= new myHashTable();
            for(int i=0;sc.hasNext();i++){
            Student s =new Student(sc.nextLine());
            table.add(s);
        }
        double sum=0;
        int index=0;
        for(Student s:table.students){
            index++;
            if(s==null){
                continue;
            }
            double t1=System.currentTimeMillis();
            Student searched=table.find(s.name);
            double t2=System.currentTimeMillis();
            sum+=t2-t1;
           
        }
        System.out.print(sum/(double)(table.numUsed));
    }
}

