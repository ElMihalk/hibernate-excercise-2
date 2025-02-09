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
}
