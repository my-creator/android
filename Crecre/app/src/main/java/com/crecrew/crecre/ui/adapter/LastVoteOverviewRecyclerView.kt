package com.crecrew.crecre.ui.adapter


/*
class LastVoteOverviewRecyclerView(private val ctx : Context, private val dataList : ArrayList<LastVoteData>) : RecyclerView.Adapter<LastVoteOverviewRecyclerView.Holder>() {
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): Holder {
        val view: View = LayoutInflater.from(ctx)!!.inflate(R.layout.rv_item_last_vote, viewGroup, false)
        return Holder(view)
    }

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: Holder, position: Int) {

        Glide.with(ctx).load(dataList[position].image).into(holder.image)
        holder.image.scaleType = ImageView.ScaleType.CENTER_CROP

        if(dataList[position].profile == "")
            Glide.with(ctx).load(R.drawable.img_profile).into(holder.profile)
        else {
            Glide.with(ctx).load(dataList[position].profile).into(holder.profile)
            holder.profile.setBackground(ShapeDrawable(OvalShape()))
            holder.profile.setClipToOutline(true)
        }

        holder.creator.text = dataList[position].creator
        holder.content.text = dataList[position].content

        holder.container.setOnClickListener {

        }
    }

    fun View.setWidth(value: Int) {
        val lp = layoutParams
        lp?.let {
            lp.width = value
            layoutParams = lp
        }
    }

    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var container = itemView.findViewById(R.id.rv_item_last_vote_rl_container) as RelativeLayout
        var image = itemView.findViewById(R.id.rv_item_last_vote_iv_image) as ImageView
        var profile = itemView.findViewById(R.id.rv_item_last_vote_iv_profile) as ImageView
        var creator = itemView.findViewById(R.id.rv_item_last_vote_creator) as TextView
        var content = itemView.findViewById(R.id.rv_item_last_vote_content) as TextView

    }
}*/