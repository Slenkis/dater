package org

import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.html.respondHtml
import io.ktor.http.content.resources
import io.ktor.http.content.static
import io.ktor.routing.get
import io.ktor.routing.routing
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import kotlinx.html.body
import kotlinx.html.div
import kotlinx.html.head
import kotlinx.html.link
import java.time.LocalDate

fun main() {
    embeddedServer(
        Netty, watchPaths = listOf("dater"), port = 8080,
        module = Application::module
    ).apply {
        start(wait = true)
    }
}

fun Application.module() {
    routing {
        get("/") {
            val date = LocalDate.now()
            call.respondHtml {
                head {
                    link(href = "/style/main.css", type = "text/css", rel = "stylesheet")
                    link(
                        href = "https://fonts.googleapis.com/css2?family=M+PLUS+Rounded+1c:wght@500&display=swap",
                        rel = "stylesheet"
                    )
                }
                body {
                    div("flex") {
                        div("item item1") { +"${date.dayOfMonth}" }
                        div("item item2") { +"${date.month} ${date.year}" }
                    }
                }
            }
        }

        static("/style") {
            resources("style")
        }
    }
}

