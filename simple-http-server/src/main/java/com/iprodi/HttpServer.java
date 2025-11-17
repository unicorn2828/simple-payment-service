package com.iprodi;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class HttpServer {

    public static void main(String[] args) throws IOException {

        // создание сокета
        ServerSocket serverSocket = new ServerSocket(8080);
        System.out.println("Server started at http://localhost:8080");

        // подключение клиента
        Socket socket = serverSocket.accept();
        System.out.println("Client connected");

        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

        while (!in.ready()) ;

        // чтение запроса
        while (in.ready()) {
            System.out.println(in.readLine());
        }

        // создание ответа
        String response = """
        <h1>Hello from http server</h1>
        <h2>It works</h2>
        """;
        out.write("HTTP/1.1 404 OK\r\n");
        out.write("Content-Type: text/html; charset=UTF-8\r\n");
        out.write("Content-Length: " + response.length() + "\r\n");
        out.write("\r\n");
        out.write(response);
        out.flush();
        socket.close();
    }

}
