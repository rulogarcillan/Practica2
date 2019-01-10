package tuppersoft.com.practica2.dialogs

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDialogFragment
import androidx.fragment.app.DialogFragment
import kotlinx.android.synthetic.main.fragment_dialog.view.*
import tuppersoft.com.data.Repository
import tuppersoft.com.domain.dbo.DialogData
import tuppersoft.com.practica2.R
import tuppersoft.com.practica2.usescases.global.THEME


class DialogAlert : AppCompatDialogFragment() {

    private lateinit var listener: DialogActions

    companion object {
        const val DIALOG_ARGS = "DIALOG_ARGS"

        fun newInstance(dialogData: DialogData): DialogAlert {
            val dialogAlert = DialogAlert()
            val args = Bundle()
            args.putParcelable(DIALOG_ARGS, dialogData)
            dialogAlert.arguments = args
            return dialogAlert
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        when (Repository.loadPreference(requireContext(), THEME, R.style.AppTheme) as Int) {
            R.style.AppTheme -> setStyle(DialogFragment.STYLE_NO_FRAME, R.style.DialogTheme)
            R.style.AppThemeDark -> setStyle(DialogFragment.STYLE_NO_FRAME, R.style.DialogThemeDark)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_dialog, container, false)

        if (arguments?.getParcelable<DialogData>(DIALOG_ARGS) != null) {
            var dialogData = arguments?.getParcelable<DialogData>(DIALOG_ARGS) as DialogData
            view.idTitle.text = dialogData.tittle
            view.idBody.text = dialogData.body
            view.idNegative.text = dialogData.negativeButton
            view.idPositive.text = dialogData.positiveButton

        }
        //  completeDialogInformation(view)
        //  val requestCode = arguments?.getParcelable<DialogDbo>(DIALOG_ARGS)?.requestCode ?: -1

        view.idPositive.setOnClickListener {
            dismiss()
            listener.buttonPositive()
        }

        view.idNegative.setOnClickListener {
            dismiss()
            listener.buttonNegative()
        }
        return view
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (parentFragment != null && parentFragment is DialogActions) {
            listener = parentFragment as DialogActions
        } else if (context is DialogActions) {
            listener = context
        } else if (childFragmentManager is DialogActions) {
            listener = childFragmentManager as DialogActions
        } else {
            RuntimeException("Error listener global action isnt implemented")
        }
    }

}