package com.movieflix.service;

import com.movieflix.entity.Streaming;
import com.movieflix.repository.StreamingRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StreamingService {
    private final StreamingRepository repository;

    public StreamingService(StreamingRepository repository) {
        this.repository = repository;
    }

    public List<Streaming> findAll(){
        return repository.findAll();
    }

    public Streaming findById(Long id){
        Optional<Streaming> streamingFound = repository.findById(id);
        return streamingFound.orElse(null);

    }

    public Streaming save(Streaming streaming){
        return repository.save(streaming);
    }

    public void delete (Long id){
        repository.deleteById(id);
        System.out.println("Streaming com o id: " + id + " deletado com sucesso.");
    }
}
