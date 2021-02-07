package ru.gbf.resourceserver.errors;

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String name, Long id) {
        super(name + " с айди = " + id + " не найден");
    }
}
