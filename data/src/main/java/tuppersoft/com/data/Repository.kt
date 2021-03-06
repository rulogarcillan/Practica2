package tuppersoft.com.data

import android.content.Context
import android.preference.PreferenceManager
import retrofit2.Call
import retrofit2.Response
import tuppersoft.com.data.connection.Client
import tuppersoft.com.data.connection.ResponseCallback
import tuppersoft.com.data.connection.Services
import tuppersoft.com.domain.dto.*
import javax.security.auth.callback.Callback


class Repository {

    companion object {
        fun <T> savePreference(mContext: Context, key: String, value: T) {
            val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(mContext)
            val editor = sharedPreferences.edit()

            when (value) {
                is Boolean -> editor.putBoolean(key, value as Boolean)
                is Float -> editor.putFloat(key, value as Float)
                is Int -> editor.putInt(key, value as Int)
                is Long -> editor.putLong(key, value as Long)
                is String -> editor.putString(key, value as String)
                else -> return
            }

            editor.apply()
        }

        fun <T> loadPreference(mContext: Context, key: String, defaultValue: T): Any {
            val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(mContext)
            return when (defaultValue) {
                is Boolean -> sharedPreferences.getBoolean(key, defaultValue as Boolean)
                is Float -> sharedPreferences.getFloat(key, defaultValue as Float)
                is Int -> sharedPreferences.getInt(key, defaultValue as Int)
                is Long -> sharedPreferences.getLong(key, defaultValue as Long)
                is String -> sharedPreferences.getString(key, defaultValue as String)
                else -> defaultValue as Any
            }
        }


        fun <T> getPost(callback: ResponseCallback<T>) {
            Client().getRetrofitCLient().create(Services::class.java).getPost().enqueue(object : Callback,
                retrofit2.Callback<MutableList<Post>> {
                override fun onFailure(call: Call<MutableList<Post>>, t: Throwable) {
                    callback.onFailure(t)
                }

                override fun onResponse(call: Call<MutableList<Post>>, response: Response<MutableList<Post>>) {
                    callback.onResponse(response.body() as T)
                }
            })
        }

        fun <T> savePost(post: Post, callback: ResponseCallback<T>) {
            Client().getRetrofitCLient().create(Services::class.java).savePost(post).enqueue(object : Callback,
                retrofit2.Callback<Post> {
                override fun onFailure(call: Call<Post>, t: Throwable) {
                    callback.onFailure(t)
                }

                override fun onResponse(call: Call<Post>, response: Response<Post>) {
                    callback.onResponse(response.body() as T)
                }
            })
        }

        fun <T> getUser(userId: Long, callback: ResponseCallback<T>) {
            Client().getRetrofitCLient().create(Services::class.java).getUser(userId).enqueue((object : Callback,
                retrofit2.Callback<MutableList<User>> {
                override fun onFailure(call: Call<MutableList<User>>, t: Throwable) {
                    callback.onFailure(t)
                }

                override fun onResponse(call: Call<MutableList<User>>, response: Response<MutableList<User>>) {
                    callback.onResponse(response.body()?.first() as T)
                }
            }))
        }

        fun <T> getUsers(callback: ResponseCallback<T>) {
            Client().getRetrofitCLient().create(Services::class.java).getUsers().enqueue((object : Callback,
                retrofit2.Callback<MutableList<User>> {
                override fun onFailure(call: Call<MutableList<User>>, t: Throwable) {
                    callback.onFailure(t)
                }

                override fun onResponse(call: Call<MutableList<User>>, response: Response<MutableList<User>>) {
                    callback.onResponse(response.body() as T)
                }
            }))
        }


        fun <T> getComments(postId: Long, callback: ResponseCallback<T>) {
            Client().getRetrofitCLient().create(Services::class.java).getComments(postId).enqueue((object : Callback,
                retrofit2.Callback<MutableList<Comment>> {
                override fun onFailure(call: Call<MutableList<Comment>>, t: Throwable) {
                    callback.onFailure(t)
                }

                override fun onResponse(call: Call<MutableList<Comment>>, response: Response<MutableList<Comment>>) {
                    callback.onResponse(response.body() as T)
                }
            }))
        }

        fun <T> getAlbums(callback: ResponseCallback<T>) {
            Client().getRetrofitCLient().create(Services::class.java).getAlbums().enqueue((object : Callback,
                retrofit2.Callback<MutableList<Album>> {
                override fun onFailure(call: Call<MutableList<Album>>, t: Throwable) {
                    callback.onFailure(t)
                }

                override fun onResponse(call: Call<MutableList<Album>>, response: Response<MutableList<Album>>) {
                    callback.onResponse(response.body() as T)
                }
            }))
        }

        fun <T> getPhotos(albumId: Long, callback: ResponseCallback<T>) {
            Client().getRetrofitCLient().create(Services::class.java).getPhotos(albumId).enqueue((object : Callback,
                retrofit2.Callback<MutableList<Photo>> {
                override fun onFailure(call: Call<MutableList<Photo>>, t: Throwable) {
                    callback.onFailure(t)
                }

                override fun onResponse(call: Call<MutableList<Photo>>, response: Response<MutableList<Photo>>) {
                    callback.onResponse(response.body() as T)
                }
            }))
        }

    }
}
