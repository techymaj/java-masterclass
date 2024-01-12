//Deep cloning
//must implement the cloneable interface
class School implements Cloneable{
    private String name;
    private int age;
    public School(String name, int age){
        this.name = name;
        this.age = age;

    }
    public String getName(){
        return name;
    }
    public int getAge(){
        return age;

    }
    public void setName(String name){
        this.name = name;
    }
    public void setAge(int age){
        this.age = age;
    }
    //this where deep cloning is different from shallow cloning
    protected Object clone()throws CloneNotSupportedException{
        School clonedSchool = new School(name, age);
        return clonedSchool;

    }
    public static void main(String []args)throws CloneNotSupportedException{
        School scl1 = new School("SMASK", 20);
        School scl2 = (School) scl1.clone();
        System.out.println("My school name is "+scl1.getName());
        System.out.println("My school name is "+ scl2.getName());
        //now lets change the name using scl2
        scl2.setName("SMACK");
        System.out.println("My school name is "+scl1.getName());//SMASK
        System.out.println("My school name is "+scl2.getName());//SMACK
        //when we change the school name using scl2, the change only happens in scl2 but not in scl1 object
        //now lets change name using scl1
        scl1.setName("BUDDO");
        System.out.println("My school name is "+scl1.getName());
        System.out.println("My school name is "+scl2.getName());
        // when we change the name using scl1, this change will only apply to the scl object but not the scl2


    }
}