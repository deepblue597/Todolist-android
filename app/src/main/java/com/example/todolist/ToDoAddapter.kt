package com.example.todolist

import android.graphics.Paint.STRIKE_THRU_TEXT_FLAG
import android.text.style.StrikethroughSpan
import android.util.Log
import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder



class ToDoAddapter(
    private val todos: MutableList<TodoItem>

) : RecyclerView.Adapter<ToDoAddapter.ToDoViewHolder>() {

    inner class ToDoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val todoItem: CheckBox = itemView.findViewById(R.id.todoItem)
        val description: TextView = itemView.findViewById(R.id.description)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToDoViewHolder { // create the view holder
        // how a specific item looks like
        val itemView = LayoutInflater.from(parent.context) // R -> resources
            .inflate(R.layout.item_todo, parent, false)     // false we do not want to attach it to the root layout so everytime flase

        return ToDoViewHolder(itemView)
    }

    private fun toggleStrike(todotile: TextView , isChecked : Boolean) {

        if(isChecked) {

            todotile.paintFlags = todotile.paintFlags or STRIKE_THRU_TEXT_FLAG

        }
        else {

            todotile.paintFlags = todotile.paintFlags or STRIKE_THRU_TEXT_FLAG.inv()
        }

    }

    fun addTodo(todo:TodoItem){
        todos.add(todo)
        notifyItemInserted(todos.size - 1)

    }

    fun deleteDoneTodos() {
        todos.removeAll { todo -> todo.done  }
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ToDoViewHolder, position: Int) { // bind the data to the views of our list
        // when a new to do is visible.
        val currentToDo = todos[position] // the current item
        holder.description.apply {
            text = currentToDo.description // the description of the todo item
        }
        holder.todoItem.apply {


            text = currentToDo.title // the title of the checkbox
            isChecked  = currentToDo.done // the check of the checkbox
            Log.d("default", "$isChecked")
            toggleStrike(this , currentToDo.done)
            setOnCheckedChangeListener { _ , isChecked ->
                toggleStrike(holder.todoItem, isChecked)
                 currentToDo.done =  !currentToDo.done // or !todoItem.isChecked
            }
        }


    }

    override fun getItemCount(): Int { // returns the amount of items
        return todos.size
    }
}