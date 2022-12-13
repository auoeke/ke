package net.auoeke.ke.plugin

import net.auoeke.ke.Properties
import net.auoeke.ke.each
import net.auoeke.ke.localResource
import net.auoeke.ke.type
import org.gradle.api.Plugin
import org.gradle.api.Project

@Suppress("UNCHECKED_CAST", "unused")
class ExtensionsPlugin : Plugin<Project> {
    private val Project.extension get(): Extension = this.extensions.getByType(type<Extension>())

    override fun apply(project: Project) {
        project.configure()

        project.gradle.projectsEvaluated {
            project.afterEvaluation()
        }
    }

    private fun Project.configure() {
        this.extensions.create("ke", type<Extension>())
    }

    private fun Project.afterEvaluation() {
        this.add("extensions")

        modules.each {key, value ->
            val dependencies = value.split(" ").toHashSet()

            this.configurations.getByName("compileClasspath").allDependencies.all {
                if (dependencies.remove("${it.group}:${it.name}") && dependencies.isEmpty()) {
                    this@afterEvaluation.add(key)
                }
            }
        }
    }

    private fun Project.add(module: String) = this.extension.configurations.each {
        this.dependencies.add(it, arrayOf(coordinates["group"], module, coordinates["version"]).joinToString(":"))
    }

    private companion object {
        val coordinates = Properties(this.type.localResource("/gradle.properties")!!)
        val modules = Properties(this.type.localResource("/dependencies.properties")!!)
    }
}
