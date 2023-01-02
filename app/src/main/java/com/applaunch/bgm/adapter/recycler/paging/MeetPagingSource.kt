package com.applaunch.bgm.adapter.recycler.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.applaunch.appbase.listner_base.BaseRepoListener
import com.applaunch.appbase.utils_base.Print
import com.applaunch.bgm.model.dto.meet_data.MeetDataModel
import com.applaunch.bgm.model.request.meet.MeetRequest
import com.applaunch.bgm.network.RetrofitClient
import com.applaunch.bgm.utils.Constant.Pagination.INITIAL_LOAD_SIZE

class MeetPagingSource(private val baseRepoListener: BaseRepoListener,private val meetRequest :MeetRequest):PagingSource<Int,MeetDataModel>() {
    private val apiService = RetrofitClient(baseRepoListener)

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MeetDataModel> {
        val currentPage = params.key ?:INITIAL_LOAD_SIZE
        val response = apiService.getApiService().meet(currentPage,currentPage,meetRequest)
        Print.log("dataResponse $response")

        return try {
            val responseData = mutableListOf<MeetDataModel>()
            val data = response.body()!!.data
           data.let {
               responseData.addAll(data)
           }

           LoadResult.Page(
                data = responseData,
                prevKey = null,
                nextKey = if (responseData.isEmpty()) null else currentPage + 10
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
    override fun getRefreshKey(state: PagingState<Int, MeetDataModel>): Int? {
        return null
    }

}