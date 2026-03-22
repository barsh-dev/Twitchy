package com.barshdev.twitchy.repository.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "url_hash_mapping")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class UrlHashMappingDataModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Long id;

    @Column(name = "url_hash", unique = true)
    String hashCode;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "url_id")
    URLDataModel urlDataModel;
}
