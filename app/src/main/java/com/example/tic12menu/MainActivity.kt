package com.example.tic12menu

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.tic12menu.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val pratosSelecionados = mutableListOf<String>()
    private var tempoTotalPreparo = 0
    private var valorTotalPedido = 0.0
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        configurarCheckboxes()
        confirmarPedido()
    }

    private fun configurarCheckboxes() {
        val checkboxes = listOf(
            binding.checkboxEntrada1,
            binding.checkboxEntrada2,
            binding.checkboxPrincipal1,
            binding.checkboxPrincipal2,
            binding.checkboxBebida1,
            binding.checkboxBebida2,
            binding.checkboxSobremesa1,
            binding.checkboxSobremesa2
        )

        checkboxes.forEach { checkbox ->
            checkbox.setOnCheckedChangeListener { _, isChecked ->
                val nomePrato = when (checkbox.id) {
                    R.id.checkbox_Entrada1 -> binding.nomeEntrada1.text.toString()
                    R.id.checkbox_Entrada2 -> binding.nomeEntrada2.text.toString()
                    R.id.checkbox_Principal1 -> binding.nomePrincipal1.text.toString()
                    R.id.checkbox_Principal2 -> binding.nomePrincipal2.text.toString()
                    R.id.checkbox_Bebida1 -> binding.nomeBebida1.text.toString()
                    R.id.checkbox_Bebida2 -> binding.nomeBebida2.text.toString()
                    R.id.checkbox_Sobremesa1 -> binding.nomeSobremesa1.text.toString()
                    R.id.checkbox_Sobremesa2 -> binding.nomeSobremesa2.text.toString()
                    else -> ""
                }

                val tempoPreparo = when (checkbox.id) {
                    R.id.checkbox_Entrada1 -> 10
                    R.id.checkbox_Entrada2 -> 10
                    R.id.checkbox_Principal1 -> 30
                    R.id.checkbox_Principal2 -> 25
                    R.id.checkbox_Bebida1 -> 5
                    R.id.checkbox_Bebida2 -> 5
                    R.id.checkbox_Sobremesa1 -> 7
                    R.id.checkbox_Sobremesa2 -> 8
                    else -> 0
                }

                val precoPrato = when (checkbox.id) {
                    R.id.checkbox_Entrada1 -> 25.90
                    R.id.checkbox_Entrada2 -> 26.90
                    R.id.checkbox_Principal1 -> 91.90
                    R.id.checkbox_Principal2 -> 86.90
                    R.id.checkbox_Bebida1 -> 17.90
                    R.id.checkbox_Bebida2 -> 15.90
                    R.id.checkbox_Sobremesa1 -> 13.90
                    R.id.checkbox_Sobremesa2 -> 18.90
                    else -> 0.0
                }

                if (isChecked) {
                    pratosSelecionados.add(nomePrato)
                    tempoTotalPreparo += tempoPreparo
                    valorTotalPedido += precoPrato
                } else {
                    pratosSelecionados.remove(nomePrato)
                    tempoTotalPreparo -= tempoPreparo
                    valorTotalPedido -= precoPrato
                }

                atualizarResumoPedido()
            }
        }
    }

    private fun atualizarResumoPedido() {
        if (pratosSelecionados.isEmpty()) {
            valorTotalPedido = 0.0
        }
        binding.itensSelecionados.text = pratosSelecionados.joinToString(", ")
        binding.tempoTotal.text = "$tempoTotalPreparo minutos"
        binding.precoTotal.text = "R$ %.2f".format(valorTotalPedido.coerceAtLeast(0.0))
    }

    private fun confirmarPedido() {
        binding.botaoConfirmarPedido.setOnClickListener {
            if (pratosSelecionados.isEmpty()) {
                Toast.makeText(
                    this, "Por favor, selecione pelo menos um produto.", Toast.LENGTH_SHORT
                ).show()
            } else {
                Toast.makeText(
                    this,
                    "Pedido enviado para o balcão do restaurante, obrigado pela preferência.",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }
}
