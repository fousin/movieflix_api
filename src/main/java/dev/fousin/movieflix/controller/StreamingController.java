package dev.fousin.movieflix.controller;

import dev.fousin.movieflix.controller.request.StreamingRequest;
import dev.fousin.movieflix.controller.response.StreamingResponse;
import dev.fousin.movieflix.mapper.StreamingMapper;
import dev.fousin.movieflix.service.StreamingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movieflix/streaming")
@RequiredArgsConstructor
public class StreamingController {
    private final StreamingService service;
    
    @GetMapping
    public ResponseEntity<List<StreamingResponse>> getAll() {
        List<StreamingResponse> list = service.findAll();
        return ResponseEntity.ok(list);
    }

    @PostMapping
    public ResponseEntity<StreamingResponse> save(@RequestBody StreamingRequest request) {
        StreamingResponse savedStreaming = service.save(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedStreaming);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StreamingResponse> getById(@PathVariable Long id) {
        return service.getById(id)
                .map(streaming -> ResponseEntity.ok(StreamingMapper.toStreamingResponse(streaming)))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
