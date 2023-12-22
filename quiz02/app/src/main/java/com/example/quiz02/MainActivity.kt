import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.quiz02.R

class MainActivity : AppCompatActivity() {
    private lateinit var button: Button
    private lateinit var button2: Button
    private lateinit var button3: Button
    private lateinit var myListView: ListView
    private lateinit var textView3: TextView
    private lateinit var textView5: TextView
    private lateinit var editTextText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initUI()
    }

    private fun initUI() {
        button = findViewById(R.id.button)
        button2 = findViewById(R.id.button2)
        button3 = findViewById(R.id.button3)
        myListView = findViewById(R.id.myListView)
        textView3 = findViewById(R.id.textView3)
        textView5 = findViewById(R.id.textView5)
        editTextText = findViewById(R.id.editTextText)

        val koleksiyonCinsleri = resources.getStringArray(R.array.koleksiyon_cinsleri)
        val koleksiyonDurumlari = resources.getStringArray(R.array.koleksiyon_durumlari)

        val items = mutableListOf<String>()
        val customAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, items)
        myListView.adapter = customAdapter

        myListView.setOnItemClickListener { _, _, position, _ ->
            val secilenItem = items[position]
            textView3.text = secilenItem
            textView5.text = secilenItem

        }

        button.setOnClickListener {
            myListView.visibility = android.view.View.VISIBLE
            items.clear()
            items.addAll(koleksiyonCinsleri)
            customAdapter.notifyDataSetChanged()
        }

        button2.setOnClickListener {
            myListView.visibility = android.view.View.VISIBLE
            items.clear()
            items.addAll(koleksiyonDurumlari)
            customAdapter.notifyDataSetChanged()
        }

        button3.setOnClickListener {
            val isim = editTextText.text.toString()
            val secilenCins = textView3.text.toString()
            val secilenDurum = textView5.text.toString()

            val paylasMetni = "Merhaba, ben $isim. $secilenCins koleksiyonu biriktiriyorum. Koleksiyonumun durumu $secilenDurum."

            val intent = Intent(Intent.ACTION_SEND)
            intent.type = "text/plain"
            intent.putExtra(Intent.EXTRA_TEXT, paylasMetni)

            startActivity(Intent.createChooser(intent, "Paylaş"))
        }
    }
}
