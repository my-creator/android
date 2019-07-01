package com.crecrew.crecre.UI.Fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.crecrew.crecre.Data.CommunityFavoriteData
import com.crecrew.crecre.R
import com.crecrew.crecre.UI.Activity.CommunitySearchActivity
import com.crecrew.crecre.UI.Adapter.CommunityFavoriteRecyclerViewAdapter
import kotlinx.android.synthetic.main.fragment_community.*
import kotlinx.android.synthetic.main.fragment_community.view.*
import org.jetbrains.anko.support.v4.startActivity

class CommunityFragment: Fragment() {

    private lateinit var rootView: View
    //private val communityfavoriteDataList = ArrayList<CommunityFavoriteData>()


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        rootView = inflater.inflate(R.layout.fragment_community, container, false)

        rootView.btn_search_community_frag.setOnClickListener() {
            startActivity<CommunitySearchActivity>()
        }

        //setRecyclerView()

        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setRecyclerView()
    }

    private fun setRecyclerView() {

        var dataList: ArrayList<CommunityFavoriteData> = ArrayList()
        dataList.add(CommunityFavoriteData("자유게시판", 0))
        dataList.add(CommunityFavoriteData("온도Ondo", 1))
        dataList.add(CommunityFavoriteData("입짧은 햇님", 1))


        val communityfavoriteRecyclerViewAdapter = CommunityFavoriteRecyclerViewAdapter(activity!!, dataList)
        rv_favorite_community_frag.adapter = communityfavoriteRecyclerViewAdapter
        rv_favorite_community_frag.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }


}