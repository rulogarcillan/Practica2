package tuppersoft.com.data.connection

import retrofit2.Call
import retrofit2.http.GET
import tuppersoft.com.domain.dto.Post

interface Services {

    /*   @GET("users?per_page=13")
       fun listUsers(): Call<ResponseGlobal<MutableList<User>>>

       @GET("users/{idUser}")
       fun getUser(@Path("idUser") idUser : Int) : Call<ResponseUser>

       @POST("users")
       fun createUser(@Body user : NewUser) : Call<Any>*/


    @GET("/posts")
    fun getPost(): Call<MutableList<Post>>
}