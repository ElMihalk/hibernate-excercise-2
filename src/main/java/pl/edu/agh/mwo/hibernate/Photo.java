package pl.edu.agh.mwo.hibernate;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "photo")
public class Photo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String date;

    @ManyToMany(mappedBy = "photos", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Set<User> users = new HashSet<>();

    public Photo(String name, String date) {
        this.name = name;
        this.date = date;
    }

    public Photo() {
    }

    public void removeUser(User user){
        this.users.remove(user);
        user.getPhotos().remove(this);
    }

    public Set<User> getUsers() {
        return users;
    }

    public void addUser(User user) {
        users.add(user);
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Photo{" + "id=" + id + ", name=" + name + ", date" + date + '}';
    }


}
