package com.mobiai.app.ui.fragment

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.mobiai.R
import com.mobiai.app.db.Note
import com.mobiai.app.viewmodel.NoteViewModel
import com.mobiai.base.basecode.ui.fragment.BaseFragment
import com.mobiai.databinding.FragmentAddBinding

class AddFragment : BaseFragment<FragmentAddBinding>() {
    private val viewModel: NoteViewModel by viewModels()
    companion object{
        fun instance() : AddFragment {
            return newInstance(AddFragment::class.java)
        }
    }
        override fun getBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentAddBinding {
            binding =  FragmentAddBinding.inflate(inflater, container, false)
            setupViews()
            return binding
        }

        private fun setupViews() {
            binding.arrowLeft.setOnClickListener {
                backNoteFragment()

            }
            binding.confirm.setOnClickListener {
                addNote()
                Toast.makeText(requireContext(), "Lưu thành công", Toast.LENGTH_SHORT).show()
                backNoteFragment()
            }
            binding.update.setOnClickListener {
                updateNote()
                backNoteFragment()
            }
        }

    private fun updateNote() {
            val bundle = arguments
            val id = bundle!!.getLong("id")
            val title = binding.editTextTitle.text.toString()
            val content = binding.editTextContent.text.toString()
            val updatedNote = Note(id, title, content)
            viewModel.updateNote(updatedNote)

    }

    override fun initView() {
            val bundle = arguments
            if (bundle != null) {

                val title = bundle.getString("title")
                val content = bundle.getString("content")

                // Đặt dữ liệu vào các EditText
                binding.editTextTitle.setText(title)
                binding.editTextContent.setText(content)
            }
    }

        private fun backNoteFragment() {
            val newFragment = NotesFragment()
            val fragmentManager = requireActivity().supportFragmentManager
            val transaction = fragmentManager.beginTransaction()
            transaction.replace(R.id.fragment_container_add, newFragment)
            transaction.addToBackStack(null)
            transaction.commit()
        }

        private fun addNote() {
            val note = Note(
                0,
                binding.editTextTitle.text.toString(),
                binding.editTextContent.text.toString()
            )
            viewModel.addNote(note)
            Log.d("Hoang", "initView: $note")
        }


    }