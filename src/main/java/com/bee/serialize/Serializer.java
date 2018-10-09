package com.bee.serialize;

public interface Serializer {

    <T> byte[] serialize(T object);

    <T> T deserialize(byte[] bytes);
}
