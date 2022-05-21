package com.songilcraft.sagak_sagak.screen.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.songilcraft.sagak_sagak.R
import com.songilcraft.sagak_sagak.config.BaseActivity
import com.songilcraft.sagak_sagak.config.BaseFragment
import com.songilcraft.sagak_sagak.config.GlobalApplication
import com.songilcraft.sagak_sagak.databinding.FragmentHomeBinding
import com.songilcraft.sagak_sagak.recycler.adapter.TicketAdapter
import com.songilcraft.sagak_sagak.recycler.decoration.TicketGridDecoration

class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::bind, R.layout.fragment_home) {

    private val viewModel : HomeViewModel by lazy { ViewModelProvider(this)[HomeViewModel::class.java] }
    private lateinit var dialog : BottomSheetDialog

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val dialogView = layoutInflater.inflate(R.layout.dialog_home_bottom_sheet, null)
        dialog = BottomSheetDialog(requireContext())

        dialog.setContentView(dialogView)
        dialog.setCanceledOnTouchOutside(false)

        val delete = dialogView.findViewById<ImageView>(R.id.home_bottom_back)

        delete.setOnClickListener {
            dialog.dismiss()
        }

        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setRecyclerView()
        setButton()
        setObserver()

        viewModel.tryGetPost(GlobalApplication.globalSharedPreferences.getInt(GlobalApplication.USER_IDX, 1))
    }

    private fun setRecyclerView(){
        binding.homeTicketRv.layoutManager = GridLayoutManager(requireContext(), 2)
        binding.homeTicketRv.adapter = TicketAdapter()
        binding.homeTicketRv.addItemDecoration(TicketGridDecoration(requireContext()))
    }

    private fun setButton() {
        binding.homeSelectCategoryIv.setOnClickListener {
            dialog.show()
        }
    }

    private fun setObserver() {
        val getPostObserver = Observer<Int> { resultCode ->
            when (resultCode){
                1000 -> {
                    (binding.homeTicketRv.adapter as TicketAdapter).applyData(viewModel.getPostData())
                }
                else -> {
                    (activity as BaseActivity<*>).showSimpleToastMessage("흠")
                }
            }
        }
        viewModel.getPostResult.observe(viewLifecycleOwner, getPostObserver)
    }


}