package com.example.empresa.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.empresa.R
import com.example.empresa.adapters.EmpresasAdapterController
import com.example.empresa.db.Empresa
import com.example.empresa.db.EmpresaDataBase
import com.example.empresa.ui.listeners.MyBirdsListener
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MyBirds : AppCompatActivity() {

    private val recyclerView by lazy<RecyclerView> { findViewById(R.id.recycler_view) }
    private val menu by lazy<ImageView> { findViewById(R.id.my_birds_image) }
    private val add by lazy<ImageView> { findViewById(R.id.add) }

    lateinit var animationScale: Animation
    private val compositeDisposable = CompositeDisposable()
    private var empresaDatabase: EmpresaDataBase? = null

    private val adapterController by lazy {
        EmpresasAdapterController(
            object : MyBirdsListener {
                override fun goToBird(topic: Empresa) {

                }
            },
            this
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_empresas)
        animationScale = AnimationUtils.loadAnimation(this, R.anim.button_choice_small)
        setListeners(animationScale)
        init()
    }

    private fun init() {
        empresaDatabase = EmpresaDataBase.getInstance(this)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapterController.adapter
        getBirds()
    }

    private fun setListeners(animationScale: Animation) {
        menu.setOnClickListener {
            it.startAnimation(animationScale)
            onBackPressed()
        }
        add.setOnClickListener {
            it.startAnimation(animationScale)
            val intent = Intent(applicationContext, AddBirdActivity::class.java)
            startActivity(intent)
        }
    }

    private fun getBirds() {
        compositeDisposable.add(Observable.fromCallable { empresaDatabase?.birdsDao()?.getBirds() }
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                it?.let {
                    adapterController.setData(it)
                }
            })
    }
}