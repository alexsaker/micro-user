package micro.user

import bench.micro.Comment
import io.micronaut.http.HttpRequest
import io.micronaut.http.client.Client
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Post
import io.reactivex.Maybe
import io.reactivex.Single

@Client("users")
interface UserClient {

    @Get("/")
    fun index(): Single<Array<User>>

    @Post("/")
    fun save(user:User): Single<User>


}