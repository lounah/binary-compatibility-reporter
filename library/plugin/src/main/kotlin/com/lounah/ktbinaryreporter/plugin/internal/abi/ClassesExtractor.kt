package com.lounah.ktbinaryreporter.plugin.internal.abi

import kotlinx.validation.api.ClassBinarySignature
import kotlinx.validation.api.filterOutNonPublic
import kotlinx.validation.api.loadApiFromJvmClasses
import org.gradle.api.file.FileCollection
import java.io.File
import java.util.jar.JarFile

internal interface ClassesExtractor<in S> {

    fun extract(source: S): List<ClassBinarySignature>

    class FileCollectionExtractor : ClassesExtractor<FileCollection> {

        override fun extract(source: FileCollection): List<ClassBinarySignature> {
            return source.asSequence()
                .flatMap(File::walkTopDown)
                .filter { it.isFile && it.extension == "class" }
                .map(File::inputStream)
                .loadApiFromJvmClasses()
        }
    }

    class JarFileExtractor : ClassesExtractor<JarFile> {

        override fun extract(source: JarFile): List<ClassBinarySignature> {
            return source.loadApiFromJvmClasses()
        }
    }

    class Composite(
        private val fileCollectionExtractor: ClassesExtractor<FileCollection>,
        private val jarFilesClassesExtractor: ClassesExtractor<JarFile>,
        private val ignoredPackages: Set<String>,
        private val ignoredClasses: Set<String>,
    ) : (FileCollection) -> List<ClassBinarySignature> {

        override fun invoke(source: FileCollection): List<ClassBinarySignature> {
            return runCatching {
                jarFilesClassesExtractor.extract(JarFile(source.singleFile))
            }.getOrElse {
                fileCollectionExtractor.extract(source)
            }.filterOutNonPublic(ignoredPackages, ignoredClasses)
        }
    }
}