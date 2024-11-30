package com.example.pid.adaptador

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.pid.R

class ViewTarea(item:View): RecyclerView.ViewHolder(item) {
    //declarar atributos
    var tvDescripcion:TextView
    var tvFechaVenci:TextView
    var tvPrioridad:TextView


    /*bloque init (referenciar)*/
    init{
        //Ejemplos
        tvDescripcion=item.findViewById(R.id.tvDescripItem)
        tvFechaVenci=item.findViewById(R.id.tvFechaVenciItem)
        tvPrioridad=item.findViewById(R.id.tvPrioridadItem)
    }
}