package Avogador_es.Stack;
import java.util.Scanner;

class StackElement<X> {
    private X element;
    private StackElement<X> next;

    StackElement() { this.element = null; this.next = null; }
    StackElement(X e) { this.element = e; this.next = null; }

    public X getElement() { return this.element; }
    public StackElement<X> getNext() { return this.next; }

    public void setElement(X e) { this.element = e; }
    public void setNext(StackElement<X> n) { this.next = n; }
}

class Stack<X> {
    private StackElement<X> head;

    public Stack() { this.head = null; }

    public void push(X e){
        StackElement<X> newElement = new StackElement<X>(e);
        if(head == null){
            head = newElement;
        }else{
            newElement.setNext(head);
            head = newElement;
        }
    }

    public X pop(){
        if(head == null)
            return null;

        X element = head.getElement();
        head = head.getNext();
        return element;
    }

    public X peek(){
        return head.getElement();
    }

    public String print() {
        String result = "[ ";
        
        StackElement<X> cursor = head;
        while (cursor != null) {
            result += cursor.getElement() + " ";
            cursor = cursor.getNext();
        }
        
        return result + "]";
    }
}

class Main {
    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        for (int i = 0; i < n; i++) {
            String operation = scanner.next();
            switch (operation) {
                case "+":
                    int number = scanner.nextInt();
                    stack.push(number);
                    break;
                case "-":
                    stack.pop();
                    break;
                case "*":
                    System.out.println(stack.peek());
                    break;
            }
        }
        scanner.close();
    }
}