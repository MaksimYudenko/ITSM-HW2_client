package by.itsm.courses.hw2c;

import com.fasterxml.jackson.databind.ObjectMapper;
import by.itsm.courses.hw2c.model.Request;
import by.itsm.courses.hw2c.model.Response;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.function.Supplier;

@Configuration
public class Config {

    @Bean
    public ObjectMapper mapper() {
        return new ObjectMapper();
    }

    @Bean
    public String name() {
        return "Tom";
    }

    @Bean
    public Supplier<Request> supplier(String name) {
        String message = "Hello, server";
        return () -> new Request(name, message);
    }

    @Bean
    public Runnable executor(ObjectMapper mapper, Supplier<Request> supplier) {
        return () -> {
            try {
                Socket socket = new Socket("localhost", 54321);
                DataOutputStream writer = new DataOutputStream(socket.getOutputStream());
                DataInputStream reader = new DataInputStream(socket.getInputStream());
                Request request = supplier.get();
                String requestString = mapper.writeValueAsString(request);
                writer.writeUTF(requestString);
                writer.flush();
                String responseString = reader.readUTF();
                Response response = mapper.readValue(responseString, Response.class);
                System.out.println(response.getMessage());
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        };
    }

}