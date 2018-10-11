package bench.micro

import com.mongodb.reactivestreams.client.MongoClient
import com.mongodb.reactivestreams.client.Success
import io.micronaut.http.HttpRequest.*

import io.micronaut.http.annotation.*
import io.micronaut.http.client.*

import io.micronaut.http.MediaType
import io.reactivex.Single
import javax.validation.Valid
import javax.validation.constraints.NotNull
import io.micronaut.validation.Validated
import micro.user.User
import micro.user.UsersOperations
import io.reactivex.Maybe
import io.micronaut.http.annotation.Get
import io.micronaut.http.client.RxHttpClient
import io.micronaut.runtime.ApplicationConfiguration
import io.reactivex.Flowable
import micro.user.GoogleSearchClient
import org.bson.BsonDocument
import java.net.URL
import javax.inject.Inject
import com.mongodb.client.model.Filters.eq

data class Success(val success: Boolean)

@Controller("/\${micro.user.version}/")
@Validated
 open class UsersController(
        @Client("https://www.google.com" ) @Inject open val http2Client: RxHttpClient,
        @Inject open val googleSearchClient: GoogleSearchClient,
        @Inject open val mongoClient:MongoClient
) : UsersOperations{


    @Get("jojo/{qs}")
    @Produces(MediaType.APPLICATION_XML)
    open fun testGoogle(qs:String):Maybe<String>?{
        return googleSearchClient.gSearch(qs);
    }


    @Produces(MediaType.APPLICATION_JSON)
    @Post("/users")
     override fun save(user: User): Single<User> {
        return Single.fromPublisher(
                mongoClient
                .getDatabase("test")
                .getCollection("users", User::class.java)
                .insertOne(user)).map { _: Success -> user }
    }


    @Produces(MediaType.APPLICATION_JSON)
    @Get("/users")
    open fun index(): Single<MutableList<User>> {
        return   Flowable.fromPublisher(
                mongoClient
                        .getDatabase("test")
                        .getCollection("users")
                        .find().limit(20))
                        .map { User(
                                it.get("name","toto"),
                                it.get("surname","def"),
                                it.get("age",56))
                        }
                       .toList()
    }

    @Delete("/users/{name}")
    open fun deleteUserByName(@Valid @NotNull name: String): Maybe<bench.micro.Success>? {
        return   Flowable.fromPublisher(
                mongoClient
                        .getDatabase("test")
                        .getCollection("users")
                        .deleteOne(eq("name", name)))
                .map{
                    if(it.deletedCount != 0L){
                    Success(true)
                    }else{
                    Success(false)}
                    }
                .firstElement()

    }

    @Get("/users/{name}")
    open fun getUserByName(@Valid @NotNull name: String):Maybe<User>{
        return   Flowable.fromPublisher(
                mongoClient
                        .getDatabase("test")
                        .getCollection("users")
                        .find(eq("name", name))
                        .limit(1))
                .map { User(
                        it.get("name","toto"),
                        it.get("surname","def"),
                        it.get("age",56))
                }.firstElement()
    }
    @Get("/hello")
    open fun hello(): Maybe<String>? {
    val httpClient= RxHttpClient.create(URL("https://jsonplaceholder.typicode.com"))
        return httpClient.retrieve<String>(GET("/comments")).firstElement()
    }
    @Produces(MediaType.APPLICATION_ATOM_XML)
    @Get("/hellogoogle")
    open fun hello2(): Maybe<String>? {
        return http2Client.retrieve<String>(GET("/search?q=test")).firstElement()
    }

}
data class Comment(val postId:Int,val id:Int,val name:String,val email:String,val body:String)