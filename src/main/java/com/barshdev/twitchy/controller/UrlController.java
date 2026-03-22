package com.barshdev.twitchy.controller;

import com.barshdev.twitchy.controller.request.UrlRequest;
import com.barshdev.twitchy.service.UrlMaskingService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@NoArgsConstructor
public class UrlController {
    @Autowired
    private UrlMaskingService urlMaskingService;

    @GetMapping("/{maskedUrl}")
    public ResponseEntity<Void> redirectTemporary(@PathVariable String maskedUrl) {
        String originalUrl = urlMaskingService.getOriginalUrl(maskedUrl);
        return ResponseEntity.status(HttpStatus.MOVED_PERMANENTLY)
                .header("Location", originalUrl)
                .build();
    }

    @PostMapping("/mask")
    public ResponseEntity<String> maskedUrl(@RequestBody UrlRequest request) {
        String markedUrl = urlMaskingService.maskUrl(request.getUrl());
        return ResponseEntity.ok(markedUrl);
    }

}
