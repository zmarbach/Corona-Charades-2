package com.aws.corona.charades.repositories;

import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class UuidGeneratorImpl implements UuidGenerator{
    @Override
    public UUID generateUUID() {
        return UUID.randomUUID();
    }
}
