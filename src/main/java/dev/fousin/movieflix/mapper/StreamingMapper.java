package dev.fousin.movieflix.mapper;

import dev.fousin.movieflix.controller.request.StreamingRequest;
import dev.fousin.movieflix.controller.response.StreamingResponse;
import dev.fousin.movieflix.entity.Streaming;
import lombok.experimental.UtilityClass;

@UtilityClass
public class StreamingMapper {
    public static Streaming toStreaming(StreamingRequest request){
        return Streaming.builder()
                .name(request.name())
                .build();
    }

    public static StreamingResponse toStreamingResponse(Streaming streaming){
        return StreamingResponse.builder()
                .id(streaming.getId())
                .nome(streaming.getName())
                .build();
    }
}
