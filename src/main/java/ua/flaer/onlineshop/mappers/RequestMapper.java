package ua.flaer.onlineshop.mappers;

public interface RequestMapper<A,B> {

    A mapFrom(B b);
}
