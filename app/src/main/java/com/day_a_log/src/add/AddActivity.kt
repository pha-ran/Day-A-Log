package com.day_a_log.src.add

import android.content.Intent
import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.util.Base64
import android.util.Base64.NO_WRAP
import android.view.Menu
import android.view.MenuItem
import com.day_a_log.R
import com.day_a_log.config.BaseActivity
import com.day_a_log.databinding.ActivityAddBinding
import com.day_a_log.src.add.log.AddLogFragment
import com.day_a_log.src.add.log.models.AddLogItem
import com.day_a_log.src.add.routine.AddRoutineFragment
import com.day_a_log.src.add.routine.models.AddRoutineItem
import java.io.ByteArrayOutputStream
import java.io.InputStream

class AddActivity : BaseActivity<ActivityAddBinding>(ActivityAddBinding::inflate) {

    internal val addRoutineItemList = ArrayList<AddRoutineItem>()
    internal val addLogItemList = ArrayList<AddLogItem>()
    internal var title : String? = null
    private var page = 0
    internal var currentImageURL : Uri? = null
    internal var currentBitmap : Bitmap? = null
    internal var profileImageBase64 : String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        currentImageURL = null
        currentBitmap = null
        profileImageBase64 = null

        setSupportActionBar(binding.toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setHomeAsUpIndicator(R.drawable.ic_activity_add_back)

        replaceFragment(0)
    }

    private fun replaceFragment(p : Int) {
        if (p == 0) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.frameLayout, AddRoutineFragment()).commitAllowingStateLoss()
            page = 0
        }
        else {
            supportFragmentManager.beginTransaction()
                .replace(R.id.frameLayout, AddLogFragment()).commitAllowingStateLoss()
            page = 1
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.tb_add_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                if (page == 0) {
                    showCustomToast("종료")
                    finish()
                }
                else {
                    setSupportActionBar(binding.toolbar)
                    replaceFragment(0)
                    showCustomToast("$page")
                }
            }
            R.id.tb_add_next -> {
                if (page == 0) {
                    item.setIcon(R.drawable.ic_activity_add_upload)
                    replaceFragment(1)
                    showCustomToast("$page")
                }
                else {
                    showCustomToast("$page, 업로드")
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }

    internal fun openGallery() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = MediaStore.Images.Media.CONTENT_TYPE
        startActivityForResult(intent, 200) //FLAG_REQ_STORAGE
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 200 && resultCode == RESULT_OK){
            currentImageURL = data?.data
            println("URI : $currentImageURL")
            currentBitmap = MediaStore.Images.Media.getBitmap(contentResolver, currentImageURL)
            println("BITMAP : $currentBitmap")

            println("이미지 경로 : "+currentImageURL+"\n"+getRealPathFromURI(currentImageURL!!))

            /*
            // Base64 인코딩부분
            val ins: InputStream? = currentImageURL?.let {
                applicationContext.contentResolver.openInputStream(
                    it
                )
            }
            val img: Bitmap = BitmapFactory.decodeStream(ins)
            ins?.close()
            val resized = Bitmap.createScaledBitmap(img, 256, 256, true)
            val byteArrayOutputStream = ByteArrayOutputStream()
            resized.compress(Bitmap.CompressFormat.JPEG, 60, byteArrayOutputStream)
            val byteArray: ByteArray = byteArrayOutputStream.toByteArray()
            val outStream = ByteArrayOutputStream()
            val res: Resources = resources
            profileImageBase64 = Base64.encodeToString(byteArray, NO_WRAP)
            // 여기까지 인코딩 끝
            println("인코딩 후 : $profileImageBase64")
             */
        } else{
            println("ActivityResult, something wrong")
        }
    }

    private fun getRealPathFromURI(uri: Uri): String {
        var buildName = Build.MANUFACTURER
        var columnIndex = 0
        var proj = arrayOf(MediaStore.Images.Media.DATA)
        var cursor = contentResolver.query(uri, proj, null, null, null)
        if (cursor!!.moveToFirst()) {
            columnIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
        }
        return cursor.getString(columnIndex)
    }
}