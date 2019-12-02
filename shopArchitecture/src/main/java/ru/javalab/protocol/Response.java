package ru.javalab.protocol;

// объект, который относится к протоколу

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

// например, HttpServletResponse
public class Response<T> {
    private T data;

    public Response(T data) {
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public static <E> Response<E> build(E data) {
        return new Response<>(data);
    }

    public static String getJson(Response response){
        ObjectMapper mapper = new ObjectMapper();
        try {
            System.out.println(mapper.writeValueAsString(response));
            return mapper.writeValueAsString(response.getData());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }
}
