package br.edu.ifsp.dmo1.sorteador.view

import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import br.edu.ifsp.dmo1.sorteador.R
import br.edu.ifsp.dmo1.sorteador.databinding.ActivityMainBinding
import br.edu.ifsp.dmo1.sorteador.model.Draw

class MainActivity : AppCompatActivity(), OnClickListener {

    private lateinit var binding: ActivityMainBinding
    private var draw = Draw()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setClickLitener()
    }

    override fun onClick(v: View) {
        when(v){
            binding.buttonUseLimit -> {
                val limit: Int = try{
                    binding.editLimit.text.toString().toInt()
                }catch (e: NumberFormatException){
                    -1
                }
                draw = if(limit > 1)
                    Draw(limit)
                else
                    Draw()
                updateUI()
            }

            binding.buttonDraw -> {
                try {
                    binding.textviewExit.text = draw.getNumber().toString()
                    updateListView()
                }catch (e: IllegalStateException){
                    Toast.makeText(this, e.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun setClickLitener(){
        binding.buttonDraw.setOnClickListener(this)
        binding.buttonUseLimit.setOnClickListener(this)
    }

    private fun updateUI(){
        val str = String.format("Intervalo de 1 à %,d.", draw.getHighBorder())
        binding.textviewInterval.text = str
        binding.editLimit.text.clear()
        binding.textviewExit.text = getString(R.string.inicie_o_sorteio)
        updateListView()
    }

    private fun updateListView(){
        val sorteios = draw.getHistory()
        val adapter = SorteioAdapter(this, sorteios)
        binding.listviewDraw.adapter = adapter
    }
}