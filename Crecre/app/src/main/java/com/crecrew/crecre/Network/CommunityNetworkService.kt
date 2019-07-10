package com.crecrew.crecre.Network

import com.crecrew.crecre.Network.Get.*
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
        @Header("token") token: String,
        @Query("type") type: String,
        @Query("name") name: String

    ): Call<GetCommunityUnlikeBoardsResponse>

    //게시판 정보 보내기
    @POST("boards/request")
    fun postBoardsRequest(
        @Header("token") token: String,
        @Body() body : JsonObject
    ): Call<PostCommunityFavoriteLikeResponse>

    //게시판 요청값
    @GET("boards/request/{boardRequestIdx}")
    fun getBoeardsRequestIdx(
        @Header("token") token : String,
        @Path("boardRequestIdx") boardRequestIdx : Int
    ): Call<GetBoardRequestNumResponse>

    //게시판 인기3개 나오는 통신
    @GET("posts/listhot/{boardIdx}")
    fun getPostListBoards(
        @Path("boardIdx") boardIdx : Int
    ):Call<GetCommunitySmallNewPostResponse>

    //게시판 게시글 리스트 보여주기
    @GET("posts/list/{boardIdx}")
    fun getPostListAllBoards(
        @Path("boardIdx") boardIdx : Int
    ):Call<GetCommunitySmallNewPostResponse>

    //게시글 상세보기
    @GET("posts/detail/{postIdx}")
    fun getPostDetailPostIdx(
        @Header("token") token : String,
        @Path("postIdx") postIdx : Int
    ) : Call<GetPostDetailResponse>

    //게시글 검색 결과
    @GET("posts/search")
    fun getPostSearchTitle(
        @Header("token") token: String,
        @Query("title") title: String,
        @Query("contents") contents : String
    ) : Call<GetCommunitySmallNewPostResponse>


    //게시판 즐겨찾기
    @POST("boards/{boardIdx}/like")
    fun postBoardsFavoriteLike(
        @Header("token") token: String,
        @Path("boardIdx") boardIdx: Int
    ): Call<PostCommunityFavoriteLikeResponse>

    //게시판 즐겨찾기 취소
    @DELETE("boards/{boardIdx}/unlike")
    fun deleteBoardsFavoriteLike(
        @Header("token") token: String,
        @Path("boardIdx") boardIdx: Int
    ): Call<PostCommunityFavoriteLikeResponse>




}