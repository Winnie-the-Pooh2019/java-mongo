package com.example.javamongo.controller

import com.example.javamongo.controller.dto.UiDto
import com.example.javamongo.data.entity.Entity
import com.example.javamongo.services.interfaces.MongoService
import kotlinx.coroutines.runBlocking
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam

abstract class CommonController<T : Entity, U: UiDto>(private val service: MongoService<T>, protected val clazz: Class<T>) {
    @GetMapping
    fun getAll(model: Model): String {
        val objects = runBlocking {
            service.findAll().map { it.toUi() }
        }
        val className = clazz.simpleName.lowercase()
        println(className)
        model.addAttribute("${className}s", objects)

        return "$className/${className}s"
    }

    @GetMapping("/byId")
    fun getSingle(@RequestParam(name = "id", required = true) id: String, model: Model): String {
        val obj = runBlocking {
            service.findById(id).toUi()
        }
        val className = clazz.simpleName.lowercase()
        println(className)
        model.addAttribute(className, obj)

        return "$className/$className"
    }

    @DeleteMapping("/delete")
    fun delete(@RequestParam(name = "id", required = false) id: String?, model: Model): String {
        runBlocking {
            if (id == null)
                service.deleteAll()
            else
                service.deleteById(id)
        }

        return "redirect:/${clazz.simpleName.lowercase()}"
    }

    @PutMapping("/save")
    fun insert(@RequestBody ui: U): String {
        runBlocking { service.insert(ui.toEntity()) }

        return "redirect:/${clazz.simpleName.lowercase()}"
    }

    @PatchMapping("/save")
    fun update(@RequestBody ui: U): String {
        runBlocking { service.update(ui.toEntity()) }

        return "redirect:/${clazz.simpleName.lowercase()}"
    }

    protected abstract suspend fun U.toEntity(): T
}