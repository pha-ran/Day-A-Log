package com.day_a_log.src.add

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.Menu
import android.view.MenuItem
import com.day_a_log.R
import com.day_a_log.config.ApplicationClass
import com.day_a_log.config.BaseActivity
import com.day_a_log.databinding.ActivityAddBinding
import com.day_a_log.src.add.log.AddLogFragment
import com.day_a_log.src.add.log.models.AddLogItem
import com.day_a_log.src.add.models.AddRoutineRequest
import com.day_a_log.src.add.models.AddRoutineResponse
import com.day_a_log.src.add.models.LogData
import com.day_a_log.src.add.routine.AddRoutineFragment
import com.day_a_log.src.add.routine.models.AddRoutineItem
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage

class AddActivity : BaseActivity<ActivityAddBinding>(ActivityAddBinding::inflate), AddRoutineView {

    internal val addRoutineItemList = ArrayList<AddRoutineItem>()
    internal val addLogItemList = ArrayList<AddLogItem>()
    internal var title : String? = null
    private var page = 0
    private var currentImageURI : Uri? = null
    internal var downloadImageURI = ArrayList<Uri>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var i = 0
        currentImageURI = null
        downloadImageURI.clear()
        for (i in 0..4) {
            downloadImageURI.add(Uri.EMPTY)
        }

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
                    //showCustomToast("$page")
                }
            }
            R.id.tb_add_next -> {
                if (page == 0) {
                    item.setIcon(R.drawable.ic_activity_add_upload)
                    replaceFragment(1)
                    //showCustomToast("$page")
                }
                else {
                    title = (supportFragmentManager.findFragmentById(R.id.frameLayout) as AddLogFragment).getTitle()
                    val logArray = mutableListOf<LogData>()
                    val s = addLogItemList
                    for(i in 1..s.size) {
                        logArray.add(
                            LogData(
                                s[i-1].time,
                                s[i-1].loc,
                                s[i-1].act,
                                s[i-1].log,
                                "",
                                "T",
                                0
                            ))
                    }

                    showLoadingDialog(this)
                    AddRoutineService(this).tryPostAddRoutine(AddRoutineRequest(
                        "T",
                        title!!,
                        "EMPTY",
                        "EMPTY",
                        logArray
                    ))
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }

    internal fun openGallery(i : Int) {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = MediaStore.Images.Media.CONTENT_TYPE
        when (i) {
            1 -> {startActivityForResult(intent, 1001)}
            2 -> {startActivityForResult(intent, 1002)}
            3 -> {startActivityForResult(intent, 1003)}
            4 -> {startActivityForResult(intent, 1004)}
            5 -> {startActivityForResult(intent, 1005)}
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == RESULT_OK){
            //ToDo uri 배열로 만든 후 번호 매칭 (requestCode) 해서 업로드, 다운로드
            when (requestCode) {
                1001 -> {
                    currentImageURI = data?.data
                    println("URI : $currentImageURI")
                    uploadImage()
                    //ToDo AddLogFragment의 이미지 설정함수 호출

                    (supportFragmentManager.findFragmentById(R.id.frameLayout) as AddLogFragment).setImage(1)
                }
                1002 -> {showCustomToast("2번 이미지")}
                1003 -> {showCustomToast("3번 이미지")}
                1004 -> {showCustomToast("4번 이미지")}
                1005 -> {showCustomToast("5번 이미지")}
                else -> {showCustomToast("갤러리 요청 코드 오류")}
            }
        }
        else{
            println("ActivityResult, something wrong")
        }
    }

    override fun onPostAddRoutineSuccess(response: AddRoutineResponse) {
        dismissLoadingDialog()
        showCustomToast(response.message)
        if (response.code == 1000) {
            finish()
        }
    }

    override fun onPostAddRoutineFailure(message: String) {
        dismissLoadingDialog()
        showCustomToast(message)
    }

    private fun uploadImage() {
        //파이어베이스 사진 업로드
        val storage = Firebase.storage
        val storageRef = storage.reference

        val imageRef = storageRef.child("test/${currentImageURI!!.lastPathSegment}")
        val uploadTask = imageRef.putFile(currentImageURI!!)

        // Register observers to listen for when the download is done or if it fails
        uploadTask.addOnFailureListener {
            // Handle unsuccessful uploads
            showCustomToast("업로드 실패, $it")
            println("업로드 실패, $it")
        }.addOnSuccessListener { taskSnapshot ->
            // taskSnapshot.metadata contains file metadata such as size, content-type, etc.
            showCustomToast("업로드 성공")
            println("$taskSnapshot")

            downloadImage()
        }
    }

    private fun downloadImage() {
        //파이어베이스 사진 다운로드
        val storage = Firebase.storage
        val storageRef = storage.reference

        storageRef.child("test/698263649").downloadUrl.addOnSuccessListener {
            println("다운로드 성공 : $it")
            downloadImageURI[3] = it
            println("다운로드 URI " + downloadImageURI[3])
        }.addOnFailureListener {
            println("다운로드 실패")
        }
    }
}