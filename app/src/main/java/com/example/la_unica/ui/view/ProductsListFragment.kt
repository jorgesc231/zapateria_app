package com.example.la_unica.ui.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.example.la_unica.databinding.FragmentProductsListBinding
import com.example.la_unica.domain.model.Product
import com.example.la_unica.ui.viewmodel.ProductViewModel

class ProductsListFragment : Fragment() {

    private lateinit var binding : FragmentProductsListBinding
    private lateinit var listAdapter : ListAdapter

    private val productViewModel : ProductViewModel by activityViewModels()
    private lateinit var nav_controller : NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentProductsListBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val list = binding.rvList

        nav_controller = view.findNavController()
        productViewModel.onCreate()

        listAdapter = ListAdapter {itemSelected(it)}
        list.setHasFixedSize(true)
        // Seteado en el xml
        //list.layoutManager = GridLayoutManager(this.context, 3)
        list.adapter = listAdapter

        productViewModel.productModel.observe(viewLifecycleOwner) {
            if (it.isEmpty()) {
                binding.rvList.visibility = View.GONE
                binding.tvEmptyList.visibility = View.VISIBLE
            } else {
                listAdapter.updateList(it)

                binding.rvList.visibility = View.VISIBLE
                binding.tvEmptyList.visibility = View.GONE
            }
        }

        productViewModel.isLoading.observe(viewLifecycleOwner) {
            binding.rvList.isVisible = !it
            binding.progress.isVisible = it
        }
    }

    private fun itemSelected(product: Product) {
        nav_controller.navigate(ProductsListFragmentDirections.actionProductsListFragmentToProductDetailFragment(product.id))
    }
}