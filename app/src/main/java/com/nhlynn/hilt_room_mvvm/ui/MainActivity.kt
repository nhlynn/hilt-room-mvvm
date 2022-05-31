package com.nhlynn.hilt_room_mvvm.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.nhlynn.hilt_room_mvvm.R
import com.nhlynn.hilt_room_mvvm.adapter.NoteAdapter
import com.nhlynn.hilt_room_mvvm.databinding.ActivityMainBinding
import com.nhlynn.hilt_room_mvvm.db.NoteEntity
import com.nhlynn.hilt_room_mvvm.delegate.NoteDelegate
import com.nhlynn.hilt_room_mvvm.utils.MyConstants
import com.nhlynn.hilt_room_mvvm.view_model.NoteViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), NoteDelegate {
    private lateinit var binding: ActivityMainBinding

    private lateinit var mNoteViewModel: NoteViewModel

    private lateinit var mNoteAdapter: NoteAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        title = getString(R.string.app_name)

        mNoteViewModel = ViewModelProvider(this)[NoteViewModel::class.java]

        mNoteAdapter = NoteAdapter(this)
        binding.rvNote.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.rvNote.adapter = mNoteAdapter

        binding.fabCreate.setOnClickListener {
            startActivity(CreateNoteActivity.newIntent(this))
        }
    }

    override fun onResume() {
        super.onResume()

        lifecycleScope.launch {
            mNoteViewModel.getAllNote().observe(this@MainActivity) {
                mNoteAdapter.setData(it)
            }
        }
    }

    override fun onViewNote(note: NoteEntity) {
        startActivity(EditNoteActivity.newIntent(this).putExtra(MyConstants.NOTE, note))
    }
}