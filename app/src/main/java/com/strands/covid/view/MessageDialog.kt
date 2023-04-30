package com.strands.covid.view

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.core.view.isVisible
import com.google.android.material.R.id.design_bottom_sheet
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetBehavior.STATE_EXPANDED
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.strands.covid.databinding.DialogMessageBinding
import com.strands.covid.util.emptyString

class MessageDialog : BottomSheetDialogFragment() {

    private lateinit var binding: DialogMessageBinding

    private var onButtonFirstClickListener: () -> Unit = {}
    private var onButtonSecondClickListener: () -> Unit = {}
    private var dismissListener: () -> Unit = {}

    var titleText: String = emptyString()
    var messageText: String = emptyString()
    var firstButtonText: String = emptyString()
    var secondButtonText: String = emptyString()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DialogMessageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
    }

    private fun setupView() = with(binding) {
        dialog?.setOnShowListener {
            val bottomSheet = dialog?.findViewById(design_bottom_sheet) as FrameLayout
            val behavior = BottomSheetBehavior.from(bottomSheet)
            behavior.state = STATE_EXPANDED
        }
        dialog?.setCancelable(false)
        dialog?.setCanceledOnTouchOutside(false)

        tvTitle.text = titleText
        tvMessage.text = messageText
        btnFirst.apply {
            isVisible = firstButtonText.isNotEmpty()
            text = firstButtonText
            setOnClickListener {
                onButtonFirstClickListener.invoke()
                dismissListener()
            }
        }
        btnSecond.apply {
            isVisible = secondButtonText.isNotEmpty()
            text = secondButtonText
            setOnClickListener {
                onButtonSecondClickListener.invoke()
                dismissListener()
            }
        }
    }

    fun setOnButtonFirstClickListener(listener: () -> Unit) {
        onButtonFirstClickListener = listener
    }

    fun setOnButtonSecondClickListener(listener: () -> Unit) {
        onButtonSecondClickListener = listener
    }

    fun setOnDismiss(listener: () -> Unit) {
        dismissListener = listener
    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
        dismissListener()
    }
}
