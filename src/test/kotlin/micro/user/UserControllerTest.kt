//package micro.user;
//
//import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
//import com.fasterxml.jackson.module.kotlin.readValue
//import io.micronaut.context.ApplicationContext
//import io.micronaut.http.client.HttpClient
//import io.micronaut.runtime.server.EmbeddedServer
//import org.jetbrains.spek.api.Spek
//import org.jetbrains.spek.api.dsl.describe
//import org.jetbrains.spek.api.dsl.it
//
//class UserControllerTest : Spek({
//
//    describe("/users") {
//        val embeddedServer : EmbeddedServer = ApplicationContext.run(EmbeddedServer::class.java)
//        val client : HttpClient = HttpClient.create(embeddedServer.url)
//
//        it("test /users responds user list") {
//            val rsp : String = client.toBlocking().retrieve("/users")
//            val mapper = jacksonObjectMapper()
//            val result = mapper.readValue<Array<User>>(rsp)
//            val expectedResult = arrayOf(User("toto", "joe", 78), User("toti", "pop", 5))
//            assert(result contentDeepEquals  expectedResult)
//
//        }
//
//        afterGroup {
//            client.close()
//            embeddedServer.close()
//        }
//    }
//})
