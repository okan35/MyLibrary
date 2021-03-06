package com.okan.pc.mylbrary.feature


import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.widget.TextView
import android.widget.Toast
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        validatePermission()



        val authorr:String = intent.getStringExtra("author") ?: ""
        val tittle:String = intent.getStringExtra("title") ?: ""
        val publishedDatee = intent.getStringExtra("publishedDate") ?: ""
        val bookCoverLink = intent.getStringExtra("bookCover") ?: ""


        author.text = authorr

        publishedDate.text = publishedDatee

        var title : TextView = findViewById(R.id.title)

        title.text = tittle

        Glide.with(this).load(bookCoverLink).into(bookCover)



        // val test = this.intent.getParcelableArrayListExtra<Book>("books")
        // var booklist = arrayListOf<Book1>()

        // booklist.add(Book1("okan","ssd",1,"1233","dcsdcds"))
       // var scanActivity = ScanActivity()
        //  println(scanActivity.booksMutable.size.toString())
        // Toast.makeText(this,scanActivity.booksMutable.size.toString(),Toast.LENGTH_SHORT).show()
        // Toast.makeText(this,)
       // var homefeed = HomeFeed(booklist)


       // my_recycler_view.layoutManager = LinearLayoutManager(applicationContext)
       // my_recycler_view.adapter = MainAdapter(homefeed)


        button_scan.setOnClickListener {
            val intent = Intent(this, ScanActivity::class.java)
            startActivity(intent)

        }

    }

    override fun onRequestPermissionsResult(requestCode: Int,
                                            permissions: Array<String>, grantResults: IntArray) {
        when (requestCode) {
            1 -> {
                // If request is cancelled, the result arrays are empty.
                if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.
                    /*  setContentView(mScannerView)
                      mScannerView!!.startCamera()*/
                } else {
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                    Toast.makeText(this, "Feature will not work without camera permission", Toast.LENGTH_SHORT).show()
                }
                return
            }

        // Add other 'when' lines to check for other
        // permissions this app might request.
            else -> {
                // Ignore all other requests.
            }
        }
    }

    public override fun onResume() {
        super.onResume()
    }

    public override fun onPause() {
        super.onPause()
    }

    private fun validatePermission() {
        val permission = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
        if (permission != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CAMERA), 1)

            // Permission is not granted
            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                            Manifest.permission.CAMERA)) {
                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
            } else {
                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(this,
                        arrayOf(Manifest.permission.CAMERA),
                        1)

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        } else {
            // Permission has already been granted
            /*setContentView(mScannerView)
            mScannerView!!.startCamera()*/
        }
    }


}
