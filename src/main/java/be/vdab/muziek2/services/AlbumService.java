package be.vdab.muziek2.services;

import be.vdab.muziek2.domain.Album;

import java.util.Optional;

public interface AlbumService {
    //het Album vinden is voldoende, je hebt toegang tot alle tracks via het attribuut
    Optional<Album> findById(long id);
}
