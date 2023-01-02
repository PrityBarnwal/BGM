package com.applaunch.appbase.network_base


import com.applaunch.appbase.listner_base.BaseRepoListener
import com.applaunch.appbase.model_base.BaseResponse
import com.applaunch.appbase.model_base.State
import com.applaunch.appbase.utils_base.Print
import com.applaunch.appbase.utils_base.checkApiCode
import com.applaunch.appbase.utils_base.convertErrorBody
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import retrofit2.Response

abstract class NetworkBoundRepository<RESULT>(
    var iRepositoryListener: BaseRepoListener?,
    private val isShowProgress: Boolean = true,
) {
    fun asFlow() = flow<State<RESULT>> {

        // Emit Loading State

        if (iRepositoryListener?.isNetworkConnected()!!) {

            if (isShowProgress) {
                iRepositoryListener?.showLoader()
            }

            // Emit Database content first
            // Fetch latest posts from remote
            val apiResponse: Response<RESULT>

            // Parse body
            val remoteData: RESULT
            val baseResponse: BaseResponse?

            withContext(Dispatchers.IO) {
                apiResponse = fetchData()


            }
            iRepositoryListener?.hideLoader()


            // Save posts into the persistence storage
            if (apiResponse.isSuccessful && checkApiCode(apiResponse.code())) {
                remoteData = apiResponse.body()!!
                Print.log("Api Success = > ${remoteData}")
                emit(State.success(remoteData))
            } else {
                val errorResponse = convertErrorBody(apiResponse.errorBody())
                Print.log("Api Error  = > ${errorResponse?.message}")
                emit(State.error(errorResponse?.message.toString()))

            }

        }
    }.catch { e ->
        // Exception occurred! Emit error
        withContext(Dispatchers.Main) {
            iRepositoryListener?.hideLoader()
            Print.log("Exception => (NetworkBoundRepository) : ${e}")
            e.printStackTrace()
        }
    }

    protected abstract suspend fun fetchData(): Response<RESULT>
}

