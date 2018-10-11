package micro.user

import io.micronaut.context.annotation.Parameter
import io.micronaut.http.client.Client
import io.micronaut.http.annotation.Get
import io.micronaut.http.HttpStatus
import io.reactivex.Maybe
import javax.inject.Inject

@Client("https://www.google.com")
interface GoogleSearchClient {

    @Get("/search?{q}")
    fun gSearch(q:String): Maybe<String>?
}