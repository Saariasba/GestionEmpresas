package com.example.empresa

import android.content.Intent
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.empresa.db.Empresa
import com.example.empresa.db.EmpresaDataBase
import com.example.empresa.ui.activity.MyBirds
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MainActivity : AppCompatActivity() {

    private val myBirds by lazy<ConstraintLayout> { findViewById(R.id.layout_one) }

    lateinit var animationScale: Animation

    private var empresaDatabase: EmpresaDataBase? = null
    private val compositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        animationScale = AnimationUtils.loadAnimation(this, R.anim.button_choice_small)
        setListeners(animationScale)
        empresaDatabase = EmpresaDataBase.getInstance(this)
    }

    private fun setListeners(animationScale: Animation) {
        myBirds.setOnClickListener {
            it.startAnimation(animationScale)
            val intent = Intent(applicationContext, MyBirds::class.java)
            startActivity(intent)
        }
    }
}