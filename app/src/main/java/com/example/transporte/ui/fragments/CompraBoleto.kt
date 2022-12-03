package com.example.transporte.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.transporte.R
import com.example.transporte.ui.fragments.model.BoletoModel
import com.example.transporte.ui.fragments.model.RutaModel
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.firestore.FirebaseFirestore
import java.util.*

class CompraBoleto: Fragment() {

        override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {

            // Inflate the layout for this fragment
            val view: View = inflater.inflate(R.layout.fragment_compra_boleto, container, false)

            val btnRegistrar: Button = view.findViewById(R.id.btnRegistrar)

            val tvNameRuta: TextView = view.findViewById(R.id.etNameRuta)
            val tvnamepasajero: TextView = view.findViewById(R.id.etnamepasajero)
            val tvdnipasajero: TextView = view.findViewById(R.id.etdnipasajero)
            val tvCantBol: TextView = view.findViewById(R.id.etCantBol)


            val db = FirebaseFirestore.getInstance()


            btnRegistrar.setOnClickListener{
                val name_ruta = tvNameRuta.text.toString()
                val name_pasajero = tvnamepasajero.text.toString()
                val dni_pasajero = tvdnipasajero.text.toString()
                val cantidad = tvCantBol.text.toString()


                val nuevaBoleto = BoletoModel(name_ruta, name_pasajero, dni_pasajero, cantidad)
                val id: UUID = UUID.randomUUID()

                db.collection("boleto")
                    .document(id.toString())
                    .set(nuevaBoleto)
                    .addOnSuccessListener {
                        showAlert(view, "Ruta registrada correctamente")
                        tvNameRuta.text = ""
                        tvnamepasajero.text = ""
                        tvdnipasajero.text = ""
                        tvCantBol.text = ""


                    }.addOnFailureListener{
                        showAlert(view, "Ocurrió un error al registrar ruta")
                    }

            }

            return view
        }
        private fun showAlert(vista: View, mensaje: String){
            Snackbar.make(vista, mensaje, Snackbar.LENGTH_LONG).show()

        }


    }