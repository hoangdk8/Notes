package com.mobiai.app.ui.fragment

import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.ViewGroup
import android.widget.Toast
import com.mobiai.R
import com.mobiai.base.basecode.ui.fragment.BaseFragment
import com.mobiai.databinding.FragmentHomeBinding
import org.mariuszgromada.math.mxparser.Expression


class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    companion object {
        fun instance(): HomeFragment {
            return newInstance(HomeFragment::class.java)
        }
    }

    @SuppressLint("SetTextI18n")
    override fun initView() {
        binding.buttonAC.setOnClickListener {
            binding.textViewResult.text = ""
            binding.textViewInput.text = ""
        }
        binding.buttonDelete.setOnClickListener {
            val removedLast = binding.textViewResult.text.toString().dropLast(1)
            binding.textViewResult.text = removedLast
        }
        binding.button0.setOnClickListener {
            binding.textViewResult.text = binding.textViewResult.text.toString() + "0"
        }
        binding.button1.setOnClickListener {
            binding.textViewResult.text = binding.textViewResult.text.toString() + "1"
        }
        binding.button2.setOnClickListener {
            binding.textViewResult.text = binding.textViewResult.text.toString() + "2"
        }
        binding.button3.setOnClickListener {
            binding.textViewResult.text = binding.textViewResult.text.toString() + "3"
        }
        binding.button4.setOnClickListener {
            binding.textViewResult.text = binding.textViewResult.text.toString() + "4"
        }
        binding.button5.setOnClickListener {
            binding.textViewResult.text = binding.textViewResult.text.toString() + "5"
        }
        binding.button6.setOnClickListener {
            binding.textViewResult.text = binding.textViewResult.text.toString() + "6"
        }
        binding.button7.setOnClickListener {
            binding.textViewResult.text = binding.textViewResult.text.toString() + "7"
        }
        binding.button8.setOnClickListener {
            binding.textViewResult.text = binding.textViewResult.text.toString() + "8"
        }
        binding.button9.setOnClickListener {
            binding.textViewResult.text = binding.textViewResult.text.toString() + "9"
        }
        binding.buttonCham.setOnClickListener {
            binding.textViewResult.text = binding.textViewResult.text.toString() + "."
        }
        binding.buttonChia.setOnClickListener {
            binding.textViewInput.text =
                binding.textViewAmduong.text.toString() + binding.textViewResult.text.toString() + "÷"
            binding.textViewResult.text = ""
            if (binding.textViewAmduong.text == "-") {
                binding.textViewAmduong.text = ""
            }
        }
        binding.buttonNhan.setOnClickListener {
            binding.textViewInput.text =
                binding.textViewAmduong.text.toString() + binding.textViewResult.text.toString() + "×"
            binding.textViewResult.text = ""
            if (binding.textViewAmduong.text == "-") {
                binding.textViewAmduong.text = ""
            }
        }
        binding.buttonTru.setOnClickListener {
            binding.textViewInput.text =
                binding.textViewAmduong.text.toString() + binding.textViewResult.text.toString() + "-"
            binding.textViewResult.text = ""
            if (binding.textViewAmduong.text == "-") {
                binding.textViewAmduong.text = ""
            }
        }
        binding.buttonCong.setOnClickListener {
            binding.textViewInput.text =
                binding.textViewAmduong.text.toString() + binding.textViewResult.text.toString() + "+"
            binding.textViewResult.text = ""
            if (binding.textViewAmduong.text == "-") {
                binding.textViewAmduong.text = ""
            }
        }
        binding.buttonBang.setOnClickListener {
            if (binding.textViewAmduong.text == "-") {
                binding.textViewAmduong.text = ""
            }
            else if (binding.textViewResult.text == "0000") {
                binding.buttonBang.visibility = View.INVISIBLE
                val newFragment = NotesFragment()
                val fragmentManager = requireActivity().supportFragmentManager
                val transaction = fragmentManager.beginTransaction()
                transaction.replace(R.id.fragment_container_home, newFragment)
                transaction.addToBackStack(null)
                transaction.commit()
            }
            else if (binding.textViewResult.text == "1111") {
                binding.buttonBang.visibility = View.INVISIBLE
                val newFragment = WebFragment()
                val fragmentManager = requireActivity().supportFragmentManager
                val transaction = fragmentManager.beginTransaction()
                transaction.replace(R.id.fragment_container_home, newFragment)
                transaction.addToBackStack(null)
                transaction.commit()
            }
            binding.textViewInput.text =
                        binding.textViewInput.text.toString() +
                        binding.textViewAmduong.text.toString() +
                        binding.textViewResult.text.toString()
            val expressionString = binding.textViewInput.text.toString()
            try {
                val expression = Expression(expressionString)
                val result = expression.calculate()
                val formattedResult = if (result % 1 == 0.0) {
                    result.toInt().toString()
                } else {
                    result.toString()
                }
                binding.textViewResult.text = formattedResult
            } catch (e: Exception) {
                binding.textViewResult.text = "Error"
            }
        }
        binding.buttonAmduong.setOnClickListener {
            if (binding.textViewAmduong.text == "") {
                binding.textViewAmduong.text = "-"
            } else {
                binding.textViewAmduong.text = ""
            }
        }
    }

    override fun getBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentHomeBinding {
        return FragmentHomeBinding.inflate(inflater, container, false)
    }
}