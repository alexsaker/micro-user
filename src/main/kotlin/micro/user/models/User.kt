package micro.user

import com.fasterxml.jackson.annotation.JsonAutoDetect
import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import org.bson.codecs.pojo.annotations.BsonCreator
import org.bson.codecs.pojo.annotations.BsonProperty
import javax.inject.Singleton

@Singleton
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
data class User (@JsonProperty("name") @BsonProperty("name") val name:String,
                 @JsonProperty("surname") @BsonProperty("surname") val surname:String,
                 @JsonProperty("age")  @BsonProperty("age")  val age:Int?=78
)