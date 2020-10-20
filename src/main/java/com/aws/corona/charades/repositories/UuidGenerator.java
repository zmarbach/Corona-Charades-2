package com.aws.corona.charades.repositories;

import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public interface UuidGenerator {
    UUID generateUUID();
}
