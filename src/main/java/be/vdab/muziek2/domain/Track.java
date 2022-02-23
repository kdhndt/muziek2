package be.vdab.muziek2.domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Embeddable;
import java.time.LocalTime;
import java.util.Objects;

@Embeddable
@Access(AccessType.FIELD)
public class Track {
    private String naam;
    private LocalTime tijd;

/*    public Track(String naam, LocalTime tijd) {
        this.naam = naam;
        this.tijd = tijd;
    }

    protected Track() {
    }*/

    public String getNaam() {
        return naam;
    }

    public LocalTime getTijd() {
        return tijd;
    }

    /*    @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Track)) return false;
            Track track = (Track) o;
            return naam.equalsIgnoreCase(track.naam);
        }

        @Override
        public int hashCode() {
            return Objects.hash(naam.toLowerCase());
        }*/

    @Override
    public boolean equals(Object object) {
        return object instanceof Track track && naam.equalsIgnoreCase(track.naam);
    }

    @Override
    public int hashCode() {
        return naam.toUpperCase().hashCode();
    }
}
