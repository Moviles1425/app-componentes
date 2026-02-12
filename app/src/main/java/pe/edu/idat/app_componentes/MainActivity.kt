package pe.edu.idat.app_componentes

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.CheckBox
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import pe.edu.idat.app_componentes.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityMainBinding
    private val listHobbies = ArrayList<String>()
    private val listUsuarios = ArrayList<String>()
    private var estadoCivil = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        ArrayAdapter.createFromResource(this, R.array.estado_civil_array,
            android.R.layout.simple_spinner_item).also { adapter ->
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.spestadocivil.adapter = adapter
        }

        binding.btnregistrar.setOnClickListener(this)
        binding.btnlistar.setOnClickListener(this)
        binding.cbfutbol.setOnClickListener(this)
        binding.cbmusica.setOnClickListener(this)
        binding.cbotros.setOnClickListener(this)
    }
    //Recuperando información de la vista:
    fun getGenero():String {
        var genero = ""
        when(binding.rggenero.checkedRadioButtonId){
            R.id.rbmasculino -> genero = binding.rbmasculino.text.toString()
            R.id.rbfemenino -> genero = binding.rbfemenino.text.toString()
            R.id.rbotro -> genero = binding.rbotro.text.toString()
        }
        return genero
    }

    override fun onClick(vista: View) {
        if(vista is CheckBox){
            getHobbies(vista)
        }else{
            when(vista.id){
                R.id.btnregistrar -> registrarUsuario()
                R.id.btnlistar -> listarUsuarios()
            }
        }
    }
    fun getHobbies(vista: View){
        val checkbox = vista as CheckBox
        if(checkbox.isChecked){
            listHobbies.add(checkbox.text.toString())
        }else{
            listHobbies.remove(checkbox.text.toString())
        }
    }
    fun registrarUsuario(){

    }
    fun listarUsuarios(){

    }

}