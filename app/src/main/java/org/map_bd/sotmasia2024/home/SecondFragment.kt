package org.map_bd.sotmasia2024.home

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.map_bd.sotmasia2024.R
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import android.util.Log
import org.map_bd.sotmasia2024.MyAdapter
import org.map_bd.sotmasia2024.UsersItem
import org.map_bd.sotmasia2024.api.Day1Room1
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class SecondFragment : Fragment() {

    lateinit var rvMain : RecyclerView
    lateinit var myAdapter: MyAdapter

    var BASE_URL ="https://mapbd.github.io/sotmAsia2024/event/"


    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val firstView: View = inflater.inflate(R.layout.fragment_second, container, false)

        rvMain =firstView.findViewById(R.id.recycler_view2)

        rvMain.layoutManager = LinearLayoutManager(context)

        getAllData()

        return firstView
    }

    private fun getAllData() {

        var retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(Day1Room1::class.java)

        var retroData = retrofit.getData()

        retroData.enqueue(object : Callback<List<UsersItem>> {
            override fun onResponse(
                call: Call<List<UsersItem>>,
                response: Response<List<UsersItem>>
            ) {
                var data = response.body()!!

                myAdapter = MyAdapter(context,data)

                rvMain.adapter = myAdapter

                Log.d("data",data.toString())
            }

            override fun onFailure(call: Call<List<UsersItem>>, t: Throwable) {

            }

        })

    }


}