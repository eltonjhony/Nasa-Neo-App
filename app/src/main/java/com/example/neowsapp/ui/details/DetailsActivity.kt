package com.example.neowsapp.ui.details

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.example.neowsapp.R
import com.example.neowsapp.domain.AsteroidMetadata
import kotlinx.android.synthetic.main.activity_details.*

class DetailsActivity : AppCompatActivity() {

    companion object {
        private const val ASTEROID_EXTRA = "ASTEROID_EXTRA_PARAM"

        fun callingIntent(context: Context?, asteroid: AsteroidMetadata) {
            context?.startActivity(Intent(context, DetailsActivity::class.java).apply {
                putExtra(ASTEROID_EXTRA, asteroid)
            })
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        populateUi()
        setupListeners()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun populateUi() {
        (intent.getSerializableExtra(ASTEROID_EXTRA) as? AsteroidMetadata)?.apply {
            closeApproachDateValue.text = this.closeAbsoluteDate
            absoluteMagnitudeValue.text = this.absoluteMagnitude
            estimatedDiameterValue.text = this.estimatedDiameter
            relativeVelocityValue.text = this.relativeVelocity
            distanceFromEarthValue.text = this.distanceFromEarth
            if (this.isPotentiallyHazardousAsteroid)
                asteroidImageView.setImageDrawable(getDrawable(R.drawable.hazardous))
            else
                asteroidImageView.setImageDrawable(getDrawable(R.drawable.not_hazardous))
        }
    }

    private fun setupListeners() {
        helpImageView.setOnClickListener {
            AlertDialog.Builder(this).apply {
                setMessage(getString(R.string.help_text))
                setPositiveButton(getString(R.string.help_accept)) { dialog, _ ->
                    dialog.dismiss()
                }
                create()
                show()
            }
        }
    }

}
