package dev.fousin.movieflix.service;

import dev.fousin.movieflix.controller.request.StreamingRequest;
import dev.fousin.movieflix.controller.response.StreamingResponse;
import dev.fousin.movieflix.entity.Streaming;
import dev.fousin.movieflix.mapper.StreamingMapper;
import dev.fousin.movieflix.repository.StreamingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StreamingService {
    private final StreamingRepository repository;

    public List<StreamingResponse> findAll() {
        return repository.findAll()
                .stream()
                .map(StreamingMapper::toStreamingResponse)
                .toList();
    }

    public StreamingResponse save(StreamingRequest streamingRequest) {
        Streaming savedStreaming = repository.save(StreamingMapper.toStreaming(streamingRequest));
        return StreamingMapper.toStreamingResponse(savedStreaming);
    }

    public Optional<Streaming> getById(Long id) {
        return repository.findById(id);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

}
