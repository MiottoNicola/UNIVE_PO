package Avogador_es.TrashDisposal;

import java.util.Scanner;

class Trash {
	private final String name;

	public Trash(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
	
	public void recycle() {
		System.out.println("Recycling " + name);
	}
	
	public void dispose() {
		System.out.println("Disposing " + name);
	}
}

class NonRecyclableTrash extends Trash {
    public NonRecyclableTrash(String name){
        super(name);
    }

    public void recycle() {
        throw new UnsupportedOperationException();
	}

}

class TrashDisposal {
    public static void dispose(Trash[] trash) {
        for (Trash t : trash) {
            try {
                t.recycle();
            } catch (UnsupportedOperationException e) {
                System.out.println(t.getName() + " cannot be recycled");
            }
            t.dispose();
        }
    }
}

class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		Trash[] trash = new Trash[n];
		for (int i = 0; i < n; i++) {
			int s = sc.nextInt();
			String name = sc.next();
			trash[i] = s == 0 ? new Trash(name) : new NonRecyclableTrash(name); 
		}
		
		TrashDisposal.dispose(trash);
		
		sc.close();
	}
}