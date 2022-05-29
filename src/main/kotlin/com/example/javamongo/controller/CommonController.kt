package com.example.javamongo.controller

import com.example.javamongo.controller.dto.UiDto
import com.example.javamongo.data.entity.Entity
import com.example.javamongo.services.interfaces.MongoService
import kotlinx.coroutines.runBlocking
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestParam
import javax.validation.Valid

abstract class CommonController<T : Entity, U: UiDto>(protected val service: MongoService<T>, private val clazz: Class<T>) {
    @GetMapping
    fun getAll(model: Model): String {
        val objects = runBlocking {
            service.findAll().map { it.toUi() }
        }
        val className = clazz.simpleName.lowercase()
        model.addAttribute("${className}s", objects)

        return "$className/${className}s"
    }

    @GetMapping("/byId")
    open fun getSingle(@RequestParam(name = "id", required = true) id: String, model: Model): String {
        val obj = runBlocking {
            service.findById(id).toUi()
        }
        val className = clazz.simpleName.lowercase()
        model.addAttribute(className, obj)

        return "$className/$className"
    }

    @GetMapping("/create")
    open fun create(model: Model): String {
        return "${clazz.simpleName.lowercase()}/${clazz.simpleName.lowercase()}"
    }

    @DeleteMapping("/delete")
    open fun delete(@RequestParam(name = "id", required = false) id: String?, model: Model): String {
        runBlocking {
            if (id == null)
                service.deleteAll()
            else
                service.deleteById(id)
        }

        return "redirect:/${clazz.simpleName.lowercase()}"
    }

    @PutMapping("/save")
    open fun insert(@Valid ui: U): String {
        runBlocking { service.insert(ui.toEntity()) }

        return "redirect:/${clazz.simpleName.lowercase()}"
    }

    protected abstract suspend fun U.toEntity(): T
}