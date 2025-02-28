package com.movieflix.mapper;


import com.movieflix.DTOs.StreamingDTO;
import com.movieflix.entity.Streaming;
import org.springframework.stereotype.Component;

@Component
public class StreamingMapper {
    public static Streaming toStreaming(StreamingDTO streamingDTO){
        if (streamingDTO == null) {
            throw new IllegalArgumentException("streamingDTO não pode ser nulo!");
        }

        Streaming streaming = new Streaming();
        if (streamingDTO.getId() != null) {
            streaming.setId(streamingDTO.getId());
        }
        streaming.setName(streamingDTO.getName());

        return streaming;
    }

    public static StreamingDTO toStreamingDTO(Streaming streaming){
        StreamingDTO streamingDTO = new StreamingDTO();
        streamingDTO.setId(streaming.getId());
        streamingDTO.setName(streaming.getName());

        return streamingDTO;
    }
}
