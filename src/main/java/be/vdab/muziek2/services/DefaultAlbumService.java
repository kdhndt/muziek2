package be.vdab.muziek2.services;

import be.vdab.muziek2.domain.Album;
import be.vdab.muziek2.repositories.AlbumRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
class DefaultAlbumService implements AlbumService {
    private final AlbumRepository albumRepository;

    public DefaultAlbumService(AlbumRepository albumRepository) {
        this.albumRepository = albumRepository;
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Album> findById(long id) {
        return albumRepository.findById(id);
    }
}
