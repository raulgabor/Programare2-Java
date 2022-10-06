package org.musiq;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Registru {
	
	private List<Melodie> melodii = new ArrayList<Melodie>();
	private List<Persoana> persoane = new ArrayList<Persoana>();
	private List<Album> albume = new ArrayList<Album>();
	private List<Artist> artisti = new ArrayList<Artist>();
	private List<Utilizator> utilizatori = new ArrayList<Utilizator>(); 
	
	public List<Utilizator> getUtilizatori() {
		return utilizatori;
	}

	public void setUtilizatori(List<Utilizator> utilizatori) {
		this.utilizatori = utilizatori;
	}

	public List<Album> getAlbume() {
		return albume;
	}

	public void setAlbume(List<Album> albume) {
		this.albume = albume;
	}

	private void generateRandomMelodii(Integer nrMelodii) {
		Double durataMin;
		for(int i = 1; i <= nrMelodii; i++) {
			durataMin = (double) Math.round(ThreadLocalRandom.current().nextDouble(0.10, 10.00) * 100) / 100.0;
			melodii.add(new Melodie(i, "Melodie_" + i, "Gen_" + i, durataMin));
		}
	}
	
	public List<Persoana> getPersoane() {
		return persoane;
	}

	public void setPersoane(List<Persoana> persoane) {
		this.persoane = persoane;
	}

	private void generateRandomPersoane(Integer nrPersoane) {
		Date dataNasterii;
		for(int i = 1; i <= nrPersoane; i++) {
			dataNasterii = new Date(ThreadLocalRandom.current().nextInt() * 1000L);
			//SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
			//String strDataNasterii = df.format(dataNasterii);
			persoane.add(new Persoana(i, "NumePersoana_" + i, dataNasterii, "Nationalitatea_" + i));
		}
	}


	public List<Melodie> getMelodii() {
		return melodii;
	}


	public void setMelodii(List<Melodie> melodii) {
		this.melodii = melodii;
	}


	public Registru(List<Melodie> melodii) {
		super();
		this.melodii = melodii;
	}
	
	private void generateRandomAlbume(Integer nrAlbume) {
		Integer anLansare;
		Random randomAn = new Random();
		for(int i = 1; i <= nrAlbume; i++) {
			anLansare = 1000 + randomAn.nextInt(2021);
			albume.add(new Album(i, "NumeAlbum_" + i, anLansare, getMelodii()));
		}
	}
	
	public List<Artist> getArtisti() {
		return artisti;
	}

	public void setArtisti(List<Artist> artisti) {
		this.artisti = artisti;
	}
	
	private void generateRandomArtisti(Integer nrArtisti) {
		Integer anDebut;
		Date dataNasterii;
		Random randomAn = new Random();
		for(int i = 1; i <= nrArtisti; i++) {
			anDebut = 1000 + randomAn.nextInt(2021);
			dataNasterii = new Date(ThreadLocalRandom.current().nextInt() * 1000L);
			artisti.add(new Artist(i, "NumePersoana_" + i, dataNasterii, "Nationalitatea_" + i, "NumeScena_" + i, anDebut, getAlbume()));
		}
	}
	
	
	
	private void generateRandomUtilizatori(Integer nrUtilizatori) {
		Date dataNasterii;
		for(int i = 1; i <= nrUtilizatori; i++) {
			dataNasterii = new Date(ThreadLocalRandom.current().nextInt() * 1000L);
			utilizatori.add(new Utilizator(i, "NumePersoana_" + i, dataNasterii, "Nationalitatea_" + i, "Nickname_" + i, getMelodii(), getAlbume()));
		}
	}

	public Registru(Integer nrMelodii, Integer nrPersoane, Integer nrAlbume, Integer nrArtisti, Integer nrUtilizatori) {
		generateRandomMelodii(nrMelodii);
		generateRandomPersoane(nrPersoane);
		generateRandomAlbume(nrAlbume);
		generateRandomArtisti(nrArtisti);
		generateRandomUtilizatori(nrUtilizatori);
	}


	public Registru() {
		generateRandomMelodii(10);
		generateRandomPersoane(10);
		generateRandomAlbume(5);
		generateRandomArtisti(5);
		generateRandomUtilizatori(5);
	}

	
	
}
