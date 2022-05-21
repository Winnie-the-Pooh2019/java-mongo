package com.example.javamongo.config.input

import kotlinx.cli.ArgParser
import kotlinx.cli.ArgType

data class Input(val login: String = "no") {
    companion object {
        fun parse(args: Array<String>): Input {
            val parser = ArgParser("simple application")

            val login: String? by parser.option(
                ArgType.String,
                shortName = "migrate",
                description = "What kind of migration u prefer (no, semi, flush)"
            )

            return if (args.isEmpty()) Input() else Input(login!!)
        }
    }
}
