package com.movieflix.controller;


import com.movieflix.DTOs.StreamingDTO;
import com.movieflix.entity.Streaming;
import com.movieflix.mapper.StreamingMapper;
import com.movieflix.service.StreamingService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movieflix/streaming")
public class StreamingController {

    private final StreamingService service;

    public StreamingController(StreamingService service) {
        this.service = service;
    }

    @GetMapping()
    public ResponseEntity<List<StreamingDTO>> findAll(){
        List<StreamingDTO> streamings = service.findAll()
                .stream()
                .map(StreamingMapper::toStreamingDTO)
                .toList();
        return ResponseEntity.ok(streamings);

    }

    @GetMapping("/{id}")
    public ResponseEntity<StreamingDTO> findById(@PathVariable Long id){
        Streaming streamingById = service.findById(id);

        if(streamingById != null) {
            StreamingDTO streaming = StreamingMapper.toStreamingDTO(streamingById);
            return ResponseEntity.status(HttpStatus.FOUND)
                    .body(streaming);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(null);
    }

    @PostMapping("/create")
    public ResponseEntity<StreamingDTO> createCategory(@Valid @RequestBody StreamingDTO request){
        Streaming requestToStreaming = StreamingMapper.toStreaming(request);
        Streaming streamingSaved = service.save(requestToStreaming);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(StreamingMapper.toStreamingDTO(streamingSaved));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id){
        Streaming streamingById = service.findById(id);
        if(streamingById != null){
            service.delete(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT)
                    .build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .build();
    }


}
