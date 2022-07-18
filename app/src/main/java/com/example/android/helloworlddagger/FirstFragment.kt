package com.example.android.helloworlddagger

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.android.helloworlddagger.databinding.FragmentFirstBinding
import javax.inject.Inject

class FirstFragment : Fragment() {

    companion object {
        fun newInstance() = FirstFragment()
    }

    // View Binding
    private var _binding: FragmentFirstBinding? = null
    private val binding get() = _binding!!

    // Fields that need to be injected by the login graph
    @Inject lateinit var viewModel: FirstViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)

        // Obtaining the login graph from LoginActivity and instantiate
        // the @Inject fields with objects from the graph
        (activity as FirstActivity).firstSubcomponent.inject(this)

        // Now you can access viewModel here and onCreateView too
        // (shared instance with the Activity and the other Fragment)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentFirstBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            textViewFromInject.text = viewModel.text

            buttonFragment.setOnClickListener {
                parentFragmentManager
                    .beginTransaction()
                    .replace(R.id.fragment_container, SecondFragment.newInstance())
                    .commit()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
