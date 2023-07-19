package com.example.la_unica.ui.view

import android.content.Intent
import android.icu.text.NumberFormat
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.bumptech.glide.Glide
import com.example.la_unica.R
import com.example.la_unica.databinding.FragmentProductDetailBinding
import com.example.la_unica.ui.viewmodel.ProductViewModel
import java.util.Locale

class ProductDetailFragment : Fragment() {

    private lateinit var binding : FragmentProductDetailBinding
    private lateinit var args: ProductDetailFragmentArgs
    private lateinit var navController : NavController

    private val productViewModel : ProductViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            args = ProductDetailFragmentArgs.fromBundle(requireArguments())
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentProductDetailBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = view.findNavController()
        productViewModel.getProductDetail(args.productId)

        productViewModel.productDetail.observe(viewLifecycleOwner) { product ->
            if (product.id != -1) {
                Glide.with(binding.root)
                    .load(product.image)
                    .placeholder(R.drawable.ic_launcher_foreground)
                    .into(binding.ivProductImage)

                val number_format = NumberFormat.getNumberInstance(Locale.ITALIAN)
                number_format.isParseIntegerOnly = true

                binding.tvProductName.text = product.name
                binding.tvPrice.text = view.context.getString(R.string.price_format_with_dollar_sign, number_format.format(product.price))

                if (product.delivery!!) {
                    binding.chipDelivery.visibility = View.VISIBLE
                    binding.chipRetiro.visibility = View.GONE
                } else {
                    binding.chipDelivery.visibility = View.GONE
                    binding.chipRetiro.visibility = View.VISIBLE
                }


                if (product.origen != "Chile") {
                    binding.chipImported.text = product.origen
                    binding.chipImported.visibility = View.VISIBLE
                    binding.chipLocal.visibility = View.GONE
                } else {
                    binding.chipLocal.text = product.origen
                    binding.chipImported.visibility = View.GONE
                    binding.chipLocal.visibility = View.VISIBLE
                }

                binding.chipSize.text = product.size.toString()

                binding.tvOrigin.text = view.context.getString(R.string.origin_text, product.origen)


                binding.btContact.setOnClickListener {
                    val addresses = arrayOf("Zapato.ventas@unica.cl")
                    val subject = "Consulta por Producto ${product.name} id ${product.id}"
                    val message = "“Hola\n" +
                            "Vi el producto ${product.name} con codigo ${product.id} y me gustaría\n" +
                            "que me contactaran a este correo o al siguiente número: __________\n" +
                            "Quedo atento.”"

                    val intent = Intent(Intent.ACTION_SENDTO)
                    intent.data = Uri.parse("mailto:")
                    intent.putExtra(Intent.EXTRA_EMAIL, addresses)
                    intent.putExtra(Intent.EXTRA_SUBJECT, subject)
                    intent.putExtra(Intent.EXTRA_TEXT, message)

                    if (intent.resolveActivity(requireActivity().packageManager) != null) {
                        startActivity(intent)
                    }
                }

            } else {
                // TODO: Error el obtener el producto de la api
            }
        }
    }
}