package com.crecrew.crecre.network

import com.crecrew.crecre.network.get.*
import com.crecrew.crecre.network.post.PostCommunityFavoriteLikeResponse
import com.google.gson.JsonObject
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*

interface CommunityNetworkService {

    //최신글 5개 보여주기
    @GET("posts/new")
    fun getCommunitySmallNewPosts(

    ): Call<GetCommunitySmallPostResponse>

    //인기글 5개 보여주기
    @GET("posts/hot")
    fun getCommunitySmallHotPosts(

    ): Call<GetCommunitySmallPostResponse>

    //최신글 전체 보여주기
    @GET("posts/allnew")
    fun getCommunityAllNewPosts(

    ): Call<GetCommunitySmallPostResponse>

    //인기글 전체 보여주기
    @GET("posts/allhot")
    fun getCommunityAllHotPosts(

    ): Call<GetCommunitySmallPostResponse>

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
    ):Call<GetCommunitySmallPostResponse>

    //게시판 게시글 리스트 보여주기
    @GET("posts/list/{boardIdx}")
    fun getPostListAllBoards(
        @Path("boardIdx") boardIdx : Int
    ):Call<GetCommunitySmallPostResponse>

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
    ) : Call<GetCommunitySmallPostResponse>


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

    //좋아요 누르기
    @POST("posts/{postIdx}/like")
    fun postPostLikeOn(
        @Header("token") token : String,
        @Path("postIdx") postIdx : Int
    ) : Call<PostCommunityFavoriteLikeResponse>

    //좋아요 삭제
    @DELETE("posts/{postIdx}/unlike")
    fun deletePostLikeOff(
        @Header("token") token : String,
        @Path("postIdx") postIdx : Int
    ):Call<PostCommunityFavoriteLikeResponse>

    //비추천 누르기
    @POST("posts/{postIdx}/hate")
    fun postPosthateOn(
        @Header("token") token : String,
        @Path("postIdx") postIdx : Int
    ) : Call<PostCommunityFavoriteLikeResponse>

    //비추천 취소
    @DELETE("posts/{postIdx}/unhate")
    fun deletePostunhateOff(
        @Header("token") token : String,
        @Path("postIdx") postIdx : Int
    ):Call<PostCommunityFavoriteLikeResponse>

    //댓글 조회
    @GET("replies/{postIdx}")
    fun getPostReply(
        @Header("token") token : String,
        @Path("postIdx") postIdx : Int
    ) : Call<GetPostReplyResponse>

    //댓글 작성
    @POST("replies/")
    fun postCommentReply(
        @Header("token") token : String,
        @Body() body : JsonObject
    ) : Call<PostCommunityFavoriteLikeResponse>

    //게시글 삭제
    @DELETE("posts/{postIdx}")
    fun deletePostComments(
        @Header("token") token : String,
        @Path("postIdx") postIdx : Int
    ) : Call<PostCommunityFavoriteLikeResponse>

    //게시글 작성
    @Multipart
    @POST("posts")
    fun postPostContentsWrite(
        @Header("token") token : String,
        @Part("boardIdx") boardIdx : Int,
        @Part("is_anonymous") is_anonymous : Int,
        @Part("title") title: RequestBody,
        @Part("contents") contents : RequestBody,
        @Part imgs : MultipartBody.Part?
    ):Call<PostCommunityFavoriteLikeResponse>

    @GET("posts/todaynew")
    fun getTodayNewPost(
    ):Call<GetTodayPostResponse>

    @GET("posts/todayhot")
    fun getTodayHotPost(
    ):Call<GetTodayPostResponse>

}