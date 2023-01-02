import com.applaunch.appbase.network_base.NetworkUtils

object BaseMapping {
    fun getBaseUrl(networkUtils: NetworkUtils): String {
        return when (networkUtils) {
            NetworkUtils.BASE_URL -> "http://3.69.105.209/api/v1/app/"
        }
    }

}

