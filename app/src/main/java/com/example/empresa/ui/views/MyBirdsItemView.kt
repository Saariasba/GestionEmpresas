package com.example.empresa.ui.views

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.airbnb.epoxy.*
import com.example.empresa.R
import com.example.empresa.db.Empresa
import com.example.empresa.ui.listeners.MyBirdsListener


@ModelView(autoLayout = ModelView.Size.MATCH_WIDTH_WRAP_HEIGHT)
class MyBirdsItemView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0

) : ConstraintLayout(context, attrs, defStyleAttr) {

    private val id by lazy<TextView> { findViewById(R.id.id) }
    private val name by lazy<TextView> { findViewById(R.id.name) }
    private val dni by lazy<TextView> { findViewById(R.id.dni) }
    private val cage by lazy<TextView> { findViewById(R.id.cage) }
    private val gender by lazy<ImageView> { findViewById(R.id.gender) }
    private val state by lazy<TextView> { findViewById(R.id.state) }
    private val container by lazy<ConstraintLayout> { findViewById(R.id.container) }

    private val animationScale: Animation =
        AnimationUtils.loadAnimation(context, R.anim.button_choice_small)
    private var listener: MyBirdsListener? = null

    private lateinit var data: Empresa

    init {
        View.inflate(context, R.layout.empresa_list_item, this)
        listeners()
    }

    private fun listeners() {
        container.setOnClickListener {
            it.startAnimation(animationScale)
        }
    }

    @ModelProp
    fun setData(data: Empresa) {
        this.data = data
    }

    @CallbackProp
    fun setListener(listener: MyBirdsListener?) {
        this.listener = listener
    }

    @AfterPropsSet
    fun setInfo() {
        id.text = data.id_bird.toString()
        name.text = data.name
        dni.text = data.dni_bird
        state.text = data.state
        if (data.gender == male) {
            gender.setImageDrawable(resources.getDrawable(R.drawable.male))
        } else {
            gender.setImageDrawable(resources.getDrawable(R.drawable.female))
        }
    }

    companion object {
        const val male = "Male"
    }
}