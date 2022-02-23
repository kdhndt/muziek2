package be.vdab.muziek2.domain;

import javax.persistence.*;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "albums")
public class Album {
    @Id
    private long id;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "artiestId")
    private Artiest artiest;
    private String naam;
    @ElementCollection
    @CollectionTable(name = "tracks", joinColumns = @JoinColumn(name = "albumId"))
    private Set<Track> tracks = new LinkedHashSet<>();

/*    public Album(Artiest artiest, String naam) {
        this.artiest = artiest;
        this.naam = naam;
    }

    protected Album() {
    }*/

    public long getId() {
        return id;
    }

    public Artiest getArtiest() {
        return artiest;
    }

    public String getNaam() {
        return naam;
    }

    public Set<Track> getTracks() {
        return Collections.unmodifiableSet(tracks);
    }
}