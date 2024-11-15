package org.map_bd.sotmasia2024

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MyAdapter(var con: Context?, var list: List<UsersItem>?) : RecyclerView.Adapter<MyAdapter.ViewHolder>() {

    inner class ViewHolder(v : View) : RecyclerView.ViewHolder(v)

    {
        var speaker = v.findViewById<TextView>(R.id.speakertypeId)
        var date = v.findViewById<TextView>(R.id.timeId)
        var name = v.findViewById<TextView>(R.id.nameId)
        var talk = v.findViewById<TextView>(R.id.talkId)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view = LayoutInflater.from(con).inflate(R.layout.program_list,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        //Glide.with(con).load(list[position].avatar_url).into(holder.img)

        holder.date.text = list!![position].time
        holder.speaker.text = list!![position].speaker
        holder.name.text = list!![position].name
        holder.talk.text = list!![position].talk

    }

    override fun getItemCount(): Int {
        return list!!.count()
    }

}