package com.applaunch.bgm.network

import com.applaunch.appbase.listner_base.BaseRepoListener
import com.applaunch.appbase.model_base.BaseResponse
import com.applaunch.appbase.model_base.State
import com.applaunch.appbase.network_base.NetworkBoundRepository
import com.applaunch.bgm.model.request.add_collection.AddCollectionRequest
import com.applaunch.bgm.model.request.challenge.EnterStepRequest
import com.applaunch.bgm.model.request.favorite.EditCollectionRequest
import com.applaunch.bgm.model.request.favorite.FavoriteListRequest
import com.applaunch.bgm.model.request.favorite.FavoriteRequest
import com.applaunch.bgm.model.request.like_dislike.LikeDislikeRequest
import com.applaunch.bgm.model.request.login.AuthRequest
import com.applaunch.bgm.model.request.meet.MeetRequest
import com.applaunch.bgm.model.request.save_remove_fav.SaveRemoveFavRequest
import com.applaunch.bgm.model.request.setting.ContactFormRequest
import com.applaunch.bgm.model.response.article_model.ArticleModel
import com.applaunch.bgm.model.response.auth_model.AuthModel
import com.applaunch.bgm.model.response.challlenge.CheckChallengeModel
import com.applaunch.bgm.model.response.collection_list.CollectListModel
import com.applaunch.bgm.model.response.favorite_model.FavoriteItemModel
import com.applaunch.bgm.model.response.favorite_model.FavoriteModel
import com.applaunch.bgm.model.response.home_model.HomeModel
import com.applaunch.bgm.model.response.meet_model.MeetModel
import com.applaunch.bgm.model.response.setting_model.ContactSupportModel
import com.applaunch.bgm.model.response.webinar_model.WebinarModel
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

class NetworkRepository(private val baseRepoListener: BaseRepoListener) {
    private val apiService = RetrofitClient(baseRepoListener)

    fun login(request: AuthRequest): Flow<State<AuthModel>> {
        return object : NetworkBoundRepository<AuthModel>(baseRepoListener) {
            override suspend fun fetchData(): Response<AuthModel> {
                return apiService.getApiService().login(request)
            }
        }.asFlow()
    }

    fun contactSupport(): Flow<State<ContactSupportModel>> {
        return object : NetworkBoundRepository<ContactSupportModel>(baseRepoListener) {
            override suspend fun fetchData(): Response<ContactSupportModel> {
                return apiService.getApiService().contactSupport()
            }
        }.asFlow()
    }

    fun contactForm(contactFormRequest: ContactFormRequest): Flow<State<BaseResponse>> {
        return object : NetworkBoundRepository<BaseResponse>(baseRepoListener) {
            override suspend fun fetchData(): Response<BaseResponse> {
                return apiService.getApiService().contactForm(contactFormRequest)
            }
        }.asFlow()
    }

    fun home(): Flow<State<HomeModel>> {
        return object : NetworkBoundRepository<HomeModel>(baseRepoListener) {
            override suspend fun fetchData(): Response<HomeModel> {
                return apiService.getApiService().home()
            }
        }.asFlow()
    }

    fun meet(page:Int,limit:Int,meetRequest: MeetRequest): Flow<State<MeetModel>> {
        return object : NetworkBoundRepository<MeetModel>(baseRepoListener) {
            override suspend fun fetchData(): Response<MeetModel> {
                return apiService.getApiService().meet(page, limit, meetRequest)
            }
        }.asFlow()
    }

    fun getCollectionList(): Flow<State<CollectListModel>> {
        return object : NetworkBoundRepository<CollectListModel>(baseRepoListener) {
            override suspend fun fetchData(): Response<CollectListModel> {
                return apiService.getApiService().getCollectList()
            }

        }.asFlow()
    }

    fun createCollection(request: AddCollectionRequest): Flow<State<BaseResponse>> {
        return object : NetworkBoundRepository<BaseResponse>(baseRepoListener) {
            override suspend fun fetchData(): Response<BaseResponse> {
                return apiService.getApiService().createCollection(request)
            }
        }.asFlow()
    }

    fun likeDislike(request: LikeDislikeRequest): Flow<State<BaseResponse>> {
        return object : NetworkBoundRepository<BaseResponse>(baseRepoListener) {
            override suspend fun fetchData(): Response<BaseResponse> {
                return apiService.getApiService().likeDislike(request)
            }
        }.asFlow()
    }

    fun addRemoveFav(request: SaveRemoveFavRequest): Flow<State<BaseResponse>> {
        return object : NetworkBoundRepository<BaseResponse>(baseRepoListener) {
            override suspend fun fetchData(): Response<BaseResponse> {
                return apiService.getApiService().addRemoveFav(request)
            }
        }.asFlow()
    }

    fun article(articleId: String): Flow<State<ArticleModel>> {
        return object : NetworkBoundRepository<ArticleModel>(baseRepoListener) {
            override suspend fun fetchData(): Response<ArticleModel> {
                return apiService.getApiService().article(articleId)
            }
        }.asFlow()
    }

    fun favorite(favoriteRequest: FavoriteRequest): Flow<State<FavoriteModel>> {
        return object : NetworkBoundRepository<FavoriteModel>(baseRepoListener) {
            override suspend fun fetchData(): Response<FavoriteModel> {
                return apiService.getApiService().favorite(favoriteRequest)
            }
        }.asFlow()
    }

    fun favoriteItem(favoriteItemId: String,favoriteListRequest: FavoriteListRequest): Flow<State<FavoriteItemModel>> {
        return object : NetworkBoundRepository<FavoriteItemModel>(baseRepoListener) {
            override suspend fun fetchData(): Response<FavoriteItemModel> {
                return apiService.getApiService().favoriteItem(favoriteItemId,favoriteListRequest)
            }
        }.asFlow()
    }
    fun allFavorite(favoriteListRequest: FavoriteListRequest):Flow<State<FavoriteItemModel>> {
        return object : NetworkBoundRepository<FavoriteItemModel>(baseRepoListener) {
            override suspend fun fetchData(): Response<FavoriteItemModel> {
                return apiService.getApiService().allFavorite(favoriteListRequest)
            }
        }.asFlow()
    }

    fun updateCollection(
        collectionId: String,
        editCollectionRequest: EditCollectionRequest
    ): Flow<State<BaseResponse>> {
        return object : NetworkBoundRepository<BaseResponse>(baseRepoListener) {
            override suspend fun fetchData(): Response<BaseResponse> {
                return apiService.getApiService()
                    .updateCollection(collectionId, editCollectionRequest)
            }
        }.asFlow()
    }
    fun deleteCollection(deleteId:String):Flow<State<BaseResponse>>{
        return object :NetworkBoundRepository<BaseResponse>(baseRepoListener){
            override suspend fun fetchData(): Response<BaseResponse> {
                return apiService.getApiService().deleteCollection(deleteId)
            }
        }.asFlow()
    }

    fun checkChallenge(type:String):Flow<State<CheckChallengeModel>>{
        return object :NetworkBoundRepository<CheckChallengeModel>(baseRepoListener){
            override suspend fun fetchData(): Response<CheckChallengeModel> {
                return apiService.getApiService().checkChallenge(type)
            }
        }.asFlow()
    }

    fun webinar(webinarId: String): Flow<State<WebinarModel>> {
        return object : NetworkBoundRepository<WebinarModel>(baseRepoListener) {
            override suspend fun fetchData(): Response<WebinarModel> {
                return apiService.getApiService().webinar(webinarId)
            }
        }.asFlow()
    }

    fun enterSteps(enterStepRequest: EnterStepRequest): Flow<State<BaseResponse>> {
        return object : NetworkBoundRepository<BaseResponse>(baseRepoListener) {
            override suspend fun fetchData(): Response<BaseResponse> {
                return apiService.getApiService().enterSteps(enterStepRequest)
            }
        }.asFlow()
    }
}