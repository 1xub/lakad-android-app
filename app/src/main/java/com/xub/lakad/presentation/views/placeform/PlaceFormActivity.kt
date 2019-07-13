package com.xub.lakad.presentation.views.placeform

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.widget.Toolbar
import com.xub.lakad.R
import com.xub.lakad.presentation.base.BaseActivity
import kotlinx.android.synthetic.main.activity_place_form.btn_submit
import kotlinx.android.synthetic.main.activity_place_form.et_description
import kotlinx.android.synthetic.main.activity_place_form.et_place_category
import kotlinx.android.synthetic.main.activity_place_form.et_place_name
import kotlinx.android.synthetic.main.activity_place_form.et_time
import kotlinx.android.synthetic.main.activity_place_form.toolbar
import org.koin.android.ext.android.inject

class PlaceFormActivity : BaseActivity<PlaceFormMvpView, PlaceFormPresenter>(),
    PlaceFormMvpView {

    companion object {
        const val PLACE = "PLACE"
        const val DESCRIPTION = "DESCRIPTION"
        const val CATEGORY = "CATEGORY"
        const val TIME = "TIME"
    }

    private val mPresenter by inject<PlaceFormPresenter>()

    override val viewRes: Int = R.layout.activity_place_form

    override fun getPresenter(): PlaceFormPresenter = mPresenter

    override fun getView(): PlaceFormMvpView = this

    override fun initViews(savedInstanceState: Bundle?) {
        btn_submit.setOnClickListener { submitForm() }

        /* init toolbar */
        setSupportActionBar(toolbar as Toolbar)

        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressed()
        }

        return super.onOptionsItemSelected(item)
    }

    private fun submitForm() {
        val place = et_place_name.text.toString()
        val description = et_description.text.toString()
        val category = et_place_category.text.toString()
        val time = et_time.text.toString()

        val intent = Intent()

        intent.putExtra(PLACE, place)
        intent.putExtra(DESCRIPTION, description)
        intent.putExtra(CATEGORY, category)
        intent.putExtra(TIME, time)

        setResult(RESULT_OK, intent)
        finish()
    }
}