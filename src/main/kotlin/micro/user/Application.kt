package micro.user

import io.micronaut.runtime.Micronaut

object Application {

    @JvmStatic
    fun main(args: Array<String>) {
        Micronaut.build()
                .packages("micro.user")
                .mainClass(Application.javaClass)
                .start()
    }
}