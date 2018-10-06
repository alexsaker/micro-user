package micro.user

import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Post
import io.micronaut.validation.Validated
import io.reactivex.Single

import javax.validation.constraints.*

@Validated
interface UsersOperations {
    @Post("/users")
    fun save(@NotNull @Body user:User): Single<User>
}