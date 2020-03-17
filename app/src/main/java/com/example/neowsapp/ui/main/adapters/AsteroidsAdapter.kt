package com.example.neowsapp.ui.main.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.neowsapp.R
import com.example.neowsapp.domain.AsteroidMetadata
import kotlinx.android.synthetic.main.asteroids_item.view.*

class AsteroidsAdapter(private var asteroids: MutableList<AsteroidMetadata> = mutableListOf(),
                       val clickListener: (AsteroidMetadata) -> Unit): RecyclerView.Adapter<AsteroidsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AsteroidsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return AsteroidsViewHolder(inflater, parent)
    }

    override fun getItemCount(): Int = asteroids.size

    override fun onBindViewHolder(holder: AsteroidsViewHolder, position: Int) {
        val asteroid: AsteroidMetadata = asteroids[position]
        holder.bind(asteroid)
        holder.itemView.setOnClickListener { clickListener(asteroid) }
    }

    fun setAsteroids(asteroids: MutableList<AsteroidMetadata>) {
        this.asteroids = asteroids
        notifyDataSetChanged()
    }
}

class AsteroidsViewHolder(inflater: LayoutInflater, parent: ViewGroup): RecyclerView.ViewHolder(inflater.inflate(
    R.layout.asteroids_item, parent, false)) {

    fun bind(asteroid: AsteroidMetadata) {
        itemView.titleView.text = asteroid.name
        itemView.closeApproachDateView.text = asteroid.closeAbsoluteDate
        if (asteroid.isPotentiallyHazardousAsteroid)
            itemView.hazardousView.setImageDrawable(itemView.context.getDrawable(R.drawable.ic_bad_hazardous_24dp))
        else
            itemView.hazardousView.setImageDrawable(itemView.context.getDrawable(R.drawable.ic_hazardous_24dp))
    }

}