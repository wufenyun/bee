package com.bee.serialize;

public interface Serializer {

    <T> byte[] encode(T object);

    <T> T decode(byte[] bytes);
}
