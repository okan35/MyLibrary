package com.okan.pc.mylbrary.feature

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.zxing.Result
import kotlinx.android.synthetic.main.recycler_view_row.view.*
import me.dm7.barcodescanner.zxing.ZXingScannerView


class MainAdapter : RecyclerView.Adapter<CustomViewHolder>(),ZXingScannerView.ResultHandler {


    var text: String = ""
    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {

        holder.itemView.book_name.text= text

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val cellForRow = layoutInflater.inflate(R.layout.recycler_view_row, parent, false)
        return CustomViewHolder(cellForRow)
    }

    override fun getItemCount(): Int {
        return 4
    }


    override fun handleResult(p0: Result?) {

        text= p0!!.text

    }
}


class CustomViewHolder(v: View) : RecyclerView.ViewHolder(v) {

}

