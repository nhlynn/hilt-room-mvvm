package com.nhlynn.hilt_room_mvvm.ui

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.nhlynn.hilt_room_mvvm.R
import com.nhlynn.hilt_room_mvvm.databinding.ActivityEditNoteBinding
import com.nhlynn.hilt_room_mvvm.db.NoteEntity
import com.nhlynn.hilt_room_mvvm.utils.MyConstants
import com.nhlynn.hilt_room_mvvm.utils.MyUtil
import com.nhlynn.hilt_room_mvvm.view_model.NoteViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class EditNoteActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEditNoteBinding

    private lateinit var mNoteViewModel: NoteViewModel

    private var note: NoteEntity? = null

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        title = getString(R.string.note)

        mNoteViewModel = ViewModelProvider(this)[NoteViewModel::class.java]

        note = intent.getParcelableExtra(MyConstants.NOTE) ?: return

        binding.tvCreatedAt.text = "Created At : ${MyUtil.convertDateTime(note!!.createdAt)}"
        binding.tvUpdatedAt.text = "Updated At : ${MyUtil.convertDateTime(note!!.updatedAt)}"
        if (note!!.createdAt == note!!.updatedAt) {
            binding.tvUpdatedAt.visibility = View.GONE
        }
        binding.edtDescription.setText(note!!.description)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, EditNoteActivity::class.java)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.save_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_save -> {
                if (note != null) {
                    lifecycleScope.launch {
                        mNoteViewModel.updateNote(note!!.id, binding.edtDescription.text.toString())
                    }
                }
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}