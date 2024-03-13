package Avogador_es.MethodToString;

abstract class Person {
    private String name;

    public Person( String name ) { this.name = name; }
    public String toString() { return name; }
}

class Student extends Person {
    public Student(String name) { super(name); }
}

class Researcher extends Person {
    public Researcher(String name) { super(name); }

    @Override
    public String toString(){ return super.toString() + ", PhD"; }
}

class Professor extends Person {
    public Professor(String name) { super(name); }

    @Override
    public String toString(){ return "Prof. " + super.toString(); }
}