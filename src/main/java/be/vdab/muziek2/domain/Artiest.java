package be.vdab.muziek2.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "artiesten")
public class Artiest {
    @Id
    private long id;
    String naam;

/*    public Artiest(String naam) {
        this.naam = naam;
    }

    protected Artiest() {
    }*/

    public long getId() {
        return id;
    }

    public String getNaam() {
        return naam;
    }
}
