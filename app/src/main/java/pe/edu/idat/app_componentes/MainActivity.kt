package pe.edu.idat.app_componentes

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.CheckBox
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import pe.edu.idat.app_componentes.databinding.ActivityMainBinding
import pe.edu.idat.app_componentes.utils.AppMensaje
import pe.edu.idat.app_componentes.utils.TipoMensaje

class MainActivity : AppCompatActivity(), View.OnClickListener, AdapterView.OnItemSelectedListener {

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
        val infoUsuario = binding.etnombres.text.toString()+"-"+
                binding.etapellidos.text.toString()+"-"+
                getGenero()+"-"+
                listHobbies.toTypedArray().contentToString()+"-"+
                estadoCivil+"-"+binding.swnotificar.isChecked
        listUsuarios.add(infoUsuario)
        setearControles()
        AppMensaje.mensajeSnackBar(binding.root, "Usuario Registrado correctamente",
            TipoMensaje.SUCCESS)
    }
    fun listarUsuarios(){
        val intentLista = Intent(this, ListaActivity::class.java).apply {
            putExtra("listausuario", listUsuarios)
        }
        startActivity(intentLista)
    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, position: Int, p3: Long) {
        estadoCivil = if(position > 0){
            p0!!.getItemAtPosition(position).toString()
        }else ""
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
    }

    fun setearControles(){
        listHobbies.clear()
        binding.etnombres.text.clear()
        binding.etapellidos.text.clear()
        binding.swnotificar.isChecked = false
        binding.rggenero.clearCheck()
        binding.cbfutbol.isChecked = false
        binding.cbmusica.isChecked = false
        binding.cbotros.isChecked = false
        binding.spestadocivil.setSelection(0)
        binding.etnombres.isFocusableInTouchMode = true
        binding.etnombres.requestFocus()
    }

}