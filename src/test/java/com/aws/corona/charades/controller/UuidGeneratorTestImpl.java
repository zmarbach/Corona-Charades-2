package com.aws.corona.charades.controller;

import com.aws.corona.charades.repositories.UuidGenerator;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class UuidGeneratorTestImpl implements UuidGenerator {
    @Override
    public UUID generateUUID() {
        return new UUID(1,1);
    }
}
