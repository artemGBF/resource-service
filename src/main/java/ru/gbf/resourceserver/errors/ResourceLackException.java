package ru.gbf.resourceserver.errors;

public class ResourceLackException extends RuntimeException {

    public ResourceLackException() {
        super("Данный товар закончился на складе :(");
    }

    public ResourceLackException(int a) {
        super("Данного товара в количестве " + a + "шт. нет на складе :(");
    }

    public ResourceLackException(long id, int a) {
        super("Товара с " + id + " в количестве " + a + "шт. нет на складе :(");
    }
}
