package com.applaunch.bgm.network

import com.applaunch.appbase.model_base.BaseResponse
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
import com.applaunch.bgm.utils.ApiConstants.ALL_FAVORITE
import com.applaunch.bgm.utils.ApiConstants.ARTICLE
import com.applaunch.bgm.utils.ApiConstants.ARTICLE_ID
import com.applaunch.bgm.utils.ApiConstants.CHECK_CHALLENGE
import com.applaunch.bgm.utils.ApiConstants.COLLECTION
import com.applaunch.bgm.utils.ApiConstants.CONTACT_FORM
import com.applaunch.bgm.utils.ApiConstants.CONTACT_SUPPORT
import com.applaunch.bgm.utils.ApiConstants.CREATE_COLLECTION
import com.applaunch.bgm.utils.ApiConstants.DELETE_COLLECTION
import com.applaunch.bgm.utils.ApiConstants.DELETE_ID
import com.applaunch.bgm.utils.ApiConstants.EDIT
import com.applaunch.bgm.utils.ApiConstants.EDIT_ID
import com.applaunch.bgm.utils.ApiConstants.ENTER_STEPS
import com.applaunch.bgm.utils.ApiConstants.FAVORITE
import com.applaunch.bgm.utils.ApiConstants.FAVORITE_ID
import com.applaunch.bgm.utils.ApiConstants.FAVORITE_ITEM
import com.applaunch.bgm.utils.ApiConstants.FAVORITE_SAVE
import com.applaunch.bgm.utils.ApiConstants.HOME
import com.applaunch.bgm.utils.ApiConstants.LIKE
import com.applaunch.bgm.utils.ApiConstants.LIMIT
import com.applaunch.bgm.utils.ApiConstants.LOGIN
import com.applaunch.bgm.utils.ApiConstants.MEET
import com.applaunch.bgm.utils.ApiConstants.PAGE
import com.applaunch.bgm.utils.ApiConstants.WEBINAR
import com.applaunch.bgm.utils.ApiConstants.WEBINAR_ID
import retrofit2.Response
import retrofit2.http.*

interface ApiService {

    @POST(LOGIN)
    suspend fun login(
        @Body authRequest: AuthRequest
    ): Response<AuthModel>

    @GET(CONTACT_SUPPORT)
    suspend fun contactSupport(): Response<ContactSupportModel>

    @POST(CONTACT_FORM)
    suspend fun contactForm(
        @Body contactFormRequest: ContactFormRequest
    ): Response<BaseResponse>

    @GET(HOME)
    suspend fun home(): Response<HomeModel>

    @POST(MEET)
    suspend fun meet(
        @Path(PAGE) page: Int,
        @Path(LIMIT) limit: Int,
        @Body meetRequest: MeetRequest
    ): Response<MeetModel>

    @GET(COLLECTION)
    suspend fun getCollectList(): Response<CollectListModel>

    @POST(CREATE_COLLECTION)
    suspend fun createCollection(
        @Body request: AddCollectionRequest
    ): Response<BaseResponse>


    @POST(LIKE)
    suspend fun likeDislike(
        @Body request: LikeDislikeRequest
    ): Response<BaseResponse>

    @POST(FAVORITE_SAVE)
    suspend fun addRemoveFav(@Body request: SaveRemoveFavRequest): Response<BaseResponse>

    @GET(ARTICLE)
    suspend fun article(
        @Path(ARTICLE_ID) id: String
    ): Response<ArticleModel>

    @POST(FAVORITE)
    suspend fun favorite(@Body favoriteRequest: FavoriteRequest): Response<FavoriteModel>

    @POST(FAVORITE_ITEM)
    suspend fun favoriteItem(
        @Path(FAVORITE_ID) id: String,
        @Body favoriteListRequest: FavoriteListRequest
    ): Response<FavoriteItemModel>

    @POST(ALL_FAVORITE)
    suspend fun allFavorite(
        @Body favoriteListRequest: FavoriteListRequest
    ): Response<FavoriteItemModel>

    @PUT(EDIT)
    suspend fun updateCollection(
        @Path(EDIT_ID) id: String,
        @Body editCollectionRequest: EditCollectionRequest
    ): Response<BaseResponse>

    @DELETE(DELETE_COLLECTION)
    suspend fun deleteCollection(
        @Path(DELETE_ID) id: String
    ): Response<BaseResponse>

    @GET(CHECK_CHALLENGE)
    suspend fun checkChallenge(@Query("type") type: String): Response<CheckChallengeModel>

    @POST(ENTER_STEPS)
    suspend fun enterSteps(@Body enterStepRequest: EnterStepRequest): Response<BaseResponse>

    @GET(WEBINAR)
    suspend fun webinar(@Path(WEBINAR_ID) id: String):Response<WebinarModel>
}