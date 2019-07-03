package com.crecrew.crecre.UI.Fragment.Community

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.crecrew.crecre.R
import com.crecrew.crecre.UI.Activity.CommunityHotPostActivity
import kotlinx.android.synthetic.main.fragment_community_popular.*
import org.jetbrains.anko.support.v4.startActivity

class CommunityRecentFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_community_recent, container, false)
    }

}
