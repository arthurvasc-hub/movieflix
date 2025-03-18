package com.movieflix.controller;


import com.movieflix.DTOs.StreamingDTO;
import com.movieflix.entity.Streaming;
import com.movieflix.mapper.StreamingMapper;
import com.movieflix.service.StreamingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movieflix/streaming")
@Tag(name = "Streaming", description = "Endpoints para gerenciamento dos serviços de streaming")
public class StreamingController {

    private final StreamingService service;

    public StreamingController(StreamingService service) {
        this.service = service;
    }

    @Operation(summary = "Listar todos os streamings", description = "Retorna todos os serviços de streaming cadastrados")
    @GetMapping()
    public ResponseEntity<List<StreamingDTO>> findAll(){
        List<StreamingDTO> streamings = service.findAll()
                .stream()
                .map(StreamingMapper::toStreamingDTO)
                .toList();
        return ResponseEntity.ok(streamings);
    }

    @Operation(summary = "Buscar streaming por ID", description = "Retorna um serviço de streaming específico pelo seu ID")
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

    @Operation(summary = "Criar novo streaming", description = "Cria um novo serviço de streaming no sistema")
    @PostMapping("/create")
    public ResponseEntity<StreamingDTO> createCategory(@Valid @RequestBody StreamingDTO request){
        Streaming requestToStreaming = StreamingMapper.toStreaming(request);
        Streaming streamingSaved = service.save(requestToStreaming);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(StreamingMapper.toStreamingDTO(streamingSaved));
    }

    @Operation(summary = "Deletar streaming por ID", description = "Deleta um serviço de streaming específico pelo seu ID")
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

