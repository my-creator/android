package com.crecrew.crecre.Network

import com.crecrew.crecre.Network.Get.GetCommunityUnlikeBoardsResponse
import com.crecrew.crecre.Network.Get.GetCommunitySmallNewPostResponse
import com.crecrew.crecre.Network.Post.PostCommunityFavoriteLikeResponse
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.http.*

interface CommunityNetworkService {

    //최신글 5개 보여주기
    @GET("posts/new")
    fun getCommunitySmallNewPosts(

    ): Call<GetCommunitySmallNewPostResponse>

    //인기글 5개 보여주기
    @GET("posts/hot")
    fun getCommunitySmallHotPosts(

    ): Call<GetCommunitySmallNewPostResponse>

    //최신글 전체 보여주기
    @GET("posts/allnew")
    fun getCommunityAllNewPosts(

    ): Call<GetCommunitySmallNewPostResponse>

    //인기글 전체 보여주기
    @GET("posts/allhot")
    fun getCommunityAllHotPosts(

    ): Call<GetCommunitySmallNewPostResponse>

    //즐겨찾지 않은 게시판 보여주기
    @GET("boards/unlike")
    fun getCommunityUnlikeBoards(
        @Header("token") token: String
    ): Call<GetCommunityUnlikeBoardsResponse>

    //즐겨찾는 게시판
    @GET("boards/like")
    fun getCommunityLikeBoards(
        @Header("token") token: String
    ): Call<GetCommunityUnlikeBoardsResponse>

    //게시판 검색했을 경우
    @GET("boards/search")
    fun getBoardsSearch(
        @Query("type") type: String,
        @Query("name") name: String

    ): Call<GetCommunityUnlikeBoardsResponse>

    //게시판 즐겨찾기
    @POST("boards/{boardIdx}/like")
    fun postBoardsFavoriteLike(
        @Header("token") token: String,
        @Path("boardIdx") boardIdx: Int
    ): Call<PostCommunityFavoriteLikeResponse>

    @POST("boards/request")
    fun postBoardsRequest(
        @Header("token") token: String,
        @Body() body : JsonObject
    ): Call<PostCommunityFavoriteLikeResponse>


}