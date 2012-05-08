package de.dis2011.core;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import de.dis2011.data.Haus;
import de.dis2011.data.Kaufvertrag;
import de.dis2011.data.Makler;
import de.dis2011.data.Mietvertrag;
import de.dis2011.data.Person;
import de.dis2011.data.Wohnung;

/**
 * Klasse zur Verwaltung aller Datenbank-Entitäten.
 * 
 */
@SuppressWarnings("unchecked")
public class ImmoService {
	//Hibernate Session
	private SessionFactory sessionFactory;
	
	public ImmoService() {
		sessionFactory = new Configuration().configure().buildSessionFactory();
	}
	
	/**
	 * Finde einen Makler mit gegebener Id
	 * @param id Die ID des Maklers
	 * @return Makler mit der ID oder null
	 */
	public Makler getMaklerById(int id) {
		Session session = sessionFactory.getCurrentSession();
		
		session.beginTransaction();
		Makler makler = (Makler) session.get(Makler.class, id);
		session.getTransaction().commit();

		return makler;
	}
	
	/**
	 * Finde einen Makler mit gegebenem Login
	 * @param login Der Login des Maklers
	 * @return Makler mit der ID oder null
	 */
	public Makler getMaklerByLogin(String login) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "from Makler as m where m.login = :login";
		
		session.beginTransaction();
		Makler makler = (Makler) session
				.createQuery(hql)
				.setParameter("login", login)
				.uniqueResult()
		;
		session.getTransaction().commit();
		
		return makler;
	}
	
	/**
	 * Gibt alle Makler zurück
	 */
	public Set<Makler> getAllMakler() {
		Session session = sessionFactory.getCurrentSession();
		String hql = "from Makler";
		
		session.beginTransaction();
		List<Makler> maklerList = session.createQuery(hql).list();
		session.getTransaction().commit();
		
		return new HashSet<Makler>(maklerList);
	}
	
	/**
	 * Fügt einen Makler hinzu
	 * @param m Der Makler
	 */
	public void addMakler(Makler m) {
		Session session = sessionFactory.getCurrentSession();
		
		session.beginTransaction();
		session.save(m);
		session.getTransaction().commit();
	}
	
	/**
	 * Löscht einen Makler
	 * @param m Der Makler
	 */
	public void deleteMakler(Makler m) {
		Session session = sessionFactory.getCurrentSession();
		
		session.beginTransaction();
		session.delete(m);
		session.getTransaction().commit();
	}
	
	/**
	 * Finde eine Person mit gegebener Id
	 * @param id Die ID der Person
	 * @return Person mit der ID oder null
	 */
	public Person getPersonById(int id) {
		Session session = sessionFactory.getCurrentSession();
		
		session.beginTransaction();
		Person person = (Person) session.get(Person.class, id);
		session.getTransaction().commit();

		return person;
	}
	
	/**
	 * Fügt eine Person hinzu
	 * @param p Die Person
	 */
	public void addPerson(Person p) {
		Session session = sessionFactory.getCurrentSession();
		
		session.beginTransaction();
		session.save(p);
		session.getTransaction().commit();
	}
	
	/**
	 * Gibt alle Personen zurück
	 */
	public Set<Person> getAllPersons() {
		Session session = sessionFactory.getCurrentSession();
		String hql = "from Person";
		
		session.beginTransaction();
		List<Person> personList = session.createQuery(hql).list();
		session.getTransaction().commit();
		
		return new HashSet<Person>(personList);
	}
	
	/**
	 * Löscht eine Person
	 * @param p Die Person
	 */
	public void deletePerson(Person p) {
		Session session = sessionFactory.getCurrentSession();
		
		session.beginTransaction();
		session.delete(p);
		session.getTransaction().commit();
		
		
	}
	
	/**
	 * Fügt ein Haus hinzu
	 * @param h Das Haus
	 */
	public void addHaus(Haus h) {
		Session session = sessionFactory.getCurrentSession();
		
		session.beginTransaction();
		session.save(h);
		session.getTransaction().commit();
	}
	
	/**
	 * Gibt alle Häuser eines Maklers zurück
	 * @param m Der Makler
	 * @return Alle Häuser, die vom Makler verwaltet werden
	 */
	public Set<Haus> getAllHaeuserForMakler(Makler m) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "from Haus where verwalter = :makler";
		
		session.beginTransaction();
		List<Haus> hausList= session
				.createQuery(hql)
				.setParameter("makler", m)
				.list()
		;
		session.getTransaction().commit();
		
		return new HashSet<Haus>(hausList);
	}
	
	/**
	 * Findet ein Haus mit gegebener ID
	 * @param m Der Makler
	 * @return Das Haus oder null, falls nicht gefunden
	 */
	public Haus getHausById(int id) {
		Session session = sessionFactory.getCurrentSession();
		
		session.beginTransaction();
		Haus haus = (Haus) session.get(Haus.class, id);
		session.getTransaction().commit();
		
		return haus;
	}
	
	/**
	 * Löscht ein Haus
	 * @param p Das Haus
	 */
	public void deleteHouse(Haus h) {
		Session session = sessionFactory.getCurrentSession();
		
		session.beginTransaction();
		session.delete(h);
		session.getTransaction().commit();
	}
	
	/**
	 * Fügt eine Wohnung hinzu
	 * @param w die Wohnung
	 */
	public void addWohnung(Wohnung w) {
		Session session = sessionFactory.getCurrentSession();
		
		session.beginTransaction();
		session.save(w);
		session.getTransaction().commit();
	}
	
	/**
	 * Gibt alle Wohnungen eines Maklers zurück
	 * @param m Der Makler
	 * @return Alle Wohnungen, die vom Makler verwaltet werden
	 */
	public Set<Wohnung> getAllWohnungenForMakler(Makler m) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "from Wohnung where verwalter = :makler";
		
		session.beginTransaction();
		List<Wohnung> wohnungList= session
				.createQuery(hql)
				.setParameter("makler", m)
				.list()
		;
		session.getTransaction().commit();
		
		return new HashSet<Wohnung>(wohnungList);
	}
	
	/**
	 * Findet eine Wohnung mit gegebener ID
	 * @param id Die ID
	 * @return Die Wohnung oder null, falls nicht gefunden
	 */
	public Wohnung getWohnungById(int id) {
		Session session = sessionFactory.getCurrentSession();
		
		session.beginTransaction();
		Wohnung wohnung = (Wohnung) session.get(Wohnung.class, id);
		session.getTransaction().commit();
		
		
		
		return wohnung;
	}
	
	/**
	 * Löscht eine Wohnung
	 * @param w Die Wohnung
	 */
	public void deleteWohnung(Wohnung w) {
		Session session = sessionFactory.getCurrentSession();
		
		session.beginTransaction();
		session.delete(w);
		session.getTransaction().commit();
		
		
	}
	
	
	/**
	 * Fügt einen Mietvertrag hinzu
	 * @param w Der Mietvertrag
	 */
	public void addMietvertrag(Mietvertrag m) {
		Session session = sessionFactory.getCurrentSession();
		
		session.beginTransaction();
		session.save(m);
		session.getTransaction().commit();
		
		
	}
	
	/**
	 * Fügt einen Kaufvertrag hinzu
	 * @param w Der Kaufvertrag
	 */
	public void addKaufvertrag(Kaufvertrag k) {
		Session session = sessionFactory.getCurrentSession();
		
		session.beginTransaction();
		session.save(k);
		session.getTransaction().commit();
		
		
	}
	
	/**
	 * Gibt alle Mietverträge zu Wohnungen eines Maklers zurück
	 * @param m Der Makler
	 * @return Alle Mietverträge, die zu Wohnungen gehören, die vom Makler verwaltet werden
	 */
	public Set<Mietvertrag> getAllMietvertraegeForMakler(Makler m) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "from Mietvertrag where wohnung.verwalter = :makler";

		session.beginTransaction();
		List<Mietvertrag> mietvertraegeList = session
				.createQuery(hql)
				.setParameter("makler", m)
				.list()
		;
		session.getTransaction().commit();
		
		return new HashSet<Mietvertrag>(mietvertraegeList);
	}
	
	/**
	 * Gibt alle Kaufverträge zu Wohnungen eines Maklers zurück
	 * @param m Der Makler
	 * @return Alle Kaufverträge, die zu Häusern gehören, dimve vom Makler verwaltet werden
	 */
	public Set<Kaufvertrag> getAllKaufvertraegeForMakler(Makler m) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "from Kaufvertrag where haus.verwalter = :makler";
		
		session.beginTransaction();
		List<Kaufvertrag> kaufvertraegeList = session
				.createQuery(hql)
				.setParameter("makler", m)
				.list()
		;
		session.getTransaction().commit();
		
		return new HashSet<Kaufvertrag>(kaufvertraegeList);
	}
	
	/**
	 * Findet einen Mietvertrag mit gegebener ID
	 * @param id Die ID
	 * @return Der Mietvertrag oder null, falls nicht gefunden
	 */
	public Mietvertrag getMietvertragById(int id) {
		Session session = sessionFactory.getCurrentSession();
		
		session.beginTransaction();
		Mietvertrag mietvertrag = (Mietvertrag) session.get(Mietvertrag.class, id);
		session.getTransaction().commit();
		
		

		return mietvertrag;
	}
	
	/**
	 * Findet alle Mietverträge, die Wohnungen eines gegebenen Verwalters betreffen
	 * @param id Der Verwalter
	 * @return Set aus Mietverträgen
	 */
	public Set<Mietvertrag> getMietvertragByVerwalter(Makler m) {
		return getAllMietvertraegeForMakler(m);
	}
	
	/**
	 * Findet alle Kaufverträge, die Häuser eines gegebenen Verwalters betreffen
	 * @param id Der Verwalter
	 * @return Set aus Kaufverträgen
	 */
	public Set<Kaufvertrag> getKaufvertragByVerwalter(Makler m) {
		return getAllKaufvertraegeForMakler(m);
	}
	
	/**
	 * Findet einen Kaufvertrag mit gegebener ID
	 * @param id Die ID
	 * @return Der Kaufvertrag oder null, falls nicht gefunden
	 */
	public Kaufvertrag getKaufvertragById(int id) {
		Session session = sessionFactory.getCurrentSession();
		
		session.beginTransaction();
		Kaufvertrag kaufvertrag = (Kaufvertrag) session.get(Kaufvertrag.class, id);
		session.getTransaction().commit();
		
		
		
		return kaufvertrag;
	}
	
	/**
	 * Löscht einen Mietvertrag
	 * @param m Der Mietvertrag
	 */
	public void deleteMietvertrag(Mietvertrag m) {
		Session session = sessionFactory.getCurrentSession();
		
		session.beginTransaction();
		session.delete(m);
		session.getTransaction().commit();
	}
	
	/**
	 * Testdaten erzeugen und in db speichern
	 */
	public void addTestData() {
		// makler erzeugen
		Makler m = new Makler();
		m.setName("Max Mustermann");
		m.setAdresse("Am Informatikum 9");
		m.setLogin("max");
		m.setPasswort("max");
		
		addMakler(m);

		// personen erzeugen
		Person p1 = new Person();
		p1.setAdresse("Informatikum");
		p1.setNachname("Mustermann");
		p1.setVorname("Erika");
		
		Person p2 = new Person();
		p2.setAdresse("Reeperbahn 9");
		p2.setNachname("Albers");
		p2.setVorname("Hans");
		
		addPerson(p1);
		addPerson(p2);
		
		// haus erzeugen
		Haus h = new Haus();
		h.setOrt("Hamburg");
		h.setPlz(22527);
		h.setStrasse("Vogt-Kölln-Straße");
		h.setHausnummer("2a");
		h.setFlaeche(384);
		h.setStockwerke(5);
		h.setKaufpreis(10000000);
		h.setGarten(true);
		h.setVerwalter(m);
		
		addHaus(h);
		
		// wohnungen erzeugen
		Wohnung w1 = new Wohnung();
		w1.setOrt("Hamburg");
		w1.setPlz(22527);
		w1.setStrasse("Vogt-Kölln-Straße");
		w1.setHausnummer("3");
		w1.setFlaeche(120);
		w1.setStockwerk(4);
		w1.setMietpreis(790);
		w1.setEbk(true);
		w1.setBalkon(false);
		w1.setVerwalter(m);
		
		Wohnung w2 = new Wohnung();
		w2.setOrt("Berlin");
		w2.setPlz(22527);
		w2.setStrasse("Vogt-Kölln-Straße");
		w2.setHausnummer("3");
		w2.setFlaeche(120);
		w2.setStockwerk(4);
		w2.setMietpreis(790);
		w2.setEbk(true);
		w2.setBalkon(false);
		w2.setVerwalter(m);
		
		addWohnung(w1);
		addWohnung(w2);
		
		// kaufvertrag erzeugen
		Kaufvertrag kv = new Kaufvertrag();
		kv.setHaus(h);
		kv.setVertragspartner(p1);
		kv.setVertragsnummer(9234);
		kv.setDatum(new Date(System.currentTimeMillis()));
		kv.setOrt("Hamburg");
		kv.setAnzahlRaten(5);
		kv.setRatenzins(4);
		
		addKaufvertrag(kv);
		
		// mietvertrag erzeugen
		Mietvertrag mv = new Mietvertrag();
		mv.setWohnung(w1);
		mv.setVertragspartner(p2);
		mv.setVertragsnummer(23112);
		mv.setDatum(new Date(System.currentTimeMillis()-1000000000));
		mv.setOrt("Berlin");
		mv.setMietbeginn(new Date(System.currentTimeMillis()));
		mv.setNebenkosten(65);
		mv.setDauer(36);
		
		addMietvertrag(mv);
	}
}
