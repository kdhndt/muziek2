package be.vdab.muziek2.repositories;

import be.vdab.muziek2.domain.Album;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlbumRepository extends JpaRepository<Album, Long> {

}
