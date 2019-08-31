package com.crecrew.crecre.network

import android.app.Application
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApplicationController : Application() {

    private val baseURL = "http://15.164.106.239:3000/api/"

    lateinit var communityNetworkService: CommunityNetworkService
    lateinit var creatorNetworkService: CreatorNetworkService
    lateinit var rankNetworkService: RankNetworkService
    lateinit var voteNetworkService: VoteNetworkService
    lateinit var userNetworkService: UserNetworkService

    companion object{
        lateinit var instance : ApplicationController
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        buildNetwork()
    }

    fun buildNetwork() {
        val retrofit : Retrofit = Retrofit.Builder()
            .baseUrl(baseURL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        communityNetworkService = retrofit.create(CommunityNetworkService::class.java)
        creatorNetworkService = retrofit.create(CreatorNetworkService::class.java)
        rankNetworkService = retrofit.create(RankNetworkService::class.java)
        voteNetworkService = retrofit.create(VoteNetworkService::class.java)
        userNetworkService = retrofit.create(UserNetworkService::class.java)
    }



}