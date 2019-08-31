package com.crecrew.crecre.ui.adapter

//class VoteListRecyclerviewAdapter(val ctx: Context, val dataList: ArrayList<VoteData>) :
//    RecyclerView.Adapter<VoteListRecyclerviewAdapter.Holder>()
//    , VoteChoiceRecyclerviewAdapter.onItemCheckListener {
//    var isCheck: Boolean = false
//    lateinit var test: TextView
//    var thisisChoice: Int = 0
//    var userToken = SharedPreferenceController.getUserToken(ctx)
//
//    val voteNetworkService: VoteNetworkService by lazy {
//        ApplicationController.instance.voteNetworkService
//    }
//
//    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//        var img_thumnail = itemView.findViewById(R.id.card_main_image) as ImageView
//        var txt_dayleft = itemView.findViewById(R.id.rv_item_current_card_dayleft) as TextView
//        var txt_ongoing = itemView.findViewById(R.id.rv_item_current_card_isongoing) as TextView
//        var stamp = itemView.findViewById(R.id.rv_item_current_card_stamp) as ImageView
//        var title = itemView.findViewById(R.id.rv_item_vote_title) as TextView
//        var explain = itemView.findViewById(R.id.rv_item_vote_explain) as TextView
//        var letsVote = itemView.findViewById(R.id.lets_vote) as TextView
//        var whitebox = itemView.findViewById(R.id.whitebox) as View
//        var choice_container = itemView.findViewById(R.id.rv_item_invote_choicesList) as RecyclerView
//        var mintbox = itemView.findViewById(R.id.rv_item_current_card_isongoing) as Button
//    }
//
//    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): VoteListRecyclerviewAdapter.Holder {
//        val view: View = LayoutInflater.from(ctx).inflate(R.layout.rv_item_currentvote_card, p0, false)
//
//        return Holder(view)
//
//    }
//
//    override fun getItemCount(): Int {
//        return dataList.size
//    }
//
//    @RequiresApi(Build.VERSION_CODES.O)
//    override fun onBindViewHolder(holder: Holder, position: Int) {
//
//        Glide.with(ctx)
//            .load(dataList[position].thumbnail_url).apply(RequestOptions().circleCrop())
//            .into(holder.img_thumnail)
//
//        holder.title.text = dataList[position].title
//        holder.explain.text = "#" + dataList[position].contents
//
//        //holder.txt_ongoing.setVisibility(View.GONE)
//
//        test = holder.letsVote
////        if(!isCheck) holder.letsVote.)
////        else holder.letsVote.setTextColor(Color.parseColor("#ff57f7"))
//
//        var cal = calculateLastime(dataList[position].end_time)
//        holder.txt_dayleft.text = "${cal}일 후 개표"
//        //holder.txt_dayleft.text = "일 후 개표"
//
//        var voteChoiceRecyclerviewAdapter = VoteChoiceRecyclerviewAdapter(ctx, dataList[position].choices)
//        voteChoiceRecyclerviewAdapter.setOnItemClickListener(this)
//        holder.choice_container.adapter = voteChoiceRecyclerviewAdapter
//        holder.choice_container.layoutManager = LinearLayoutManager(ctx, LinearLayoutManager.VERTICAL, false)
//
//        if (userToken != null && dataList[position].my_choice != null) { //투표함
//            var i: Int = dataList[position].choices.size
//            var j: Int = 0;
//
//            while (j < i) {
//                if (dataList[position].choices[j].choice_idx != dataList[position].my_choice) dataList[position].choices[j].checked = false
//                else dataList[position].choices[j].checked = true
//                dataList[position].choices[j].cho = -2
//                j++
//            }
//
//            //holder.letsVote.setTextColor(Color.parseColor("#aaaaaa"))
//        } else if (userToken != null && dataList[position].my_choice == null) { //아직 투표 안함
//            holder.stamp.setVisibility(View.GONE)
//            holder.whitebox.setVisibility(View.GONE)
//            holder.mintbox.setVisibility(View.GONE)
//            var i: Int = dataList[position].choices.size
//            var j: Int = 0;
//
//            while (j < i) {
//                dataList[position].choices[j].checked = true
//                j++
//            }
//
//            holder.letsVote.setOnClickListener {
//                if (isCheck){
//                    j=0;
//                    while (j < i) {
//                        if (dataList[position].choices[j].cho == j) {
//                            thisisChoice = j;
//                            dataList[position].choices[j].cho = -2
//                        }
//                        j++
//                    }
//                }
//                postVoteResponse(dataList[position].choices[thisisChoice].choice_idx, position)
//            }
//        } else if (userToken == null) {
//            holder.stamp.setVisibility(View.GONE)
//            holder.whitebox.setVisibility(View.GONE)
//            holder.mintbox.setVisibility(View.GONE)
//        }
//    }
//
//    override fun onCheck(isCheck: Boolean) {
//        if (isCheck) {
//            test.setTextColor(Color.parseColor("#ff57f7"))
//        }else{
//            test.setTextColor(Color.parseColor("#aaaaaa"))
//        }
//    }
//
//    fun postVoteResponse(choice: Int, position: Int) {
//        var jsonObject = JSONObject()
//        jsonObject.put("choiceIdx", choice)
//
//        val gsonObject = JsonParser().parse(jsonObject.toString()) as JsonObject
//        val postVoteResponse: Call<PostVoteChoiceResponse> =
//            voteNetworkService.postVoteChoiceResponse(userToken, gsonObject, dataList[position].vote_idx)
//        postVoteResponse.enqueue(object : Callback<PostVoteChoiceResponse> {
//            override fun onFailure(call: Call<PostVoteChoiceResponse>, t: Throwable) {
//                Log.e("Post vote choice", t.toString())
//            }
//
//            override fun onResponse(call: Call<PostVoteChoiceResponse>, response: Response<PostVoteChoiceResponse>) {
//                if (response.isSuccessful) {
//                    if (response.body()!!.status == 201) {
//                        Log.d("Post vote choice", "success!")
//
//                    }else{
//                        Log.d("Post vote choice", "error!")
//                    }
//                }
//            }
//        })
//    }
//}
//
//
//
