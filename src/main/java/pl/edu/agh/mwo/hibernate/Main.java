package pl.edu.agh.mwo.hibernate;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;
import java.util.Set;

public class Main {

	Session session;

	public static void main(String[] args) {
		Main main = new Main();
//		1. Dodaj użytkowników, albumy, zdjęcia, polubienia.
//		main.addUser(); //4.1.

//		2. Usuń polubienie, zdjęcie, album, użytkownika — tak, aby
//		zaprezentować każdy z poniższych przypadków:
//			1. usunięcie polubienia nie wpłynie na pozostałe elementy,
//			2. usunięcie zdjęcia usunie jego polubienia,
//			3. usunięcie albumu usunie zdjęcia w nim zawarte (wraz z
//			konsekwencjami usunięcia zdjęcia),
//			4. usunięcie użytkownika usunie jego albumy (wraz z
//			konsekwencjami usunięcia albumu) i polubienia.
//		main.removeLike(); //4.2.1
		main.removePhoto();

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
		Album album = new Album("Album 5", "Góry");
		Photo photo = new Photo("Giewont", "01.01.2025");

		album.addPhoto(photo);
		user.addAlbum(album);
		user.addPhoto(photo);
		photo.addUser(user);

		Transaction transaction = session.beginTransaction();
		session.save(user);
		transaction.commit();
	}

	public void removeLike() {
        Query<User> query = session.createQuery("from User u where u.id = 1", User.class);
        User user = query.uniqueResult();

        Photo photo_1 = null;
        for (Photo photo : user.getPhotos()) {
            photo_1 = photo;
            break;
        }
        photo_1.removeUser(user);
        Transaction transaction = session.beginTransaction();
        session.update(user);
        transaction.commit();
    }

	public void removePhoto(){
		Query<Photo> query =  session.createQuery("from Photo p where p.id = 9", Photo.class);
		Photo photo = query.uniqueResult();
		Transaction transaction = session.beginTransaction();
		session.delete(photo);
		transaction.commit();
	}



	public void close() {
		session.close();
		HibernateUtil.shutdown();
	}
}
