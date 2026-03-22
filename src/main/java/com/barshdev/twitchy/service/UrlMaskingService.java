package com.barshdev.twitchy.service;

import com.barshdev.twitchy.exceptions.InvalidArgException;
import com.barshdev.twitchy.exceptions.NotFoundException;
import com.barshdev.twitchy.repository.UrlHashMappingRepository;
import com.barshdev.twitchy.repository.UrlRepository;
import com.barshdev.twitchy.repository.model.URLDataModel;
import com.barshdev.twitchy.repository.model.UrlHashMappingDataModel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.time.OffsetDateTime;
import java.util.Base64;

@AllArgsConstructor
@NoArgsConstructor
@Service
public class UrlMaskingService {
    @Value("${base.domain.url}")
    private String baseDomainUrl;
    @Autowired
    private UrlRepository urlRepository;
    @Autowired
    private UrlHashMappingRepository urlHashMappingRepository;

    public String maskUrl(String url) {
        UrlValidator.isValidUrl(url);

        String hashCode = DigestUtils.md5DigestAsHex(url.getBytes());
        String referenceId = checkIfShortenUrlAvailable(hashCode);

        if(referenceId != null) {
            return baseDomainUrl + EncodingUtils.encode(referenceId);
        }

        URLDataModel urlDataModel = urlRepository.save(URLDataModel.builder().originalUrl(url).createDate(OffsetDateTime.now())
                        .isActive(true).build());

        urlHashMappingRepository.save(UrlHashMappingDataModel.builder().hashCode(hashCode)
                        .urlDataModel(urlDataModel).build());

        String shortenUrl = EncodingUtils.encode(urlDataModel.getId().toString());

        return baseDomainUrl + shortenUrl;
    }

    public String checkIfShortenUrlAvailable(String hashCode) {
        UrlHashMappingDataModel urlHash = urlHashMappingRepository.findByHashCode(hashCode);
        if(urlHash != null) {
            return urlHash.getUrlDataModel().getId().toString();
        }
        return null;
    }

    public String getOriginalUrl(String resourceName) {
        Long referenceId = null;
        try {
            referenceId = Long.valueOf(EncodingUtils.decode(resourceName));
        } catch (NumberFormatException e) {
            throw new InvalidArgException("Invalid resource name: " + resourceName);
        }
        URLDataModel urlDataModel = urlRepository.findById(referenceId).orElse(null);
        if (urlDataModel == null) {
            throw new NotFoundException("Shorten Url");
        }
        return urlDataModel.getOriginalUrl();
    }
}
