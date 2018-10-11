package micro.user

import io.micronaut.context.annotation.Context
import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpResponse
import io.micronaut.http.hateos.JsonError
import io.micronaut.http.annotation.Error
import io.micronaut.http.hateos.Link
import io.micronaut.http.HttpStatus
import javax.inject.Singleton

@Singleton
@Context
@Error(status = HttpStatus.NOT_FOUND, global = true)
fun notFound(request: HttpRequest<*>): HttpResponse<JsonError> {
    println("#COUOCU")
    val error = JsonError("Page Not Found2")
            .link(Link.SELF, Link.of(request.uri))

    return HttpResponse.notFound<JsonError>()
            .body(error)
}

@Error(global = true)
fun error(request: HttpRequest<Any>, e: Throwable): HttpResponse<JsonError> {
    val error = JsonError("Bad Things Happened: " + e.message)
            .link(Link.SELF, Link.of(request.uri))

    return HttpResponse.serverError<JsonError>()
            .body(error)
}