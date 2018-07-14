package com.okan.pc.mylbrary.feature

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.widget.Toast
import com.google.gson.GsonBuilder
import com.google.zxing.Result
import kotlinx.android.synthetic.main.activity_main.*
import me.dm7.barcodescanner.zxing.ZXingScannerView
import okhttp3.*
import java.io.IOException


class ScanActivity : AppCompatActivity(), ZXingScannerView.ResultHandler {

    private var mScannerView: ZXingScannerView? = null
    var barcode: Long = 0
    //var books = ArrayList<Book>()
    var booksMutable = mutableListOf<Book>()

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

        try {
            booksMutable.add(Book(rawResult.text.toLong()))
        } catch (e: Exception) {

            e.printStackTrace()
        }
        val my_recycler_view = findViewById(R.id.my_recycler_view) as RecyclerView?
        /*       val my_recycler_view = findViewById(R.id.my_recycler_view) as RecyclerView?
               my_recycler_view?.layoutManager = LinearLayoutManager(this)
               my_recycler_view?.adapter = MainAdapter(booksMutable)*/
        println("books size " + booksMutable.size)
        my_recycler_view?.adapter = MainAdapter(booksMutable)
        val intent = Intent(this, MainActivity::class.java)

        //val test = ArrayList<Book>(booksMutable)
        //intent.putParcelableArrayListExtra("books",test)
        //onBackPressed()

        fetchJson(rawResult.text)

        startActivity(intent)
        // If you would like to resume scanning, call this method below:
        //mScannerView.resumeCameraPreview(this);


    }

    fun fetchJson( bookIsbn: String) {
        val url = "http://xisbn.worldcat.org/webservices/xid/isbn/"+ bookIsbn +"?method=getMetadata&format=json&fl=*"

        val request = Request.Builder().url(url).build()

        val client = OkHttpClient()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call?, e: IOException?) {
                println("Error in executing call")
            }

            override fun onResponse(call: Call?, response: Response?) {
                val body = response?.body()?.string()

                println(body)
                val gson = GsonBuilder().create()


                val bookList = gson.fromJson(body,BookList::class.java)
               // response?.close()

                
            }
        })
    }
}
class BookList (val list: List<list>)

class list (val title: String, val author: String, val isbn: LongArray, val year: String, val publisher: String)

