package android.imd.httpexample


import android.os.AsyncTask
import android.widget.TextView
import org.json.JSONException
import org.json.JSONObject
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL

class CepTask(var txtResultado: TextView): AsyncTask<String, Void, String>() {

    // Implementa acesso HTTP
    override fun doInBackground(vararg string: String?): String {
        var stringUrl = string[0] // Apesar do nome, é um vararg
        var inputStream: InputStream? = null
        var inputStreamReader: InputStreamReader? = null
        var buffer: StringBuffer? = null

        try {
            var url: URL = URL(stringUrl)
           var conn  = url.openConnection() as HttpURLConnection

            inputStream = conn.inputStream
            inputStreamReader = InputStreamReader(inputStream)

            var reader = BufferedReader(inputStreamReader)

            buffer = StringBuffer()

            var linha: String? = null

            while (true){
                linha = reader.readLine()
                if (linha == null) break
                buffer.append(linha)
            }

        } catch (e: MalformedURLException){
            e.printStackTrace()
        } catch (e: IOException){
            e.printStackTrace()
        }

        return buffer.toString()
    }

    // Recebe resultado de doInBackground()
    override fun onPostExecute(result: String?) {
        super.onPostExecute(result)

        var logradouro: String? = null
        var bairro: String? = null

        try {
            var jsonObject = JSONObject(result)
            logradouro = jsonObject.getString("logradouro")
            bairro = jsonObject.getString("bairro")
        } catch (e: JSONException){
//            e.printStackTrace()
        }

        txtResultado.text = "$logradouro - $bairro"
    }

}