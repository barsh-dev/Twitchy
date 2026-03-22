package com.barshdev.twitchy.repository.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.OffsetDateTime;

@Entity
@Table(name = "urls")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class URLDataModel {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "url_seq")
    @SequenceGenerator(name = "url_seq", sequenceName = "url_sequence", initialValue = 100, allocationSize = 1)
    @Column(name = "id")
    Long id;
    @Column(name = "original_url", nullable = false)
    String originalUrl;
    @Column(name = "create_date", nullable = false)
    OffsetDateTime createDate;
    @Column(name = "is_active")
    Boolean isActive;
}
