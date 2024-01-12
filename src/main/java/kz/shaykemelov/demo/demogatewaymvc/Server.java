package kz.shaykemelov.demo.demogatewaymvc;

import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Server
{
    @Bean
    public HttpServer httpServer() throws IOException
    {
        final var httpServer = HttpServer.create(new InetSocketAddress(8081), 0);
        httpServer.createContext("/", exchange ->
        {
            final byte[] bytes = "Hello".getBytes();
            exchange.sendResponseHeaders(200, bytes.length);
            exchange.getResponseBody().write(bytes);
            exchange.getResponseBody().close();
        });

        httpServer.setExecutor(null);
        httpServer.start();

        return httpServer;
    }
}
