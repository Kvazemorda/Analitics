public class test {
    public static void main(String[] args) {
        Person person = new Person();
        person.setAge(10);
        changePersonAge(person);
        System.out.println(person.getAge());
        DoSomthing doSomthing = new DoSomthing(person);
        System.out.println(person.getAge());

    }

    private static void changePersonAge(Person person){
        person.setAge(25);
    }
}
