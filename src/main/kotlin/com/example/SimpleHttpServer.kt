package com.example

import com.sun.net.httpserver.HttpExchange
import com.sun.net.httpserver.HttpHandler
import com.sun.net.httpserver.HttpServer
import java.io.IOException
import java.net.InetSocketAddress

object SimpleHttpServer {
    @JvmStatic
    @Throws(IOException::class)
    fun main(args: Array<String>) {
        val server: HttpServer = HttpServer.create(InetSocketAddress(8080), 0)

        server.createContext("/", HttpHandler { exchange ->
            val response = "Hello, World!"
            exchange.sendResponseHeaders(200, response.toByteArray().size.toLong())
            exchange.responseBody.use { os ->
                os.write(response.toByteArray())
            }
        })

        server.start()
        println("Server is listening on port 8080...")
    }
}
