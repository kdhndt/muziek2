package be.vdab.muziek2.controller;

import be.vdab.muziek2.domain.Album;
import be.vdab.muziek2.domain.Track;
import be.vdab.muziek2.exceptions.AlbumNietGevondenException;
import be.vdab.muziek2.services.AlbumService;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.EntityLinks;
import org.springframework.hateoas.server.ExposesResourceFor;
import org.springframework.hateoas.server.TypedEntityLinks;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@ExposesResourceFor(Album.class)
@RestController
@RequestMapping("albums")
class AlbumController {
    private final AlbumService albumService;
    private final TypedEntityLinks.ExtendedTypedEntityLinks<Album> links;

    AlbumController(AlbumService albumService, EntityLinks links) {
        this.albumService = albumService;
        this.links = links.forType(Album.class, Album::getId);
    }

    //todo: vraag Hans
    //simpelweg een Album of EntityModel<Album> als returnwaarde geven zorgt voor problemen wanneer je met associaties werkt? (SQL dialect error)
    //ben je in dat geval verplicht van steeds een record of inner class aan te maken de info als DTO terug te geven?

    @GetMapping("{id}")
    EntityModel<AlbumArtiest> getAlbum(@PathVariable long id) {
        return albumService.findById(id).map(album ->
                        //werkt niet: EntityModel.of(album.getNaam(), album.getArtiest())
                        EntityModel.of(new AlbumArtiest(album))
                                //albums/1
                                .add(links.linkToItemResource(album))
                                //albums/1/tracks
                                .add(links.linkForItemResource(album).slash("tracks").withRel("tracks")))
                .orElseThrow(AlbumNietGevondenException::new);
    }

    @ExceptionHandler(AlbumNietGevondenException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    void albumNietGevonden() {
    }

    @GetMapping("{id}/tracks")
    CollectionModel<Track> getTracks(@PathVariable long id) {
        //Track is geen Entity, dus een EntityModel<Track> is overbodig. CollectionModel is voldoende om als JSON weer te geven, maak een CM van je tracks attribuut
        //Een EntityModel is nodig wanneer je links wil toevoegen aan elke Track
        return albumService.findById(id).map(album -> CollectionModel.of(album.getTracks())
                        .add(links.linkForItemResource(album).slash("tracks").withRel("tracks"))
                        .add(links.linkToItemResource(album)/*.withRel("album")*/))
                .orElseThrow(AlbumNietGevondenException::new);
    }

    //private inner (nested) class -- improve code readability and maintainability
    //plaats dit steeds voor de sluit accolade
    //we kunnen dit hier plaatsen aangezien deze class enkel hier gebruikt wordt
    private static class AlbumArtiest {
        private final String album;
        private final String artiest;

        AlbumArtiest(Album album) {
            this.album = album.getNaam();
            this.artiest = album.getArtiest().getNaam();
        }

        public String getAlbum() {
            return album;
        }

        public String getArtiest() {
            return artiest;
        }
    }

    //record kan ook:
/*    private record AlbumArtiest(String naam, String artiest) {
        AlbumArtiest(Album album) {
            this(album.getNaam(), album.getArtiest().getNaam());
        }
    }*/

}
