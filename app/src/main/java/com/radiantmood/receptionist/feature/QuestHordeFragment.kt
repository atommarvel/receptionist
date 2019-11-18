package com.radiantmood.receptionist.feature

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import com.radiantmood.receptionist.R
import com.radiantmood.receptionist.core.App
import com.radiantmood.receptionist.di.DaggerFragmentComponent
import com.radiantmood.receptionist.di.FragmentComponent
import com.radiantmood.receptionist.ext.toggleSelected
import kotlinx.android.synthetic.main.quest_collection_fragment.*
import javax.inject.Inject

class QuestHordeFragment : Fragment() {

    lateinit var fragmentComponent: FragmentComponent

    @Inject
    lateinit var vmFactory: ViewModelProvider.Factory
    @Inject
    lateinit var actionModeManager: ActionModeManager
    @Inject
    lateinit var adapter: QuestHordeRVAdapter

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
        fragmentComponent = DaggerFragmentComponent.builder()
            .appComponent(App.appComponent)
            .build()
            .also { it.inject(this) }
        setupRecyclerView()
        observeActionModeInfo()
        viewModel.fetchQuests()
    }

    private fun setupRecyclerView() {
        rvQuests.layoutManager = LinearLayoutManager(context)
        rvQuests.adapter = adapter
        ItemTouchHelper(
            ItemTouchHelperCallback(viewModel, actionModeManager::isActionModeEnabled)
        ).attachToRecyclerView(rvQuests)
        viewModel.questsLiveData.observe(this, Observer {
            adapter.submitList(ArrayList(it))
        })
        adapter.isSelected = viewModel::isSelected
        adapter.itemClickObserver.observe(this, Observer { clickEvent ->
            if (actionModeManager.isActionModeEnabled) {
                toggleRVItemSelected(clickEvent.position)
            }
            Toast.makeText(context, "${clickEvent.quest?.title} tapped", Toast.LENGTH_SHORT).show()
        })
    }


    private fun observeActionModeInfo() {
        viewModel.actionModeLiveData.observe(this, Observer { info ->
            when {
                !actionModeManager.isActionModeEnabled && info.enable -> {
                    actionModeManager.initActionMode(this, viewModel)
                    toggleRVItemSelected(info.position)
                }
                !info.enable -> actionModeManager.finishActionMode(this)
            }
        })
    }

    private fun toggleRVItemSelected(position: Int) {
        viewModel.toggleSelected(position)
        rvQuests.toggleSelected(position)
        actionModeManager.setTitle("${viewModel.selectedCount()} Selected")
    }
}