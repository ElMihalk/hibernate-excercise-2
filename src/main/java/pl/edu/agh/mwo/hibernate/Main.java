package pl.edu.agh.mwo.hibernate;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class Main {

	Session session;

	public static void main(String[] args) {
		Main main = new Main();
		main.printUsers();
		// tu wstaw kod aplikacji
		
		main.close();
	}

	public Main() {
		session = HibernateUtil.getSessionFactory().openSession();
	}

	public void printUsers(){
		Query<User> query = session.createQuery("from User", User.class);
		List<User> users = query.list();

		System.out.println("###Users: ");
		for (User user : users){
			System.out.println(user);
			System.out.println("     ###Albums: ");
			for (Album album : user.getAlbums()){
				System.out.print("     ");
				System.out.println(album);
				System.out.println("         ###Photos: ");
				for (Photo photo : album.getPhotos()){
					System.out.print("          ");
					System.out.println(photo);
				}
			}
		}

    }

	public void addUser(){
		User user = new User("JohnSmith12", "30.12.2024");
		Transaction transaction = session.beginTransaction();
		session.save(user);
		transaction.commit();
	}

	public void close() {
		session.close();
		HibernateUtil.shutdown();
	}
}
