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

//		Funkcje realizujące instrukcje z punktu 4.
//		main.addUser(); //4.1.
//		main.removeLike(); //4.2.1
//		main.removePhoto(); //4.2.2
//		main.removeAlbum(); //4.2.3
//		main.removeUser(); //4.2.4

		main.printUsers();
		
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
		for (User user : photo.getUsers()){
			photo.removeUser(user);
			session.update(user);
		}
		session.delete(photo);
		transaction.commit();
	}

	public void removeAlbum(){
		Query<Album> query = session.createQuery("from Album a where a.id = 5", Album.class);
		Album album = query.uniqueResult();
		Transaction transaction = session.beginTransaction();
		session.delete(album);
		transaction.commit();
	}

	public void removeUser(){
		Query<User> query = session.createQuery("from User u where u.id = 3");
		User user = query.uniqueResult();
		Transaction transaction = session.beginTransaction();
		session.delete(user);
		transaction.commit();
	}



	public void close() {
		session.close();
		HibernateUtil.shutdown();
	}
}
