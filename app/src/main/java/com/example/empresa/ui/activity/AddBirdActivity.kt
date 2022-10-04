package com.example.empresa.ui.activity

import android.content.Intent
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.empresa.MainActivity
import com.example.empresa.R
import com.example.empresa.db.Empresa
import com.example.empresa.db.EmpresaDataBase
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class AddBirdActivity : AppCompatActivity() {

    private val menu by lazy<ImageView> { findViewById(R.id.my_birds_image) }
    private val save by lazy<ImageView> { findViewById(R.id.save) }

    private val id by lazy<EditText> { findViewById(R.id.id) }
    private val name by lazy<EditText> { findViewById(R.id.name) }
    private val image by lazy<EditText> { findViewById(R.id.image) }
    private val specie by lazy<EditText> { findViewById(R.id.specie) }
    private val dniBird by lazy<EditText> { findViewById(R.id.dni_bird) }
    private val state by lazy<EditText> { findViewById(R.id.state) }
    private val gender by lazy<EditText> { findViewById(R.id.gender) }

    lateinit var animationScale: Animation
    private val compositeDisposable = CompositeDisposable()
    private var empresaDatabase: EmpresaDataBase? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_empresa)
        animationScale = AnimationUtils.loadAnimation(this, R.anim.button_choice_small)
        setListeners(animationScale)
        empresaDatabase = EmpresaDataBase.getInstance(this)
    }

    private fun setListeners(animationScale: Animation) {
        menu.setOnClickListener {
            it.startAnimation(animationScale)
            onBackPressed()
        }
        save.setOnClickListener {
            it.startAnimation(animationScale)
            if (id.text.isNullOrEmpty() ||
                name.text.isNullOrEmpty() ||
                image.text.isNullOrEmpty() ||
                specie.text.isNullOrEmpty() ||
                dniBird.text.isNullOrEmpty() ||
                state.text.isNullOrEmpty() ||
                gender.text.isNullOrEmpty()
            ) {
                Toast.makeText(this, "Llenar todos los campos por favor", Toast.LENGTH_SHORT).show()
            } else {
                val empresa = Empresa(
                    Integer.valueOf(id.text.toString()),
                    name.text.toString(),
                    image.text.toString(),
                    specie.text.toString(),
                    dniBird.text.toString(),
                    state.text.toString(),
                    gender.text.toString()
                )
                compositeDisposable.add(Observable.fromCallable {
                    empresaDatabase?.birdsDao()?.insert(empresa)
                }
                    .subscribeOn(Schedulers.computation())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe {
                        Toast.makeText(this, "Registro Exitoso", Toast.LENGTH_SHORT).show()
                        val intent = Intent(applicationContext, MainActivity::class.java)
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
                        startActivity(intent)
                    })
            }
        }
    }
}