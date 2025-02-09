package pl.edu.agh.mwo.hibernate;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "albums")
public class Album {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String description;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "photo_id")
    private Set<Photo> albums = new HashSet<>();

    public Set<Photo> getAlbums() {
        return albums;
    }

    public void addPhoto(Photo photo) {
        albums.add(photo);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Album{" + "id=" + id + ", name=" + name + ", description=" + description + '}';
    }

//    CREATE TABLE albums (id INTEGER PRIMARY KEY, name TEXT, description TEXT);
//    INSERT INTO albums (id, name, description) VALUES (1, 'Album 1', 'Zwierzęta');
//    INSERT INTO albums (id, name, description) VALUES (2, 'Album 2', 'Rośliny');
//    INSERT INTO albums (id, name, description) VALUES (3, 'Album 3', 'Zabytki');
//    INSERT INTO albums (id, name, description) VALUES (4, 'Album 4', 'Widoki');
}
