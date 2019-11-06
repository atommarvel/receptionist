package com.radiantmood.receptionist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.quest_collection_fragment.*
import javax.inject.Inject


class QuestHordeFragment : Fragment() {

    @Inject
    lateinit var vmFactory: ViewModelProvider.Factory

    private val viewModel: QuestHordeViewModel by lazy {
        ViewModelProviders.of(this, vmFactory).get(QuestHordeViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.quest_collection_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        App.appComponent.inject(this)
        setupRecyclerView()
        viewModel.fetchQuests()
    }

    private fun setupRecyclerView() {
        rvQuests.layoutManager = LinearLayoutManager(context)
        rvQuests.adapter = viewModel.adapter
    }

}
