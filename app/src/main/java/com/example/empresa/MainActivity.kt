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
    private val breedingPairs by lazy<ConstraintLayout> { findViewById(R.id.layout_two) }
    private val familyTree by lazy<ConstraintLayout> { findViewById(R.id.layout_three) }
    private val cages by lazy<ConstraintLayout> { findViewById(R.id.layout_four) }
    private val photoGallery by lazy<ConstraintLayout> { findViewById(R.id.layout_five) }
    private val chicksToRing by lazy<ConstraintLayout> { findViewById(R.id.layout_six) }
    private val incubation by lazy<ConstraintLayout> { findViewById(R.id.layout_seven) }
    private val settings by lazy<ConstraintLayout> { findViewById(R.id.layout_eight) }
    private val information by lazy<ConstraintLayout> { findViewById(R.id.layout_nine) }

    lateinit var animationScale: Animation

    private var empresaDatabase: EmpresaDataBase? = null
    private val compositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        animationScale = AnimationUtils.loadAnimation(this, R.anim.button_choice_small)
        setListeners(animationScale)
        empresaDatabase = EmpresaDataBase.getInstance(this)
        val empresa = Empresa(
            651,
            "ave uno",
            "image.jpg",
            "copetón",
            "13854351",
            "Reproducción",
            "Male"
        )
        val empresa2 = Empresa(
            5,
            "ave dos",
            "image.jpg",
            "copetón",
            "13854351",
            "Reproducción",
            "Male"
        )
        val empresa3 = Empresa(
            6,
            "ave tres",
            "image.jpg",
            "copetón",
            "13854351",
            "Reproducción",
            "Male"
        )
        val empresa4 = Empresa(
            61,
            "ave cuatro",
            "image.jpg",
            "copetón",
            "13854351",
            "Reproducción",
            "Female"
        )
        val empresa5 = Empresa(
            6251,
            "ave cinco",
            "image.jpg",
            "copetón",
            "13854351",
            "Reproducción",
            "Female"
        )
        val empresa6 = Empresa(
            651,
            "ave seis",
            "image.jpg",
            "copetón",
            "13854351",
            "Reproducción",
            "Female"
        )
        compositeDisposable.add(Observable.fromCallable { empresaDatabase?.birdsDao()?.insert(empresa) }
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe())
        compositeDisposable.add(Observable.fromCallable { empresaDatabase?.birdsDao()?.insert(empresa2) }
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe())
        compositeDisposable.add(Observable.fromCallable { empresaDatabase?.birdsDao()?.insert(empresa3) }
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe())
        compositeDisposable.add(Observable.fromCallable { empresaDatabase?.birdsDao()?.insert(empresa4) }
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe())
        compositeDisposable.add(Observable.fromCallable { empresaDatabase?.birdsDao()?.insert(empresa5) }
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe())
        compositeDisposable.add(Observable.fromCallable { empresaDatabase?.birdsDao()?.insert(empresa6) }
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe())
    }

    private fun setListeners(animationScale: Animation) {
        myBirds.setOnClickListener {
            it.startAnimation(animationScale)
            val intent = Intent(applicationContext, MyBirds::class.java)
            startActivity(intent)
        }
        breedingPairs.setOnClickListener {
            it.startAnimation(animationScale)
        }
        familyTree.setOnClickListener {
            it.startAnimation(animationScale)
        }
        cages.setOnClickListener {
            it.startAnimation(animationScale)
        }
        photoGallery.setOnClickListener {
            it.startAnimation(animationScale)
        }
        chicksToRing.setOnClickListener {
            it.startAnimation(animationScale)
        }
        incubation.setOnClickListener {
            it.startAnimation(animationScale)
        }
        settings.setOnClickListener {
            it.startAnimation(animationScale)
        }
        information.setOnClickListener {
            it.startAnimation(animationScale)
        }
    }
}