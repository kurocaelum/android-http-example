package android.imd.httpexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnRecuperar.setOnClickListener {
            var cep = edtCep.text.toString()
            var task = CepTask(txtResultado)
            var url = "https://viacep.com.br/ws/$cep/json"
//            var url = "https://viacep.com.br/ws/59069390/json"

            task.execute(url)
        }
    }
}
