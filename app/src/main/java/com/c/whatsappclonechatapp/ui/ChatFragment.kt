package com.c.whatsappclonechatapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.c.whatsappclonechatapp.adapter.ChatAdapter
import com.c.whatsappclonechatapp.databinding.FragmentChatBinding
import com.c.whatsappclonechatapp.model.UserModel
import com.facebook.shimmer.ShimmerFrameLayout
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener


class ChatFragment : Fragment() {

    lateinit var binding: FragmentChatBinding
    private var database: FirebaseDatabase?=null
    lateinit var userList :ArrayList<UserModel>

    private val mShimmerViewContainer: ShimmerFrameLayout? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentChatBinding.inflate(layoutInflater)

        database= FirebaseDatabase.getInstance()
        userList= ArrayList()


        database!!.reference.child("users")
            .addValueEventListener(object : ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {
                    userList.clear()
                    for (snapshot1 in snapshot.children){
                        val user=snapshot1.getValue(UserModel::class.java)
                        if(user!!.uid!=FirebaseAuth.getInstance().uid){
                            binding.shimmerViewContainer.stopShimmerAnimation();
                            binding.shimmerViewContainer.visibility = View.GONE;
                            binding.userListRecyclerView.visibility = View.VISIBLE;
                            userList.add(user)
                        }
                    }

                    binding.userListRecyclerView.adapter=ChatAdapter(requireContext(),userList)

                }


                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }
            })


        return binding.root
    }

    override fun onResume() {
        super.onResume()
        binding.shimmerViewContainer.startShimmerAnimation()
    }

    override fun onPause() {
        binding.shimmerViewContainer.stopShimmerAnimation()
        super.onPause()
    }

}