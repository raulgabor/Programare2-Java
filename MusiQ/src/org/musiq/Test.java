package org.musiq;

public class Test {

	public static void main(String[] args) {		
		Registru registru = new Registru(10, 10, 5, 5, 5);
		
		System.out.println("Afizeaza melodii: ******************************************");
		for(Melodie m: registru.getMelodii()) {
			System.out.println(m);
		}

	System.out.println("Afizeaza persoane: ******************************************");
	for(Persoana p: registru.getPersoane()) {
		System.out.println(p);
	}
	
	System.out.println("Afizeaza albume: ******************************************");
	for(Album a: registru.getAlbume()) {
		System.out.println(a);
	}
	
	System.out.println("Afizeaza artisti: ******************************************");
	for(Artist ar: registru.getArtisti()) {
		System.out.println(ar);
	}
	
	System.out.println("Afizeaza utilizatori: ******************************************");
	for(Utilizator u: registru.getUtilizatori()) {
		System.out.println(u);
	}
  }
}
