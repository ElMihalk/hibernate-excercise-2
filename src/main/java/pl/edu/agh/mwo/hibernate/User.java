package pl.edu.agh.mwo.hibernate;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String username;

    @Column
    private String joinDate;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "album_id")
    private Set<Album> albums = new HashSet<>();

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "users_photos",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "photo_id")
    )
    Set<Photo> photos = new HashSet<>();

    public Set<Album> getAlbums() {
        return albums;
    }

    public void addAlbum(Album album) {
        albums.add(album);
    }

    public Set<Photo> getPhotos() {
        return photos;
    }

    public void addPhoto(Photo photo) {
        photos.add(photo);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(String joinDate) {
        this.joinDate = joinDate;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Album{" + "id=" + id + ", username=" + username + ", joinDate" + joinDate + '}';
    }
}
