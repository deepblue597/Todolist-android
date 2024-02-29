package com.example.todolist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todolist.databinding.ActivityMainBinding



class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var todoAdapter: ToDoAddapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        todoAdapter = ToDoAddapter(mutableListOf())
        binding.todoItems.adapter = todoAdapter
        binding.todoItems.layoutManager = LinearLayoutManager(this)

        binding.addButtonTodo.setOnClickListener {
            val todoTitle = binding.TodoTitle.text.toString()

            if (todoTitle.isNotEmpty()) {
                val todo = TodoItem(todoTitle)
                todoAdapter.addTodo(todo)
                binding.TodoTitle.text.clear()
            }
        }

        binding.deleteButtonToDo.setOnClickListener {
            todoAdapter.deleteDoneTodos()
        }
    }
}