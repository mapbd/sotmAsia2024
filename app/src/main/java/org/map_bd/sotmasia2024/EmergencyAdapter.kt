package org.map_bd.sotmasia2024

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView

class EmergencyAdapter(private val context: Activity, private val arrayList: ArrayList<User>): ArrayAdapter<User>(context, R.layout.list_item, arrayList) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        val inflater : LayoutInflater = LayoutInflater.from(context)
        val view : View = inflater.inflate(R.layout.list_item, null)
        val imageView : ImageView = view.findViewById(R.id.profile_pic)
        val userName : TextView = view.findViewById(R.id.personName)
        val number : TextView = view.findViewById(R.id.lastMassage)

        imageView.setImageResource(arrayList[position].imageId)
        userName.text = arrayList[position].name
        number.text = arrayList[position].phone

        return view
    }
}