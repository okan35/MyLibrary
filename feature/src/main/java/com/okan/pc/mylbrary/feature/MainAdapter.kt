package com.okan.pc.mylbrary.feature

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.recycler_view_row.view.*


class MainAdapter(val bookList: MutableList<Book>) : RecyclerView.Adapter<CustomViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context).inflate(R.layout.recycler_view_row, parent, false)
        return CustomViewHolder(layoutInflater)
    }


    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {

        holder.bindItems(bookList[position])
      /*  holder.itemView.text_isbn.text = bookList[position].barcode.toString()*/
//        notifyDataSetChanged()

    }



    override fun getItemCount(): Int {
        return bookList.size
    }

}


class CustomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bindItems(book: Book) {

        itemView.text_isbn.text = book.barcode.toString()
    }

}

