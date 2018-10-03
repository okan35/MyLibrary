package com.okan.pc.mylbrary.feature

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.Toast
import com.google.gson.GsonBuilder
import com.google.gson.JsonObject
import com.google.zxing.Result
import kotlinx.android.synthetic.main.activity_main.*
import me.dm7.barcodescanner.zxing.ZXingScannerView
import okhttp3.*
import org.json.JSONObject
import java.io.IOException


class ScanActivity : AppCompatActivity(), ZXingScannerView.ResultHandler {

    private var mScannerView: ZXingScannerView? = null
    var barcode: Long = 0
    //var books = ArrayList<Book>()
  //  var booksMutable = mutableListOf<Book>()

    public override fun onCreate(state: Bundle?) {
        super.onCreate(state)
        mScannerView = ZXingScannerView(this)   // Programmatically initialize the scanner view
        setContentView(mScannerView)                // Set the scanner view as the content view
    }

    public override fun onResume() {
        super.onResume()
        mScannerView!!.setResultHandler(this) // Register ourselves as a handler for scan results.
        mScannerView!!.startCamera()          // Start camera on resume
    }

    public override fun onPause() {
        super.onPause()
        mScannerView!!.stopCamera()           // Stop camera on pause
    }

    override fun handleResult(rawResult: Result) {
           Toast.makeText(this, rawResult.text, Toast.LENGTH_SHORT).show()

        fetchJson(rawResult.text)

        // If you would like to resume scanning, call this method below:
        //mScannerView.resumeCameraPreview(this);


    }

    fun fetchJson(bookIsbn: String) {
        var url = "https://www.googleapis.com/books/v1/volumes?q=isbn:"+bookIsbn

        val request = Request.Builder().url(url).build()

        val client = OkHttpClient()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call?, e: IOException?) {
                println("Error in executing call")
            }

            override fun onResponse(call: Call?, response: Response?) {
                val body = response?.body()?.string()
               //
                // println("book " + body)

                val jsonObject = JSONObject(body)
                //val jsonBody = jsonObject.getAsJsonObject(body)

                println("body " + jsonObject.toString(4))
                if (jsonObject.getString("totalItems").equals("0")){
                    val intent = Intent(this@ScanActivity,MainActivity::class.java)
                    intent.putExtra("title","Book was not found")
                    startActivity(intent)
                    return
                }

                val items = jsonObject.getJSONArray("items").getJSONObject(0)

                val id = items.getString("id")
                val volumeInfo = items.getJSONObject("volumeInfo")
                val title = volumeInfo.getString("title")
                val author = volumeInfo.getJSONArray("authors").get(0).toString()
                val publishedDate = volumeInfo.getString("publishedDate")
                val bookCover = volumeInfo.getJSONObject("imageLinks").getString("smallThumbnail")

                val book = Book(id,author,title,publishedDate, bookCover)

                println("id " + book.id + " title " + book.title + "bookcover " + bookCover)

                val intent = Intent(this@ScanActivity,MainActivity::class.java)
                //intent.putExtra("id", book.id)
                intent.putExtra("title",book.title)
                intent.putExtra("author", book.author)
                intent.putExtra("publishedDate", book.publishedDate)
                intent.putExtra("bookCover",book.bookCoverLink)

                startActivity(intent)


                //println("book id " + book.id + " book title " + book.title)





                // val gson = GsonBuilder().create()
                //  val homeFeed = gson.fromJson(body, HomeFeed::class.java)

                //val book = gson.fromJson(body,BookEntity.Book::class.java)

             //   println("book kind " + book.items.kind)
                /*runOnUiThread {
                    val my_recycler_view = findViewById(R.id.my_recycler_view) as RecyclerView?

//                    println("size " + homeFeed.Book1.get(0))
                    my_recycler_view?.adapter = MainAdapter(homeFeed)
                    my_recycler_view?.layoutManager = LinearLayoutManager(applicationContext)
                }*/
            }
        })

       /* val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)*/
    }
}

class HomeFeed(val Book1: List<Book1>)

class Book1(val title: String, val author: String, val isbn: Long, val year: String, val publisher: String)



