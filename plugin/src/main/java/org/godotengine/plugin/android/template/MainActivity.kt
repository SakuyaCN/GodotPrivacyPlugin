package org.godotengine.plugin.android.template

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowInsets
import android.view.WindowInsetsController
import android.view.WindowManager
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.material.card.MaterialCardView
import com.google.gson.Gson
import io.noties.markwon.Markwon
import io.noties.markwon.html.HtmlPlugin
import org.godotengine.plugin.android.template.entity.ConfigEntity
import kotlin.system.exitProcess

class MainActivity : AppCompatActivity()  {

    //声明sp 缓存
    private val sp by lazy {
        getSharedPreferences("godot_privacy", Activity.MODE_PRIVATE)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //判断是否同意
        if(sp.getBoolean("argee",false)){
            toGodotApp()
            return
        }
        setFullScreen()
        setContentView(R.layout.activity_main)
        try {
            loadJsonFromAsset().let {configEntity ->
                findViewById<TextView>(R.id.textview).apply {
                    val markwon =  Markwon.builder(context)
                        .usePlugin(HtmlPlugin.create { plugin -> plugin.addHandler(AlignTagHandler()) })
                        .build()
                    markwon.setMarkdown(this,loadTextFromAsset(configEntity.textPath))
                    setTextColor(Color.parseColor(configEntity.textColor))
                }

                findViewById<Button>(R.id.agree).apply {
                    setBackgroundColor(Color.parseColor(configEntity.agreeBtn.color))
                    setTextColor(Color.parseColor(configEntity.agreeBtn.textColor))
                }
                findViewById<Button>(R.id.quit).apply {
                    setBackgroundColor(Color.parseColor(configEntity.cancelBtn.color))
                    setTextColor(Color.parseColor(configEntity.cancelBtn.textColor))
                }
                findViewById<ConstraintLayout>(R.id.root).setBackgroundColor(Color.parseColor(configEntity.backgroundColor))
                findViewById<MaterialCardView>(R.id.content).setCardBackgroundColor(Color.parseColor(configEntity.contentColor))
            }
        }catch (e:Exception){
            e.printStackTrace()
        }

        findViewById<Button>(R.id.agree).setOnClickListener {
            //读取asset目录
            toGodotApp()
            //设置缓存argee 同意
            sp.edit().putBoolean("argee",true).apply()

        }
        findViewById<Button>(R.id.quit).setOnClickListener {
            exitProcess(0)
        }
    }

    private fun setFullScreen() {
        // 隐藏状态栏和导航栏
        window.decorView.systemUiVisibility =
            (View.SYSTEM_UI_FLAG_FULLSCREEN or
                    View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or
                    View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY)

        // 隐藏ActionBar（如果有的话）
        supportActionBar?.hide()

        // 设置透明状态栏和导航栏（适用于 Android 5.0 及以上版本）
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.statusBarColor = resources.getColor(android.R.color.transparent)
            window.navigationBarColor = resources.getColor(android.R.color.transparent)
        }

        // 如果需要适配水滴屏等异形屏
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            window.attributes.layoutInDisplayCutoutMode =
                WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES
        }

        // 针对 Android 11 及以上版本的新方式
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.insetsController?.let {
                it.hide(WindowInsets.Type.statusBars() or WindowInsets.Type.navigationBars())
                it.systemBarsBehavior =
                    WindowInsetsController.BEHAVIOR_DEFAULT
            }
        }
    }

    private fun toGodotApp(){
        val name = "com.godot.game.GodotApp"
        val clzz = Class.forName(name)
        startActivity(Intent(this,clzz))
        finish()
    }

    private fun loadJsonFromAsset():ConfigEntity{
        return Gson().fromJson(loadTextFromAsset("addons/GodotPrivacyPlugin/config.json"),ConfigEntity::class.java)
    }

    private fun loadTextFromAsset(path:String):String {
        val assetManager = assets
        val inputStream = assetManager.open(path.replace("res://",""))
        val bytes = ByteArray(inputStream.available())
        inputStream.read(bytes)
        val content = String(bytes)
        Log.e("读取文件",content)
        return content
    }

}