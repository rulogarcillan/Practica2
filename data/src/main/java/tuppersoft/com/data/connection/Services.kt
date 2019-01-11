package tuppersoft.com.data.connection

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query
import tuppersoft.com.domain.dto.*

interface Services {

    @POST("/posts")
    fun savePost(@Body post: Post): Call<Post>

    @GET("/posts")
    fun getPost(): Call<MutableList<Post>>

    @GET("/users")
    fun getUser(@Query("id") userID: Long): Call<MutableList<User>>

    @GET("/users")
    fun getUsers(): Call<MutableList<User>>

    @GET("/comments")
    fun getComments(@Query("postId") postId: Long): Call<MutableList<Comment>>

    @GET("/albums")
    fun getAlbums(): Call<MutableList<Album>>

    @GET("/photos")
    fun getPhotos(@Query("albumId") albumId: Long): Call<MutableList<Photo>>
}