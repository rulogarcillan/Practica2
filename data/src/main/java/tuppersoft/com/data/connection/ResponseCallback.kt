package tuppersoft.com.data.connection

interface ResponseCallback<T> {

    fun onResponse(response: T)

    fun onFailure(t: Throwable)

}