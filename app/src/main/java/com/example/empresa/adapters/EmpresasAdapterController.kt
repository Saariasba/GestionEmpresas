package com.example.empresa.adapters

import android.content.Context
import com.airbnb.epoxy.AsyncEpoxyController
import com.example.empresa.db.Empresa
import com.example.empresa.ui.listeners.MyBirdsListener
import com.example.empresa.ui.views.MyBirdsItemViewModel_


class EmpresasAdapterController(
    private val listener: MyBirdsListener,
    private val context: Context
) : AsyncEpoxyController() {

    var list = listOf<Empresa>()

    fun setData(result: List<Empresa>) {
        cancelPendingModelBuild()
        list = result
        requestModelBuild()
    }

    override fun buildModels() {
        showBirds()
    }

    private fun showBirds() {
        list.forEach {
            MyBirdsItemViewModel_()
                .id(it.id_bird)
                .data(it)
                .listener(listener)
                .addTo(this)
        }
    }

}