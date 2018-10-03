 package com.okan.pc.mylbrary.feature

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.recycler_view_row.view.*


class MainAdapter(val homeFeed: HomeFeed) : RecyclerView.Adapter<CustomViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context).inflate(R.layout.recycler_view_row, parent, false)

        return CustomViewHolder(layoutInflater)
    }


    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {

        val bookName = homeFeed.Book1.get(position).title
        println("bookName " + bookName)
        holder?.itemView?.book_name?.text = bookName
/*        val book = homeFeed.Book1[position]
        holder.bindItems(book)*/
        //holder.bindItems(homeFeed.Book1.get(position))
      /*  holder.itemView.text_isbn.text = homeFeed[position].barcode.toString()*/
//        notifyDataSetChanged()

    }



    override fun getItemCount(): Int {
        return homeFeed.Book1.size
    }

}


class CustomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bindItems(bookBook1: Book1) {

        println("HELE")
        println(" ISBN "+bookBook1.isbn.toString())
        itemView.text_isbn.text = bookBook1.isbn.toString()
    }

}

