package com.example.pid.adaptador

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.pid.R

class ViewProyecto(item:View): RecyclerView.ViewHolder(item) {
    //declarar atributos
    var tvNombre:TextView
    var imgProyecto:ImageView


    /*bloque init (referenciar)*/
    init{
        //Ejemplos
        tvNombre=item.findViewById(R.id.tvNombreProyecto)
        imgProyecto=item.findViewById(R.id.imgProyecto)
    }
}