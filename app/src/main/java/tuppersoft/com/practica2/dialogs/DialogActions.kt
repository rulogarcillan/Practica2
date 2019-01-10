package tuppersoft.com.practica2.dialogs

interface DialogActions {

    fun buttonPositive( requestCode: Int = -1)

    fun buttonNegative( requestCode: Int = -1)
}