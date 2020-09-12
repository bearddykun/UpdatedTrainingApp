package com.example.updatedtrainingapp

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_general.*
import org.jetbrains.anko.find

abstract class GeneralActivity : AppCompatActivity() {

    override fun setContentView(layoutResID: Int) {
        val layoutInflater: LayoutInflater = layoutInflater

        val container =
            layoutInflater.inflate(R.layout.activity_general, window.decorView as ViewGroup, false)
        layoutInflater.inflate(
            layoutResID,
            container.findViewById(R.id.cont_root) as ViewGroup,
            true
        )
        super.setContentView(container)
        progressLay.visibility = View.GONE
    }

    fun showErrorSnack(error: String) {
        showErrorSnack(error, coordinatorLayout)
    }

    private fun showErrorSnack(error: CharSequence, coordinatorLayout: CoordinatorLayout) {
        val snackBar = Snackbar
            .make(coordinatorLayout, error, Snackbar.LENGTH_INDEFINITE)
            .setAction(R.string.app_error_dismiss) { }

        snackBar.view.setBackgroundColor(ContextCompat.getColor(this, R.color.white))
        snackBar.setActionTextColor(ContextCompat.getColor(this, R.color.black))

        val sbView = snackBar.view
        val textView = sbView.find<TextView>(com.google.android.material.R.id.snackbar_text)
        textView.setTextColor(ContextCompat.getColor(this, R.color.black))
        textView.maxLines = 10
        snackBar.show()
    }

    fun showProgressView() {
        progressLay.visibility = View.VISIBLE
    }

    fun hideProgressView() {
        progressLay.visibility = View.GONE
    }

    protected fun startActivity(activityClass: Class<*>) {
        startActivity(activityClass, false)
    }

    private fun startActivity(activityClass: Class<*>, lockBackAction: Boolean) {
        val intent = Intent(this, activityClass)
        if (lockBackAction) {
            intent.flags =
                Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_NO_ANIMATION or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        startActivity(intent)
    }
}
