package com.strands.covid.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.strands.covid.R
import com.strands.covid.util.emptyString
import com.strands.covid.view.MessageDialog

abstract class BaseActivity : AppCompatActivity() {

    abstract fun getLayoutResource(): Int
    abstract fun setupView()
    abstract fun subscribeState()

    protected open fun getLayoutBinding(): View? = null

    private var progressView: View? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupContentLayout()
        setupView()
        subscribeState()
    }

    private fun setupContentLayout() {
        if (getLayoutBinding() != null) return setContentView(getLayoutBinding())
        setContentView(getLayoutResource())
    }

    fun showLoadingScreen(isLoading: Boolean) {
        val viewGroup = window.decorView as ViewGroup
        if (progressView != null) viewGroup.removeView(progressView)
        else progressView =
            LayoutInflater.from(this).inflate(R.layout.layout_loading_screen, viewGroup, false)

        if (isLoading) viewGroup.addView(progressView)
        else {
            viewGroup.removeView(progressView)
            progressView = null
        }
    }

    fun showMessageDialog(
        title: String = emptyString(),
        message: String = emptyString(),
        firstButton: String = emptyString(),
        secondButton: String = emptyString(),
        firstButtonClickAction: () -> Unit = {},
        secondButtonClickAction: () -> Unit = {}
    ) {
        val messageDialog = MessageDialog()
        messageDialog.apply {
            titleText = title
            messageText = message
            firstButtonText = firstButton
            secondButtonText = secondButton
            setOnButtonFirstClickListener { firstButtonClickAction() }
            setOnButtonSecondClickListener { secondButtonClickAction() }
            setOnDismiss { messageDialog.dismiss() }
            show(supportFragmentManager, MessageDialog::class.java.name)
        }
    }
}
